
/**
 * Date: 24 Oct, 2018
 * Link: http://codeforces.com/contest/1068/problem/C
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
import java.util.ArrayList;
import java.util.LinkedList;

public class Colored_Rooks_1068_C {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int m = in.ni();
        int xs[] = new int[m];
        int ys[] = new int[m];
        for (int i = 0; i < m; i++) {
            xs[i] = in.ni();
            ys[i] = in.ni();
        }
        int count[] = new int[n + 1];
        boolean bb[][] = new boolean[n + 1][5001];
        Point[][] p = new Point[n + 1][5000];
        for (int i = 0; i < m; i++) {
            int x = xs[i];
            int y = ys[i];
            p[x][i] = new Point(x, i + 1);
            p[y][i] = new Point(y, i + 1);
            count[x]++;
            count[y]++;
        }
        int end = (int) 1e9;
        for (int i = 1; i <= n; i++) {
            if (count[i] == 0) {
                System.out.println("1");
                System.out.println(i + " " + end--);
            } else {
                System.out.println(count[i]);
                for (int j = 0; j < 5000; j++) {
                    if (p[i][j] != null) {
                        System.out.println(p[i][j].x + " " + p[i][j].y);
                    }
                }
            }
        }
    }

    static class Point {

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    static class UnionFind {

        private int noOfComponents, n;
        private int component[], size[];
        private ArrayList<LinkedList<Integer>> members;

        public UnionFind(int p) {
            n = p;
            noOfComponents = n;
            component = new int[n + 1];
            size = new int[n + 1];
            members = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                component[i] = i;
                size[i] = 1;
                members.add(new LinkedList<Integer>());
                members.get(i).add(i);
            }
        }

        public int find(int k) {
            return component[k];
        }

        public void union(int a, int b) {
            if (size[a] > size[b]) {
                a = a ^ b;
                b = a ^ b;
                a = a ^ b;
            }
            LinkedList<Integer> membersofa = members.get(a);
            LinkedList<Integer> membersofb = members.get(b);
            for (int i = 0; i < size[a]; i++) {
                int member = membersofa.removeFirst();
                component[member] = component[b];
                membersofb.add(member);
            }
            size[b] = size[b] + size[a];
            noOfComponents--;
        }

        public int noOfComponents() {
            return noOfComponents;
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
    }
}
