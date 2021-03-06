
/**
 * Date: 19 Oct, 2018
 * Link: https://www.codechef.com/SNCK1A19/problems/AVGMAT
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
import java.util.Arrays;

public class AVGMAT {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int t = in.ni();
        while (t-- > 0) {
            int n = in.ni();
            int m = in.ni();
            char c[][] = new char[n][m];
            int ans[] = new int[n + m];
            for (int i = 0; i < n; i++) {
                c[i] = in.next().toCharArray();
            }
            int sum[][][] = new int[n][m][n + m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i == 0 && j == 0) {
                    } else if (i == 0) {
                        for (int k = 0; k < n + m; k++) {
                            sum[i][j][k] = sum[i][j - 1][k];
                        }
                    } else if (j == 0) {
                        for (int k = 0; k < n + m; k++) {
                            sum[i][j][k] = sum[i - 1][j][k];
                        }
                    } else {
                        for (int k = 0; k < n + m; k++) {
                            sum[i][j][k] = sum[i - 1][j][k] + sum[i][j - 1][k] - sum[i - 1][j - 1][k];
                        }

                    }
                    if (c[i][j] == '1') {
                        sum[i][j][i + j]++;
                        for (int k = 0; k < n + m && k <= i + j; k++) {
                            ans[i + j - k] += sum[i][j][k];
                        }
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    Arrays.fill(sum[i][j], 0);
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m / 2; j++) {
                    c[i][j] ^= c[i][m - j - 1];
                    c[i][m - j - 1] ^= c[i][j];
                    c[i][j] ^= c[i][m - j - 1];
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i == 0 && j == 0) {

                    } else if (i == 0) {
                        for (int k = 0; k < n + m; k++) {
                            sum[i][j][k] = sum[i][j - 1][k];
                        }
                    } else if (j == 0) {
                        for (int k = 0; k < n + m; k++) {
                            sum[i][j][k] = sum[i - 1][j][k];
                        }
                    } else {
                        if (c[i][j] == '1') {
                            for (int k = 0; k < n + m && k <= i + j; k++) {
                                ans[i + j - k] += sum[i - 1][j - 1][k];
                            }
                        }
                        for (int k = 0; k < n + m; k++) {
                            sum[i][j][k] = sum[i - 1][j][k] + sum[i][j - 1][k] - sum[i - 1][j - 1][k];
                        }
                    }
                    if (c[i][j] == '1') {
                        sum[i][j][i + j]++;
                    }
                }
            }
            for (int i = 1; i <= n + m - 2; i++) {
                System.out.print(ans[i] + " ");
            }
        }
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
                a[i] = nl();
            }
            return a;
        }

        public long[] gla(int n, int start, int end) throws IOException {
            validate(n, start, end);
            long a[] = new long[n];
            for (int i = start; i < end; i++) {
                a[i] = nl();
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

        int a, b;

        public Data(int a, int b) {
            this.a = a;
            this.b = b;
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
