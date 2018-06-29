
/**
 * Date: 7 Apr, 2018
 * Link: http://codeforces.com/contest/960/problem/A
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Check_the_string_960_A {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        char c[] = in.next().toCharArray();
        boolean ap = false;
        int index = -1;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 'a') {
                ap = true;
                index = i;
                continue;
            }
            break;
        }
        if (ap) {
            boolean bp = false;
            for (int i = index + 1; i < c.length; i++) {
                if (c[i] == 'b') {
                    bp = true;
                    index = i;
                    continue;
                }
                break;
            }
            if (bp) {
                boolean cp = false;
                for (int i = index + 1; i < c.length; i++) {
                    if (c[i] == 'c') {
                        cp = true;
                        index = i;
                        continue;
                    }
                    break;
                }
                if (cp && index == c.length - 1) {
                    int na = 0;
                    int nb = 0;
                    int nc = 0;
                    for (int i = 0; i < c.length; i++) {
                        if (c[i] == 'a') {
                            na++;
                        }
                        if (c[i] == 'b') {
                            nb++;
                        }
                        if (c[i] == 'c') {
                            nc++;
                        }
                    }
                    if (nc == na || nc == nb) {
                        System.out.println("YES");
                    } else {
                        System.out.println("NO");
                    }
                } else {
                    System.out.println("NO");
                }
            } else {
                System.out.println("NO");
            }
        } else {
            System.out.println("NO");
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
