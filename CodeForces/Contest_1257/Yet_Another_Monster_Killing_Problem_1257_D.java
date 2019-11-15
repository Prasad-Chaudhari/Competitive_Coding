
/**
 * Date: 13 Nov, 2019
 * Link: https://codeforces.com/contest/1257/problem/D
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

public class Yet_Another_Monster_Killing_Problem_1257_D {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int t = in.ni();
        outer: while (t-- > 0) {
            int n = in.ni();
            int a[] = in.gia(n);
            int m = in.ni();
            int max[] = new int[n + 1];
            for (int i = 0; i < m; i++) {
                int p = in.ni();
                int s = in.ni();
                max[s] = Math.max(max[s], p);
            }
            for (int i = n - 1; i >= 1; i--) {
                max[i] = Math.max(max[i], max[i + 1]);
            }
            int i = 0;
            int ans = 0;
            while (i < n) {
                int maxx = 0;
                int l = 1;
                int j = i;
                while (j < n) {
                    maxx = Math.max(maxx, a[j]);
                    if (maxx > max[l]) {
                        break;
                    }
                    j++;
                    l++;
                }
                if (j == i) {
                    System.out.println(-1);
                    continue outer;
                }
                i = j;
                ans++;
            }
            System.out.println(ans);
        }
        in.bw.flush();
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

    static class MaxSegmentTree extends SegmentTree {

        @Override
        public int keyFunc(int a, int b) {
            return Math.max(a, b);
        }
    }

    static class SegmentTree {

        private int[] tree, array, index;
        int DEFAULT = 0;

        public void getArray(int[] a) {
            array = a;
            int si = a.length;
            double x = Math.log(si) / Math.log(2);
            int n = (int) (Math.pow(2, Math.ceil(x) + 1)) + 1;
            tree = new int[n];
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

        public void update(int start, int end, int pos, int idx, int x) {
            if (start == end) {
                array[start] = x;
                tree[pos] = x;
            } else {
                int mid = (start + end) / 2;
                if (start <= idx && idx <= mid) {
                    update(start, mid, 2 * pos, idx, x);
                } else {
                    update(mid + 1, end, 2 * pos + 1, idx, x);
                }
                tree[pos] = keyFunc(tree[2 * pos], tree[2 * pos + 1]);
            }
        }

        public int query(int start, int end, int pos, int l, int r) {
            if (start > r || end < l) {
                return DEFAULT;
            }
            if (l <= start && end <= r) {
                return tree[pos];
            } else {
                int mid = (start + end) / 2;
                int a = query(start, mid, 2 * pos, l, r);
                int b = query(mid + 1, end, 2 * pos + 1, l, r);
                return keyFunc(a, b);
            }
        }

        public void printTree() {
            for (int i = 0; i <= 2 * array.length; i++) {
                System.out.println(i + " " + tree[i]);
            }
            System.out.println();
        }

        public int keyFunc(int a, int b) {
            return a + b;
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
