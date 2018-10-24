
/**
 * Date: 21 Oct, 2018
 * Link: https://www.codechef.com/SNCK1A19/problems/CHEFADD
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

public class CHEFADD {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int t = in.ni();
        while (t-- > 0) {
            int a = in.ni();
            int b = in.ni();
            int c = in.ni();
            String sc = "";
            int oa = 0;
            int ob = 0;
            int temp = a;
            while (temp > 0) {
                oa += temp % 2;
                temp /= 2;
            }
            temp = b;
            while (temp > 0) {
                ob += temp % 2;
                temp /= 2;
            }
            temp = c;
            while (temp > 0) {
                sc += temp % 2;
                temp /= 2;
            }
            int dp1[][] = new int[oa + 1][ob + 1];
            int dp2[][] = new int[oa + 1][ob + 1];
            if (sc.charAt(0) == '0') {
                dp1[0][0] = 1;
                dp2[1][1] = 1;
            } else {
                dp1[1][0] = 1;
                dp1[0][1] = 1;
            }
            int z1[][] = new int[oa + 1][ob + 1];
            int z2[][] = new int[oa + 1][ob + 1];
            for (int k = 1; k < sc.length(); k++) {
                for (int i = 0; i <= oa; i++) {
                    for (int j = 0; j <= ob; j++) {
                        z1[i][j] = dp1[i][j];
                        z2[i][j] = dp2[i][j];
                    }
                }
                for (int i = 0; i <= oa; i++) {
                    for (int j = 0; j <= ob; j++) {
                        if (sc.charAt(k) == '1') {
                            if (i > 0 && j > 0) {
                                dp1[i][j] = z1[i - 1][j] + z1[i][j - 1] + z2[i][j];
                                dp2[i][j] = z2[i - 1][j - 1];
                            } else if (i > 0) {
                                dp1[i][j] = z2[i][j] + z1[i - 1][j];
                                dp2[i][j] = 0;
                            } else if (j > 0) {
                                dp1[i][j] = z2[i][j] + z1[i][j - 1];
                                dp2[i][j] = 0;
                            } else {
                                dp2[i][j] = 0;
                                dp1[i][j] = z2[i][j];
                            }
                        } else {
                            if (i > 0 && j > 0) {
                                dp2[i][j] = z1[i - 1][j - 1] + z2[i][j - 1] + z2[i - 1][j];
                            } else if (i > 0) {
                                dp2[i][j] = z2[i - 1][j];
                            } else if (j > 0) {
                                dp2[i][j] = z2[i][j - 1];
                            } else {
                                dp2[i][j] = 0;
                            }
                        }
                    }
                }
            }
            System.out.println(dp1[oa][ob]);
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
