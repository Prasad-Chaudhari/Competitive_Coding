
/**
 * Date: 10 Jul, 2018
 * Link: https://www.codechef.com/JULY18B/problems/NSA
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

public class NSA {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int t = in.ni();
        while (t-- > 0) {
            char c[] = in.next().toCharArray();
            int n = c.length;
            int front[][] = new int[n][26];
            int back[][] = new int[n][26];
            front[0][c[0] - 'a']++;
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < 26; j++) {
                    front[i][j] = front[i - 1][j];
                }
                front[i][c[i] - 'a']++;
            }
            back[n - 1][c[n - 1] - 'a']++;
            for (int i = n - 2; i >= 0; i--) {
                for (int j = 0; j < 26; j++) {
                    back[i][j] = back[i + 1][j];
                }
                back[i][c[i] - 'a']++;
            }
            long ans = 0;
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < c[i] - 'a'; j++) {
                    ans += front[i - 1][j];
                }
            }
            long min = ans;
            for (int i = 0; i < n; i++) {
                long temp = ans;
                if (i > 0) {
                    for (int j = 0; j < c[i] - 'a'; j++) {
                        temp -= front[i - 1][j];
                    }
                }
                if (i < n - 1) {
                    for (int j = c[i] - 'a' + 1; j < 26; j++) {
                        temp -= back[i + 1][j];
                    }
                }
                if (i < n - 1) {
                    for (int j = 1; j < 26; j++) {
                        temp += back[i + 1][j];
                    }
                }
                if (temp + Math.abs(c[i] - 'a' - 0) < min) {
                    min = temp + Math.abs(c[i] - 'a' - 0);
                }
                for (int j = 1; j < 26; j++) {
                    if (i < n - 1) {
                        temp -= back[i + 1][j];
                    }
                    if (i > 0) {
                        temp += front[i - 1][j - 1];
                    }
                    if (temp + Math.abs(c[i] - j - 'a') < min) {
                        min = temp + Math.abs(c[i] - j - 'a');
                    }
                }
            }
            System.out.println(min);
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
