
/**
 * Date: 16 Jun, 2019
 * Link: https://codeforces.com/contest/1181/problem/B
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
import java.math.BigInteger;
import java.util.Arrays;

public class Split_a_Number_1181_B {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();

        int l = in.ni();
        String n = in.next();

        StringBuilder maxs = new StringBuilder();
        for (int i = 0; i < l + 1; i++) {
            maxs.append('9');
        }
        BigInteger max = new BigInteger(maxs.toString());
        BigInteger i1, i2, ans1, ans2;
        char c[] = n.toCharArray();
        if (l % 2 == 1) {
            if (c[l / 2] == '0') {
                int x1 = l / 2;
                while (x1 >= 0 && c[x1] == '0') {
                    x1--;
                }

                if (x1 == 0) {
                    ans1 = max;
                } else {
                    i1 = new BigInteger(n.substring(0, x1));
                    i2 = new BigInteger(n.substring(x1));
                    ans1 = i1.add(i2);
                }

                x1 = l / 2;
                while (x1 < l && c[x1] == '0') {
                    x1++;
                }

                if (x1 == l) {
                    ans2 = max;
                } else {
                    i1 = new BigInteger(n.substring(0, x1));
                    i2 = new BigInteger(n.substring(x1));
                    ans2 = i1.add(i2);
                }

                if (ans1.compareTo(ans2) > 0) {
                    System.out.println(ans2);
                } else {
                    System.out.println(ans1);
                }
            } else {
                int x1 = l / 2;
                i1 = new BigInteger(n.substring(0, x1));
                i2 = new BigInteger(n.substring(x1));
                ans1 = i1.add(i2);
                x1++;
                i1 = new BigInteger(n.substring(0, x1));
                i2 = new BigInteger(n.substring(x1));
                ans2 = i1.add(i2);
                if (ans1.compareTo(ans2) > 0) {
                    System.out.println(ans2);
                } else {
                    System.out.println(ans1);
                }
            }
        } else {
            if (l % 2 == 0) {
                if (c[(l / 2) + 1] == '0') {
                    int x1 = l / 2 + 1;
                    while (x1 >= 0 && c[x1] == '0') {
                        x1--;
                    }

                    if (x1 == 0) {
                        ans1 = max;
                    } else {
                        i1 = new BigInteger(n.substring(0, x1));
                        i2 = new BigInteger(n.substring(x1));
                        ans1 = i1.add(i2);
                    }

                    x1 = l / 2;
                    while (x1 < l && c[x1] == '0') {
                        x1++;
                    }

                    if (x1 == l) {
                        ans2 = max;
                    } else {
                        i1 = new BigInteger(n.substring(0, x1));
                        i2 = new BigInteger(n.substring(x1));
                        ans2 = i1.add(i2);
                    }

                    if (ans1.compareTo(ans2) > 0) {
                        System.out.println(ans2);
                    } else {
                        System.out.println(ans1);
                    }
                } else {
                    int x1 = l / 2;
                    i1 = new BigInteger(n.substring(0, x1));
                    i2 = new BigInteger(n.substring(x1));
                    ans1 = i1.add(i2);
                    x1++;
                    i1 = new BigInteger(n.substring(0, x1));
                    i2 = new BigInteger(n.substring(x1));
                    ans2 = i1.add(i2);
                    if (ans1.compareTo(ans2) > 0) {
                        System.out.println(ans2);
                    } else {
                        System.out.println(ans1);
                    }
                }
            }
        }

        in.bw.flush();
    }

    static class FastIO {

        private final BufferedReader br;
        private final BufferedWriter bw;
        private String s[];
        private int index;
        private final StringBuilder sb;

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

        public int[][][] gwtree(int n) throws IOException {
            int m = n - 1;
            int adja[][] = new int[n + 1][];
            int weight[][] = new int[n + 1][];
            int from[] = new int[m];
            int to[] = new int[m];
            int count[] = new int[n + 1];
            int cost[] = new int[n + 1];
            for (int i = 0; i < m; i++) {
                from[i] = i + 1;
                to[i] = ni();
                cost[i] = ni();
                count[from[i]]++;
                count[to[i]]++;
            }
            for (int i = 0; i <= n; i++) {
                adja[i] = new int[count[i]];
                weight[i] = new int[count[i]];
            }
            for (int i = 0; i < m; i++) {
                adja[from[i]][count[from[i]] - 1] = to[i];
                adja[to[i]][count[to[i]] - 1] = from[i];
                weight[from[i]][count[from[i]] - 1] = cost[i];
                weight[to[i]][count[to[i]] - 1] = cost[i];
                count[from[i]]--;
                count[to[i]]--;
            }
            return new int[][][]{adja, weight};
        }

        public int[][][] gwg(int n, int m) throws IOException {
            int adja[][] = new int[n + 1][];
            int weight[][] = new int[n + 1][];
            int from[] = new int[m];
            int to[] = new int[m];
            int count[] = new int[n + 1];
            int cost[] = new int[n + 1];
            for (int i = 0; i < m; i++) {
                from[i] = ni();
                to[i] = ni();
                cost[i] = ni();
                count[from[i]]++;
                count[to[i]]++;
            }
            for (int i = 0; i <= n; i++) {
                adja[i] = new int[count[i]];
                weight[i] = new int[count[i]];
            }
            for (int i = 0; i < m; i++) {
                adja[from[i]][count[from[i]] - 1] = to[i];
                adja[to[i]][count[to[i]] - 1] = from[i];
                weight[from[i]][count[from[i]] - 1] = cost[i];
                weight[to[i]][count[to[i]] - 1] = cost[i];
                count[from[i]]--;
                count[to[i]]--;
            }
            return new int[][][]{adja, weight};
        }

        public int[][] gtree(int n) throws IOException {
            int adja[][] = new int[n + 1][];
            int from[] = new int[n - 1];
            int to[] = new int[n - 1];
            int count[] = new int[n + 1];
            for (int i = 0; i < n - 1; i++) {
                from[i] = i + 1;
                to[i] = ni();
                count[from[i]]++;
                count[to[i]]++;
            }
            for (int i = 0; i <= n; i++) {
                adja[i] = new int[count[i]];
            }
            for (int i = 0; i < n - 1; i++) {
                adja[from[i]][--count[from[i]]] = to[i];
                adja[to[i]][--count[to[i]]] = from[i];
            }
            return adja;
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

        public void print(int s) throws IOException {
            bw.write(s + "");
        }

        public void println(int s) throws IOException {
            bw.write(s + "");
            bw.newLine();
        }

        public void print(long s) throws IOException {
            bw.write(s + "");
        }

        public void println(long s) throws IOException {
            bw.write(s + "");
            bw.newLine();
        }

        public void print(double s) throws IOException {
            bw.write(s + "");
        }

        public void println(double s) throws IOException {
            bw.write(s + "");
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
