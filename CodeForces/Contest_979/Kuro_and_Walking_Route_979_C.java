
/**
 * Date: 20 Nov, 2018
 * Link: http://codeforces.com/problemset/problem/979/C
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
import java.util.Stack;

public class Kuro_and_Walking_Route_979_C {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int x = in.ni();
        int y = in.ni();
        int adja[][] = in.gg(n, n - 1);
        LCA_SparseTable lca = new LCA_SparseTable(n, x, adja);
        Stack<Integer> st = new Stack<>();
        int subtree[] = new int[n + 1];
        Arrays.fill(subtree, 1);
        boolean b[] = new boolean[n + 1];
        boolean som[] = new boolean[n + 1];
        som[y] = true;
        b[x] = true;
        st.push(x);
        while (!st.isEmpty()) {
            int root = st.peek();
            boolean p = true;
            for (int i : adja[root]) {
                p &= b[i];
            }
            if (p) {
                for (int i : adja[root]) {
                    if (i != lca.parent[root]) {
                        if (som[i]) {
                            som[root] = true;
                        }
                        if (!som[i]) {
                            subtree[root] += subtree[i];
                        }
                    }
                }
                st.pop();
            } else {
                for (int i : adja[root]) {
                    if (!b[i]) {
                        b[i] = true;
                        st.push(i);
                    }
                }
            }
        }
        System.out.println(1l * n * (n - 1) - 1l * subtree[y] * subtree[x]);
    }

    static class LCA_SparseTable {

        private final int parent[], level[], sptable[][];

        public LCA_SparseTable(int n, int top, int adja[][]) {

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
