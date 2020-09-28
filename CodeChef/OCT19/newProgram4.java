
/**
 * Date: 09 Oct, 2019
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
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

class newProgram4 {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        long mod = 998244353;
        Number_Theoretic_Transform ntt = new Number_Theoretic_Transform(mod, 3);
        long coeffs[][] = new long[2001][];
        coeffs[0] = new long[] { 1 };
        coeffs[1] = new long[] { 1, 1 };
        for (int i = 2; i <= 2000; i++) {
            int l = coeffs[i - 1].length + 1;
            coeffs[i] = new long[l];
            coeffs[i][0] = 1;
            for (int j = 1; j < l - 1; j++) {
                coeffs[i][j] = coeffs[i - 1][j] + coeffs[i - 1][j - 1];
                coeffs[i][j] %= mod;
            }
            coeffs[i][l - 1] = 1;
        }
        long coeffs_neg[][] = new long[2001][];
        for (int i = 0; i <= 2000; i++) {
            coeffs_neg[i] = Arrays.copyOf(coeffs[i], i + 1);
            for (int j = 0; j < i + 1; j++) {
                if ((j & 1) == 1) {
                    coeffs_neg[i][i - j] *= -1;
                    coeffs_neg[i][i - j] += mod;
                }
            }
        }
        // int t = in.ni();
        // while (t-- > 0) {
        // int n = in.ni();
        // int m = in.ni();
        // long q = in.nl();
        // int z = in.ni();
        for (int n = 1; n <= 16; n++) {
            for (int m = 1; m <= 16; m++) {
                if (n * m > 16) {
                    continue;
                }
                int q = 6;
                long count[] = new long[n * m + 1];
                int mat[][] = new int[n][m];
                solve(0, q, count, mat);
                long p2 = Expo.a_pow_n_mod_m(2, mod - 2, mod);
                long p2_m = Expo.a_pow_n_mod_m(p2, m, mod);
                long p2_n = Expo.a_pow_n_mod_m(p2, n, mod);

                long odd_x[] = new long[m + 1];
                for (int i = 0; i <= m; i++) {
                    long a[] = coeffs_neg[i];
                    long b[] = coeffs[m - i];
                    // long f[] = ntt.multiply(a, b);
                    long f[] = multiply(a, b, mod);
                    for (int j = 0; j < f.length; j++) {
                        int c = 2 * j - m;
                        long p = Expo.a_pow_n_mod_m(Math.abs(c), q, mod);
                        if (c < 0 && (q & 1) == 1) {
                            p *= -1;
                            p += mod;
                        }
                        odd_x[i] += (p * f[j]) % mod;
                    }
                    odd_x[i] %= mod;
                    odd_x[i] *= coeffs[m][i];
                    odd_x[i] %= mod;
                    odd_x[i] *= p2_m;
                    odd_x[i] %= mod;
                }
                long odd_y[] = new long[n + 1];
                for (int i = 0; i <= n; i++) {
                    long a[] = coeffs_neg[i];
                    long b[] = coeffs[n - i];
                    // long f[] = ntt.multiply(a, b);
                    long f[] = multiply(a, b, mod);
                    for (int j = 0; j < f.length; j++) {
                        int c = 2 * j - n;
                        long p = Expo.a_pow_n_mod_m(Math.abs(c), q, mod);
                        if (c < 0 && (q & 1) == 1) {
                            p *= -1;
                            p += mod;
                        }
                        odd_y[i] += (p * f[j]) % mod;
                    }
                    odd_y[i] %= mod;
                    odd_y[i] *= coeffs[n][i];
                    odd_y[i] %= mod;
                    odd_y[i] *= p2_n;
                    odd_y[i] %= mod;
                }
                Map<Integer, Long> map = new Hashtable<>();
                for (int i = 0; i <= n; i++) {
                    for (int j = 0; j <= m; j++) {
                        long p = odd_y[i] * odd_x[j];
                        p %= mod;
                        int odds = n * j + m * i - 2 * i * j;
                        map.put(odds, map.getOrDefault(odds, 0l) + p);
                    }
                }
                for (int i = 0; i <= m * n; i++) {
                    if (count[i] != map.getOrDefault(i, 0l)) {
                        System.err.println(n + " " + m + " " + i);
                    }
                }
                // for (int i : map.keySet()) {
                // System.out.println(i + " " + map.get(i));
                // }
                // in.bw.write(map.getOrDefault(z, 0l) + "\n");
            }
        }
        in.bw.flush();
    }

    static void solve(int index, int qs, long count[], int mat[][]) {
        if (index == qs) {
            int odds = 0;
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[0].length; j++) {
                    if (mat[i][j] % 2 == 1) {
                        odds++;
                    }
                }
            }
            count[odds]++;
        } else {
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[0].length; j++) {
                    for (int k = 0; k < mat.length; k++) {
                        mat[k][j]++;
                    }
                    for (int k = 0; k < mat[0].length; k++) {
                        mat[i][k]++;
                    }
                    solve(index + 1, qs, count, mat);
                    for (int k = 0; k < mat.length; k++) {
                        mat[k][j]--;
                    }
                    for (int k = 0; k < mat[0].length; k++) {
                        mat[i][k]--;
                    }
                }
            }
        }
    }

    static public long[] multiply(long a[], long b[], long mod) {
        long ans[] = new long[a.length + b.length - 1];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                ans[i + j] += (a[i] * b[j]) % mod;
            }
        }
        for (int i = 0; i < ans.length; i++) {
            ans[i] %= mod;
        }
        return ans;
    }

    static class Number_Theoretic_Transform {
        /**
         * mod => numeric tranformation modulo this value r => Primitive root of mod
         */

        private long mod;
        private long r;
        long[] a, b, c;
        long[][] w, w_inv;

        public Number_Theoretic_Transform(long mod, long r) {
            this.mod = mod;
            this.r = r;
        }

        public long[] multiply(long[] a, long[] b) {
            int a_l = 1;
            while (a_l < a.length) {
                a_l <<= 1;
            }
            int b_l = 1;
            while (b_l < b.length) {
                b_l <<= 1;
            }
            /*
             * This variable length is inclusive of 2*n. No need to multiple it by 2
             */
            int n = Math.max(a_l, b_l) << 1;
            a = Arrays.copyOf(a, n);
            b = Arrays.copyOf(b, n);
            long p = mod - 1;
            long temp_length = n;
            while ((temp_length & 1) == 0) {
                p >>= 1;
                temp_length >>= 1;
            }
            long w = a_pow_n_mod_m(r, p, mod);

            a = FFT(a, w);
            b = FFT(b, w);
            long c[] = new long[n];
            for (int i = 0; i < n; i++) {
                c[i] = a[i] * b[i];
                c[i] %= mod;
            }
            long w_inv = a_pow_n_mod_m(w, mod - 2, mod);
            w_inv %= mod;
            c = FFT(c, w_inv);
            long n_inv = a_pow_n_mod_m(n, mod - 2, mod);
            for (int i = 0; i < c.length; i++) {
                c[i] = c[i] * n_inv;
                c[i] %= mod;
            }
            return c;
        }

        private long[] FFT(long a[], long w_n) {
            if (a.length == 1) {
                return a;
            }
            long w_n_2 = (w_n * w_n) % mod;
            long a_even[] = new long[a.length / 2];
            long a_odd[] = new long[a.length / 2];
            for (int i = 0; i < a.length; i++) {
                if ((i & 1) == 1) {
                    a_odd[i / 2] = a[i];
                } else {
                    a_even[i / 2] = a[i];
                }
            }
            a_even = FFT(a_even, w_n_2);
            a_odd = FFT(a_odd, w_n_2);
            long w = 1;
            for (int i = 0; i < a.length / 2; i++) {
                a[i] = (a_even[i] + (w * a_odd[i])) % mod;
                a[i + a.length / 2] = (a_even[i] - (w * a_odd[i])) % mod;
                w *= w_n;

                if (a[i] < 0) {
                    a[i] += mod;
                }
                if (a[i + a.length / 2] < 0) {
                    a[i + a.length / 2] += mod;
                }
                w %= mod;
            }
            return a;
        }

        private long a_pow_n_mod_m(long a, long n, long m) {
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
            p *= p;
            p %= m;
            if ((n & 1) == 1) {
                p *= a;
                p %= m;
            }
            return p;
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
        final BufferedWriter bw;
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

        public long nl() throws IOException {
            return Long.parseLong(nextToken());
        }

        public String next() throws IOException {
            return nextToken();
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
