
/**
 * Date: 12 Jun, 2018
 * Link: http://codeforces.com/contest/990/problem/D
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Graph_And_Its_Complement_990_D {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int a = in.ni();
        int b = in.ni();
        if (a == 1) {
            if (b == 1) {
                if (n > 3) {
                    in.println("YES");
                    int ans[][] = new int[n + 1][n + 1];
                    for (int i = 1; i <= n - b; i++) {
                        ans[i][i + 1] = 1;
                        ans[i + 1][i] = 1;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (int i = 1; i <= n; i++) {
                        for (int j = 1; j <= n; j++) {
                            sb.append(ans[i][j]);
                        }
                        sb.append("\n");
                    }
                    in.println(sb.toString());
                } else if (n != 1) {
                    in.print("NO");
                } else {
                    in.print("YES\n0");
                }
            } else {
                in.println("YES");
                int ans[][] = new int[n + 1][n + 1];
                for (int i = 1; i <= n - b; i++) {
                    ans[i][i + 1] = 1;
                    ans[i + 1][i] = 1;
                }
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        if (i != j) {
                            ans[i][j] ^= 1;
                        }
                    }
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        sb.append(ans[i][j]);
                    }
                    sb.append("\n");
                }
                in.println(sb.toString());
            }
        } else {
            if (b == 1) {
                in.println("YES");
                int ans[][] = new int[n + 1][n + 1];
                for (int i = 1; i <= n - a; i++) {
                    ans[i][i + 1] = 1;
                    ans[i + 1][i] = 1;
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        sb.append(ans[i][j]);
                    }
                    sb.append("\n");
                }
                in.println(sb.toString());
            } else {
                in.println("NO");
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
