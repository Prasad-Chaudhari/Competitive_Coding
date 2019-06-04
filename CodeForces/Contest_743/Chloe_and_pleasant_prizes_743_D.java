
/**
 * Date: 29 Dec, 2018
 * Link: https://codeforces.com/problemset/problem/743/D
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
import java.util.PriorityQueue;
import java.util.Stack;
import javafx.scene.layout.Priority;

public class Chloe_and_pleasant_prizes_743_D {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int a[] = in.gia(n);
        int adja[][] = in.gg(n, n - 1);
        int freq[] = new int[n];
        for (int i = 1; i <= n; i++) {
            freq[adja[i].length]++;
        }
        PriorityQueue<Data> pq = new PriorityQueue<>((d1, d2) -> {
            return Long.compare(d1.a, d2.a);
        });
        long subtree[] = new long[n + 1];
        boolean b[] = new boolean[n + 1];
        Stack<Integer> st = new Stack<>();
        st.push(1);
        b[1] = true;
        long max = Long.MIN_VALUE;
        LCA_SparseTable lca = new LCA_SparseTable(n, 1, adja);
        while (!st.isEmpty()) {
            int root = st.peek();
            boolean p = true;
            for (int i : adja[root]) {
                p &= b[i];
            }
            if (p) {
                st.pop();
                subtree[root] = a[root - 1];
                for (int i : adja[root]) {
                    subtree[root] += subtree[i];
                }
                pq.add(new Data(-subtree[root], root));
                Data max_data = pq.remove();
                while (!pq.isEmpty()) {
                    Data max_2 = pq.remove();
                    int lca_x_y = lca.lca((int) max_data.b, (int) max_2.b);
                    if (lca_x_y != max_data.b && lca_x_y != max_2.b) {
                        max = Math.max(max, -(max_data.a + max_2.a));
                        pq.add(max_2);
                        break;
                    }
                }
                pq.add(max_data);
            } else {
                for (int i : adja[root]) {
                    if (!b[i]) {
                        b[i] = true;
                        st.push(i);
                    }
                }
            }
        }
        if (max == Long.MIN_VALUE) {
            System.out.println("Impossible");
        } else {
            System.out.println(max);
        }
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

    }
}
