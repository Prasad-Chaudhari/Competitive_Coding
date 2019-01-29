
/**
 * Date: 24 Nov, 2018
 * Link: https://codeforces.com/contest/1080/problem/C
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

public class Masha_and_two_friends_1080_C {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int t = in.ni();
        while (t-- > 0) {
            long n = in.ni();
            long m = in.ni();
            long x1 = in.ni();
            long y1 = in.ni();
            long x2 = in.ni();
            long y2 = in.ni();
            long x3 = in.ni();
            long y3 = in.ni();
            long x4 = in.ni();
            long y4 = in.ni();
            x1--;
            y1--;
            x3--;
            y3--;
            Data ini = black_white(m, n);
            long inderx = Math.min(x2, x4) - Math.max(x1, x3);
            long indery = Math.min(y2, y4) - Math.max(y1, y3);
            Data wp = black_white(x2 - x1, y2 - y1);
            if (x1 % 2 != y1 % 2) {
                wp.a ^= wp.b;
                wp.b ^= wp.a;
                wp.a ^= wp.b;
            }
            ini.a += wp.b;
            ini.b -= wp.b;
            Data bp = black_white(x4 - x3, y4 - y3);
            if (x3 % 2 != y3 % 2) {
                bp.a ^= bp.b;
                bp.b ^= bp.a;
                bp.a ^= bp.b;
            }
            ini.a -= bp.a;
            ini.b += bp.a;
            if (inderx > 0 && indery > 0) {
                Data inter = black_white(inderx, indery);
                if ((Math.max(x1, x3)) % 2 == (Math.max(y1, y3)) % 2) {
                    inter.a ^= inter.b;
                    inter.b ^= inter.a;
                    inter.a ^= inter.b;
                }
                ini.a -= inter.a;
                ini.b += inter.a;
            }
            System.out.println(ini.a + " " + ini.b);
        }
    }

    private static Data black_white(long n, long m) {
        if (n == 0 || m == 0) {
            return new Data(-1, -1);
        }
        long w = 0;
        long b = 0;
        w = (n / 2) * m;
        if (n % 2 == 1) {
            w += (m / 2) + m % 2;
        }
        b = n * m - w;
        return new Data(w, b);
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
        }

        public void println(String s) throws IOException {
            bw.write(s);
            bw.newLine();
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

        long a, b;

        public Data(long a, long b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Data o) {
            if (a == o.a) {
                return Long.compare(b, o.b);
            }
            return Long.compare(a, o.a);
        }

//        public static void sort(int a[]) {
//            Data d[] = new Data[a.length];
//            for (int i = 0; i < a.length; i++) {
//                d[i] = new Data(a[i], 0);
//            }
//            Arrays.sort(d);
//            for (int i = 0; i < a.length; i++) {
//                a[i] = d[i].a;
//            }
//        }
    }
}
