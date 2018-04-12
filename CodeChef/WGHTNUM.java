
/**
 * Date: 11 Apr, 2018
 * Link: https://www.codechef.com/APRIL18B/problems/WGHTNUM
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WGHTNUM {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int t = in.ni();
        while (t-- > 0) {
            long n = in.nl();
            int w = in.ni();
            long ans = Expo.a_pow_n_mod_m(10, n - 2, 1000000007);
            if (w == 0) {
                System.out.println((ans * 9) % 1000000007);
            } else if (w > 0) {
                int i = 1;
                long temp = ans;
                ans = 0;
                while (i + w <= 9) {
                    ans += temp;
                    ans %= 1000000007;
                    i++;
                }
                System.out.println((ans) % 1000000007);
            } else {
                int i = 0;
                long temp = ans;
                ans = 0;
                while (i - w <= 9) {
                    ans += temp;
                    ans %= 1000000007;
                    i++;
                }
                System.out.println((ans) % 1000000007);
            }
        }
    }

    static class FastIO2 {

        private final BufferedReader br;
        private String s[];
        private int index;

        public FastIO2() throws IOException {
            br = new BufferedReader(new InputStreamReader(System.in));
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
                a[i] = ni();
            }
            return a;
        }

        public long[] gla(int n, int start, int end) throws IOException {
            validate(n, start, end);
            long a[] = new long[n];
            for (int i = start; i < end; i++) {
                a[i] = ni();
            }
            return a;
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

    private static class Expo {

        static long a_pow_n_mod_m(long a, long n, long m) {
            if (n == 0) {
                return 1;
            }
            if (n == 1) {
                return a;
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
}
