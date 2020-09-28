
/**
 * Date: 07 Aug, 2020
 * Link: https://www.codechef.com/AUG20A/problems/CHEFCOMP
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

class CHEFCOMP {

    public static void main(String[] args) throws IOException {
        FastIO in = new FastIO(args);
        int t = in.ni();
        int max = 200000;
        int[] p = new int[max + 1], a = new int[max + 1], b = new int[max + 1];
        int permutation_pos[] = new int[max + 1];
        int represent_by[] = new int[max + 1];
        int count[] = new int[max + 1];
        int from[] = new int[max - 1];
        int to[] = new int[max - 1];
        int new_edges_index = 0;
        int new_tree[][] = new int[max + 1][];
        boolean visited[] = new boolean[max + 1];
        summa = new long[max + 1];
        day = new int[max + 1];
        int ans[] = new int[max + 1];
        int i = 0, index = 0, root;
        Stack<Integer> st = new Stack<>();
        while (t-- > 0) {
            int n = in.ni();
            int adja[][] = in.gg(n, n - 1);
            i = 1;
            while (i <= n)
                p[++i - 1] = in.ni();
            i = 1;
            while (i <= n)
                a[++i - 1] = in.ni();
            i = 1;
            while (i <= n)
                b[++i - 1] = in.ni();
            i = 1;
            while (i <= n) {
                permutation_pos[p[i]] = i;
                ++i;
            }
            new_edges_index = 0;
            FastUnionFind uf = new FastUnionFind(n);
            i = 1;
            while (i <= n) {
                represent_by[uf.find(i)] = i;
                ++i;
            }
            Arrays.fill(count, 0, n + 1, 0);
            Arrays.fill(from, 0, n - 1, 0);
            Arrays.fill(to, 0, n - 1, 0);
            i = n;
            while (i >= 1) {
                int v = p[--i + 1];
                for (int u : adja[v]) {
                    if (permutation_pos[v] < permutation_pos[u]) {
                        from[new_edges_index] = represent_by[uf.find(u)];
                        to[new_edges_index] = v;
                        count[to[new_edges_index]]++;
                        count[from[new_edges_index]]++;
                        uf.union(v, u);
                        new_edges_index++;
                    }
                }
                represent_by[uf.find(v)] = v;
            }
            i = 1;
            while (i <= n)
                new_tree[i] = new int[count[++i - 1]];
            i = 0;
            while (i < n - 1) {
                new_tree[from[i]][--count[from[i]]] = to[i];
                new_tree[to[i]][--count[to[i]]] = from[i];
                ++i;
            }
            st.add(p[1]);
            Arrays.fill(visited, 0, n + 1, false);
            index = 0;
            while (!st.isEmpty()) {
                root = st.peek();
                if (visited[root]) {
                    --index;
                    st.pop();
                } else {
                    visited[root] = true;
                    summa[index] = (index == 0 ? 0 : summa[index - 1]) + a[root];
                    day[index] = permutation_pos[root];
                    ans[root] = ans(index, b[root]);
                    ++index;
                    for (int j : new_tree[root])
                        if (!visited[j])
                            st.push(j);
                }
            }
            i = 1;
            while (i <= n)
                in.println(ans[++i - 1]);
        }
        in.bw.flush();
    }

    static long summa[];
    static int[] day;

    private static int ans(int r, int b) {
        int l = 0;
        while (r - l > 1) {
            int mid = (l + r) >> 1;
            if (summa[mid] < b) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        if (b <= summa[l]) {
            return day[l];
        } else if (b <= summa[r]) {
            return day[r];
        } else {
            return -1;
        }
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
