
/**
 * Date: 2 Jun, 2018
 * Link: https://www.hackerearth.com/challenge/competitive/may-circuits-18/algorithm/increasing-values-8ae86432/
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Arrays;

public class Increasing_Values {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int q = in.ni();
        int X = in.ni();
        int m = n - 1;
        int adja[][] = new int[n + 1][];
        int from[] = new int[m];
        int to[] = new int[m];
        int count[] = new int[n + 1];
        for (int i = 0; i < m; i++) {
            from[i] = in.ni();
            to[i] = in.ni();
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

        Stack<Integer> st = new Stack<>();
        Stack<Integer> st2 = new Stack<>();
        int parent[] = new int[n + 1];
        int level[] = new int[n + 1];
        st.push(1);
        st2.push(1);
        level[1] = 1;
        parent[1] = -1;
        int sptable[][] = new int[n + 1][20];
        for (int[] ff : sptable) {
            Arrays.fill(ff, -1);
        }
        while (!st.isEmpty()) {
            int root = st.pop();
            for (int i : adja[root]) {
                if (parent[i] == 0) {
                    st.push(i);
                    st2.push(i);
                    parent[i] = root;
                    level[i] = level[root] + 1;
                }
            }
            sptable[root][0] = parent[root];
            int j = 0;
            while (sptable[root][j] != -1) {
                sptable[root][j + 1] = sptable[sptable[root][j]][j];
                j++;
            }
        }

        long d[] = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            d[i] = in.ni();
        }

        Chain[] chainRep = new Chain[n + 1];

        int subtree[] = new int[n + 1];
        boolean good[] = new boolean[n + 1];
        while (!st2.isEmpty()) {
            int root = st2.pop();
            subtree[root] = 1;
            int max = 0;
            int bigChild = -1;
            for (int i : adja[root]) {
                if (i != parent[root]) {
                    d[root] += d[i];
                    subtree[root] += subtree[i];
                    if (subtree[i] > max) {
                        max = subtree[i];
                        bigChild = i;
                    }
                }
            }
            if (d[root] > X) {
                int curr = root;
                while (curr != -1 && !good[curr]) {
                    good[curr] = true;
                    curr = parent[curr];
                }
            }
            if (bigChild != -1) {
                chainRep[root] = chainRep[bigChild];
                chainRep[root].members.addFirst(root);
                chainRep[root].head = root;
                chainRep[root].size++;
            } else {
                chainRep[root] = new Chain(root);
            }
        }
        int ccount = 0;
        for (int i = 1; i <= n; i++) {
            if (good[i]) {
                ccount++;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (chainRep[i].bitree == null) {
                int l = chainRep[i].size;
                chainRep[i].bitree = new BIT(l);
                long sub = 0;
                for (int j = 0; j < l; j++) {
                    int mem = chainRep[i].members.removeLast();
                    chainRep[i].bitree.update(j, l, d[mem] - sub);
                    sub = d[mem];
                }
                chainRep[i].members = null;
            }
        }
        System.gc();
        while (q-- > 0) {
            int node = in.ni();
            int inc = in.ni();
            int curr = node;
            while (curr != -1) {
                Chain ch = chainRep[curr];
                int index = ch.size - (level[curr] - level[ch.head] + 1);
                ch.bitree.update(index, ch.size, inc);
                curr = parent[ch.head];
            }
            curr = node;
            while (curr != -1) {
                int j;
                for (j = 0; j < 20; j++) {
                    int fv = sptable[curr][j];
                    if (fv != -1) {
                        Chain ch = chainRep[fv];
                        if (ch.bitree.getSum(ch.size - (level[fv] - level[ch.head] + 1)) > X) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (j == 0) {
                    break;
                } else {
                    curr = sptable[curr][j - 1];
                }
            }
            if (chainRep[curr].bitree.getSum(chainRep[curr].size - (level[curr] - level[chainRep[curr].head] + 1)) <= X) {
                curr = parent[curr];
            }
            while (curr != -1 && !good[curr]) {
                good[curr] = true;
                curr = parent[curr];
                ccount++;
            }
            in.println(ccount + "");
        }
    }

    static class FastIO {

        private final BufferedReader br;
        private final BufferedWriter bw;
        private String s[];
        private int index;

        public FastIO() throws IOException {
            br = new BufferedReader(new InputStreamReader(System.in));
            bw = new BufferedWriter(new OutputStreamWriter(System.out, "UTF-8"));
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
            for (int i = 0; i < n; i++) {
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
}

class Chain {

    int head, size;
    LinkedList<Integer> members;
    BIT bitree;

    public Chain(int i) {
        size = 1;
        head = i;
        members = new LinkedList<>();
        members.add(i);
        bitree = null;
    }

    public void addMember(int i) {
        members.add(i);
        size++;
    }
}

class BIT {

    long BITree[];

    public BIT(int n) {
        BITree = new long[n + 1];
    }

    public void update(int index, int n, long value) {
        index++;
        while (index <= n) {
            BITree[index] += value;
            index += index & -index;
        }
    }

    public long getSum(int i) {
        i++;
        long sum = 0;
        while (i > 0) {
            sum += BITree[i];
            i -= (i & -i);
        }
        return sum;
    }
}
