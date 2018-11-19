
/**
 * Date: 4 Nov, 2018
 * Link: https://www.codechef.com/NOV18B/problems/GMEDIAN
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

public class GMEDIAN {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int t = in.ni();
        long mod = 1000000007;
        Factorial fac = new Factorial(1000, mod);
        while (t-- > 0) {
            int n = in.ni();
            int a[] = in.gia(n);
            int freq[] = new int[2 * n + 1];
            for (int i = 0; i < n; i++) {
                freq[a[i]]++;
            }
            int[] front_sum = new int[2 * n + 1];
            front_sum[0] = freq[0];
            for (int i = 1; i <= 2 * n; i++) {
                front_sum[i] = front_sum[i - 1] + freq[i];
            }
            int[] back_sum = new int[2 * n + 1];
            back_sum[2 * n] = freq[2 * n];
            for (int i = 2 * n - 1; i >= 0; i--) {
                back_sum[i] += back_sum[i + 1] + freq[i];
            }
            long ans = 0;
            for (int i = 1; i < 2 * n; i++) {
                for (int j = 0; j < freq[i]; j++) {
                    int p = front_sum[i - 1] + j + back_sum[i + 1];
                    int q = front_sum[i - 1] + j + 1;
                    if (p >= 1 && q <= p) {
                        long temp = fac.fac[p];
                        temp *= fac.inv_fac[p - q];
                        temp %= mod;
                        temp *= fac.inv_fac[q];
                        temp %= mod;
                        ans += temp;
                        ans %= mod;
                    }
                }
            }
            ans = Factorial.pow_mod(2, n, mod) - 1 - ans;
            ans += 2 * mod;
            ans %= mod;
            System.out.println(ans);
        }
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

        static long pow_mod(long a, long n, long m) {
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
            bw.flush();
        }

        public void println(String s) throws IOException {
            bw.write(s);
            bw.newLine();
            bw.flush();
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
