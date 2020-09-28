
/**
 * Date: 06 Sep, 2020
 * Link: https://codeforces.com/contest/1405/problem/D
 * 
 * @author: Prasad Chaudhari
 * @linkedIn: https://www.linkedin.com/in/prasad-chaudhari-841655a6/
 * @git: https://github.com/Prasad-Chaudhari
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;

public class Tree_Tag_1405_D {

    public static void main(String[] args) throws IOException {
        FastIO in = new FastIO(args);
        int t = in.ni();
        while (t-- > 0) {
            int n = in.ni();
            int a = in.ni();
            int b = in.ni();
            int da = in.ni();
            int db = in.ni();
            int adja[][] = in.gg(n, n - 1);
            Stack<Integer> st = new Stack<>();
            boolean visited[] = new boolean[n + 1];
            LCA_SparseTable lca_sp = new LCA_SparseTable(n, 1, adja);
            int level[] = new int[n + 1];
            st.push(1);
            while (!st.isEmpty()) {
                int root = st.pop();
                visited[root] = true;
                for (int i : adja[root]) {
                    if (!visited[i]) {
                        level[i] = level[root] + 1;
                        st.push(i);
                    }
                }
            }
            int lca = lca_sp.lca(a, b);
            int dist_a_b = level[a] + level[b] - 2 * level[lca];
            int max = 0;
            int max_v = -1;
            for (int i = 0; i <= n; i++) {
                if (level[i] > max) {
                    max = level[i];
                    max_v = i;
                }
            }
            LCA_SparseTable lca_sp2 = new LCA_SparseTable(n, max_v, adja);
            int level2[] = lca_sp2.level;
            max = 0;
            max_v = -1;
            for (int i = 0; i <= n; i++) {
                if (level2[i] > max) {
                    max = level2[i];
                    max_v = i;
                }
            }
            int diameter = level2[max_v];
            if (dist_a_b <= da) {
                System.out.println("Alice");
            } else if (2 * da >= diameter) {
                System.out.println("Alice");
            } else if (2 * da < db) {
                System.out.println("Bob");
            } else {
                System.out.println("Alice");
            }
        }
        in.bw.flush();
    }

    static public class LCA_SparseTable {

        final int parent[], level[], sptable[][];

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

    static class FastIO {

        private final BufferedReader br;
        private final BufferedWriter bw;
        private String s[];
        private int index;

        public FastIO(String[] args) throws IOException {
            if (args.length > 1) {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(args[0]))));
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(args[1]))));
            } else {
                br = new BufferedReader(new InputStreamReader(System.in));
                bw = new BufferedWriter(new OutputStreamWriter(System.out, "UTF-8"));
            }
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
