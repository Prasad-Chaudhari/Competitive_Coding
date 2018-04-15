
/**
 * Date: 15 Apr, 2018
 * Link: https://www.codechef.com/APRIL18B/problems/GOODPREF
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GOODPREF {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int t = in.ni();
        while (t-- > 0) {
            char c[] = in.next().toCharArray();
            long n = in.ni();
            int diff[] = new int[c.length];
            int a = 0;
            int b = 0;
            int count = 0;
            for (int i = 0; i < c.length; i++) {
                if (c[i] == 'a') {
                    a++;
                } else {
                    b++;
                }
                diff[i] = a - b;
                if (diff[i] > 0) {
                    count++;
                }
            }
            if (a == b) {
                System.out.println(n * (count));
            } else if (a > b) {
                long ans = 0;
                int index = c.length - 1;
                int x = 0;
                Arrays.sort(diff);
                count = 0;
                while (count != c.length && n > 0) {
                    for (int i = index; i >= 0; i--) {
                        index = i;
                        if (diff[i] + x * (a - b) <= 0) {
                            break;
                        }
                        count++;
                    }
                    ans += count;
                    n--;
                    x++;
                }
                ans += (c.length) * n;
                System.out.println(ans);
            } else {
                long ans = 0;
                int index = 0;
                int x = 0;
                Arrays.sort(diff);
                count = 0;
                while (count != c.length && n > 0) {
                    for (int i = index; i < c.length; i++) {
                        index = i;
                        if (diff[i] + x * (a - b) > 0) {
                            break;
                        }
                        count++;
                    }
                    ans += c.length - count;
                    n--;
                    x++;
                }
                System.out.println(ans);
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
