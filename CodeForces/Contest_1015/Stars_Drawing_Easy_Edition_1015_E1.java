
/**
 * Date: 31 Jul, 2018
 * Link: http://codeforces.com/contest/1015/problem/E1
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

public class Stars_Drawing_Easy_Edition_1015_E1 {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int m = in.ni();
        char c[][] = new char[n][m];
        int i, j;
        i = 0;
        while (i < n) {
            c[i] = in.next().toCharArray();
            ++i;
        }
        int row[][] = new int[n][m];
        int col[][] = new int[n][m];
        i = 0;
        while (i < n) {
            int count = 0;
            j = 0;
            while (j < m) {
                if (c[i][j] == '*') {
                    col[i][j] = count++;
                } else {
                    count = 0;
                }
                ++j;
            }
            count = 0;
            j = m - 1;
            while (j > 0) {
                if (c[i][j] == '*') {
                    col[i][j] = Math.min(col[i][j], count++);
                } else {
                    count = 0;
                }
                --j;
            }
            ++i;
        }
        i = 0;
        while (i < m) {
            j = 0;
            int l = 0;
            while (j < n) {
                if (c[j][i] == '*') {
                    row[j][i] = l++;
                } else {
                    l = 0;
                }
                ++j;
            }
            l = 0;
            j = n - 1;
            while (j >= 0) {
                if (c[j][i] == '*') {
                    row[j][i] = Math.min(row[j][i], l++);
                } else {
                    l = 0;
                }
                --j;
            }
            ++i;
        }
        int main[][] = new int[n][m];
        int count = 0;
        BIT[] bitRow = new BIT[n];
        BIT[] bitCol = new BIT[m];
        i = 0;
        while (i < n) {
            bitRow[i] = new BIT(m);
            ++i;
        }
        i = 0;
        while (i < m) {
            bitCol[i] = new BIT(n);
            ++i;
        }
        i = 0;
        while (i < n) {
            j = 0;
            while (j < m) {
                main[i][j] = Math.min(row[i][j], col[i][j]);
                if (main[i][j] != 0) {
                    count++;
                    bitRow[i].update(j - main[i][j], m, 1);
                    bitRow[i].update(j + main[i][j] + 1, m, -1);
                    bitCol[j].update(i - main[i][j], n, 1);
                    bitCol[j].update(i + main[i][j] + 1, n, -1);
                }
                ++j;
            }
            ++i;
        }
        i = 0;
        while (i < n) {
            j = 0;
            while (j < m) {
                if (c[i][j] == '*') {
                    if (bitRow[i].getSum(j) == 0 && bitCol[j].getSum(i) == 0) {
                        System.out.println("-1");
                        return;
                    }
                }
                ++j;
            }
            ++i;
        }
        System.out.println(count);
        i = 0;
        while (i < n) {
            j = 0;
            while (j < m) {
                if (main[i][j] != 0) {
                    in.println((i + 1) + " " + (j + 1) + " " + main[i][j]);
                }
                ++j;
            }
            ++i;
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

    static final class BIT {

        int BITree[];

        public BIT(int n) {
            BITree = new int[n + 1];
        }

        public void update(int index, int n, int value) {
            index++;
            while (index <= n) {
                BITree[index] += value;
                index += index & -index;
            }
        }

        public long getSum(int i) {
            i++;
            long sum = 0;
            while (i > 0) {
                sum += BITree[i];
                i -= (i & -i);
            }
            return sum;
        }
    }
}
