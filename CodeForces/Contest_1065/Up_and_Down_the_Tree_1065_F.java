
/**
 * Date: 14 Oct, 2018
 * Link: http://codeforces.com/contest/1065/problem/F
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

public class Up_and_Down_the_Tree_1065_F {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int k = in.ni();
        int from[] = new int[n - 1];
        int to[] = new int[n - 1];
        int count[] = new int[n + 1];
        int i = 0;
        while (i < n - 1) {
            from[i] = i + 2;
            to[i] = in.ni();
            ++count[from[i]];
            ++count[to[i]];
            ++i;
        }
        adja = new int[n + 1][];
        i = 0;
        while (i <= n) {
            adja[i] = new int[count[i]];
            ++i;
        }
        i = 0;
        while (i < n - 1) {
            adja[from[i]][--count[from[i]]] = to[i];
            adja[to[i]][--count[to[i]]] = from[i];
            ++i;
        }
        LinkedList<Integer> leaves = new LinkedList<>();
        i = 2;
        while (i <= n) {
            if (adja[i].length == 1) {
                leaves.add(i);
            }
            ++i;
        }
        st = new Stack<>();
        int parent[] = new int[n + 1];
        int true_parents[] = new int[n + 1];
        vi = new boolean[n + 1];
        int level[] = new int[n + 1];
        st.push(1);
        parent[1] = -1;
        true_parents[1] = -1;
        vi[1] = true;
        while (!st.isEmpty()) {
            int root = st.pop();
            for (int j : adja[root]) {
                if (!vi[j]) {
                    vi[j] = true;
                    parent[j] = root;
                    true_parents[j] = root;
                    level[j] = level[root] + 1;
                    st.push(j);
                }
            }
        }
        uf = new UnionFind(n + 2);
        Arrays.fill(vi, false);
        for (int l : leaves) {
            int curr = l;
            while (curr != -1 && level[l] - level[curr] <= k) {
                uf.union(l, curr);
                st.push(curr);
                curr = parent[curr];
            }
            while (!st.isEmpty()) {
                parent[st.pop()] = curr;
            }
        }
        noofleaves = new int[n + 1];
        for (int j : leaves) {
            noofleaves[uf.find(j)]++;
        }
        dp = new int[n + 1];
        st.push(1);
        vi[1] = true;
        Data d[] = new Data[n];
        i = 1;
        while (i <= n) {
            d[i - 1] = new Data(level[i], i, true_parents[i]);
            ++i;
        }
        Arrays.sort(d);
        int set[] = new int[n + 1];
        i = 1;
        while (i <= n) {
            set[i] = uf.find(i);
            ++i;
        }
        i = n - 1;
        while (i >= 1) {
            if (set[d[i].b] != set[d[i].c]) {
                dp[set[d[i].c]] = Math.max(dp[set[d[i].c]], noofleaves[set[d[i].b]] + dp[set[d[i].b]]);
            }
            --i;
        }
        System.out.println(dp[uf.find(1)] + noofleaves[uf.find(1)]);
    }

    static Stack<Integer> st;
    static int adja[][];
    static int dp[];
    static boolean vi[];
    static UnionFind uf;
    static int[] noofleaves;

    static class UnionFind {

        int parent[], size[], rank[];
        int n;

        public UnionFind(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 1; i <= n; i++) {
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

        int a, b, c;

        public Data(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
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
