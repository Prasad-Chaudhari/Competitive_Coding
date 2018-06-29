
/**
 * Date: 12 Jun, 2018
 * Link: http://codeforces.com/contest/989/problem/B
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class A_Tide_of_Riverscape_989_B {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int p = in.ni();
        char c[] = in.next().toCharArray();
        int a[][] = new int[p][3];
        for (int i = 0; i < n; i++) {
            if (c[i] == '.') {
                a[i % p][0]++;
            }
            if (c[i] == '0') {
                a[i % p][1]++;
            }
            if (c[i] == '1') {
                a[i % p][2]++;
            }
        }
        if (n == p) {
            System.out.println("No");
            return;
        }
        boolean not_period = false;
        for (int i = 0; i < p; i++) {
            if (a[i][1] != 0 && a[i][2] != 0) {
                not_period = true;
            } else {
                if (a[i][0] != 0) {
                    if (a[i][0] != 1) {
                        not_period = true;
                    } else {
                        if (a[i][1] != 0 || a[i][2] != 0) {
                            not_period = true;
                        }
                    }
                }
            }
        }
        if (not_period) {
            for (int i = 0; i < n; i++) {
                if (c[i] == '.') {
                    if (a[i % p][0] == 1) {
                        if (a[i % p][1] == 0) {
                            System.out.print("0");
                        } else {
                            System.out.print("1");
                        }
                    } else {
                        System.out.print("0");
                        c[i] = 0;
                        for (int j = i; j < n; j += p) {
                            if (c[j] == '.') {
                                c[j] = '1';
                                break;
                            }
                        }
                    }
                } else {
                    System.out.print(c[i]);
                }
            }
        } else {
            System.out.println("No");
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
