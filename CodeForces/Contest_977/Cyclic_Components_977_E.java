
/**
 * Date: 6 May, 2018
 * Link: http://codeforces.com/contest/977/problem/E
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Cyclic_Components_977_E {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int n = in.ni();
        int m = in.ni();
        ArrayList<LinkedList<Integer>> a = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            a.add(new LinkedList<Integer>());
        }
        for (int i = 0; i < m; i++) {
            int x = in.ni();
            int y = in.ni();
            a.get(x).addFirst(y);
            a.get(y).addFirst(x);
        }
        Graph g = new Graph(a);
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

final class Graph {

    int[] level, parent, connected;
    int n;
    Queue<Integer> q;
    Stack<Integer> st;
    ArrayList<LinkedList<Integer>> a;
    boolean[] bcon;

    public Graph(ArrayList<LinkedList<Integer>> input) {
        a = input;
        n = a.size() - 1;
        level = new int[n + 1];
        parent = new int[n + 1];
        connected = new int[n + 1];
        bcon = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            level[i] = -1;
            parent[i] = -1;
        }
        //q = new LinkedList<Integer>();
        st = new Stack<Integer>();
        build();
    }

    void build() {
        boolean[] c = new boolean[n + 1];
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (!c[i]) {
                Stack<Integer> st2 = new Stack<>();
                st.push(i);
                st2.push(i);
                while (!st.isEmpty()) {
                    int root = st.pop();
                    c[root] = true;
                    LinkedList<Integer> l = a.get(root);
                    LinkedList<Integer> l2 = new LinkedList<Integer>();
                    int size = l.size();
                    for (int j = 0; j < size; j++) {
                        int sib = l.removeFirst();
                        if (!c[sib]) {
                            st.push(sib);
                            st2.push(sib);
                            c[sib] = true;
                        }
                        l2.addFirst(sib);
                    }
                    a.set(root, l2);
                }
                boolean p = false;
                while (!st2.isEmpty()) {
                    int root = st2.pop();
                    if (a.get(root).size() != 2) {
//                        System.out.println(root);
                        p = true;
                    }
                }
                if (!p) {
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }
}
