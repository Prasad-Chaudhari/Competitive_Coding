
/**
 * Date: 27 Dec, 2018
 * Link: https://codeforces.com/contest/1095/problem/F
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
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

public class Make_It_Connected_1095_F {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int m = in.ni();
        long a[] = in.gla(n);
        Data d[] = new Data[m + n];
        long minn = Long.MAX_VALUE;
        int min_index = -1;
        for (int i = 1; i <= n; i++) {
            if (minn > a[i - 1]) {
                minn = a[i - 1];
                min_index = i;
            }
        }
        for (int i = 0; i < m; i++) {
            d[i] = new Data(in.ni(), in.ni(), in.nl());
        }
        for (int i = m; i < m + n; i++) {
            if (min_index != i - m + 1) {
                d[i] = new Data(min_index, i - m + 1, a[min_index - 1] + a[i - m]);
            } else {
                d[i] = new Data(min_index, min_index, Long.MAX_VALUE);
            }
        }
        Arrays.sort(d);
        FastUnionFind uf = new FastUnionFind(n);
        long ans = 0;
        for (int i = 0; i < n + m - 1; i++) {
            if (uf.find(d[i].a) != uf.find(d[i].b)) {
                ans += d[i].c;
                uf.union(d[i].a, d[i].b);
            }
        }
        System.out.println(ans);
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
            a = find(a);
            b = find(b);
            if (a == b) {
                return;
            }
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

    static class FastUnionFind {

        int noOfComponents, n;
        private int[] parent, rank;

        public FastUnionFind(int n) {
            this.n = n;
            this.noOfComponents = n;
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 0; i <= n; i++) {
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
            a = find(a);
            b = find(b);
            if (a != b) {
                if (rank[a] > rank[b]) {
                    parent[b] = a;
                } else if (rank[b] > rank[a]) {
                    parent[a] = b;
                } else {
                    parent[b] = a;
                    rank[a]++;
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

        int a, b;
        long c;

        public Data(int a, int b, long c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Data o) {
            return Long.compare(c, o.c);
        }
    }
}
