
/**
 * Date: 22 May, 2018
 * Link: http://codeforces.com/contest/985/problem/B
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Switches_and_Lamps_985_B {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int n = in.ni();
        int m = in.ni();
        int a[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            char c[] = in.next().toCharArray();
            for (int j = 0; j < m; j++) {
                a[i][j] = c[j] - '0';
            }
        }
        int pref[][] = new int[n][m];
        int pre[] = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                pref[i][j] = pre[j];
                pre[j] = pre[j] | a[i][j];
            }
        }
        int suff[][] = new int[n][m];
        int suf[] = new int[m];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                suff[i][j] = suf[j];
                suf[j] = suf[j] | a[i][j];
            }
        }
        boolean p = false;
        for (int i = 0; i < n; i++) {
            int b[] = new int[m];
            int count = 0;
            for (int j = 0; j < m; j++) {
                if (i < n - 1) {
                    b[j] |= suff[i][j];
                }
                if (i > 0) {
                    b[j] |= pref[i][j];
                }
                if (b[j] == 1) {
                    count++;
                }
            }
            if (count == m) {
                p = true;
            }
        }
        System.out.println(p ? "YEs" : "NO");
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
