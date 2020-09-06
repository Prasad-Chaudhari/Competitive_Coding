
/**
 * Date: 23 Sep, 2019
 * Link: http://codeforces.com/problemset/problem/342/E
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
import java.util.LinkedList;
import java.util.Stack;

public class Xenia_and_Tree_342_E {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int m = in.ni();
        int adja[][] = in.gg(n, n - 1);
        int d[] = new int[n + 1];
        Arrays.fill(d, n);
        boolean[] b = new boolean[n + 1];
        LinkedList<Integer> st = new LinkedList<>();
        LinkedList<Integer> changed = new LinkedList<>();
        b[1] = true;
        st.add(1);
        d[1] = 0;
        changed.add(1);
        while (!st.isEmpty()) {
            int root = st.remove();
            for (int i : adja[root]) {
                if (!b[i]) {
                    b[i] = true;
                    changed.add(i);
                    if (d[i] > d[root] + 1) {
                        d[i] = d[root] + 1;
                        st.add(i);
                    }
                }
            }
        }
        while (!changed.isEmpty()) {
            b[changed.remove()] = false;
        }
        int root_m = (int) Math.sqrt(m);
        LinkedList<Integer> l = new LinkedList<>();
        LCA_SparseTable lca_sp = new LCA_SparseTable(n, 1, adja);
        for (int i = 0; i < m;) {
            for (int j = 0; j < root_m && i < m; j++, i++) {
                if (in.ni() == 1) {
                    l.add(in.ni());
                } else {
                    int v = in.ni();
                    int min = d[v];
                    for (int u : l) {
                        int lca = lca_sp.lca(u, v);
                        int dist = lca_sp.level(u) + lca_sp.level(v) - 2 * lca_sp.level(lca);
                        min = Math.min(min, dist);
                    }
                    in.println(min);
                }
            }
            for (int j : l) {
                changed.add(j);
                b[j] = true;
                d[j] = 0;
            }
            while (!l.isEmpty()) {
                int root = l.remove();
                for (int j : adja[root]) {
                    if (!b[j]) {
                        b[j] = true;
                        changed.add(j);
                        if (d[j] > d[root] + 1) {
                            d[j] = d[root] + 1;
                            l.add(j);
                        }
                    }
                }
            }
            while (!changed.isEmpty()) {
                b[changed.remove()] = false;
            }
        }
        in.bw.flush();
    }

    static public class LCA_SparseTable {

        private final int n, parent[], level[], sptable[][];

        public LCA_SparseTable(int n, int top, int adja[][]) {

            this.n = n;
            parent = new int[n + 1];
            level = new int[n + 1];
            sptable = new int[n + 1][25];
            Stack<Integer> st = new Stack<>();
            boolean b[] = new boolean[n + 1];

            for (int i[] : sptable) {
                Arrays.fill(i, -1);
            }
            Arrays.fill(parent, -1);
            st.push(top);
            b[top] = true;

            while (!st.isEmpty()) {
                int root = st.pop();
                for (int i : adja[root]) {
                    if (!b[i]) {
                        b[i] = true;
                        parent[i] = root;
                        level[i] = level[root] + 1;
                        st.push(i);
                    }
                }
                int j = 0;
                sptable[root][j] = parent[root];
                while (sptable[root][j] != -1) {
                    sptable[root][j + 1] = sptable[sptable[root][j]][j];
                    j++;
                }
            }
        }

        public int[][] sptable() {
            return sptable;
        }

        public int level(int n) {
            return level[n];
        }

        public int lca(int u, int v) {
            if (level[u] < level[v]) {
                u ^= v;
                v ^= u;
                u ^= v;
            }
            int dist = level[u] - level[v];
            while (dist > 0) {
                int raised_to = (int) (Math.log(dist) / Math.log(2));
                u = sptable[u][raised_to];
                dist -= 1 << raised_to;
            }
            if (u == v) {
                return v;
            }
            for (int j = 24; j >= 0; j--) {
                if (sptable[u][j] != -1 && sptable[u][j] != sptable[v][j]) {
                    u = sptable[u][j];
                    v = sptable[v][j];
                }
            }
            return parent[u];
        }
    }

    static class FastIO {

        private final BufferedReader br;
        private final BufferedWriter bw;
        private String s[];
        private int index;
        private final StringBuilder sb;

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
            int first = -1;
            for (int i = 0; i < m; i++) {
                from[i] = ni();
                to[i] = ni();
                if (i == 0) {
                    first = from[i];
                }
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
            adja[0] = new int[] { first };
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

        public static void sort(int a[]) {
            Data d[] = new Data[a.length];
            for (int i = 0; i < a.length; i++) {
                d[i] = new Data(a[i], 0);
            }
            Arrays.sort(d);
            for (int i = 0; i < a.length; i++) {
                a[i] = d[i].a;
            }
        }
    }
}
