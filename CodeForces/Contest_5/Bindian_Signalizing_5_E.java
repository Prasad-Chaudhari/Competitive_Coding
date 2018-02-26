
/**
 * Date: 26 Feb, 2018
 * Link : http://codeforces.com/problemset/problem/5/E
 * Comment : TLE
 * 
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bindian_Signalizing_5_E {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int n = in.ni();
        int b[] = in.gia(n);
        int max = -2;
        int index = -2;
        for (int i = 0; i < n; i++) {
            if (max < b[i]) {
                max = b[i];
                index = i;
            }
        }
        int a[] = new int[n + 1];
        a[0] = b[index];
        a[n] = b[index];
        for (int i = 0; i < n; i++) {
            a[i] = b[(index + i) % n];
        }
        b=a;
        int r[] = new int[n + 1];
        int l[] = new int[n + 1];
        int c[] = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            r[i] = i + 1;
            while (r[i] < n && b[i] >=b[r[i]]) {
                r[i] = r[r[i]];
            }
        }
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            l[i] = i - 1;
            while (l[i] > 0 && b[i] > b[l[i]]) {
                l[i] = l[l[i]];
            }
            if (l[i] > 0 && b[i] == b[l[i]]) {
                c[i] = c[l[i]] + 1;
                l[i] = l[l[i]];
            }
        }
        for (int i = 1; i < n; i++) {
            ans += c[i];
        }
        for (int i = 1; i < n; i++) {
            ans += 1;
            if (!(l[i] == 0 && r[i] == n)) {
                ans += 1;
            }
        }
        System.out.println(ans);
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
