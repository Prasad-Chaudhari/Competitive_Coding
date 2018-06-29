
/**
 * Date: 16 Jun, 2018
 * Link: http://codeforces.com/contest/993/problem/B
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Open_Communication_993_B {

    public static void main(String[] args) throws java.lang.Exception {
        FastIO in = new FastIO();
        int n = in.ni();
        int m = in.ni();
        Pair p[] = new Pair[n];
        Pair pp[] = new Pair[m];
        for (int i = 0; i < n; i++) {
            p[i] = new Pair(in.ni(), in.ni());
        }
        for (int i = 0; i < m; i++) {
            pp[i] = new Pair(in.ni(), in.ni());
        }
        Map<Pair, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int t = p[i].singleCommon(pp[j]);
                if (t != -1) {
                    if (map.containsKey(p[i])) {
                        if (map.get(p[i]) != t) {
                            System.out.println(-1);
                            return;
                        }
                    } else {
                        map.put(p[i], t);
                    }
                }
            }
        }
        Pair[] temp = p;
        p = pp;
        pp = temp;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int t = p[i].singleCommon(pp[j]);
                if (t != -1) {
                    if (map.containsKey(p[i])) {
                        if (map.get(p[i]) != t) {
                            System.out.println(-1);
                            return;
                        }
                    } else {
                        map.put(p[i], t);
                    }
                }
            }
        }
        if (map.keySet().isEmpty()) {
            System.out.println(-1);
        } else if (map.keySet().size() == 1) {
            for (int i : map.values()) {
                System.out.println(i);
            }
        } else {
            Set<Integer> ss = new HashSet<>();
            for (int i : map.values()) {
                ss.add(i);
            }
            if (ss.size() == 1) {
                for (int i : ss) {
                    System.out.println(i);
                }
            } else {
                System.out.println(0);
            }
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
}

class Pair {

    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int singleCommon(Pair p) {
        if (p.x == x && p.y != y) {
            return p.x;
        } else if (p.x != x && p.y == y) {
            return p.y;
        } else if (p.x == y && p.y != x) {
            return p.x;
        } else if (p.x != y && p.y == x) {
            return p.y;
        }
        return -1;
    }
}
