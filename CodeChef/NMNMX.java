
/**
 * Date: 11 Jul, 2018
 * Link: https://www.codechef.com/JULY18B/problems/NMNMX
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

public class NMNMX {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int t = in.ni();
        long m = 1000000007;
        Factorial f = new Factorial(5000, m - 1);
        while (t-- > 0) {
            int n = in.ni();
            int k = in.ni();
            Data d[] = new Data[n];
            for (int i = 0; i < n; i++) {
                d[i] = new Data(0, in.ni(), 0);
            }
            Arrays.sort(d);
            long ans = 1;
            for (int i = 0; i < n; i++) {
                long x = f.nCr(n - 1, k - 1) - f.nCr(n - i - 1, k - 1) - f.nCr(i - 1 + 1, k - 1);
                x = (m - 1 + x % (m - 1)) % (m - 1);
                ans = (ans * pow(d[i].b, x, m)) % m;
            }
            System.out.println(ans % m);
        }
    }

    private static long pow(long a, long n, long m) {
        if (n == 1) {
            return a;
        }
        if (n == 0) {
            return 1;
        }
        a = a % m;
        long p = pow(a, n / 2, m);
        p = (p * p) % m;
        if ((n & 1) == 1) {
            return (p * a) % m;
        }
        return p;
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

    static class Factorial {

        long nCr[][] = new long[5001][5001];

        public Factorial(int n, long m) {
            nCr[0][0] = 1;
            for (int i = 1; i <= n; i++) {
                nCr[i][0] = 1;
                for (int j = 1; j <= i; j++) {
                    nCr[i][j] = (nCr[i - 1][j] + nCr[i - 1][j - 1]) % m;
                }
            }
        }

        public long nCr(int n, int r) {
            if (n <= 0) {
                return 0;
            }
            if (n < r) {
                return 0;
            }
            return nCr[n][r];
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
        public int compareTo(Data d) {
            if (Integer.compare(b, d.b) == 0) {
                return Integer.compare(c, d.c);
            } else {
                return Integer.compare(b, d.b);
            }
        }
    }
}
