
/**
 * Date: 1 Sep, 2018
 * Link: http://codeforces.com/contest/1028/problem/F
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
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

public class Make_Symmetrical_1028_F {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int q = in.ni();
        Map<Long, LinkedList<Long>> cir = new Hashtable<>();
        Map<Long, Long> midline = new Hashtable<>();
        Map<Long, Long> online = new Hashtable<>();
        long mod = 10000000;
        GCD gcd = new GCD();
        long gcdd;
        int n = 0;
        while (q-- > 0) {
            int t = in.ni();
            long x = in.ni();
            long y = in.ni();
            Long r2 = (long) x * x + (long) y * y;
            switch (t) {
                case 1:
                    if (!cir.containsKey(r2)) {
                        LinkedList<Long> l = new LinkedList<>();
                        l.add(x * mod + y);
                        cir.put(r2, l);
                    } else {
                        for (long p : cir.get(r2)) {
                            long px = p / mod + x;
                            long py = p % mod + y;
                            gcdd = gcd.calc_gcd(px, py);
                            px /= gcdd;
                            py /= gcdd;
                            if (midline.containsKey((px) * mod + py)) {
                                midline.put((px) * mod + py, midline.get((px) * mod + py) + 2);
                            } else {
                                midline.put((px) * mod + py, 2l);
                            }
                        }
                        cir.get(r2).add(x * mod + y);
                    }
                    gcdd = gcd.calc_gcd(x, y);
                    x /= gcdd;
                    y /= gcdd;
                    if (online.containsKey(x * mod + y)) {
                        online.put(x * mod + y, online.get(x * mod + y) + 1);
                    } else {
                        online.put(x * mod + y, 1l);
                    }
                    n++;
                    break;
                case 2:
                    int size = cir.get(r2).size();
                    for (int i = 0; i < size; i++) {
                        long p = cir.get(r2).removeFirst();
                        long px = p / mod;
                        long py = p % mod;
                        if (x != px || y != py) {
                            px = px + x;
                            py = py + y;
                            gcdd = gcd.calc_gcd(px, py);
                            px /= gcdd;
                            py /= gcdd;
                            midline.put(px * mod + py, midline.get(px * mod + py) - 2);
                            cir.get(r2).add(p);
                        }
                    }
                    gcdd = gcd.calc_gcd(x, y);
                    x /= gcdd;
                    y /= gcdd;
                    online.put(x * mod + y, online.get(x * mod + y) - 1);
                    n--;
                    break;
                default:
                    gcdd = gcd.calc_gcd(x, y);
                    x /= gcdd;
                    y /= gcdd;
                    long midl = 0;
                    if (midline.containsKey(x * mod + y)) {
                        midl = midline.get(x * mod + y);
                    }
                    long onli = 0;
                    if (online.containsKey(x * mod + y)) {
                        onli = online.get(x * mod + y);
                    }
                    System.out.println(n - midl - onli);
                    break;
            }
        }
    }

    static class GCD {

        public GCD() {
            // Sry Nothing to initalise
        }

        public long a_inv_b(long a, long m) {
            if (gcd(a, m) == 1) {
                long m0 = m;
                long y = 0, x = 1;
                if (m == 1) {
                    return 0;
                }
                while (a > 1) {
                    long q = a / m;
                    long t = m;
                    m = a % m;
                    a = t;
                    t = y;
                    y = x - q * y;
                    x = t;
                }
                if (x < 0) {
                    x += m0;
                }
                return x;
            } else {
                return -1;
            }
        }

        public int calc_gcd(int a, int b) {
            if (b > a) {
                a = a ^ b;
                b = a ^ b;
                a = a ^ b;
            }
            return (int) gcd(a, b);
        }

        public long calc_gcd(long a, long b) {
            if (b > a) {
                a = a ^ b;
                b = a ^ b;
                a = a ^ b;
            }
            return gcd(a, b);
        }

        private long gcd(long a, long b) {
            if (b == 0) {
                return a;
            }
            long gcd = gcd(b, a % b);
            return gcd;
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

    static class Data implements Comparable<Data> {

        int a, b;

        public Data(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Data o) {
            return Integer.compare(a, o.a);
        }
    }
}
