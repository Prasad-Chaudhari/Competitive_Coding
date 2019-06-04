
/**
 * Date: 28 Dec, 2018
 * Link: https://codeforces.com/problemset/problem/383/C
 *
 * @author Prasad-Chaudhari
 * @linkedIn: https://www.linkedin.com/in/prasad-chaudhari-841655a6/
 * @git: https://github.com/Prasad-Chaudhari
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Propagating_tree_383_C {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int m = in.ni();
        int a[] = in.gia(n);
        int adja[][] = in.gg(n, n - 1);
        int eulertour[] = new int[2 * n - 1];
        int level[] = new int[n + 1];
        boolean b[] = new boolean[n + 1];
        dfs(b, adja, 1, eulertour, level);
        int first[] = new int[n + 1];
        int last[] = new int[n + 1];
        Arrays.fill(first, -1);
        for (int i = 0; i < 2 * n - 1; i++) {
            if (first[eulertour[i]] == -1) {
                first[eulertour[i]] = i;
            }
            last[eulertour[i]] = i;
        }
        SegmentTree pos = new SegmentTree();
        SegmentTree neg = new SegmentTree();
        for (int i = 0; i < 2 * n - 1; i++) {
            pos.update(0, 2 * n - 2, i, i, a[eulertour[i] - 1]);
            neg.update(0, 2 * n - 2, i, i, a[eulertour[i] - 1]);
        }
        while (m-- > 0) {
            if (in.ni() == 1) {
                int x = in.ni();
                int val = in.ni();
                if (level[x] == 0) {
                    pos.update(0, 2 * n - 2, first[x], last[x], val);
                    neg.update(0, 2 * n - 2, first[x], last[x], -val);
                } else {
                    pos.update(0, 2 * n - 2, first[x], last[x], -val);
                    neg.update(0, 2 * n - 2, first[x], last[x], val);
                }
            } else {
                int x = in.ni();
                if (level[x] == 0) {
                    System.out.println(pos.query(0, 2 * n - 2, first[x], first[x]));
                } else {
                    System.out.println(neg.query(0, 2 * n - 2, first[x], first[x]));
                }
            }
        }
    }

    static class SegmentTree {

        static class Node {

            int lazy;
            int value;
            Node leftNode, rightNode;

            public Node() {
                lazy = 0;
                value = 0;
                leftNode = null;
                rightNode = null;
            }

        }
        Node root;
        int DEFAULT = 0;

        public SegmentTree() {
            root = new Node();
        }

        public int query(int start, int end, int l, int r) {
            return query(start, end, l, r, root);
        }

        private int query(int start, int end, int l, int r, Node root) {
            if (toBeUpdated(root)) {
                updateCurr(root, root.lazy, start, end);
                if (start != end) {
                    updateLeft(root, 0);
                    updateRight(root, 0);
                }
                root.lazy = 0;
            }
            if (end < l || r < start) {
                return DEFAULT;
            } else if (l <= start && end <= r) {
                return root.value;
            } else {
                int mid = (start + end) / 2;
                int left = query(start, mid, l, r, root.leftNode);
                int right = query(mid + 1, end, l, r, root.rightNode);
                return keyFunction(left, right);
            }
        }

        public void update(int start, int end, int l, int r, int value) {
            update(start, end, l, r, value, root);
        }

        private void update(int start, int end, int l, int r, int value, Node root) {
//            System.out.println(start + " " + end + " " + l + " " + r);
            if (toBeUpdated(root)) {
                updateCurr(root, root.lazy, start, end);
                if (start != end) {
                    updateLeft(root, 0);
                    updateRight(root, 0);
                }
                root.lazy = 0;
            }
            if (end < l || r < start) {

            } else if (l <= start && end <= r) {
                updateCurr(root, value, start, end);
                if (start != end) {
                    updateLeft(root, value);
                    updateRight(root, value);
                }
            } else {
                int mid = (start + end) / 2;
                if (root.leftNode == null) {
                    root.leftNode = new Node();
                }
                if (root.rightNode == null) {
                    root.rightNode = new Node();
                }
                update(start, mid, l, r, value, root.leftNode);
                update(mid + 1, end, l, r, value, root.rightNode);
                root.value = keyFunction(getValue(root.leftNode), getValue(root.rightNode));
            }
        }

        private void updateCurr(Node root, int value, int start, int end) {
            root.value += value * (end - start + 1);
        }

        private int getValue(Node root) {
            if (root == null) {
                return DEFAULT;
            } else {
                return root.value;
            }
        }

        private int keyFunction(int a, int b) {
            return a + b;
        }

        private boolean toBeUpdated(Node root) {
            return root.lazy != 0;
        }

        private void updateLeft(Node root, int value) {
            if (root.leftNode == null) {
                root.leftNode = new Node();
            }
            root.leftNode.lazy += root.lazy + value;
        }

        private void updateRight(Node root, int value) {
            if (root.rightNode == null) {
                root.rightNode = new Node();
            }
            root.rightNode.lazy += root.lazy + value;
        }

        public void inOrder(int start, int end, Node root) {
            if (start == end) {
                System.out.println(start + " " + end + " " + root.value + " " + root.lazy);
                return;
            }
            int mid = (start + end) / 2;
            inOrder(start, mid, root.leftNode);
            System.out.println(start + " " + end + " " + root.value + " " + root.lazy);
            inOrder(mid + 1, end, root.rightNode);
        }
    }

    static public class SegmentTree_LazyPropagation {

        int n;
        static int DEFAULT = 0;
        int tree[];
        int[] lazy_value;

        public SegmentTree_LazyPropagation(int si) {
            double x = Math.log(si) / Math.log(2);
            n = (int) (Math.pow(2, Math.ceil(x) + 1)) + 1;
            tree = new int[n];
            lazy_value = new int[n];
        }

        public int query(int start, int end, int pos, int l, int r) {
            if (toBeUpdated(pos)) {
                updateFunction(pos, lazy_value[pos]);
                if (start != end) {
                    lazyUpdate(2 * pos, lazy_value[pos]);
                    lazyUpdate(2 * pos + 1, lazy_value[pos]);
                }
                lazy_value[pos] = 0;
            }
            if (end < l || r < start) {
                return DEFAULT;
            } else if (l <= start && end <= r) {
                return tree[pos];
            } else {
                int mid = (start + end) / 2;
                int a = query(start, mid, 2 * pos, l, r);
                int b = query(mid + 1, end, 2 * pos + 1, l, r);
                return keyFunction(a, b);
            }
        }

        public void update(int start, int end, int pos, int l, int r, int d) {
            if (start > r || end < l) {
                return;
            }
            if (toBeUpdated(pos)) {
                updateFunction(pos, lazy_value[pos]);
                if (start != end) {
                    lazyUpdate(2 * pos, lazy_value[pos]);
                    lazyUpdate(2 * pos + 1, lazy_value[pos]);
                }
                lazy_value[pos] = 0;
            }
            if (l <= start && end <= r) {
                updateFunction(pos, d);
                if (start != end) {
                    lazyUpdate(2 * pos, d);
                    lazyUpdate(2 * pos + 1, d);
                }
            } else {
                int mid = (start + end) / 2;
                update(start, mid, 2 * pos, l, r, d);
                update(mid + 1, end, 2 * pos + 1, l, r, d);
                tree[pos] = keyFunction(tree[2 * pos], tree[2 * pos + 1]);
            }
        }

        private int keyFunction(int a, int b) {
            return a + b;
        }

        private void lazyUpdate(int pos, int d) {
            lazy_value[pos] += d;
        }

        private void updateFunction(int pos, int d) {
            tree[pos] += d;
        }

        private boolean toBeUpdated(int pos) {
            return lazy_value[pos] != 0;
        }
    }

    static int t = 0;

    private static void dfs(boolean[] b, int[][] adja, int i, int[] eulertour, int[] level) {
        eulertour[t++] = i;
        b[i] = true;
        for (int j : adja[i]) {
            if (!b[j]) {
                level[j] = level[i] ^ 1;
                dfs(b, adja, j, eulertour, level);
                eulertour[t++] = i;
            }
        }
    }

    static class FastIO {

        private final BufferedReader br;
        private final BufferedWriter bw;
        private String s[];
        private int index;
        private StringBuilder sb;

        public FastIO() throws IOException {
            br = new BufferedReader(new InputStreamReader(System.in));
            bw = new BufferedWriter(new OutputStreamWriter(System.out, "UTF-8"));
            s = br.readLine().split(" ");
            sb = new StringBuilder();
            index = 0;
        }

        public int ni() throws IOException {
            return Integer.parseInt(nextToken());
        }

        public double nd() throws IOException {
            return Double.parseDouble(nextToken());
        }

        public long nl() throws IOException {
            return Long.parseLong(nextToken());
        }

        public String next() throws IOException {
            return nextToken();
        }

        public String nli() throws IOException {
            try {
                return br.readLine();
            } catch (IOException ex) {

            }
            return null;
        }

        public int[] gia(int n) throws IOException {
            int a[] = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = ni();
            }
            return a;
        }

        public int[] gia(int n, int start, int end) throws IOException {
            validate(n, start, end);
            int a[] = new int[n];
            for (int i = start; i < end; i++) {
                a[i] = ni();
            }
            return a;
        }

        public double[] gda(int n) throws IOException {
            double a[] = new double[n];
            for (int i = 0; i < n; i++) {
                a[i] = nd();
            }
            return a;
        }

        public double[] gda(int n, int start, int end) throws IOException {
            validate(n, start, end);
            double a[] = new double[n];
            for (int i = start; i < end; i++) {
                a[i] = nd();
            }
            return a;
        }

        public long[] gla(int n) throws IOException {
            long a[] = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = nl();
            }
            return a;
        }

        public long[] gla(int n, int start, int end) throws IOException {
            validate(n, start, end);
            long a[] = new long[n];
            for (int i = start; i < end; i++) {
                a[i] = nl();
            }
            return a;
        }

        public int[][] gg(int n, int m) throws IOException {
            int adja[][] = new int[n + 1][];
            int from[] = new int[m];
            int to[] = new int[m];
            int count[] = new int[n + 1];
            for (int i = 0; i < m; i++) {
                from[i] = ni();
                to[i] = ni();
                count[from[i]]++;
                count[to[i]]++;
            }
            for (int i = 0; i <= n; i++) {
                adja[i] = new int[count[i]];
            }
            for (int i = 0; i < m; i++) {
                adja[from[i]][--count[from[i]]] = to[i];
                adja[to[i]][--count[to[i]]] = from[i];
            }
            return adja;
        }

        public void print(String s) throws IOException {
            bw.write(s);
        }

        public void println(String s) throws IOException {
            bw.write(s);
            bw.newLine();
        }

        private String nextToken() throws IndexOutOfBoundsException, IOException {
            if (index == s.length) {
                s = br.readLine().split(" ");
                index = 0;
            }
            return s[index++];
        }

        private void validate(int n, int start, int end) {
            if (start < 0 || end >= n) {
                throw new IllegalArgumentException();
            }
        }
    }

    static class Data implements Comparable<Data> {

        int a, b;

        public Data(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Data o) {
            if (a == o.a) {
                return Integer.compare(b, o.b);
            }
            return Integer.compare(a, o.a);
        }

        public static void sort(int a[]) {
            Data d[] = new Data[a.length];
            for (int i = 0; i < a.length; i++) {
                d[i] = new Data(a[i], 0);
            }
            Arrays.sort(d);
            for (int i = 0; i < a.length; i++) {
                a[i] = d[i].a;
            }
        }
    }
}
