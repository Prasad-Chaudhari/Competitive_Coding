
/**
 * Date: 31 May, 2018
 * Link: http://codeforces.com/contest/980/problem/E
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class The_Number_Games_980_E {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int k = in.ni();
        int i, j;
        int pow[] = new int[20];
        for (i = 0; i < 20; i++) {
            pow[i] = (int) Math.pow(2, i);
        }
        int adja[][] = in.gg(n, n - 1);
        Stack<Integer> st = new Stack<>();
        int parent[] = new int[n + 1];
        byte visited[] = new byte[n + 1];
        int sptable[][] = new int[n + 1][22];
        st.push(n);
        visited[n] = 1;
        for (i = 0; i < n + 1; i++) {
            parent[i] = -1;
        }
        for (int[] arr : sptable) {
            for (j = 0; j < 22; j++) {
                arr[j] = -1;
            }
        }
        while (!st.isEmpty()) {
            int root = st.pop();
            for (int sib : adja[root]) {
                if (visited[sib] == 0) {
                    parent[sib] = root;
                    st.push(sib);
                    visited[sib] = 1;
                }
            }
            sptable[root][0] = parent[root];
            j = 0;
            while (sptable[root][j] != -1) {
                sptable[root][j + 1] = sptable[sptable[root][j]][j];
                j++;
            }
        }
        for (i = 0; i < n; i++) {
            visited[i] = 0;
        }
        visited[n] = 1;
        k = n - k;
        k--;
        int node, depth, curr, curr2;
        for (i = n - 1; k != 0 && i > 0; i--) {
            if (visited[i] == 1) {
                continue;
            }
            depth = 0;
            curr = i;
            while (curr != -1 && visited[curr] == 0) {
                for (j = 0; j < 22; j++) {
                    node = sptable[curr][j];
                    if (node != -1) {
                        if (visited[node] == 1) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (j == 0) {
                    depth++;
                    break;
                }
                depth += pow[j - 1];
                curr = sptable[curr][j - 1];
            }
            if (k != 0 && depth <= k) {
                curr2 = i;
                while (visited[curr2] == 0) {
                    visited[curr2] = 1;
                    curr2 = parent[curr2];
                }
                k -= depth;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (i = 1; i <= n; i++) {
            if (visited[i] == 0) {
                sb.append(i);
                sb.append('\n');
            }
        }
        System.out.println(sb.toString());
    }

    static class FastIO {

        private final BufferedReader br;
        private String s[];
        private int index;

        public FastIO() throws IOException {
            br = new BufferedReader(new InputStreamReader(System.in));
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
