
/**
 * Date: 24 Nov, 2019
 * Link: https://codeforces.com/contest/1262/problem/D2
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
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

public class Optimal_Subsequences_Hard_Version_1262_D {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int a[] = in.gia(n);
        int m = in.ni();
        Data d[] = new Data[m];
        Data d2[] = new Data[n];
        for (int i = 0; i < m; i++) {
            d[i] = new Data(in.ni(), in.ni(), i);
        }
        for (int i = 0; i < n; i++) {
            d2[i] = new Data(a[i], -i, 0);
        }
        Arrays.sort(d);
        Arrays.sort(d2);
        int l = 0;
        int max = 0;
        Treap<Integer> treap = new Treap<>();
        int ans[] = new int[m];
        for (int i = 0; i < m; i++) {
            max = Math.max(d[i].a, max);
            while (l < max) {
                treap.add(-d2[n - l - 1].b);
                l++;
            }
            ans[d[i].c] = a[treap.index(d[i].b)];
        }
        for (int i = 0; i < m; i++) {
            in.println(ans[i]);
        }
        in.bw.flush();
    }

    static class Treap<T extends Comparable<T>> {
        private class Node {
            Node left;
            Node right;
            int priority;
            T value;
            int size;

            public Node() {
                left = null;
                right = null;
            }
        }

        private Node root;
        private Random r;

        public Treap() {
            root = null;
            r = new Random();
        }

        public void add(T d) {
            root = traverse(root, d);
        }

        // one based indexing
        public T index(int index) {
            Node curr = root;
            T ans = null;
            while (curr != null) {
                ans = curr.value;
                if (getSize(curr.left) + 1 == index) {
                    ans = curr.value;
                    break;
                } else if (getSize(curr.left) >= index) {
                    curr = curr.left;
                } else {
                    index -= getSize(curr.left) + 1;
                    curr = curr.right;
                }
            }
            return ans;
        }

        public int getLessThan(T d) {
            Node curr = root;
            int less_than_number = 0;
            while (curr != null) {
                if (d.compareTo(curr.value) == 0) {
                    if (curr.left != null) {
                        less_than_number += curr.left.size;
                    }
                    return less_than_number;
                } else if (d.compareTo(curr.value) > 0) {
                    if (curr.left != null) {
                        less_than_number += curr.left.size + 1;
                    } else {
                        less_than_number++;
                    }
                    curr = curr.right;
                } else {
                    curr = curr.left;
                }
            }
            return less_than_number;
        }

        public void inorder() {
            inorder_T(root);
            System.out.println("");
        }

        private Node traverse(Node n, T d) {
            if (n == null) {
                n = new Node();
                n.value = d;
                n.priority = r.nextInt(1000000000);
                n.size = 1;
                return n;
            }
            n.size = getSize(n.left) + getSize(n.right) + 1;
            if (d.compareTo(n.value) <= 0) {
                n.left = traverse(n.left, d);
                if (n.left.priority > n.priority) {
                    n = rightRotate(n);
                }
            } else {
                n.right = traverse(n.right, d);
                if (n.right.priority > n.priority) {
                    n = leftRotate(n);
                }
            }
            n.size = getSize(n.left) + getSize(n.right) + 1;
            return n;
        }

        private Node rightRotate(Node y) {
            Node x = y.left;
            Node t2 = x.right;
            x.right = y;
            y.left = t2;
            y.size = getSize(y.left) + getSize(y.right) + 1;
            x.size = getSize(x.left) + getSize(x.right) + 1;
            return x;
        }

        private Node leftRotate(Node x) {
            Node y = x.right;
            Node t2 = y.left;
            y.left = x;
            x.right = t2;
            y.size = getSize(y.left) + getSize(y.right) + 1;
            x.size = getSize(x.left) + getSize(x.right) + 1;
            return y;
        }

        private int getSize(Node n) {
            return n == null ? 0 : n.size;
        }

        private void inorder_T(Node root) {
            if (root != null) {
                inorder_T(root.left);
                System.out.print(root.value + " ");
                inorder_T(root.right);
            }
        }
    }

    static class Data implements Comparable<Data> {

        int a, b, c;

        public Data(int a, int b, int c) {
            this.a = a;
            this.c = c;
            this.b = b;
        }

        @Override
        public int compareTo(Data o) {
            if (a == o.a) {
                return Integer.compare(b, o.b);
            }
            return Integer.compare(a, o.a);
        }

        // public static void sort(int a[]) {
        // Data d[] = new Data[a.length];
        // for (int i = 0; i < a.length; i++) {
        // d[i] = new Data(a[i], 0);
        // }
        // Arrays.sort(d);
        // for (int i = 0; i < a.length; i++) {
        // a[i] = d[i].a;
        // }
        // }
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
