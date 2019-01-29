
/**
 * Date: 4 Jan, 2019
 * Link: https://codeforces.com/contest/1097/problem/D
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Makoto_and_a_Blackboard_1097_D {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        long n = in.nl();
        int k = in.ni();
        int primes[] = Primes.getPrimes(40000000);
        long mod = 1000000007;
        long ans = 1;
        int limit = (int) (Math.sqrt(n) + 10);
        for (int i = 0; primes[i] <= limit; i++) {
            int count = 0;
            while (n % primes[i] == 0) {
                n /= primes[i];
                count++;
            }
            if (count >= 1) {
                long dp[][] = new long[count + 1][2];
                long pro = 1;
                for (int j = 0; j <= count; j++) {
                    dp[j][0] = pro;
                    pro *= primes[i];
                    dp[j][0] %= mod;
                }
                for (int j = 1; j <= k; j++) {
                    dp[0][1] = 1;
                    for (int l = 1; l <= count; l++) {
                        dp[l][1] = dp[l - 1][1] + dp[l][0];
                        dp[l][1] %= mod;
                    }
                    for (int l = 1; l <= count; l++) {
                        dp[l][1] *= Expo.a_pow_n_mod_m(l + 1, mod - 2, mod);
                        dp[l][1] %= mod;
                    }
                    for (int l = 0; l <= count; l++) {
                        dp[l][0] = dp[l][1];
                        dp[l][1] = 0;
                    }
                }
                ans *= dp[count][0];
                ans %= mod;
            }
        }
        if (n != 1) {
            int count = 1;
            long dp[][] = new long[count + 1][2];
            long pro = 1;
            for (int j = 0; j <= count; j++) {
                dp[j][0] = pro;
                pro *= n;
                dp[j][0] %= mod;
            }
            for (int j = 1; j <= k; j++) {
                dp[0][1] = 1;
                for (int l = 1; l <= count; l++) {
                    dp[l][1] = dp[l - 1][1] + dp[l][0];
                    dp[l][1] %= mod;
                }
                for (int l = 1; l <= count; l++) {
                    dp[l][1] *= Expo.a_pow_n_mod_m(l + 1, mod - 2, mod);
                    dp[l][1] %= mod;
                }
                for (int l = 0; l <= count; l++) {
                    dp[l][0] = dp[l][1];
                    dp[l][1] = 0;
                }
            }
            ans *= dp[count][0];
            ans %= mod;
        }
        System.out.println(ans);
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
