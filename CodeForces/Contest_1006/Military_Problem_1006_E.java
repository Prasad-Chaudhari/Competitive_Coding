
/**
 * Date: 16 Jul, 2018
 * Link: http://codeforces.com/contest/1006/problem/E
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

public class Military_Problem_1006_E {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int q = in.ni();
        int from[] = new int[n - 1];
        int to[] = new int[n - 1];
        int count[] = new int[n + 1];
        for (int i = 0; i < n - 1; i++) {
            from[i] = i + 2;
            to[i] = in.ni();
            count[to[i]]++;
        }
        int adja[][] = new int[n + 1][];
        for (int i = 0; i <= n; i++) {
            adja[i] = new int[count[i]];
        }
        for (int i = 0; i < n - 1; i++) {
            adja[to[i]][--count[to[i]]] = from[i];
        }
        for (int i = 0; i <= n; i++) {
            Data d[] = new Data[adja[i].length];
            for (int j = 0; j < adja[i].length; j++) {
                d[j] = new Data(adja[i][j], 0);
            }
            Arrays.sort(d);
            for (int j = 0; j < adja[i].length; j++) {
                adja[i][j] = d[j].a;
            }
        }
        int subtree[] = new int[n + 1];
        int firstapp[] = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            firstapp[i] = -1;
            subtree[i] = 1;
        }
        int dfs[] = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(1);
        dfs(st, dfs, subtree, firstapp, adja);
        while (q-- > 0) {
            int u = in.ni();
            int k = in.ni();
            if (k > subtree[u]) {
                System.out.println(-1);
            } else {
                System.out.println(dfs[firstapp[u] + k - 1]);
            }
        }

    }
    static int index = 0;

    private static void dfs(Stack<Integer> st, int[] dfs, int subtree[], int firstapp[], int adja[][]) {
        while (!st.isEmpty()) {
            int root = st.pop();
            dfs[index] = root;
            firstapp[root] = index;
            index++;
            for (int i : adja[root]) {
                st.push(i);
                dfs(st, dfs, subtree, firstapp, adja);
                subtree[root] += subtree[i];
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

        int a;
        int b;

        public Data(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Data d) {
            if (Integer.compare(a, d.a) == 0) {
                return Integer.compare(b, d.b);
            }
            return Integer.compare(a, d.a);
        }
    }
}
