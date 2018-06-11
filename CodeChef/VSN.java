
/**
 * Date: 11 Jun, 2018
 * Link: https://www.codechef.com/JUNE18B/problems/VSN
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class VSN {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int t = in.ni();
        while (t-- > 0) {
            double px = in.ni();
            double py = in.ni();
            double pz = in.ni();
            double x0 = in.nl() - px;
            double y0 = in.nl() - py;
            double z0 = in.nl() - pz;
            double dx = in.nl();
            double dy = in.nl();
            double dz = in.nl();
            double cx = in.nl() - px;
            double cy = in.nl() - py;
            double cz = in.nl() - pz;
            double r = in.nl();
            if ((dx == 0 && dy == 0 && dz == 0) || (r == 0)) {
                System.out.println("0");
            } else {
                double a1 = dx * cx + dy * cy + dz * cz;
                double b1 = x0 * cx + y0 * cy + z0 * cz;
                double k = cx * cx + cy * cy + cz * cz - r * r;
                double a2 = (Math.pow(dx, 2) + Math.pow(dy, 2) + Math.pow(dz, 2)) * k;
                double b2 = 2 * k * (dx * x0 + dy * y0 + dz * z0);
                double c2 = (Math.pow(x0, 2) + Math.pow(y0, 2) + Math.pow(z0, 2)) * k;
                double a3 = a1 * a1 - a2;
                double b3 = 2 * a1 * b1 - b2;
                double c3 = b1 * b1 - c2;
                if (a3 == 0) {
                    double ans = -1.0 * c3 / b3;
                    System.out.println(ans);
                } else {
                    if (b3 * b3 - 4 * a3 * c3 < 0) {
                        System.out.println(-1 * b3 / (2 * a3));
                    } else {
                        double ans = (Math.sqrt(b3 * b3 - 4 * a3 * c3) - b3) / (2.0 * a3);
                        double ans2 = (-Math.sqrt(b3 * b3 - 4 * a3 * c3) - b3) / (2.0 * a3);
                        if (ans >= 0 && ans2 < 0) {
                            System.out.println(ans);
                        } else if (ans2 >= 0 && ans < 0) {
                            System.out.println(ans2);
                        } else {
                            System.out.println(Math.min(ans, ans2));
                        }
                    }
                }
            }
        }
    }

    static class FastIO {

        private final BufferedReader br;
        private final BufferedWriter bw;
        private String s[];
        private int index;

        public FastIO() throws IOException {
            br = new BufferedReader(new InputStreamReader(System.in));
            bw = new BufferedWriter(new OutputStreamWriter(System.out, "UTF-8"));
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

        public int[][] gg(int n, int m) throws IOException {
            int adja[][] = new int[n + 1][];
            int from[] = new int[m];
            int to[] = new int[m];
            int count[] = new int[n + 1];
            for (int i = 0; i < m; i++) {
                from[i] = ni();
                to[i] = ni();
                count[from[i]]++;
                count[to[i]]++;
            }
            for (int i = 0; i <= n; i++) {
                adja[i] = new int[count[i]];
            }
            for (int i = 0; i < m; i++) {
                adja[from[i]][--count[from[i]]] = to[i];
                adja[to[i]][--count[to[i]]] = from[i];
            }
            return adja;
        }

        public void print(String s) throws IOException {
            bw.write(s);
            bw.flush();
        }

        public void println(String s) throws IOException {
            bw.write(s);
            bw.newLine();
            bw.flush();
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
