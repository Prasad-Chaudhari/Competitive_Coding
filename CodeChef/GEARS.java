
/**
 * Date: 20 Jul, 2018
 * Link: https://www.codechef.com/JULY18B/problems/GEARS
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
import java.util.ArrayList;
import java.util.LinkedList;

public class GEARS {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int m = in.ni();
        long a[] = in.gla(n);
        UnionFind uf = new UnionFind(n);
        int queries[][] = new int[m][4];
        for (int i = 0; i < m; i++) {
            queries[i][0] = in.ni();
            queries[i][1] = in.ni();
            queries[i][2] = in.ni();
            if (queries[i][0] == 3) {
                queries[i][3] = in.ni();
            }
        }
        GCD gcd = new GCD();
        for (int i = 0; i < m; i++) {
            if (queries[i][0] == 1) {
                int x = queries[i][1];
                a[x - 1] = queries[i][2];
            }
            if (queries[i][0] == 2) {
                int x = queries[i][1];
                int y = queries[i][2];
                if (uf.find(x) == uf.find(y)) {
                    if (uf.color(x) == uf.color(y)) {
                        uf.blockThis(x);
                        uf.blockThis(y);
                    }
                } else {
                    uf.union(x, y);
                }
            }
            if (queries[i][0] == 3) {
                int x = queries[i][1];
                int y = queries[i][2];
                long v = queries[i][3];
                if (x == y) {
                    System.out.println(v + "/1");
                } else if (uf.find(x) != uf.find(y)) {
                    System.out.println("0");
                } else {
                    if (uf.isBlocked(x)) {
                        System.out.println("0");
                    } else {
                        if (uf.color(x) != uf.color(y)) {
                            System.out.print("-");
                        }
                        long ans = 1l * v * a[x - 1];
                        long d = gcd.calc_gcd(ans, a[y - 1]);
                        long num = ans / d;
                        long den = a[y - 1] / d;
                        System.out.println(num + "/" + den);
                    }
                }
            }
        }
    }

    static class UnionFind {

        ArrayList<LinkedList<Integer>> ds = new ArrayList<>();
        int parent[];
        boolean blocked[];
        int size[];
        boolean color[];

        public UnionFind(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            color = new boolean[n + 1];
            blocked = new boolean[n + 1];
            for (int i = 0; i <= n; i++) {
                size[i] = 1;
                parent[i] = i;
                LinkedList<Integer> l = new LinkedList<>();
                l.add(i);
                ds.add(l);
            }
        }

        public int find(int i) {
            if (parent[i] != i) {
                return find(parent[i]);
            } else {
                return i;
            }
        }

        public void blockThis(int i) {
            blocked[find(i)] = true;
        }

        public boolean isBlocked(int i) {
            return blocked[find(i)];
        }

        public boolean color(int i) {
            return color[i];
        }

        public void union(int i, int j) {
            int pi = find(i);
            int pj = find(j);
            if (pi != pj) {
                if (size[pi] > size[pj]) {
                    pi ^= pj;
                    pj ^= pi;
                    pi ^= pj;
                }
                if (color[i] == color[j]) {
                    for (int k : ds.get(pi)) {
                        color[k] ^= true;
                    }
                }
                for (int k : ds.get(pi)) {
                    ds.get(pj).add(k);
                    parent[k] = pj;
                }
                size[pj] += size[pi];
                if (blocked[pi] || blocked[pj]) {
                    blocked[pj] = true;
                }
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
