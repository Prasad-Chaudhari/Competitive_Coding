
/**
 * Date: 15 Nov, 2019
 * Link: https://codeforces.com/contest/1257/problem/G
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
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;
import java.util.PriorityQueue;

public class Divisor_Set_1257_G {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int p[] = in.gia(n);
        long mod = 998244353;
        Number_Theoretic_Transform ntt = new Number_Theoretic_Transform(mod, 3);
        Map<Integer, Integer> map = new Hashtable<>();
        for (int i = 0; i < n; i++) {
            map.put(p[i], map.getOrDefault(p[i], 0) + 1);
        }
        PriorityQueue<long[]> pq = new PriorityQueue<>(new Comparator<long[]>() {
            @Override
            public int compare(long[] a, long b[]) {
                return Integer.compare(a.length, b.length);
            }
        });
        for (int i : map.keySet()) {
            long f[] = new long[map.get(i) + 1];
            Arrays.fill(f, 1);
            pq.add(f);
        }
        while (pq.size() > 1) {
            long l1[] = pq.remove();
            long l2[] = pq.remove();
            pq.add(ntt.multiply(l1, l2));
        }
        long ans[] = pq.remove();
        System.out.println(ans[n / 2]);
        in.bw.flush();
    }

    static class Number_Theoretic_Transform {
        /**
         * mod => numeric tranformation modulo this value, r => Primitive root of mod
         */
        private long mod;
        long r;
        private long[] a, b, c;
        private long[] w_n, w_n_inv;
        private int max_depth;

        public Number_Theoretic_Transform(long mod, long r) {
            this.mod = mod;
            this.r = r;
            long k = mod - 1;
            max_depth = 19;
            while (max_depth-- > 0) {
                k >>= 1;
            }
            max_depth = 19;
            a = new long[1 << max_depth];
            b = new long[1 << max_depth];
            c = new long[1 << max_depth];
            w_n = new long[1 << max_depth];
            w_n_inv = new long[1 << max_depth];
            long w = a_pow_n_mod_m(r, k, mod);
            long w_inv = a_pow_n_mod_m(w, mod - 2, mod);
            long pro = 1;
            long pro_inv = 1;
            int i = 0;
            while (i < w_n.length) {
                w_n[i] = pro;
                w_n_inv[i] = pro_inv;
                pro *= w;
                pro %= mod;
                pro_inv *= w_inv;
                pro_inv %= mod;
                ++i;
            }
        }

        public long[] multiply(long[] _a, long[] _b) {
            int l = 1;
            int count = 0;
            int i = 0;
            while (l < _a.length + _b.length) {
                count++;
                l <<= 1;
            }
            i = 0;
            while (i < _a.length) {
                a[i] = (_a[++i - 1] + mod) % mod;
            }
            while (i < l) {
                a[++i - 1] = 0;
            }
            i = 0;
            while (i < _b.length) {
                b[i] = (_b[++i - 1] + mod) % mod;
            }
            while (i < l) {
                b[++i - 1] = 0;
            }
            FFT(a, count, w_n);
            FFT(b, count, w_n);
            i = 0;
            while (i < l) {
                c[i] = (a[i] * b[i]) % mod;
                ++i;
            }
            FFT(c, count, w_n_inv);
            int c_l = l;
            while (c[--c_l] == 0) {
            }
            c_l++;
            long l_inv = a_pow_n_mod_m(l, mod - 2, mod);
            long ans[] = new long[c_l];
            i = 0;
            while (i < c_l) {
                ans[i] = (c[++i - 1] * l_inv) % mod;
            }
            return ans;
        }

        private void FFT(long[] a, int depth, long[] w_n) {
            // int temp_depth = depth - 1;
            // int l = 1 << depth;
            // int s_l = 1 << depth;
            // int ref = 0;
            // while (temp_depth-- > 0) {
            // ref = 0;
            // while (ref < l) {
            // odd_even_shuffle(a, ref, s_l);
            // ref += s_l;
            // }
            // s_l >>= 1;
            // }
            int l = 1 << depth;
            int s_l = 2;
            int ref = 0;
            int i = 1, j = 0, b;
            while (i < l) {
                b = l;
                do {
                    j ^= b >>= 1;
                } while ((j & b) == 0);
                if (i < j) {
                    long temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
                ++i;
            }
            int curr_depth = 1;
            long p1, p2;
            s_l = 2;
            ref = 0;
            while (curr_depth <= depth) {
                ref = 0;
                while (ref < l) {
                    i = 0;
                    while (i < (s_l >> 1)) {
                        p1 = (a[ref + i] + (w_n[i << (max_depth - curr_depth)] * a[ref + i + (s_l >> 1)])) % mod;
                        p2 = (a[ref + i] - (w_n[i << (max_depth - curr_depth)] * a[ref + i + (s_l >> 1)])) % mod;
                        a[ref + i] = p1;
                        a[ref + i + (s_l >> 1)] = (p2 + mod) % mod;
                        ++i;
                    }
                    ref += s_l;
                }
                curr_depth++;
                s_l <<= 1;
            }
        }

        private long a_pow_n_mod_m(long a, long n, long m) {
            if (n == 0) {
                return 1;
            }
            long p = a_pow_n_mod_m(a, n / 2, m);
            if (n % 2 == 0) {
                return (p * p) % m;
            } else {
                return ((p * p) % m * a) % m;
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
