
/**
 * Date: 12 Nov, 2018
 * Link: http://codeforces.com/contest/1076/problem/D
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
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class Edge_Deletion_1076_D {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int m = in.ni();
        int k = in.ni();
        int from[] = new int[m];
        int to[] = new int[m];
        int w[] = new int[m];
        int count[] = new int[n + 1];

        for (int i = 0; i < m; i++) {
            from[i] = in.ni();
            to[i] = in.ni();
            w[i] = in.ni();
            count[from[i]]++;
            count[to[i]]++;
        }

        int adja[][] = new int[n + 1][];
        int weights[][] = new int[n + 1][];
        int index[][] = new int[n + 1][];
        for (int i = 0; i <= n; i++) {
            adja[i] = new int[count[i]];
            weights[i] = new int[count[i]];
            index[i] = new int[count[i]];
        }

        for (int i = 0; i < m; i++) {
            adja[from[i]][count[from[i]] - 1] = to[i];
            weights[from[i]][count[from[i]] - 1] = w[i];
            index[from[i]][count[from[i]] - 1] = i;

            adja[to[i]][count[to[i]] - 1] = from[i];
            weights[to[i]][count[to[i]] - 1] = w[i];
            index[to[i]][count[to[i]] - 1] = i;

            count[from[i]]--;
            count[to[i]]--;
        }

        for (int i = 1; i <= n; i++) {
            Data d[] = new Data[adja[i].length];
            for (int j = 0; j < adja[i].length; j++) {
                d[j] = new Data(adja[i][j], 0, weights[i][j], index[i][j]);
            }
            if (adja[i].length >= 1) {
                Arrays.sort(d);
                for (int j = 0; j < adja[i].length; j++) {
                    adja[i][j] = d[j].a;
                    weights[i][j] = d[j].c;
                    index[i][j] = d[j].i;
                }
            }
        }
        long dist[] = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        PriorityQueue<Data> pq = new PriorityQueue<>(new Comparator<Data>() {
            @Override
            public int compare(Data d1, Data d2) {
                return Long.compare(d1.b, d2.b);
            }
        });
        pq.add(new Data(1, 0, -1, -1));
        int pre[] = new int[n + 1];
        int edgeindex[] = new int[n + 1];
        while (!pq.isEmpty()) {
            Data d = pq.remove();
            if (dist[d.a] > d.b) {
                pre[d.a] = d.c;
                dist[d.a] = d.b;
                edgeindex[d.a] = d.i;
                for (int i = 0; i < adja[d.a].length; i++) {
                    pq.add(new Data(adja[d.a][i], dist[d.a] + weights[d.a][i], d.a, index[d.a][i]));
                }
            }
        }
        Data d[] = new Data[n - 1];
        for (int i = 2; i <= n; i++) {
            d[i - 2] = new Data(pre[i], 0, i, edgeindex[i]);
        }
        Arrays.sort(d);
        boolean b[] = new boolean[m];
        int t = 0;
        for (int i = 1; i <= n; i++) {
            if (t < n - 1 && d[t].a == i) {
                for (int j = 0; j < adja[i].length; j++) {
                    if (t < n - 1 && d[t].a == i && d[t].c == adja[i][j] && d[t].i != -1) {
                        b[d[t].i] = true;
                        t++;
                    }
                }
            }
        }
        if (k >= n - 1) {
            System.out.println(n - 1);
            for (int i = 0; i < m; i++) {
                if (b[i]) {
                    in.println((i + 1) + " ");
                }
            }
            in.bw.flush();
            return;
        }

        from = new int[n - 1];
        to = new int[n - 1];
        int iddd[] = new int[n - 1];
        adja = new int[n + 1][];
        Arrays.fill(count, 0);
        for (int i = 2; i <= n; i++) {
            from[i - 2] = pre[i];
            to[i - 2] = i;
            iddd[i - 2] = edgeindex[i];
            count[from[i - 2]]++;
            count[to[i - 2]]++;
        }
        for (int i = 0; i <= n; i++) {
            adja[i] = new int[count[i]];
            index[i] = new int[count[i]];
        }
        for (int i = 0; i < n - 1; i++) {

            adja[from[i]][count[from[i]] - 1] = to[i];
            index[from[i]][count[from[i]] - 1] = iddd[i];

            adja[to[i]][count[to[i]] - 1] = from[i];
            index[to[i]][count[to[i]] - 1] = iddd[i];

            count[to[i]]--;
            count[from[i]]--;
        }

        Stack<Integer> st = new Stack<Integer>();
        boolean vi[] = new boolean[n + 1];
        st.push(1);
        vi[1] = true;
        System.out.println(k);
        while (!st.isEmpty()) {
            int root = st.pop();
            for (int i = 0; i < adja[root].length; i++) {
                if (!vi[adja[root][i]]) {
                    vi[adja[root][i]] = true;
                    st.push(adja[root][i]);
                    if (k-- > 0) {
                        in.println((index[root][i] + 1) + "");
                    } else {
                        in.bw.flush();
                        return;
                    }
                }
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

        int a;
        long b;
        int c;
        int i;

        public Data(int a, long b, int c, int i) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.i = i;
        }

        @Override
        public int compareTo(Data o) {
            if (a == o.a) {
                return Long.compare(c, o.c);
            }
            return Long.compare(a, o.a);
        }

//        public static void sort(int a[]) {
//            Data d[] = new Data[a.length];
//            for (int i = 0; i < a.length; i++) {
//                d[i] = new Data(a[i], 0);
//            }
//            Arrays.sort(d);
//            for (int i = 0; i < a.length; i++) {
//                a[i] = d[i].a;
//            }
//        }
    }
}
