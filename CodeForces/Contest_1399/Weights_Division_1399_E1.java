
/**
 * Date: 06 Aug, 2020
 * Link: https://codeforces.com/contest/1399/problem/E
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
import java.util.PriorityQueue;
import java.util.Stack;

public class Weights_Division_1399_E1 {

    public static void main(String[] args) throws IOException {
        FastIO in = new FastIO(args);
        int t = in.ni();
        while (t-- > 0) {
            int n = in.ni();
            long s = in.nl();
            int c[] = new int[n - 1];
            int from[] = new int[n - 1];
            int to[] = new int[n - 1];
            int w[] = new int[n - 1];
            int count[] = new int[n + 1];
            for (int i = 0; i < n - 1; i++) {
                from[i] = in.ni();
                to[i] = in.ni();
                w[i] = in.ni();
                c[i] = 1;
                count[from[i]]++;
                count[to[i]]++;
            }
            int adja[][] = new int[n + 1][];
            int weight[][] = new int[n + 1][];
            int cost[][] = new int[n + 1][];
            for (int i = 0; i <= n; i++) {
                adja[i] = new int[count[i]];
                weight[i] = new int[count[i]];
                cost[i] = new int[count[i]];
            }
            for (int i = 0; i < n - 1; i++) {
                adja[from[i]][count[from[i]] - 1] = to[i];
                weight[from[i]][count[from[i]] - 1] = w[i];
                cost[from[i]][count[from[i]] - 1] = c[i];

                adja[to[i]][count[to[i]] - 1] = from[i];
                weight[to[i]][count[to[i]] - 1] = w[i];
                cost[to[i]][count[to[i]] - 1] = c[i];
                count[from[i]]--;
                count[to[i]]--;
            }
            long summation[] = new long[n + 1];
            int no_of_leaves[] = new int[n + 1];
            for (int i = 2; i <= n; i++) {
                no_of_leaves[i] = adja[i].length == 1 ? 1 : 0;
            }
            int parent[] = new int[n + 1];
            boolean visited[] = new boolean[n + 1];
            Stack<Integer> st = new Stack<>();
            st.push(1);
            PriorityQueue<Data> pq = new PriorityQueue<>();
            while (!st.isEmpty()) {
                int root = st.peek();
                visited[root] = true;
                boolean p = true;
                for (int i : adja[root]) {
                    p &= visited[i];
                }
                if (p) {
                    for (int i = 0; i < adja[root].length; i++) {
                        int v = adja[root][i];
                        if (v != parent[root]) {
                            Data d = new Data(no_of_leaves[v], weight[root][i], cost[root][i]);
                            pq.add(d);
                            no_of_leaves[root] += no_of_leaves[v];
                        }
                    }
                    st.pop();
                } else {
                    for (int i = 0; i < adja[root].length; i++) {
                        int v = adja[root][i];
                        if (!visited[v]) {
                            st.push(v);
                            parent[v] = root;
                            summation[v] = summation[root] + weight[root][i];
                        }
                    }
                }
            }
            long curr_s = 0;
            for (int i = 2; i <= n; i++) {
                if (adja[i].length == 1) {
                    curr_s += summation[i];
                }
            }
            int ans = 0;
            while (curr_s > s) {
                Data d = pq.remove();
                curr_s -= 1l * d.a * d.b;
                d.b /= 2;
                curr_s += 1l * d.a * d.b;
                pq.add(d);
                ans++;
            }
            System.out.println(ans);
        }
        in.bw.flush();
    }

    static class Data implements Comparable<Data> {

        int a, b, c;

        public Data(int a, int b, int c) {
            this.a = a;
            this.c = c;
            this.b = b;
        }

        public long getScore() {
            return 1l * a * (b - (b / 2));
        }

        @Override
        public int compareTo(Data o) {
            return Long.compare(-getScore(), -o.getScore());
        }

        // public static void sort(int a[]) {
        // Data d[] = new Data[a.length];
        // for (int i = 0; i < a.length; i++) {
        // d[i] = new Data(a[i], 0);
        // }
        // Arrays.sort(d);
        // for (int i = 0; i < a.length; i++) {
        // a[i] = d[i].a;
        // }
        // }
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
