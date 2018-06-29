
/**
 * Date: 26 Feb, 2018
 * Link : http://codeforces.com/problemset/problem/6/A
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Triangle_6_A {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int a = in.ni();
        int b = in.ni();
        int c = in.ni();
        int d = in.ni();
        boolean tri = false;
        boolean seg = false;
        if (a < b + c && b < a + c && c < b + a) {
            tri = true;
        }
        if (a < b + d && d < b + a && b < a + d) {
            tri = true;
        }
        if (a < d + c && c < d + a && d < a + c) {
            tri = true;
        }
        if (d < b + c && b < d + c && c < b + d) {
            tri = true;
        }
        if (a == b + c) {
            seg = true;
        }
        if (b == a + c) {
            seg = true;
        }
        if (c == b + a) {
            seg = true;
        }
        if (a == b + d) {
            seg = true;
        }
        if (d == b + a) {
            seg = true;
        }
        if (b == d + a) {
            seg = true;
        }
        if (a == d + c) {
            seg = true;
        }
        if (c == d + a) {
            seg = true;
        }
        if (d == a + c) {
            seg = true;
        }
        if (d == b + c) {
            seg = true;
        }
        if (b == d + c) {
            seg = true;
        }
        if (c == b + d) {
            seg = true;
        }
        if (tri) {
            System.out.println("TRIANGLE");
        } else if (seg) {
            System.out.println("SEGMENT");
        } else {
            System.out.println("IMPOSSIBLE");
        }
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
