
/**
 * Date: 2 Sep, 2018
 * Link: http://codeforces.com/contest/1037/problem/D
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
import java.util.LinkedList;

public class Valid_BFS_1037_D {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();

        int n = in.ni();
        int adja[][] = in.gg(n, n - 1);
        int a[] = in.gia(n);

        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        boolean b[] = new boolean[n + 1];
        b[1] = true;
        int level[] = new int[n + 1];
        int parent[] = new int[n + 1];
        parent[1] = -1;
        int index[] = new int[n + 1];
        int pos[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            pos[a[i - 1]] = i;
        }
        while (!l.isEmpty()) {
            int root = l.removeFirst();
            b[root] = true;
            for (int j : adja[root]) {
                if (!b[j]) {
                    level[j] = level[root] + 1;
                    parent[j] = root;
                    index[j] = pos[parent[j]];
                    b[j] = true;
                    l.addLast(j);
                }
            }
        }
//        for (int i = 1; i <= n; i++) {
//            System.out.println(i + " " + index[i]);
//        }
        for (int i = 1; i < n; i++) {
//            System.out.println(a[i] + " " + level[a[i]]);
            if (level[a[i - 1]] > level[a[i]]) {
                System.out.println("No");
                return;
            } else if (level[a[i - 1]] == level[a[i]]) {
                if (index[a[i - 1]] > index[a[i]]) {
                    System.out.println("No");
                    return;
                }
            }
        }
        System.out.println("Yes");
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

        int a, b;

        public Data(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Data o) {
            return Integer.compare(a, o.a);
        }
    }
}
