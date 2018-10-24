
/**
 * Date: 19 Oct, 2018
 * Link: https://www.codechef.com/SNCK1A19/problems/ARRGRAPH
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

public class ARRGRAPH {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out, "UTF8"));
        int t = in.ni();
        GCD g = new GCD();
        while (t-- > 0) {
            UnionFind uf = new UnionFind(51);
            int n = in.ni();
            int a[] = in.gia(n);
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (uf.find(a[i]) != uf.find(a[j])) {
                        if (g.calc_gcd(a[i], a[j]) == 1) {
                            uf.union(a[i], a[j]);
                        }
                    }
                }
            }
            int count = 0;
            int com[] = new int[51];
            for (int i = 0; i < n; i++) {
                if (com[uf.find(a[i])] == 0) {
                    count++;
                    com[uf.find(a[i])]++;
                }
            }
            int noof47 = 0;
            for (int i : a) {
                if (i == 47) {
                    noof47++;
                }
            }
            int count2 = 1;
            for (int i = 1; i < n; i++) {
                if (a[i - 1] == a[i]) {
                    count2++;
                }
            }
            if (n == 1) {
                log.write("0\n" + a[0] + "\n");
            } else if (count == 1) {
                if (count2 == n) {
                    if (a[0] == 47) {
                        a[0] = 46;
                    } else {
                        a[0] = 47;
                    }
                    log.write("1\n");
                    for (int i : a) {
                        log.write(i + " ");
                    }
                    log.write("\n");
                } else {
                    log.write("0\n");
                    for (int i : a) {
                        log.write(i + " ");
                    }
                    log.write("\n");
                }
            } else {
                if (noof47 == 0) {
                    a[0] = 47;
                    log.write("1\n");
                    for (int i : a) {
                        log.write(i + " ");
                    }
                    log.write("\n");
                } else {
                    a[0] = 2;
                    log.write("1\n");
                    for (int i : a) {
                        log.write(i + " ");
                    }
                    log.write("\n");
                }
            }
        }
        log.flush();
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

    static class UnionFind {

        int parent[], size[], rank[];
        int n;

        public void reset() {
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public UnionFind(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int p) {
            if (parent[p] == p) {
                return p;
            } else {
                return find(parent[p]);
            }
        }

        public void union(int a, int b) {
            if (find(a) == find(b)) {
                return;
            }
            a = find(a);
            b = find(b);
            if (rank[a] >= rank[b]) {
                parent[b] = a;
                rank[a]++;
            } else if (rank[a] > rank[b]) {
                parent[b] = a;
            } else {
                parent[a] = b;
            }
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
