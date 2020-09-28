
/**
 * Date: 07 Oct, 2019
 * Link: 
 * 
 * @author Prasad Chaudhari
 * @linkedIn: https://www.linkedin.com/in/prasad-chaudhari-841655a6/
 * @git: https://github.com/Prasad-Chaudhari
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
import java.util.TreeSet;

class newProgram3 {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int q = in.ni();
        int adja[][] = in.gg(n, n - 1);
        long a[] = new long[n + 1];
        for (int i = 0; i < n; i++) {
            a[i + 1] = in.ni();
        }

        TreeSet<Integer> ts = new TreeSet<>();

        Stack<Integer> st = new Stack<>();
        int level[] = new int[n + 1];
        boolean b[] = new boolean[n + 1];
        st.push(1);
        while (!st.isEmpty()) {
            int root = st.pop();
            b[root] = true;
            ts.add(0 - level[root]);
            for (int i : adja[root]) {
                if (!b[i]) {
                    st.push(i);
                    level[i] = level[root] + 1;
                }
            }
        }
        boolean isLeaf[] = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            isLeaf[i] = adja[i].length == 1;
        }

        int freq[] = new int[n + 1];
        LinkedList<Data> l = new LinkedList<>();
        for (int i = 0; i < q; i++) {
            char c = in.next().charAt(0);
            int v = in.ni();
            freq[v]++;
            if (c == '+') {
                int k = in.ni();
                l.add(new Data(c, v, k, i));
            } else {
                l.add(new Data(c, v, 0, i));
            }
            ts.add(i + 1 - level[v]);
        }
        Data queries[][] = new Data[n + 1][];
        for (int i = 0; i <= n; i++) {
            queries[i] = new Data[freq[i]];
        }
        while (!l.isEmpty()) {
            Data d = l.remove();
            queries[d.b][--freq[d.b]] = d;
        }
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < queries[i].length / 2; j++) {
                Data temp = queries[i][j];
                queries[i][j] = queries[i][queries[i].length - 1 - j];
                queries[i][queries[i].length - 1 - j] = temp;
            }
        }

        Map<Integer, Integer> diff_map = new Hashtable<>();
        int count = 1;
        while (!ts.isEmpty()) {
            diff_map.put(ts.pollFirst(), count++);
        }
        SegmentTree segmentTree = new SegmentTree();
        segmentTree.getArray(new long[count]);

        long ans[] = new long[q];
        Arrays.fill(ans, -1);

        Arrays.fill(b, false);
        st.push(1);
        while (!st.isEmpty()) {
            int root = st.peek();
            if (b[root]) {
                segmentTree.update(0, count - 1, 1, diff_map.get(0 - level[root]), -a[root]);
                for (Data d : queries[root]) {
                    if (d.a == '+') {
                        segmentTree.update(0, count - 1, 1, diff_map.get(d.d + 1 - level[root]), -d.c);
                    }
                }
                st.pop();
            } else {
                b[root] = true;
                segmentTree.update(0, count - 1, 1, diff_map.get(0 - level[root]), a[root]);
                for (Data d : queries[root]) {
                    if (d.a == '+') {
                        segmentTree.update(0, count - 1, 1, diff_map.get(d.d + 1 - level[root]), d.c);
                    } else {
                        if (isLeaf[root]) {
                            ans[d.d] = segmentTree.query(0, count - 1, 1, 0, diff_map.get(d.d + 1 - level[root]));
                        } else {
                            ans[d.d] = segmentTree.query(0, count - 1, 1, diff_map.get(d.d + 1 - level[root]),
                                    diff_map.get(d.d + 1 - level[root]));
                        }
                    }
                }
                for (int i : adja[root]) {
                    if (!b[i]) {
                        st.push(i);
                    }
                }
            }
        }
        for (int i = 0; i < q; i++) {
            if (ans[i] != -1) {
                in.println(ans[i]);
            }
        }
        in.bw.flush();
    }

    static class SegmentTree {

        private long[] tree, array;
        int DEFAULT = 0;

        public void getArray(long[] a) {
            array = a;
            int si = a.length;
            double x = Math.log(si) / Math.log(2);
            int n = (int) (Math.pow(2, Math.ceil(x) + 1)) + 1;
            tree = new long[n];
        }

        public void build(int start, int end, int pos) {
            if (start == end) {
                tree[pos] = array[start];
            } else {
                int mid = (start + end) / 2;
                build(start, mid, 2 * pos);
                build(mid + 1, end, 2 * pos + 1);
                tree[pos] = keyFunc(tree[2 * pos], tree[2 * pos + 1]);
            }
        }

        public void update(int start, int end, int pos, int idx, long x) {
            if (start == end) {
                array[start] += x;
                tree[pos] += x;
            } else {
                int mid = (start + end) / 2;
                if (idx <= mid) {
                    update(start, mid, 2 * pos, idx, x);
                } else {
                    update(mid + 1, end, 2 * pos + 1, idx, x);
                }
                tree[pos] = keyFunc(tree[2 * pos], tree[2 * pos + 1]);
            }
        }

        public long query(int start, int end, int pos, int l, int r) {
            if (start > r || end < l) {
                return DEFAULT;
            }
            if (l <= start && end <= r) {
                return tree[pos];
            } else {
                int mid = (start + end) / 2;
                long a = query(start, mid, 2 * pos, l, r);
                long b = query(mid + 1, end, 2 * pos + 1, l, r);
                return keyFunc(a, b);
            }
        }

        public void printTree() {
            for (int i = 0; i <= 2 * array.length; i++) {
                System.out.println(i + " " + tree[i]);
            }
            System.out.println();
        }

        public long keyFunc(long a, long b) {
            return a + b;
        }
    }

    static class Data implements Comparable<Data> {

        int a, b, c, d;

        public Data(int a, int b, int c, int d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
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
                d[i] = new Data(a[i], 0, 0, 0);
            }
            Arrays.sort(d);
            for (int i = 0; i < a.length; i++) {
                a[i] = d[i].a;
            }
        }
    }

    static class FastIO {

        private final BufferedReader br;
        private final BufferedWriter bw;
        private String s[];
        private int index;

        public FastIO() throws IOException {
            br = new BufferedReader(new InputStreamReader(System.in));
            bw = new BufferedWriter(new OutputStreamWriter(System.out, "UTF-8"));
            s = br.readLine().split(" ");
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

        public int[][][] gwtree(int n) throws IOException {
            int m = n - 1;
            int adja[][] = new int[n + 1][];
            int weight[][] = new int[n + 1][];
            int from[] = new int[m];
            int to[] = new int[m];
            int count[] = new int[n + 1];
            int cost[] = new int[n + 1];
            for (int i = 0; i < m; i++) {
                from[i] = i + 1;
                to[i] = ni();
                cost[i] = ni();
                count[from[i]]++;
                count[to[i]]++;
            }
            for (int i = 0; i <= n; i++) {
                adja[i] = new int[count[i]];
                weight[i] = new int[count[i]];
            }
            for (int i = 0; i < m; i++) {
                adja[from[i]][count[from[i]] - 1] = to[i];
                adja[to[i]][count[to[i]] - 1] = from[i];
                weight[from[i]][count[from[i]] - 1] = cost[i];
                weight[to[i]][count[to[i]] - 1] = cost[i];
                count[from[i]]--;
                count[to[i]]--;
            }
            return new int[][][] { adja, weight };
        }

        public int[][][] gwg(int n, int m) throws IOException {
            int adja[][] = new int[n + 1][];
            int weight[][] = new int[n + 1][];
            int from[] = new int[m];
            int to[] = new int[m];
            int count[] = new int[n + 1];
            int cost[] = new int[n + 1];
            for (int i = 0; i < m; i++) {
                from[i] = ni();
                to[i] = ni();
                cost[i] = ni();
                count[from[i]]++;
                count[to[i]]++;
            }
            for (int i = 0; i <= n; i++) {
                adja[i] = new int[count[i]];
                weight[i] = new int[count[i]];
            }
            for (int i = 0; i < m; i++) {
                adja[from[i]][count[from[i]] - 1] = to[i];
                adja[to[i]][count[to[i]] - 1] = from[i];
                weight[from[i]][count[from[i]] - 1] = cost[i];
                weight[to[i]][count[to[i]] - 1] = cost[i];
                count[from[i]]--;
                count[to[i]]--;
            }
            return new int[][][] { adja, weight };
        }

        public int[][] gtree(int n) throws IOException {
            int adja[][] = new int[n + 1][];
            int from[] = new int[n - 1];
            int to[] = new int[n - 1];
            int count[] = new int[n + 1];
            for (int i = 0; i < n - 1; i++) {
                from[i] = i + 1;
                to[i] = ni();
                count[from[i]]++;
                count[to[i]]++;
            }
            for (int i = 0; i <= n; i++) {
                adja[i] = new int[count[i]];
            }
            for (int i = 0; i < n - 1; i++) {
                adja[from[i]][--count[from[i]]] = to[i];
                adja[to[i]][--count[to[i]]] = from[i];
            }
            return adja;
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

        public void print(int s) throws IOException {
            bw.write(s + "");
        }

        public void println(int s) throws IOException {
            bw.write(s + "");
            bw.newLine();
        }

        public void print(long s) throws IOException {
            bw.write(s + "");
        }

        public void println(long s) throws IOException {
            bw.write(s + "");
            bw.newLine();
        }

        public void print(double s) throws IOException {
            bw.write(s + "");
        }

        public void println(double s) throws IOException {
            bw.write(s + "");
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
}
