
/**
 * Date: 12 Feb, 2019
 * Link: https://codeforces.com/contest/1114/problem/F
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

public class Please_another_Queries_on_Array_1114_F {

    public static void main(String[] args) throws IOException {
        FastIO in = new FastIO();
        int n = in.ni();
        int q = in.ni();
        int a[] = in.gia(n);
        int primes[] = Primes.getPrimes(300);
        OrSegmentTree ost = new OrSegmentTree(n);
        long mul[] = new long[primes.length];
        long mod = 1000000007;
        for (int i = 0; i < primes.length; i++) {
            mul[i] = primes[i] - 1;
            mul[i] *= Expo.a_pow_n_mod_m(primes[i], mod - 2, mod);
            mul[i] %= mod;
        }
        ProBIT probit = new ProBIT(n);
        for (int i = 0; i < n; i++) {
            probit.update(n, i, a[i]);
            long p = 0;
            for (int j = primes.length - 1; j >= 0; j--) {
                p *= 2;
                if (a[i] % primes[j] == 0) {
                    p++;
                }
            }
            ost.update(0, n - 1, 1, i, i, p);
        }
        while (q-- > 0) {
            if (in.next().charAt(0) == 'M') {
                int l = in.ni();
                int r = in.ni();
                int x = in.ni();
                probit.update(n, l - 1, x);
                probit.update(n, r, Expo.a_pow_n_mod_m(x, mod - 2, mod));
                long p = 0;
                for (int j = primes.length - 1; j >= 0; j--) {
                    p *= 2;
                    if (x % primes[j] == 0) {
                        p++;
                    }
                }
                ost.update(0, n - 1, 1, l - 1, r - 1, p);
            } else {
                int l = in.ni();
                int r = in.ni();
                long ans = 1;
                ans *= probit.getPro(r - 1);
                if (l > 1) {
                    ans *= Expo.a_pow_n_mod_m(probit.getPro(l - 2), mod - 2, mod);
                }
                ans %= mod;
                long p = ost.query(0, n - 1, 1, l - 1, r - 1);
                int i = 0;
                while (p > 0) {
                    if (p % 2 == 1) {
                        ans *= mul[i];
                        ans %= mod;
                    }
                    p /= 2;
                    i++;
                }
                System.out.println(ans);
//                in.println(ans + "");
            }
        }
        in.bw.flush();
    }

    static class ProBIT {

        long[] BIT;
        long mod = 1000000007;

        public ProBIT(int n) {
            BIT = new long[n + 1];
            Arrays.fill(BIT, 1);
        }

        public long getPro(int index) {
            long pro = 1;
            index++;
            while (index > 0) {
                pro *= BIT[index];
                pro %= mod;
                index -= (index & -index);
            }
            return pro;
        }

        public void update(int n, int index, long v) {
            index++;
            while (index <= n) {
                BIT[index] *= v;
                BIT[index] %= mod;
                index += index & -index;
            }
        }
    }

    static class OrSegmentTree {

        int DEAFAULT = 0;
        long[] tree, lazy;

        public OrSegmentTree(int n) {
            tree = new long[4 * n];
            lazy = new long[4 * n];
        }

        public void update(int start, int end, int pos, int l, int r, long value) {
            if (lazy[pos] != 0) {
                tree[pos] |= lazy[pos];
                if (start != end) {
                    lazy[2 * pos] |= lazy[pos];
                    lazy[2 * pos + 1] |= lazy[pos];
                }
                lazy[pos] = 0;
            }
            if (end < l || r < start) {

            } else if (l <= start && end <= r) {
                tree[pos] |= value;
                if (start != end) {
                    lazy[2 * pos] |= value;
                    lazy[2 * pos + 1] |= value;
                }
            } else {
                int mid = (start + end) / 2;
                update(start, mid, 2 * pos, l, r, value);
                update(mid + 1, end, 2 * pos + 1, l, r, value);
                tree[pos] = tree[2 * pos] | tree[2 * pos + 1];
            }
        }

        public long query(int start, int end, int pos, int l, int r) {
            if (lazy[pos] != 0) {
                tree[pos] |= lazy[pos];
                if (start != end) {
                    lazy[2 * pos] |= lazy[pos];
                    lazy[2 * pos + 1] |= lazy[pos];
                }
                lazy[pos] = 0;
            }
            if (end < l || r < start) {
                return DEAFAULT;
            } else if (l <= start && end <= r) {
                return tree[pos];
            } else {
                int mid = (start + end) / 2;
                long a = query(start, mid, 2 * pos, l, r);
                long b = query(mid + 1, end, 2 * pos + 1, l, r);
                return (a | b);
            }
        }
    }

    static class ProSegmentTree {

        int DEAFAULT = 1;
        long[] tree, lazy;
        long mod = 1000000007;

        public ProSegmentTree(int n) {
            tree = new long[4 * n];
            lazy = new long[4 * n];
            Arrays.fill(lazy, 1);
            Arrays.fill(tree, 1);
        }

        public void update(int start, int end, int pos, int l, int r, long value) {
            if (lazy[pos] != 1) {
                tree[pos] *= Expo.a_pow_n_mod_m(lazy[pos], end - start + 1, mod);
                tree[pos] %= mod;
                if (start != end) {
                    lazy[2 * pos] *= lazy[pos];
                    lazy[2 * pos + 1] *= lazy[pos];
                    lazy[2 * pos] %= mod;
                    lazy[2 * pos + 1] %= mod;
                }
                lazy[pos] = 1;
            }
            if (end < l || r < start) {

            } else if (l <= start && end <= r) {
                tree[pos] *= Expo.a_pow_n_mod_m(value, end - start + 1, mod);
                tree[pos] %= mod;
                if (start != end) {
                    lazy[2 * pos] *= value;
                    lazy[2 * pos + 1] *= value;
                    lazy[2 * pos] %= mod;
                    lazy[2 * pos + 1] %= mod;
                }
            } else {
                int mid = (start + end) / 2;
                update(start, mid, 2 * pos, l, r, value);
                update(mid + 1, end, 2 * pos + 1, l, r, value);
                tree[pos] = (tree[2 * pos] * tree[2 * pos + 1]) % mod;
            }
        }

        public long query(int start, int end, int pos, int l, int r) {
            if (lazy[pos] != 1) {
                tree[pos] *= Expo.a_pow_n_mod_m(lazy[pos], end - start + 1, mod);
                tree[pos] %= mod;
                if (start != end) {
                    lazy[2 * pos] *= lazy[pos];
                    lazy[2 * pos + 1] *= lazy[pos];
                    lazy[2 * pos] %= mod;
                    lazy[2 * pos + 1] %= mod;
                }
                lazy[pos] = 1;
            }
            if (end < l || r < start) {
                return DEAFAULT;
            } else if (l <= start && end <= r) {
                return tree[pos];
            } else {
                int mid = (start + end) / 2;
                long a = query(start, mid, 2 * pos, l, r);
                long b = query(mid + 1, end, 2 * pos + 1, l, r);
                return (a * b) % mod;
            }
        }
    }

    static class Primes {

        public static int[] getPrimes(int n) {
            boolean[] c = new boolean[n + 1];
            int nop = 0;
            int root = (int) (Math.sqrt(n) + 1);
            for (int i = 2; i <= root; i++) {
                if (!c[i]) {
                    for (int j = i * i; j <= n; j = j + i) {
                        c[j] = true;
                    }
                }
            }
            for (int i = 2; i < n; i++) {
                if (!c[i]) {
                    nop++;
                }
            }
            int[] primes = new int[nop];
            nop = 0;
            for (int i = 2; i < n; i++) {
                if (!c[i]) {
                    primes[nop++] = i;
                }
            }
            return primes;
        }
    }

    static class Expo {

        static long a_pow_n_mod_m(long a, long n, long m) {
            if (n == 0) {
                return 1;
            }
            if (n == 1) {
                return a;
            }
            long p = a_pow_n_mod_m(a, n / 2, m);
            if (n % 2 == 0) {
                return (p * p) % m;
            } else {
                return ((p * p) % m * a) % m;
            }
        }

        static long a_pow_n(long a, long n) {
            if (n == 0) {
                return 1;
            }
            if (n == 1) {
                return a;
            }
            long p = a_pow_n(a, n / 2);
            if (n % 2 == 0) {
                return (p * p);
            } else {
                return ((p * p) * a);
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
//            br = new BufferedReader(new FileReader(new File("input")));
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
            int i = 0;
            while (i < n) {
                a[i] = ni();
                ++i;
            }
            return a;
        }

        public int[] gia(int n, int start, int end) throws IOException {
            validate(n, start, end);
            int a[] = new int[n];
            int i = start;
            while (i < end) {
                a[i] = ni();
                ++i;
            }
            return a;
        }

        public double[] gda(int n) throws IOException {
            double a[] = new double[n];
            int i = 0;
            while (i < n) {
                a[i] = nd();
                ++i;
            }
            return a;
        }

        public double[] gda(int n, int start, int end) throws IOException {
            validate(n, start, end);
            double a[] = new double[n];
            int i = start;
            while (i < end) {
                a[i] = nd();
                ++i;
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
            int i = 0;
            while (i < m) {
                from[i] = ni();
                to[i] = ni();
                cost[i] = ni();
                count[from[i]]++;
                count[to[i]]++;
                i++;
            }
            i = 0;
            while (i <= n) {
                adja[i] = new int[count[i]];
                weight[i] = new int[count[i]];
                ++i;
            }
            i = 0;
            while (i < m) {
                adja[from[i]][count[from[i]] - 1] = to[i];
                adja[to[i]][count[to[i]] - 1] = from[i];
                weight[from[i]][count[from[i]] - 1] = cost[i];
                weight[to[i]][count[to[i]] - 1] = cost[i];
                count[from[i]]--;
                count[to[i]]--;
                ++i;
            }
            return new int[][][]{adja, weight};
        }

        public int[][] gtree(int n) throws IOException {
            int adja[][] = new int[n + 1][];
            int from[] = new int[n - 1];
            int to[] = new int[n - 1];
            int count[] = new int[n + 1];
            int i = 0;
            while (i < n - 1) {
                from[i] = i + 1;
                to[i] = ni();
                count[from[i]]++;
                count[to[i]]++;
                ++i;
            }
            i = 0;
            while (i <= n) {
                adja[i] = new int[count[i]];
                ++i;
            }
            i = 0;
            while (i < n - 1) {
                adja[from[i]][--count[from[i]]] = to[i];
                adja[to[i]][--count[to[i]]] = from[i];
                ++i;
            }
            return adja;
        }

        public int[][] gg(int n, int m) throws IOException {
            int adja[][] = new int[n + 1][];
            int from[] = new int[m];
            int to[] = new int[m];
            int count[] = new int[n + 1];
            int i = 0;
            while (i < m) {
                from[i] = ni();
                to[i] = ni();
                count[from[i]]++;
                count[to[i]]++;
                i++;
            }
            i = 0;
            while (i <= n) {
                adja[i] = new int[count[i]];
                ++i;
            }
            i = 0;
            while (i < m) {
                adja[from[i]][--count[from[i]]] = to[i];
                adja[to[i]][--count[to[i]]] = from[i];
                ++i;
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
