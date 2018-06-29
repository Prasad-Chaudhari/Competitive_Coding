
/**
 * Date: 14 Apr, 2018
 * Link: http://codeforces.com/contest/961/problem/D
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Pair_Of_Lines_961_D {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int n = in.ni();
        if (n <= 4) {
            System.out.println("YEs");
        } else {
            int x[] = new int[n];
            int y[] = new int[n];
            for (int i = 0; i < n; i++) {
                x[i] = in.ni();
                y[i] = in.ni();
            }
            Random r = new Random();
            int p = 1000000;
            while (p-- > 0) {
                int a = r.nextInt(n);
                int b = r.nextInt(n);
                int c = r.nextInt(n);
                if (a != b && b != c && a != c) {
                    if (check(a, b, c, x, y)) {
                        boolean f[] = new boolean[n];
                        f[a] = true;
                        f[b] = true;
                        f[c] = true;
                        int count = 3;
                        for (int j = 0; j < n; j++) {
                            if (!f[j]) {
                                if (check(a, b, j, x, y)) {
                                    f[j] = true;
                                    count++;
                                }
                            }
                        }
                        if (count >= n - 2) {
                            System.out.println("YES");
                            return;
                        }
                        for (int i = 0; i < n; i++) {
                            if (!f[i]) {
                                a = i;
                                break;
                            }
                        }
                        for (int i = a + 1; i < n; i++) {
                            if (!f[i]) {
                                b = i;
                                break;
                            }
                        }
                        for (int i = 0; i < n; i++) {
                            if (!f[i]) {
                                if (check(a, b, i, x, y)) {
                                    f[i] = true;
                                    count++;
                                }
                            }
                        }
                        if (count == n) {
                            System.out.println("YEs");
                            return;
                        } else {
                            break;
                        }
                    }
                }
            }
            System.out.println("No");
        }
    }

    private static boolean check(int a, int b, int c, int[] x, int[] y) {
        long x1 = x[a];
        long x2 = x[b];
        long x3 = x[c];
        long y1 = y[a];
        long y2 = y[b];
        long y3 = y[c];
        return x1 * (y2 - y3) + x2 * (y3 - y1) == -x3 * (y1 - y2);
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
