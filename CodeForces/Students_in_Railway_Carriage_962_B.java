
/**
 * Date: 20 Apr, 2018
 * Link: http://codeforces.com/problemset/problem/962/B
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Students_in_Railway_Carriage_962_B {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int n = in.ni();
        int a = in.ni();
        int b = in.ni();
        char c[] = in.next().toCharArray();
        if (a > b) {
            a ^= b;
            b ^= a;
            a ^= b;
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (c[i] == '.') {
                if (a > b) {
                    a ^= b;
                    b ^= a;
                    a ^= b;
                }
                int p = 0;
                char d = 'a';
                for (int j = i; j < n; j++, i++) {
                    if (c[j] == '*') {
                        break;
                    }
                    if (p % 2 == 0) {
                        if (b == 0) {
                            break;
                        }
                        c[i] = 'b';
                        count++;
                        b--;
                    } else {
                        if (a == 0) {
                            d = '.';
                        }
                        if (d == 'a') {
                            c[i] = 'a';
                            count++;
                            a--;
                        }
                    }
                    p++;
                }
            }
        }
        System.out.println(count);
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
