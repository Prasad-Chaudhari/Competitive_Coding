
/**
 * Date: 6 May, 2018
 * Link: http://codeforces.com/contest/977/problem/D
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Divide_by_three_multiply_by_two_977_D {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int n = in.ni();
        long a[] = in.gla(n);
        Arrays.sort(a);
        long b[] = new long[n];
        boolean p[] = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            long start = a[i];
            p[i] = true;
            b[count++] = a[i];
            while (count != n) {
                int j;
                for (j = 0; j < n; j++) {
                    if (!p[j]) {
                        if (a[j] % 3 == 0 && a[j] == start / 3) {
                            b[count++] = a[j];
                            start = a[j];
                            p[j] = true;
                            break;
                        }
                        if (a[j] == start * 2) {
                            b[count++] = a[j];
                            start = a[j];
                            p[j] = true;
                            break;
                        }
                    }
                }
                if (j == n) {
                    break;
                }
            }
            if (count != n) {
                for (int j = 0; j < n; j++) {
                    b[j] = 0;
                    p[j] = false;
                }
                count = 0;
            } else {
                for (long j : b) {
                    System.out.print(j + " ");
                }
                break;
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
                a[i] = nl();
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
