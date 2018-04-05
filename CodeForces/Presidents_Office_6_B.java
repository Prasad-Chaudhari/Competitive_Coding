package CodeForces;


/**
 * Date: 26 Feb, 2018
 * Link : http://codeforces.com/problemset/problem/6/B
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Presidents_Office_6_B {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int n = in.ni();
        int m = in.ni();
        char P = in.next().charAt(0);
        char[][] c = new char[n][m];
        for (int i = 0; i < n; i++) {
            c[i] = in.next().toCharArray();
        }
        Set<Character> s = new HashSet<>();
        int pi1 = 0;
        int pj1 = 0;
        int pi2 = 0;
        int pj2 = 0;
        boolean p = true;
        Outer:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] == P) {
                    if (p) {
                        pi1 = i;
                        pj1 = j;
                        p = false;
                    }
                    pi2 = i;
                    pj2 = j;
                }
            }
        }
        for (int i = pi1; i <= pi2; i++) {
            int j = pj1 - 1;
            if (i >= 0 && i < n && j >= 0 && j < m) {
                s.add(c[i][j]);
            }
        }
        for (int i = pi1; i <= pi2; i++) {
            int j = pj2 + 1;
            if (i >= 0 && i < n && j >= 0 && j < m) {
                s.add(c[i][j]);
            }
        }
        for (int i = pj1; i <= pj2; i++) {
            int j = pi1 - 1;
            if (i >= 0 && i < m && j >= 0 && j < n) {
                s.add(c[j][i]);
            }
        }
        for (int i = pj1; i <= pj2; i++) {
            int j = pi2 + 1;
            if (i >= 0 && i < m && j >= 0 && j < n) {
                s.add(c[j][i]);
            }
        }
        int count = 0;
        for (char d : s) {
            if (d != '.') {
                count++;
            }
        }
        System.out.println(count);
    }

    static class FastIO2 {

        private final BufferedReader br;
        private StringTokenizer st;

        public FastIO2() throws IOException {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer(br.readLine());
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

        public String nli() {
            return st.nextToken("");
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

        private String nextToken() throws IOException {
            while (st.countTokens() != 0) {
                return st.nextToken();
            }
            st = new StringTokenizer(br.readLine());
            return nextToken();
        }

        private void validate(int n, int start, int end) {
            if (start < 0 || end >= n) {
                throw new IllegalArgumentException();
            }
        }
    }
}
