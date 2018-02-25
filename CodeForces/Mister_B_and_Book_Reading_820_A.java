
/**
 * Date: 25 Feb, 2018
 * Link : http://codeforces.com/contest/820/problem/A
 * 
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Mister_B_and_Book_Reading_820_A {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int c = in.ni();
        int v0 = in.ni();
        int v1 = in.ni();
        int a = in.ni();
        int l = in.ni();
        int currv = v0;
        int currpage = 1;
        int count = 0;
        while (currpage <= c) {
            currpage -= l;
            if (currpage <= 0) {
                currpage = 1;
            }
            currpage += currv;
            count++;
            if (currv < v1) {
                currv += a;
            }
            if (currv >= v1) {
                currv = v1;
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
