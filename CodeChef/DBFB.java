
/**
 * Date: 14 May, 2018
 * Link: https://www.codechef.com/MAY18B/problems/DBFB
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DBFB {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int t = in.ni();
        long mod = 1000000007;
        while (t-- > 0) {
            int m = in.ni();
            int n = in.ni();
            int a[] = in.gia(m);
            int b[] = in.gia(m);
            switch (n) {
                case 1: {
                    long sum = 0;
                    for (int i = 0; i < m; i++) {
                        sum = (sum + a[i]) % mod;
                    }
                    System.out.println((sum * m) % mod);
                    break;
                }
                case 2: {
                    long sum2 = 0;
                    for (int i = 0; i < m; i++) {
                        sum2 = (sum2 + b[i]) % mod;
                    }
                    System.out.println((sum2 * m) % mod);
                    break;
                }
                default: {
                    long prea = 1;
                    long posta = 0;
                    long preb = 0;
                    long postb = 1;
                    for (int i = 3; i <= n; i++) {
                        long prex = (prea + preb) % mod;
                        long postx = (posta + postb) % mod;
                        prea = preb;
                        posta = postb;
                        preb = prex;
                        postb = postx;
                    }
                    long sum = 0;
                    for (int i = 0; i < m; i++) {
                        sum = (sum + a[i]) % mod;
                    }
                    long sum2 = 0;
                    for (int i = 0; i < m; i++) {
                        sum2 = (sum2 + b[i]) % mod;
                    }
                    long ans = (((sum * m) % mod * preb) % mod + ((sum2 * m) % mod * postb) % mod) % mod;
                    System.out.println(ans);
                    break;
                }
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
}
