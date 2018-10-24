
/**
 * Date: 11 Oct, 2018
 * Link: https://www.codechef.com/OCT18B/problems/CCIRCLES
 *
 * @author Prasad-Chaudhari
 * @linkedIn: https://www.linkedin.com/in/prasad-chaudhari-841655a6/
 * @git: https://github.com/Prasad-Chaudhari
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CCIRCLES {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int q = in.ni();
        int x[] = new int[n];
        int y[] = new int[n];
        int r[] = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.ni();
            y[i] = in.ni();
            r[i] = in.ni();
        }
        int sum[] = new int[1000002];
        int i = 0, j;
        while (i < n) {
            j = i + 1;
            while (j < n) {
                int minr = Math.min(r[i], r[j]);
                int maxr = Math.max(r[i], r[j]);
                double c1c2sq = c1c2sq(x[i], y[i], x[j], y[j]);
                if (c1c2sq >= Math.pow(r[i] + r[j], 2)) {
                    double c1c2 = sqrt(c1c2sq);
                    int min = (int) (c1c2 - r[i] - r[j]);
                    int max = (int) (c1c2 + r[i] + r[j]);
                    if (c1c2 * c1c2 < c1c2sq) {
                        min++;
                    }
                    max = Math.min(max, 1000000);
                    sum[max + 1] -= 1;
                    min = Math.max(0, min);
                    sum[min] += 1;
                } else {
                    if (c1c2sq >= Math.pow(maxr - minr, 2)) {
                        int min = 0;
                        int max = (int) (sqrt(c1c2sq) + maxr + minr);
                        sum[max + 1] -= 1;
                        sum[min] += 1;
                    } else {
                        double c1c2 = sqrt(c1c2sq);
                        int min = (int) (maxr - c1c2 - minr);
                        int max = (int) (maxr + sqrt(c1c2sq) + minr);
                        sum[max + 1] -= 1;
                        sum[min] += 1;
                    }
                }
                ++j;
            }
            ++i;
        }
        i = 1;
        while (i < 1000002) {
            sum[i] += sum[i - 1];
            ++i;
        }
        while (--q >= 0) {
            in.println(sum[in.ni()] + "");
        }
    }

    private static int sqrt(double d) {
        double ss = Math.max(0, (long) Math.sqrt(d) - 2);
        while (ss * ss < d) {
            ss++;
        }
        if (ss * ss == d) {
            return (int) ss;
        }
        ss--;
        return (int) ss;
    }

    private static double c1c2sq(double x1, double y1, double x2, double y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

    static class FastIO {

        private final BufferedReader br;
        private final BufferedWriter bw;
        private String s[];
        private int index;
        private StringBuilder sb;

        public FastIO() throws IOException {
            br = new BufferedReader(new InputStreamReader(System.in));
            bw = new BufferedWriter(new OutputStreamWriter(System.out, "UTF-8"));
            s = br.readLine().split(" ");
            sb = new StringBuilder();
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

    static class Data implements Comparable<Data> {

        int a, b, c;

        public Data(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Data o) {
            if (a == o.a) {
                return Integer.compare(b, o.b);
            }
            return Integer.compare(a, o.a);
        }
    }
}
