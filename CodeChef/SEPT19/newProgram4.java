package Coding_contest;

/**
 * Date: 12 Sep, 2019
 * Link: 
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

class newProgram4 {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int s = in.ni();
        int k = in.ni();
        int c[] = new int[n];
        int v[] = new int[n];
        long mod = 998244353;
        for (int i = 0; i < n; i++) {
            c[i] = in.ni();
            v[i] = in.ni();
        }
        if (n <= 20) {
            long ans = 0;
            int p = (int) (Math.pow(2, n));
            for (int i = 0; i < p; i++) {
                int temp = i;
                long sum = 0;
                int cs = 0;
                for (int j = 0; j < n; j++) {
                    if (temp % 2 == 1) {
                        sum += v[j];
                        cs += c[j];
                    }
                    temp /= 2;
                }
                if (cs <= s) {
                    sum %= mod;
                    ans += Expo.a_pow_n_mod_m(sum, k, mod);
                    ans %= mod;
                }
            }
            System.out.println(ans);
            return;
        }
        if (k <= 100) {
            Factorial f = new Factorial(k, mod);
            long s_k[][] = new long[s + 1][k + 1];
            boolean[] b = new boolean[s + 1];
            for (int i = 0; i < n; i++) {
                for (int j = s - c[i]; j >= 0; j--) {
                    if (b[j]) {
                        long arr[] = combine(s_k[j], v[i], mod, f);
                        for (int l = 0; l <= k; l++) {
                            s_k[j + c[i]][l] += arr[l];
                            s_k[j + c[i]][l] %= mod;
                        }
                        b[j + c[i]] = true;
                    }
                }
                if (c[i] <= s) {
                    long p = 1;
                    for (int j = 0; j <= k; j++) {
                        s_k[c[i]][j] += p;
                        s_k[c[i]][j] %= mod;
                        p *= v[i];
                        p %= mod;
                    }
                    b[c[i]] = true;
                }
            }
            long ans = 0;
            for (int i = 1; i <= s; i++) {
                ans += s_k[i][k];
                ans %= mod;
            }
            System.out.println(ans);
            return;
        }
        System.out.println(0);
        in.bw.flush();
    }

    private static long[] combine(long sum_v[], long v_j, long mod, Factorial f) {
        int k = sum_v.length - 1;
        long ans[] = new long[k + 1];
        long pows[] = new long[k + 1];
        pows[0] = 1;
        for (int i = 1; i <= k; i++) {
            pows[i] = pows[i - 1] * v_j;
            pows[i] %= mod;
        }

        for (int i = 0; i <= k; i++) {
            for (int j = 0; j <= i; j++) {
                long p = sum_v[j];
                p *= pows[i - j];
                p %= mod;
                p *= f.fac[i];
                p %= mod;
                p *= f.inv_fac[j];
                p %= mod;
                p *= f.inv_fac[i - j];
                p %= mod;
                ans[i] += p;
                ans[i] %= mod;
            }
        }
        return ans;
    }

    static class Factorial {

        long fac[], inv_fac[];

        public Factorial(int n, long m) {
            fac = new long[n + 1];
            inv_fac = new long[n + 1];
            fac[0] = 1;
            inv_fac[0] = 1;
            for (int i = 1; i <= n; i++) {
                fac[i] = (fac[i - 1] * i) % m;
                inv_fac[i] = pow_mod(fac[i], m - 2, m);
            }
        }

        long pow_mod(long a, long n, long m) {
            if (n == 0) {
                return 1;
            }
            if (n == 1) {
                return a;
            }
            long p = pow_mod(a, n / 2, m);
            if (n % 2 == 0) {
                return (p * p) % m;
            } else {
                return ((p * p) % m * a) % m;
            }
        }
    }

    static class Expo {

        static long a_pow_n_mod_m(long a, long n, long m) {
            if (a == 0) {
                return 0;
            }
            if (n == 0) {
                return 1;
            }
            if (n == 1) {
                return a % m;
            }
            long p = a_pow_n_mod_m(a, n / 2, m);
            if (n % 2 == 0) {
                return (p * p) % m;
            } else {
                return ((p * p) % m * a) % m;
            }
        }

        static long a_pow_n(long a, long n) {
            if (n == 0) {
                return 1;
            }
            if (n == 1) {
                return a;
            }
            long p = a_pow_n(a, n / 2);
            if (n % 2 == 0) {
                return (p * p);
            } else {
                return ((p * p) * a);
            }
        }
    }

    static class FastIO {

        private final BufferedReader br;
        private final BufferedWriter bw;
        private String s[];
        private int index;
        private final StringBuilder sb;

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
