
/**
 * Date: 8 Sep, 2018
 * Link: http://codeforces.com/contest/1036/problem/E
 * Comment : Thanks to UselessChallenger for implementation insights
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
import java.util.Map;

public class Covered_Points_1036_E {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        Map<Long, Integer> map = new Hashtable<>();
        int i = 0;
        Point[][] ps = new Point[n][];
        while (i < n) {
            ps[i] = new Point[]{
                new Point(in.ni(), in.ni()),
                new Point(in.ni(), in.ni())
            };
            for (int j = 0; j < i; j++) {
                Point p = intersection(ps[i][0], ps[i][1], ps[j][0], ps[j][1]);
                if (p != null) {
                    if (map.containsKey(p.hash())) {
                        map.put(p.hash(), map.get(p.hash()) + 1);
                    } else {
                        map.put(p.hash(), 1);
                    }
                }
            }
            i++;
        }
        i = 0;
        long ans = 0;
        GCD gcd = new GCD();
        while (i < n) {
            ans += gcd.calc_gcd(Math.abs(ps[i][0].x - ps[i][1].x), Math.abs(ps[i][0].y - ps[i][1].y)) + 1;
            i++;
        }
        int summa[] = new int[1000000];
        int sum = 0;
        for (i = 0; i <= 1000; i++) {
            sum += i;
            summa[sum] = i;
        }
        for (Long p : map.keySet()) {
            ans -= summa[map.get(p)];
        }
        System.out.println(ans);
    }

    private static Point intersection(Point p1, Point p2, Point p3, Point p4) {
        long a1 = (p2.y - p1.y);
        long b1 = (p1.x - p2.x);
        long c1 = a1 * p1.x + b1 * p1.y;
        long a2 = (p4.y - p3.y);
        long b2 = (p3.x - p4.x);
        long c2 = a2 * p3.x + b2 * p3.y;
        long d = a1 * b2 - a2 * b1;
        if (d != 0) {
            long x = (c1 * b2 - c2 * b1);
            long y = -(c1 * a2 - c2 * a1);
            if (Math.abs(x) % Math.abs(d) == 0 && Math.abs(y) % Math.abs(d) == 0) {
                x /= d;
                y /= d;
                if (x >= Math.min(p1.x, p2.x) && x <= Math.max(p1.x, p2.x) && x >= Math.min(p3.x, p4.x) && x <= Math.max(p3.x, p4.x)) {
                    if (y >= Math.min(p1.y, p2.y) && y <= Math.max(p1.y, p2.y) && y >= Math.min(p3.y, p4.y) && y <= Math.max(p3.y, p4.y)) {
                        return new Point(x, y);
                    }
                }
            }
        }
        return null;
    }

    static class Point {

        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public long hash() {
            return (x + 1000000) * 30000000 + (y + 1000000);
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
