
/**
 * Date: 10 Apr, 2018
 * Link: http://codeforces.com/contest/962/problem/C
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Make_a_Square_962_C {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        char c[] = in.next().toCharArray();
        long p = 1;
        int a[] = new int[600000];
        int count = 0;
        while (true) {
            if (p * p > 2000000000) {
                break;
            }
            a[count++] = (int) (p * p);
            p++;
        }
        System.out.println(min(c, a, count, 0));
    }

    private static int min(char[] c, int[] a, int count, int index) {
        if (index == c.length) {
            String s = "";
            for (int i = 0; i < c.length; i++) {
                if (c[i] != '.') {
                    s += c[i];
                }
            }
            if (s.equals("")) {
                return -1;
            }
            if (Arrays.binarySearch(a, 0, count, Integer.parseInt(s)) >= 0) {
                return c.length - ("" + Integer.parseInt(s)).length();
            } else {
                return -1;
            }
        } else {
            int ans1 = min(c, a, count, index + 1);
            char k = c[index];
            c[index] = '.';
            int ans = min(c, a, count, index + 1);
            c[index] = k;
            if (ans1 != -1 && ans != -1) {
                return Math.min(ans, ans1);
            } else if (ans != -1) {
                return ans;
            } else if (ans1 != -1) {
                return ans1;
            } else {
                return -1;
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
