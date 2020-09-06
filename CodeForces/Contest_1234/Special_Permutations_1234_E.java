
/**
 * Date: 19 Nov, 2019
 * Link: https://codeforces.com/contest/1234/problem/E
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
import java.util.PriorityQueue;

public class Special_Permutations_1234_E {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int m = in.ni();
        int x[] = in.gia(m);
        SegmentTree_LazyPropagation seg = new SegmentTree_LazyPropagation(n + 1);
        for (int i = 0; i < m - 1; i++) {
            int u = x[i];
            int v = x[i + 1];
            if (u != v) {
                if (u > v) {
                    u ^= v;
                    v ^= u;
                    u ^= v;
                }
                if (u > 1) {
                    seg.update(0, n, 1, 1, u - 1, v - u);
                }
                seg.update(0, n, 1, u, u, v - 1);
                if (v - u > 1) {
                    seg.update(0, n, 1, u + 1, v - 1, v - u - 1);
                }
                seg.update(0, n, 1, v, v, u);
                if (v < n) {
                    seg.update(0, n, 1, v + 1, n, v - u);
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            in.println(seg.query(0, n, 1, i, i));
        }
        in.bw.flush();
    }

    static public class SegmentTree_LazyPropagation {

        int n;
        static int DEFAULT = 0;
        long tree[];
        long[] lazy_value;

        public SegmentTree_LazyPropagation(int si) {
            double x = Math.log(si) / Math.log(2);
            n = (int) (Math.pow(2, Math.ceil(x) + 1)) + 1;
            tree = new long[n];
            lazy_value = new long[n];
        }

        public long query(int start, int end, int pos, int l, int r) {
            if (toBeUpdated(pos)) {
                updateFunction(pos, lazy_value[pos], start, end);
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
                long a = query(start, mid, 2 * pos, l, r);
                long b = query(mid + 1, end, 2 * pos + 1, l, r);
                return keyFunction(a, b);
            }
        }

        public void update(int start, int end, int pos, int l, int r, int d) {
            if (start > r || end < l) {
                return;
            }
            if (toBeUpdated(pos)) {
                updateFunction(pos, lazy_value[pos], start, end);
                if (start != end) {
                    lazyUpdate(2 * pos, lazy_value[pos]);
                    lazyUpdate(2 * pos + 1, lazy_value[pos]);
                }
                lazy_value[pos] = 0;
            }
            if (l <= start && end <= r) {
                updateFunction(pos, d, start, end);
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

        private long keyFunction(long a, long b) {
            return a + b;
        }

        private void lazyUpdate(int pos, long d) {
            lazy_value[pos] += d;
        }

        private void updateFunction(int pos, long d, int start, int end) {
            tree[pos] += (end - start + 1) * d;
        }

        private boolean toBeUpdated(int pos) {
            return lazy_value[pos] != 0;
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
