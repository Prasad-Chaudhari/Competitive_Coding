
/**
 * Date: 19 Apr, 2018
 * Link: https://www.codechef.com/APRIL18B/problems/CUTPLANT
 * Comment: 70 points only
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CUTPLANT {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int t = in.ni();
        while (t-- > 0) {
            int n = in.ni();
            int a[] = in.gia(n);
            int b[] = in.gia(n);
            for (int i = 0; i < n; i++) {
                if (a[i] < b[i]) {
                    System.out.println(-1);
                    return;
                }
            }
            int ans = 0;
            MaxSegmentTree maxst = new MaxSegmentTree();
            maxst.getArray(b);
            maxst.build(0, n - 1, 1);
            MinSegmentTree minst = new MinSegmentTree();
            minst.getArray(a);
            minst.build(0, n - 1, 1);
            Map<Integer, LinkedList<Integer>> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                if (map.keySet().contains(b[i])) {
                    map.get(b[i]).add(i);
                } else {
                    map.put(b[i], new LinkedList<>());
                    map.get(b[i]).add(i);
                }
            }
            boolean done[] = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (!done[i]) {
                    LinkedList<Integer> l = map.get(b[i]);
                    l.removeFirst();
                    int size = l.size();
                    int count = 0;
                    for (int j = 0; j < size; j++) {
                        int k = l.removeFirst();
                        if (maxst.query(0, n - 1, 1, i, k) > b[i]) {
                            l.addFirst(k);
                            break;
                        }
                        if (minst.query(0, n - 1, 1, i, k) < b[i]) {
                            l.addFirst(k);
                            break;
                        }
                        if (a[k] != b[k]) {
                            count++;
                        }
                        done[k] = true;
                    }
                    if (!(count == 0 && a[i] == b[i])) {
                        ans++;
                    }
                }
            }
            System.out.println(ans);
        }
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
}

class MaxSegmentTree extends SegmentTree {

    @Override
    public int keyFunc(int a, int b) {
        return Math.max(a, b);
    }
}

class MinSegmentTree extends SegmentTree {

    public MinSegmentTree() {
        this.DEFAULT = Integer.MAX_VALUE;
    }

    @Override
    public int keyFunc(int a, int b) {
        return Math.min(a, b);
    }
}

class SegmentTree {

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
