
/**
 * Date: 8 May, 2018
 * Link: http://codeforces.com/contest/980/problem/B
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Marlin_980_B {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int n = in.ni();
        int k = in.ni();
        char c[][] = new char[4][n];
        for (int i = 0; i < 4; i++) {
            Arrays.fill(c[i], '.');
        }
        if (k % 2 == 0) {
            for (int i = 1; i < n - 1; i++) {
                if (k > 0) {
                    c[1][i] = '#';
                    k--;
                }
                if (k > 0) {
                    c[2][i] = '#';
                    k--;
                }
            }
        } else {
            c[1][n / 2] = '#';
            k--;
            for (int i = n / 2 - 1; i >= 1; i--) {
                if (k > 0) {
                    c[1][i] = '#';
                    k--;
                }
                if (k > 0) {
                    c[1][n - 1 - i] = '#';
                    k--;
                }
            }
            if (k > 0) {
                for (int i = 1; i < n / 2; i++) {
                    if (k > 0) {
                        c[2][i] = '#';
                        k--;
                    }
                    if (k > 0) {
                        c[2][n - 1 - i] = '#';
                        k--;
                    }
                }
            }
            if (k > 0) {
                c[2][n / 2] = '#';
                k--;
            }
        }
        if (k == 0) {
            System.out.println("Yes");
            for (int i = 0; i < 4; i++) {
                System.out.println(c[i]);
            }
        } else {
            System.out.println("NO");
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
