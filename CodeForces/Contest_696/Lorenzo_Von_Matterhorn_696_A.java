
/**
 * Date: 14 Nov, 2018
 * Link: http://codeforces.com/problemset/problem/696/A
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
import java.util.Hashtable;
import java.util.Map;

public class Lorenzo_Von_Matterhorn_696_A {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int q = in.ni();
        Map<Long, Long> map = new Hashtable<>();
        while (q-- > 0) {
            if (in.ni() == 1) {
                long u = in.nl();
                long v = in.nl();
                int w = in.ni();
                if (v > u) {
                    u ^= v;
                    v ^= u;
                    u ^= v;
                }
                while (u != v) {
                    int distu = (int) (Math.log(u) / Math.log(2));
                    int distv = (int) (Math.log(v) / Math.log(2));
                    while (distu > distv) {
                        map.put(u, map.getOrDefault(u, 0l) + w);
                        u /= 2;
                        distu--;
                    }
                    while (distv > distu) {
                        map.put(v, map.getOrDefault(v, 0l) + w);
                        v /= 2;
                        distv--;
                    }
                    if (u != v) {
                        map.put(u, map.getOrDefault(u, 0l) + w);
                        map.put(v, map.getOrDefault(v, 0l) + w);
                        u /= 2;
                        v /= 2;
                    }
                }
            } else {
                long ans = 0;
                long u = in.nl();
                long v = in.nl();
                if (v > u) {
                    u ^= v;
                    v ^= u;
                    u ^= v;
                }
                while (u != v) {
                    int distu = (int) (Math.log(u) / Math.log(2));
                    int distv = (int) (Math.log(v) / Math.log(2));
                    while (distu > distv) {
                        ans += map.getOrDefault(u, 0l);
                        u /= 2;
                        distu--;
                    }
                    while (distv > distu) {
                        ans += map.getOrDefault(v, 0l);
                        v /= 2;
                        distv--;
                    }
                    if (u != v) {
                        ans += map.getOrDefault(v, 0l);
                        ans += map.getOrDefault(u, 0l);
                        u /= 2;
                        v /= 2;
                    }
                }
                System.out.println(ans);
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

        public static void sort(int a[]) {
            Data d[] = new Data[a.length];
            for (int i = 0; i < a.length; i++) {
                d[i] = new Data(a[i], 0);
            }
            Arrays.sort(d);
            for (int i = 0; i < a.length; i++) {
                a[i] = d[i].a;
            }
        }
    }
}
