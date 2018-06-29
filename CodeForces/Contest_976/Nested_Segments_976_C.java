
/**
 * Date: 1 May, 2018
 * Link: http://codeforces.com/contest/976/problem/C
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Nested_Segments_976_C {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int n = in.ni();
        Data d[] = new Data[n];
        for (int i = 0; i < n; i++) {
            d[i] = new Data(in.ni(), in.ni(), i + 1);
        }
        Arrays.sort(d, new Com());
        int a[] = new int[n];
        int b[] = new int[n];
        int c[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = d[i].a;
            b[i] = d[i].b;
            c[i] = d[i].c;
        }
        MinSegmentTree minsegt = new MinSegmentTree();
        minsegt.getArray(b);
        minsegt.build(0, n - 1, 1);
        for (int i = 0; i < n; i++) {
            int index = Arrays.binarySearch(a, b[i]);
            if (index < 0) {
                index = -(index + 1);
                index--;
            }
            if (index == i) {
                if (i + 1 < n && b[i + 1] == b[i]) {
                    System.out.println(c[i + 1] + " " + c[i]);
                    return;
                }
            } else {
                int ans = minsegt.query(0, n - 1, 1, i + 1, index);
                if (ans <= b[i]) {
                    for (int j = i + 1; i <= index; j++) {
                        if (b[j] == ans) {
                            System.out.println(c[j] + " " + c[i]);
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("-1 -1");
    }

    static class FastIO2 {

        private final BufferedReader br;
        private String s[];
        private int index;

        public FastIO2() throws IOException {
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

    private static class Data {

        int a, b, c;

        public Data(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

    }

    private static class Com implements Comparator<Data> {

        @Override
        public int compare(Data a, Data b) {
            if (a.a == b.a) {
                if (a.b == b.b) {
                    return Integer.compare(a.c, b.c);
                } else {
                    return Integer.compare(-a.b, -b.b);
                }
            } else {
                return a.a - b.a;
            }
        }
    }

    private static class MaxSegmentTree extends SegmentTree {

        @Override
        public int keyFunc(int a, int b) {
            return Math.max(a, b);
        }
    }

    private static class MinSegmentTree extends SegmentTree {

        public MinSegmentTree() {
            this.DEFAULT = Integer.MAX_VALUE;
        }

        @Override
        public int keyFunc(int a, int b) {
            return Math.min(a, b);
        }
    }

    private static class SegmentTree {

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
}
