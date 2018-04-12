
/**
 * Date: 12 Apr, 2018
 * Link: https://www.codechef.com/APRIL18B/problems/HIGHWAYC
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HIGHWAYC {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int t = in.ni();
        while (t-- > 0) {
            int n = in.ni();
            double s = in.ni();
            double y = in.ni();
            double t0 = y / (s);
            double v[] = in.gda(n);
            double d[] = in.gda(n);
            double p[] = in.gda(n);
            double c[] = in.gda(n);
            double time = 0;
            for (int i = 0; i < n; i++) {
                if (d[i] == 1) {
                    p[i] += time * v[i];
                    if (p[i] > 0) {
                        if (p[i] - c[i] < .000001) {
                            time += (c[i] - p[i] + .000001) / (v[i]);
                        }
                    } else {
                        if (p[i] + t0 * v[i] > -.000001) {
                            time += (c[i] - p[i] + .000001) / v[i];
                        }
                    }
                } else {
                    p[i] -= time * v[i];
                    if (p[i] > 0) {
                        if (p[i] - t0 * v[i] < .000001) {
                            time += (c[i] + p[i] + .000001) / v[i];
                        }
                    } else {
                        if (c[i] + p[i] > -.000001) {
                            time += (c[i] + p[i] + .000001) / (v[i]);
                        }
                    }
                }
                time += t0;
            }
            System.out.println(time);
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
