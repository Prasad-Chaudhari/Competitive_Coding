
/**
 * Date: 25 Apr, 2018
 * Link: http://codeforces.com/contest/965/problem/B
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Battleship_965_B {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int n = in.ni();
        int k = in.ni();
        char c[][] = new char[n][n];
        int a[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            c[i] = in.next().toCharArray();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean p = true;
                for (int l = 0; l < k; l++) {
                    if (i + l == n || c[i + l][j] == '#') {
                        p = false;
                        break;
                    }
                }
                if (p) {
                    for (int l = 0; l < k; l++) {
                        a[i + l][j]++;
                    }
                }
                p = true;
                for (int l = 0; l < k; l++) {
                    if (j + l == n || c[i][j + l] == '#') {
                        p = false;
                        break;
                    }
                }
                if (p) {
                    for (int l = 0; l < k; l++) {
                        a[i][j + l]++;
                    }
                }
            }
        }
        int max = 0;
        int x0 = 0;
        int y0 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] > max) {
                    max = a[i][j];
                    x0 = i;
                    y0 = j;
                }
            }
        }
        System.out.println((x0 + 1) + " " + (y0 + 1));
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
