
/**
 * Date: 31 May, 2018
 * Link: http://codeforces.com/contest/987/problem/D
 * Comment: Faster Graph IO and Standard Output
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Fair_987_D {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int n = in.ni();
        int m = in.ni();
        int k = in.ni();
        int s = in.ni();
        int type[] = in.gia(n);
        int count[] = new int[n + 1];
        int from[] = new int[m];
        int to[] = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = in.ni();
            to[i] = in.ni();
            count[from[i]]++;
            count[to[i]]++;
        }
        int adja[][] = new int[n + 1][];
        for (int i = 0; i <= n; i++) {
            adja[i] = new int[count[i]];
        }
        for (int i = 0; i < m; i++) {
            adja[from[i]][--count[from[i]]] = to[i];
            adja[to[i]][--count[to[i]]] = from[i];
        }
        int nfork[][] = new int[n + 1][k + 1];
        byte done[] = new byte[n + 1];
        int depth[] = new int[n + 1];
        int q[] = new int[n + 1];
        for (int i = 1; i <= k; i++) {
            int index = 0;
            for (int j = 1; j <= n; j++) {
                done[j] = 0;
                depth[j] = 0;
                if (type[j - 1] == i) {
                    q[index++] = j;
                    done[j] = 1;
                }
            }
            for (int j = 0; j < index; j++) {
                int root = q[j];
                for (int nh : adja[root]) {
                    if (done[nh] != 1) {
                        depth[nh] = depth[root] + 1;
                        q[index++] = nh;
                        done[nh] = 1;
                        nfork[nh][i] = depth[nh];
                    }
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            Arrays.sort(nfork[i]);
            int ans = 0;
            for (int j = 1; j <= s; j++) {
                ans += nfork[i][j];
            }
            in.bw.write(ans + "\n");
            in.bw.flush();
        }
    }

    static class FastIO2 {

        private final BufferedReader br;
        final BufferedWriter bw;
        private String s[];
        private int index;

        public FastIO2() throws IOException {
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
