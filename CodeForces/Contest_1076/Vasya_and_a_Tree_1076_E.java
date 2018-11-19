
/**
 * Date: 13 Nov, 2018
 * Link: http://codeforces.com/contest/1076/problem/E
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
import java.util.Stack;

public class Vasya_and_a_Tree_1076_E{ 

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int adja[][] = in.gg(n, n - 1);
        int m = in.ni();
        Data d[] = new Data[m];
        int count[] = new int[n + 1];
        for (int i = 0; i < m; i++) {
            d[i] = new Data(in.ni(), in.ni(), in.ni());
            count[d[i].a]++;
        }
        Data curr[][] = new Data[n + 1][];
        for (int i = 0; i <= n; i++) {
            curr[i] = new Data[count[i]];
        }
        for (int i = 0; i < m; i++) {
            curr[d[i].a][--count[d[i].a]] = d[i];
        }
        long delete[] = new long[n + 1];
        int level[] = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            count[i] = adja[i].length - 1;
        }
        count[1]++;
        Stack<Integer> st = new Stack<>();
        boolean b[] = new boolean[n + 1];
        b[1] = true;
        st.push(1);
        long value[] = new long[n + 1];
        dfs(st, delete, level, value, 0, b, curr, adja);
        for (int i = 1; i <= n; i++) {
            in.println(value[i] + "");
        }
        in.bw.flush();;
    }

    private static void dfs(Stack<Integer> st, long[] delete, int[] level, long[] value, long acc, boolean[] b, Data curr[][], int adja[][]) {
        if (st.isEmpty()) {
            return;
        }
        int root = st.pop();
        for (Data item : curr[root]) {
            acc += item.c;
            if (level[root] + item.b + 1 < delete.length - 1) {
                delete[level[root] + item.b + 1] += item.c;
            }
        }
        value[root] = acc - delete[level[root]];
        for (int i : adja[root]) {
            if (!b[i]) {
                b[i] = true;
                st.push(i);
                level[i] = level[root] + 1;
                dfs(st, delete, level, value, acc - delete[level[root]], b, curr, adja);
            }
        }
        for (Data item : curr[root]) {
            if (level[root] + item.b + 1 < delete.length - 1) {
                delete[level[root] + item.b + 1] -= item.c;
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
