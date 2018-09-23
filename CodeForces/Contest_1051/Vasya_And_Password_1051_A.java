
/**
 * Date: 20 Sep, 2018
 * Link: http://codeforces.com/contest/1051/problem/A
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

public class Vasya_And_Password_1051_A {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int t = in.ni();
        while (t-- > 0) {
            String s = in.next();
            char c[] = s.toCharArray();
            int n = c.length;
            int small[] = new int[c.length];
            int ins = 0;
            int cap[] = new int[c.length];
            int inc = 0;
            int dig[] = new int[c.length];
            int ind = 0;
            for (int i = 0; i < c.length; i++) {
                if (islower(c[i])) {
                    small[ins++] = i;
                }
                if (isCap(c[i])) {
                    cap[inc++] = i;
                }
                if (isdig(c[i])) {
                    dig[ind++] = i;
                }
            }
            if (ins == 0) {
                if (inc == 0) {
                    c[dig[0]] = 'a';
                    c[dig[1]] = 'A';
                } else {
                    if (ind == 0) {
                        c[cap[0]] = 'a';
                        c[cap[1]] = '0';
                    } else {
                        for (int i = 0; i < n; i++) {
                            if (isCap(c[i]) && inc > 1) {
                                c[i] = 'a';
                                break;
                            }
                            if (isdig(c[i]) && ind > 1) {
                                c[i] = 'a';
                                break;
                            }
                        }
                    }
                }
            } else {
                if (inc == 0) {
                    if (ind == 0) {
                        c[small[0]] = 'A';
                        c[small[1]] = '1';
                    } else {
                        for (int i = 0; i < n; i++) {
                            if (islower(c[i]) && ins > 1) {
                                c[i] = 'A';
                                break;
                            }
                            if (isdig(c[i]) && ind > 1) {
                                c[i] = 'A';
                                break;
                            }
                        }
                    }
                } else {
                    if (ind == 0) {
                        for (int i = 0; i < n; i++) {
                            if (islower(c[i]) && ins > 1) {
                                c[i] = '0';
                                break;
                            }
                            if (isCap(c[i]) && inc > 1) {
                                c[i] = '0';
                                break;
                            }
                        }
                    } else {

                    }
                }
            }
            System.out.println(c);
        }
    }

    static boolean islower(char c) {
        return c >= 'a' && c <= 'z';
    }

    static boolean isCap(char c) {
        return c >= 'A' && c <= 'Z';
    }

    static boolean isdig(char c) {
        return c >= '0' && c <= '9';
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

        int a, b;

        public Data(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Data o) {
            if (a != o.a) {
                return Integer.compare(b, o.b);
            }
            return Integer.compare(a, o.a);
        }
    }
}
