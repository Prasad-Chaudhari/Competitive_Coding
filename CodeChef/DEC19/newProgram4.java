
/**
 * Date: 13 Dec, 2019
 * Link: 
 * 
 * @author Prasad Chaudhari
 * @linkedIn: https://www.linkedin.com/in/prasad-chaudhari-841655a6/
 * @git: https://github.com/Prasad-Chaudhari
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

class newProgram4 {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO(args);
        int t = in.ni();
        Data d[][] = new Data[10][];
        int fac[] = new int[10];
        fac[0] = 1;
        for (int i = 1; i < 10; i++) {
            fac[i] = fac[i - 1] * i;
        }
        d[0] = new Data[1];
        d[0][0] = new Data(0, 1);
        for (int i = 1; i <= 9; i++) {
            ArrayList<Integer> al = new ArrayList<>();
            permute(al, 0, 0, i, -1);
            int size = al.size();
            d[i] = new Data[size];
            for (int j = 0; j < size; j++) {
                int curr = al.get(j);
                int m = al.get(j) % 10;
                int count = 1;
                int freq = fac[i];
                while (curr > 0) {
                    curr /= 10;
                    if (m != curr % 10) {
                        freq /= fac[count];
                        count = 1;
                        m = curr % 10;
                    } else {
                        count++;
                    }
                }
                freq /= fac[count];
                d[i][j] = new Data(al.get(j), freq);
            }
        }
        for (int l = 1; l <= 1000; l++) {
            for (int r = l; r <= 1000; r++) {
                if (r <= 1000000000) {
                    Map<Integer, Integer> all_l = all(l - 1, d);
                    Map<Integer, Integer> all_r = all(r, d);
                    all_l.remove(0);
                    all_r.remove(0);
                    Map<char[], Integer> all_l_l = new Hashtable<>();
                    for (int i : all_l.keySet()) {
                        all_l_l.put(("" + i).toCharArray(), all_l.get(i));
                    }
                    Map<char[], Integer> all_r_r = new Hashtable<>();
                    for (int i : all_r.keySet()) {
                        all_r_r.put(("" + i).toCharArray(), all_r.get(i));
                    }
                    long ans1 = from_l_r(all_l_l, all_l_l);
                    long ans2 = from_l_r(all_l_l, all_r_r);
                    long ans3 = from_l_r(all_r_r, all_r_r);
                    long ans = ans1 + ans3 - (2 * ans2);
                    in.println(l + " " + r + " " + ans);
                } else {
                    System.out.println(0);
                }
            }
        }
        in.bw.flush();
    }

    static long from_l_r(Map<char[], Integer> l, Map<char[], Integer> r) {
        long ans3 = 0;
        int sum = 0;
        int i = 0;
        int j = 0;
        int max = 0;
        long mod = 1000000007;
        for (char[] n : l.keySet()) {
            for (char[] m : r.keySet()) {
                max = Math.max(n.length, m.length);
                sum = 0;
                while (--max >= 0) {
                    i = max < n.length ? (n[max] - '0') : 0;
                    j = max < m.length ? (m[max] - '0') : 0;
                    sum += Math.abs(i - j);
                }
                long p = (1l * l.get(n) * r.get(m)) % mod;
                ans3 += (1l * p * sum) % mod;
            }
            ans3 %= mod;
        }
        return ans3;
    }

    static Map<Integer, Integer> all(long l, Data arr[][]) {
        char c[] = ("" + l).toCharArray();
        Map<Integer, Integer> map = new Hashtable<>();
        int freqs[] = new int[10];
        for (int i = 0; i < c.length; i++) {
            int num = c[i] - '0';
            for (int j = 0; j < num; j++) {
                freqs[j]++;
                int p = getNumber(freqs);
                for (int k = 0; k < arr[c.length - 1 - i].length; k++) {
                    int q = merge(p, arr[c.length - 1 - i][k].a);
                    map.put(q, map.getOrDefault(q, 0) + arr[c.length - 1 - i][k].b);
                }
                freqs[j]--;
            }
            freqs[num]++;
        }
        int p = getNumber(freqs);
        p = Integer.parseInt(new StringBuilder("" + p).reverse().toString());
        map.put(p, map.getOrDefault(p, 0) + 1);
        return map;
    }

    static int getNumber(int a[]) {
        int ans = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < a[i]; j++) {
                ans = 10 * ans + i;
            }
        }
        return ans;
    }

    static int merge(int a, int b) {
        int c = 0;
        while (a > 0 && b > 0) {
            if (a % 10 > b % 10) {
                c = (10 * c) + (a % 10);
                a /= 10;
            } else {
                c = (10 * c) + (b % 10);
                b /= 10;
            }
        }
        while (a > 0) {
            c = (10 * c) + a % 10;
            a /= 10;
        }
        while (b > 0) {
            c = (10 * c) + b % 10;
            b /= 10;
        }
        return c;
    }

    static void permute(ArrayList<Integer> al, int num, int index, int n, int pre) {
        if (index == n) {
            al.add(num);
        } else {
            for (int i = pre + 1; i < 10; i++) {
                permute(al, 10 * num + i, index + 1, n, i - 1);
            }
        }
    }

    static class Data implements Comparable<Data> {

        int a, b;

        public Data(final int a, final int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(final Data o) {
            if (a == o.a) {
                return Integer.compare(b, o.b);
            }
            return Integer.compare(a, o.a);
        }

        public static void sort(final int a[]) {
            final Data d[] = new Data[a.length];
            for (int i = 0; i < a.length; i++) {
                d[i] = new Data(a[i], 0);
            }
            Arrays.sort(d);
            for (int i = 0; i < a.length; i++) {
                a[i] = d[i].a;
            }
        }
    }

    static class FastIO {

        private final BufferedReader br;
        private final BufferedWriter bw;
        private String s[];
        private int index;

        public FastIO(String[] args) throws IOException {
            if (args.length > 1) {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(args[0]))));
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(args[1]))));
            } else {
                br = new BufferedReader(new InputStreamReader(System.in));
                bw = new BufferedWriter(new OutputStreamWriter(System.out, "UTF-8"));
            }
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
            } catch (final IOException ex) {

            }
            return null;
        }

        public int[] gia(final int n) throws IOException {
            final int a[] = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = ni();
            }
            return a;
        }

        public int[] gia(final int n, final int start, final int end) throws IOException {
            validate(n, start, end);
            final int a[] = new int[n];
            for (int i = start; i < end; i++) {
                a[i] = ni();
            }
            return a;
        }

        public double[] gda(final int n) throws IOException {
            final double a[] = new double[n];
            for (int i = 0; i < n; i++) {
                a[i] = nd();
            }
            return a;
        }

        public double[] gda(final int n, final int start, final int end) throws IOException {
            validate(n, start, end);
            final double a[] = new double[n];
            for (int i = start; i < end; i++) {
                a[i] = nd();
            }
            return a;
        }

        public long[] gla(final int n) throws IOException {
            final long a[] = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = nl();
            }
            return a;
        }

        public long[] gla(final int n, final int start, final int end) throws IOException {
            validate(n, start, end);
            final long a[] = new long[n];
            for (int i = start; i < end; i++) {
                a[i] = nl();
            }
            return a;
        }

        public int[][][] gwtree(final int n) throws IOException {
            final int m = n - 1;
            final int adja[][] = new int[n + 1][];
            final int weight[][] = new int[n + 1][];
            final int from[] = new int[m];
            final int to[] = new int[m];
            final int count[] = new int[n + 1];
            final int cost[] = new int[n + 1];
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
            return new int[][][] { adja, weight };
        }

        public int[][][] gwg(final int n, final int m) throws IOException {
            final int adja[][] = new int[n + 1][];
            final int weight[][] = new int[n + 1][];
            final int from[] = new int[m];
            final int to[] = new int[m];
            final int count[] = new int[n + 1];
            final int cost[] = new int[n + 1];
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
            return new int[][][] { adja, weight };
        }

        public int[][] gtree(final int n) throws IOException {
            final int adja[][] = new int[n + 1][];
            final int from[] = new int[n - 1];
            final int to[] = new int[n - 1];
            final int count[] = new int[n + 1];
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

        public int[][] gg(final int n, final int m) throws IOException {
            final int adja[][] = new int[n + 1][];
            final int from[] = new int[m];
            final int to[] = new int[m];
            final int count[] = new int[n + 1];
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

        public void print(final String s) throws IOException {
            bw.write(s);
        }

        public void println(final String s) throws IOException {
            bw.write(s);
            bw.newLine();
        }

        public void print(final int s) throws IOException {
            bw.write(s + "");
        }

        public void println(final int s) throws IOException {
            bw.write(s + "");
            bw.newLine();
        }

        public void print(final long s) throws IOException {
            bw.write(s + "");
        }

        public void println(final long s) throws IOException {
            bw.write(s + "");
            bw.newLine();
        }

        public void print(final double s) throws IOException {
            bw.write(s + "");
        }

        public void println(final double s) throws IOException {
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

        private void validate(final int n, final int start, final int end) {
            if (start < 0 || end >= n) {
                throw new IllegalArgumentException();
            }
        }
    }
}
