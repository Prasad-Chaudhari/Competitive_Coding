
/**
 * Date: 09 Nov, 2019
 * Link: https://codeforces.com/contest/1245/problem/D
 * 
 * @author Prasad Chaudhari
 * @linkedIn: https://www.linkedin.com/in/prasad-chaudhari-841655a6/
 * @git: https://github.com/Prasad-Chaudhari
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Shichikuji_and_Power_Grid_1245_D {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int x[] = new int[n];
        int y[] = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.ni();
            y[i] = in.ni();
        }
        int c[] = in.gia(n);
        int k[] = in.gia(n);
        Data d[] = new Data[(n * (n - 1)) / 2];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long k_ = k[i] + k[j];
                long dist = Math.abs(x[i] - x[j]) + Math.abs(y[i] - y[j]);
                d[index++] = new Data(i, j, k_ * dist);
            }
        }
        Arrays.sort(d);
        long cost_of_tree[] = new long[n];
        Data min_power_cost[] = new Data[n];
        for (int i = 0; i < n; i++) {
            min_power_cost[i] = new Data(i, c[i], 0);
        }
        FastUnionFind uf = new FastUnionFind(n);
        Set<Data> s = new HashSet<>();
        for (int i = 0; i < n; i++) {
            s.add(min_power_cost[i]);
        }
        LinkedList<Data> l = new LinkedList<>();
        for (int i = 0; i < d.length; i++) {
            Data dd = d[i];
            if (uf.find(dd.a) != uf.find(dd.b)) {
                long c1 = cost_of_tree[uf.find(dd.a)];
                long c2 = cost_of_tree[uf.find(dd.b)];
                Data d1 = min_power_cost[uf.find(dd.a)];
                Data d2 = min_power_cost[uf.find(dd.b)];
                long ans1 = c1 + c2 + d1.b + d2.b;
                long ans2 = c1 + c2 + dd.c + Math.min(d1.b, d2.b);
                if (ans2 < ans1) {
                    l.add(dd);
                    Data min = d1.b < d2.b ? d1 : d2;
                    if (min == d1) {
                        s.remove(d2);
                    } else {
                        s.remove(d1);
                    }
                    uf.union(dd.a, dd.b);
                    min_power_cost[uf.find(dd.a)] = min;
                    cost_of_tree[uf.find(dd.a)] = c1 + c2 + dd.c;
                }
            }
        }
        long min_cost = 0;
        for (Data dd : s) {
            min_cost += dd.b;
        }
        for (Data dd : l) {
            min_cost += dd.c;
        }
        System.out.println(min_cost);
        System.out.println(s.size());
        for (Data dd : s) {
            System.out.println(dd.a + 1);
        }
        System.out.println(l.size());
        for (Data dd : l) {
            System.out.println((dd.a + 1) + " " + (dd.b + 1));
        }
        in.bw.flush();
    }

    static public class FastUnionFind {

        int noOfComponents, n;
        private int[] parent, rank;

        public FastUnionFind(int n) {
            this.n = n;
            this.noOfComponents = n;
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
            a = find(a);
            b = find(b);
            if (a != b) {
                if (rank[a] > rank[b]) {
                    parent[b] = a;
                } else if (rank[b] > rank[a]) {
                    parent[a] = b;
                } else {
                    parent[b] = a;
                    rank[a]++;
                }
                noOfComponents--;
            }
        }
    }

    static class Data implements Comparable<Data> {

        int a, b;
        long c;

        public Data(int a, int b, long c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Data o) {
            if (c == o.c) {
                if (a == o.a) {
                    return Integer.compare(b, o.b);
                }
                return Integer.compare(a, o.a);
            }
            return Long.compare(c, o.c);
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

        public int[][][] gwtree(int n) throws IOException {
            int m = n - 1;
            int adja[][] = new int[n + 1][];
            int weight[][] = new int[n + 1][];
            int from[] = new int[m];
            int to[] = new int[m];
            int count[] = new int[n + 1];
            int cost[] = new int[n + 1];
            for (int i = 0; i < m; i++) {
                from[i] = i + 1;
                to[i] = ni();
                cost[i] = ni();
                count[from[i]]++;
                count[to[i]]++;
            }
            for (int i = 0; i <= n; i++) {
                adja[i] = new int[count[i]];
                weight[i] = new int[count[i]];
            }
            for (int i = 0; i < m; i++) {
                adja[from[i]][count[from[i]] - 1] = to[i];
                adja[to[i]][count[to[i]] - 1] = from[i];
                weight[from[i]][count[from[i]] - 1] = cost[i];
                weight[to[i]][count[to[i]] - 1] = cost[i];
                count[from[i]]--;
                count[to[i]]--;
            }
            return new int[][][] { adja, weight };
        }

        public int[][][] gwg(int n, int m) throws IOException {
            int adja[][] = new int[n + 1][];
            int weight[][] = new int[n + 1][];
            int from[] = new int[m];
            int to[] = new int[m];
            int count[] = new int[n + 1];
            int cost[] = new int[n + 1];
            for (int i = 0; i < m; i++) {
                from[i] = ni();
                to[i] = ni();
                cost[i] = ni();
                count[from[i]]++;
                count[to[i]]++;
            }
            for (int i = 0; i <= n; i++) {
                adja[i] = new int[count[i]];
                weight[i] = new int[count[i]];
            }
            for (int i = 0; i < m; i++) {
                adja[from[i]][count[from[i]] - 1] = to[i];
                adja[to[i]][count[to[i]] - 1] = from[i];
                weight[from[i]][count[from[i]] - 1] = cost[i];
                weight[to[i]][count[to[i]] - 1] = cost[i];
                count[from[i]]--;
                count[to[i]]--;
            }
            return new int[][][] { adja, weight };
        }

        public int[][] gtree(int n) throws IOException {
            int adja[][] = new int[n + 1][];
            int from[] = new int[n - 1];
            int to[] = new int[n - 1];
            int count[] = new int[n + 1];
            for (int i = 0; i < n - 1; i++) {
                from[i] = i + 1;
                to[i] = ni();
                count[from[i]]++;
                count[to[i]]++;
            }
            for (int i = 0; i <= n; i++) {
                adja[i] = new int[count[i]];
            }
            for (int i = 0; i < n - 1; i++) {
                adja[from[i]][--count[from[i]]] = to[i];
                adja[to[i]][--count[to[i]]] = from[i];
            }
            return adja;
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

        public void print(int s) throws IOException {
            bw.write(s + "");
        }

        public void println(int s) throws IOException {
            bw.write(s + "");
            bw.newLine();
        }

        public void print(long s) throws IOException {
            bw.write(s + "");
        }

        public void println(long s) throws IOException {
            bw.write(s + "");
            bw.newLine();
        }

        public void print(double s) throws IOException {
            bw.write(s + "");
        }

        public void println(double s) throws IOException {
            bw.write(s + "");
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
}
