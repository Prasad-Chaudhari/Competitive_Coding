
/**
 * Date: 14 Nov, 2018
 * Link: http://codeforces.com/problemset/problem/369/C
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

public class Valera_and_Elections_369_C {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int from[] = new int[n - 1];
        int to[] = new int[n - 1];
        int pro[] = new int[n - 1];
        int count[] = new int[n + 1];
        for (int i = 0; i < n - 1; i++) {
            from[i] = in.ni();
            to[i] = in.ni();
            pro[i] = in.ni();
            count[from[i]]++;
            count[to[i]]++;
        }
        int adja[][] = new int[n + 1][];
        int pr[][] = new int[n + 1][];
        for (int i = 0; i <= n; i++) {
            adja[i] = new int[count[i]];
            pr[i] = new int[count[i]];
        }
        for (int i = 0; i < n - 1; i++) {
            adja[from[i]][count[from[i]] - 1] = to[i];
            adja[to[i]][count[to[i]] - 1] = from[i];
            pr[from[i]][count[from[i]] - 1] = pro[i];
            pr[to[i]][count[to[i]] - 1] = pro[i];
            count[from[i]]--;
            count[to[i]]--;
        }
        Stack<Integer> st = new Stack<>();
        st.push(1);
        boolean b[] = new boolean[n + 1];
        b[1] = true;
        int numb_prob[] = new int[n + 1];
        int last[] = new int[n + 1];
        int parent[] = new int[n + 1];
        while (!st.isEmpty()) {
            int root = st.peek();
            boolean p = true;
            for (int i : adja[root]) {
                p &= b[i];
            }
            if (p) {
                if (last[root] == 0) {
                    if (numb_prob[root] != numb_prob[parent[root]]) {
                        last[root] = root;
                    }
                    if (last[root] != 0) {
                        last[parent[root]] = last[root];
                    }   
                } else {
                    last[parent[root]] = last[root];
                }
                st.pop();
            } else {
                for (int j = 0; j < adja[root].length; j++) {
                    if (!b[adja[root][j]]) {
                        b[adja[root][j]] = true;
                        st.push(adja[root][j]);
                        numb_prob[adja[root][j]] = numb_prob[root] + pr[root][j] - 1;
                        parent[adja[root][j]] = root;
                    }
                }
            }
        }
        int countt = 0;
        int freq[] = new int[n + 1];
        for (int j = 1; j <= n; j++) {
            int i = last[j];
            if (i != 0) {
                if (freq[i] == 0) {
                    countt++;
                }
                freq[i]++;
            }
        }
        System.out.println(countt);
        for (int i = 1; i <= n; i++) {
            if (freq[i] > 0) {
                System.out.println(i);
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
