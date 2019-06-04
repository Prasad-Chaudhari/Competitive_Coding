
/**
 * Date: 20 Nov, 2018
 * Link: http://codeforces.com/problemset/problem/482/B
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

public class Interesting_Array_482_B {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int m = in.ni();
        Data l[] = new Data[m];
        Data r[] = new Data[m];
        Data d[] = new Data[m];
        int i = 0;
        while (i < m) {
            int x = in.ni();
            int y = in.ni();
            int q = in.ni();
            d[i] = new Data(x, y, q);
            l[i] = new Data(x, i, q);
            r[i] = new Data(y, i, q);
            ++i;
        }
        Arrays.sort(l);
        Arrays.sort(r);
        OrSegmentTree or = new OrSegmentTree();
        or.getArray(new int[m]);
        or.build(0, m - 1, 1);
        int l_ind = 0;
        int r_ind = 0;
        int ans[] = new int[n];
        for (i = 1; i <= n; i++) {
            while (l_ind < m && l[l_ind].l <= i) {
                or.update(0, m - 1, 1, l[l_ind].r - 1, l[l_ind].q);
                l_ind++;
            }
            ans[i - 1] = or.query(0, m - 1, 1, 0, m - 1);
            while (r_ind < m && r[r_ind].l <= i) {
                or.update(0, m - 1, 1, r[r_ind].r - 1, 0);
                r_ind++;
            }
        }
        AnsSegmentTree and = new AnsSegmentTree();
        and.getArray(ans);
        and.build(0, n - 1, 1);
        for (i = 0; i < m; i++) {
            if (and.query(0, n - 1, 1, d[i].l - 1, d[i].r - 1) != d[i].q) {
                System.out.println("NO");
                return;
            }
        }
        in.println("YES");
        for (int j : ans) {
            in.println(j + "");
        }
        in.bw.flush();
    }

    static class AnsSegmentTree extends SegmentTree {

        public AnsSegmentTree() {
            DEFAULT = Integer.MAX_VALUE;
        }

        @Override
        public int keyFunc(int a, int b) {
            return a & b;
        }
    }

    static class OrSegmentTree extends SegmentTree {

        public OrSegmentTree() {
            DEFAULT = 0;
        }

        @Override
        public int keyFunc(int a, int b) {
            return a | b;
        }
    }

    static class SegmentTree {

        private int[] tree, array;
        int DEFAULT = 0;

        public void getArray(int[] a) {
            array = a;
            int si = a.length;
            double x = Math.log(si) / Math.log(2);
            int n = (int) (Math.pow(2, Math.ceil(x) + 1)) + 1;
            tree = new int[n];
        }

        public void build(int start, int end, int pos) {
            if (start == end) {
                tree[pos] = array[start];
            } else {
                int mid = (start + end) / 2;
                build(start, mid, 2 * pos);
                build(mid + 1, end, 2 * pos + 1);
                tree[pos] = keyFunc(tree[2 * pos], tree[2 * pos + 1]);
            }
        }

        public void update(int start, int end, int pos, int idx, int x) {
            if (start == end) {
                array[start] = x;
                tree[pos] = x;
            } else {
                int mid = (start + end) / 2;
                if (start <= idx && idx <= mid) {
                    update(start, mid, 2 * pos, idx, x);
                } else {
                    update(mid + 1, end, 2 * pos + 1, idx, x);
                }
                tree[pos] = keyFunc(tree[2 * pos], tree[2 * pos + 1]);
            }
        }

        public int query(int start, int end, int pos, int l, int r) {
            if (start > r || end < l) {
                return DEFAULT;
            }
            if (l <= start && end <= r) {
                return tree[pos];
            } else {
                int mid = (start + end) / 2;
                int a = query(start, mid, 2 * pos, l, r);
                int b = query(mid + 1, end, 2 * pos + 1, l, r);
                return keyFunc(a, b);
            }
        }

        public void printTree() {
            for (int i = 0; i <= 2 * array.length; i++) {
                System.out.println(i + " " + tree[i]);
            }
            System.out.println();
        }

        public int keyFunc(int a, int b) {
            return a + b;
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

        int l, r, q;

        public Data(int l, int r, int q) {
            this.l = l;
            this.r = r;
            this.q = q;
        }

        @Override
        public int compareTo(Data o) {
            if (l == o.l) {
                return Integer.compare(r, o.r);
            }
            return Integer.compare(l, o.l);
        }
    }
}
