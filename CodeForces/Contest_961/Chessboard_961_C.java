
/**
 * Date: 14 Apr, 2018
 * Link: http://codeforces.com/contest/961/problem/C
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Chessboard_961_C {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int n = in.ni();
        int a[][] = new int[n][n];
        int b[][] = new int[n][n];
        int c[][] = new int[n][n];
        int d[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int j = 0; j < n; j++) {
                a[i][j] = s.charAt(j) - '0';
            }
        }
        in.next();
        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int j = 0; j < n; j++) {
                b[i][j] = s.charAt(j) - '0';
            }
        }
        in.next();
        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int j = 0; j < n; j++) {
                c[i][j] = s.charAt(j) - '0';
            }
        }
        in.next();
        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int j = 0; j < n; j++) {
                d[i][j] = s.charAt(j) - '0';
            }
        }
        int min = 2 * n * 2 * n + 1;
        min = Math.min(min, compute(a, b, c, d));
        min = Math.min(min, compute(a, b, d, c));
        min = Math.min(min, compute(a, c, b, d));
        min = Math.min(min, compute(a, c, d, b));
        min = Math.min(min, compute(b, a, c, d));
        min = Math.min(min, compute(b, c, a, d));
        min = Math.min(min, compute(b, c, d, a));
        min = Math.min(min, compute(b, d, a, c));
        min = Math.min(min, compute(b, d, c, a));
        min = Math.min(min, compute(a, d, b, c));
        min = Math.min(min, compute(a, d, c, b));
        min = Math.min(min, compute(c, a, b, d));
        min = Math.min(min, compute(c, a, d, b));
        min = Math.min(min, compute(c, b, a, d));
        min = Math.min(min, compute(c, b, d, a));
        min = Math.min(min, compute(c, d, a, b));
        min = Math.min(min, compute(c, d, b, a));
        min = Math.min(min, compute(b, a, d, c));
        min = Math.min(min, compute(d, a, b, c));
        min = Math.min(min, compute(d, a, c, b));
        min = Math.min(min, compute(d, b, a, c));
        min = Math.min(min, compute(d, b, c, a));
        min = Math.min(min, compute(d, c, a, b));
        min = Math.min(min, compute(d, c, b, a));
        System.out.println(min);
    }

    private static int compute(int[][] a, int[][] b, int[][] c, int[][] d) {
        int n = a.length;
        int cell[][] = new int[2 * n][2 * n];
        int f[][] = new int[2 * n][2 * n];
        int overlap = 0;
        int overlap2 = 0;
        for (int i = 0; i < n; i++) {
            System.arraycopy(a[i], 0, f[i], 0, n);
        }
        for (int i = 0; i < n; i++) {
            System.arraycopy(b[i], 0, f[i], n, n);
        }
        for (int i = 0; i < n; i++) {
            System.arraycopy(c[i], 0, f[i + n], 0, n);
        }
        for (int i = 0; i < n; i++) {
            System.arraycopy(d[i], 0, f[i + n], n, n);
        }
        for (int i = 0; i < 2 * n; i++) {
            for (int j = 0; j < 2 * n; j++) {
                cell[i][j] = (i + j) % 2;
            }
        }
        for (int i = 0; i < 2 * n; i++) {
            for (int j = 0; j < n * 2; j++) {
                if (cell[i][j] != f[i][j]) {
                    overlap++;
                }
            }
        }
        for (int i = 0; i < 2 * n; i++) {
            for (int j = 0; j < 2 * n; j++) {
                cell[i][j] = ((i + j) % 2) ^ 1;
            }
        }
        for (int i = 0; i < 2 * n; i++) {
            for (int j = 0; j < n * 2; j++) {
                if (cell[i][j] != f[i][j]) {
                    overlap2++;
                }
            }
        }
        return Math.min(overlap, overlap2);
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
