
/**
 * Date: 14 Nov, 2018
 * Link: http://codeforces.com/problemset/problem/763/A
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

public class Timofey_and_a_tree_763_A {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int adja[][] = in.gg(n, n - 1);
        int colors[] = in.gia(n);
        int root1 = -1;
        int root2 = -1;
        for (int i = 1; i <= n; i++) {
            for (int j : adja[i]) {
                if (colors[i - 1] != colors[j - 1]) {
                    root1 = i;
                    root2 = j;
                    break;
                }
            }
        }
        if (root1 == -1) {
            System.out.println("YES 1");
            return;
        }
        boolean b[] = new boolean[n + 1];
        boolean all_same[] = new boolean[n + 1];
        Stack<Integer> st = new Stack<>();
        for (int i : adja[root1]) {
            st.push(i);
            b[i] = true;
            while (!st.isEmpty()) {
                int root = st.pop();
                if (root != root1) {
                    for (int j : adja[root]) {
                        if (j != root1 && !b[j]) {
                            b[j] = true;
                            st.push(j);
                            if (colors[j - 1] != colors[i - 1]) {
                                all_same[i] = true;
                            }
                        }
                    }
                }
            }
        }
        boolean ans = false;
        for (int i : adja[root1]) {
            ans |= all_same[i];
        }
        if (!ans) {
            System.out.println("YES " + root1);
            return;
        }
        System.out.println("");
        Arrays.fill(all_same, false);
        Arrays.fill(b, false);
        for (int i : adja[root2]) {
            st.push(i);
            b[i] = true;
            while (!st.isEmpty()) {
                int root = st.pop();
                if (root != root2) {
                    for (int j : adja[root]) {
                        if (j != root2 && !b[j]) {
                            b[j] = true;
                            st.push(j);
                            if (colors[j - 1] != colors[i - 1]) {
                                all_same[i] = true;
                            }
                        }
                    }
                }
            }
        }
        ans = false;
        for (int i : adja[root2]) {
            ans |= all_same[i];
        }
        if (!ans) {
            System.out.println("YES " + root2);
            return;
        }
        System.out.println("NO");
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
