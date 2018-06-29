
/**
 * Date: 25 May, 2018
 * Link: http://codeforces.com/contest/982/problem/C
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class Cut_em_all_982_C {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int n = in.ni();
        if (n % 2 == 1) {
            System.out.println(-1);
            return;
        }
        ArrayList<LinkedList<Integer>> a = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            a.add(new LinkedList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int x = in.ni();
            int y = in.ni();
            a.get(x).add(y);
            a.get(y).add(x);
        }
        int subtreesize[] = new int[n + 1];
        Stack<Integer> st = new Stack<>();
        st.push(1);
        boolean c[] = new boolean[n + 1];
        int parent[] = new int[n + 1];
        Stack<Integer> st2 = new Stack<>();
        st2.push(1);
        while (!st.isEmpty()) {
            int root = st.pop();
            c[root] = true;
            for (int i : a.get(root)) {
                if (!c[i]) {
                    c[i] = true;
                    parent[i] = root;
                    st.push(i);
                    st2.push(i);
                }
            }
        }
        Arrays.fill(subtreesize, 1);
        int k = 0;
        while (!st2.isEmpty()) {
            int node = st2.pop();
            if (subtreesize[node] % 2 == 1) {
                subtreesize[parent[node]] += subtreesize[node];
            } else {
                k++;
            }
        }
        System.out.println(k - 1);
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
