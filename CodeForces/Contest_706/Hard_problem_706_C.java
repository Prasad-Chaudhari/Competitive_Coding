
/**
 * Date: 10 Nov, 2018
 * Link: http://codeforces.com/problemset/problem/706/C
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

public class Hard_problem_706_C {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int cost[] = in.gia(n);
        char c[][] = new char[n][];
        for (int i = 0; i < n; i++) {
            c[i] = in.next().toCharArray();
        }
        long dp_s[] = new long[n];
        long dp_r[] = new long[n];
        dp_r[0] = cost[0];
        boolean b[] = new boolean[n];
        b[0] = true;
        for (int i = 1; i < n; i++) {
            boolean p1 = false;
            boolean p2 = false;
            int q1 = 0;
            int q2 = 0;
            for (int j = 0; j < c[i - 1].length && j < c[i].length; j++) {
                if (c[i - 1][j] > c[i][j]) {
                    q1 = -1;
                    break;
                }
                if (c[i - 1][j] < c[i][j]) {
                    q1 = 1;
                    break;
                }
            }
            if (q1 == 0 && c[i - 1].length <= c[i].length) {
                q1 = 1;
            }
            for (int j = 0; j < c[i - 1].length && j < c[i].length; j++) {
                if (c[i - 1][j] > c[i][c[i].length - 1 - j]) {
                    q2 = -1;
                    break;
                }
                if (c[i - 1][j] < c[i][c[i].length - 1 - j]) {
                    q2 = 1;
                    break;
                }
            }
            if (q2 == 0 && c[i - 1].length <= c[i].length) {
                q2 = 1;
            }
            p1 = q1 == 1;
            p2 = q2 == 1;
            if (b[i - 1]) {
                boolean p3 = false;
                boolean p4 = false;
                int q3 = 0;
                int q4 = 0;
                for (int j = 0; j < c[i - 1].length && j < c[i].length; j++) {
                    if (c[i - 1][c[i - 1].length - 1 - j] > c[i][j]) {
                        q3 = -1;
                        break;
                    }
                    if (c[i - 1][c[i - 1].length - 1 - j] < c[i][j]) {
                        q3 = 1;
                        break;
                    }
                }
                if (q3 == 0 && c[i - 1].length <= c[i].length) {
                    q3 = 1;
                }
                for (int j = 0; j < c[i - 1].length && j < c[i].length; j++) {
                    if (c[i - 1][c[i - 1].length - 1 - j] > c[i][c[i].length - 1 - j]) {
                        q4 = -1;
                        break;
                    }
                    if (c[i - 1][c[i - 1].length - 1 - j] < c[i][c[i].length - 1 - j]) {
                        q4 = 1;
                        break;
                    }
                }
                if (q4 == 0 && c[i - 1].length <= c[i].length) {
                    q4 = 1;
                }
                p3 = q3 == 1;
                p4 = q4 == 1;
                if (p1 && p3) {
                    dp_s[i] = Math.min(dp_s[i - 1], dp_r[i - 1]);
                    b[i] = true;
                    if (p2 && p4) {
                        dp_r[i] = cost[i] + Math.min(dp_s[i - 1], dp_r[i - 1]);
                    } else if (p2) {
                        dp_r[i] = cost[i] + dp_s[i - 1];
                    } else if (p4) {
                        dp_r[i] = cost[i] + dp_r[i - 1];
                    } else {
                        b[i] = false;
                    }
                } else if (p1) {
                    dp_s[i] = dp_s[i - 1];
                    b[i] = true;
                    if (p2 && p4) {
                        dp_r[i] = cost[i] + Math.min(dp_s[i - 1], dp_r[i - 1]);
                    } else if (p2) {
                        dp_r[i] = cost[i] + dp_s[i - 1];
                    } else if (p4) {
                        dp_r[i] = cost[i] + dp_r[i - 1];
                    } else {
                        b[i] = false;
                    }
                } else if (p3) {
                    dp_s[i] = dp_r[i - 1];
                    b[i] = true;
                    if (p2 && p4) {
                        dp_r[i] = cost[i] + Math.min(dp_s[i - 1], dp_r[i - 1]);
                    } else if (p2) {
                        dp_r[i] = cost[i] + dp_s[i - 1];
                    } else if (p4) {
                        dp_r[i] = cost[i] + dp_r[i - 1];
                    } else {
                        b[i] = false;
                    }
                } else {
                    if (p2 && p4) {
                        dp_r[i] = cost[i] + Math.min(dp_r[i - 1], dp_s[i - 1]);
                    } else if (p2) {
                        dp_r[i] = cost[i] + dp_s[i - 1];
                    } else if (p4) {
                        dp_r[i] = cost[i] + dp_r[i - 1];
                    } else {
                        System.out.println("-1");
                        return;
                    }
                    dp_s[i] = dp_r[i];
                    dp_r[i] = 0;
                    for (int j = 0; j < c[i].length / 2; j++) {
                        c[i][j] ^= c[i][c[i].length - 1 - j];
                        c[i][c[i].length - 1 - j] ^= c[i][j];
                        c[i][j] ^= c[i][c[i].length - 1 - j];
                    }
                }
            } else {
                if (p1 && p2) {
                    dp_s[i] = dp_s[i - 1];
                    dp_r[i] = dp_s[i - 1] + cost[i];
                    b[i] = true;
                } else if (p1) {
                    dp_s[i] = dp_s[i - 1];
                } else if (p2) {
                    dp_s[i] = dp_s[i - 1] + cost[i];
                    for (int j = 0; j < c[i].length / 2; j++) {
                        c[i][j] ^= c[i][c[i].length - 1 - j];
                        c[i][c[i].length - 1 - j] ^= c[i][j];
                        c[i][j] ^= c[i][c[i].length - 1 - j];
                    }
                } else {
                    System.out.println("-1");
                    return;
                }
            }
        }
        if (b[n - 1]) {
            System.out.println(Math.min(dp_r[n - 1], dp_s[n - 1]));
        } else {
            System.out.println(dp_s[n - 1]);
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

        public static void sort(int a[]) {
            Data d[] = new Data[a.length];
            for (int i = 0; i < a.length; i++) {
                d[i] = new Data(a[i], 0);
            }
            Arrays.sort(d);
            for (int i = 0; i < a.length; i++) {
                a[i] = d[i].a;
            }
        }
    }
}
