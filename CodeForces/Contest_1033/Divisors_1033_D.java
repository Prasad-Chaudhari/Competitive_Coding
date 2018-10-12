
/**
 * Date: 7 Oct, 2018
 * Link: http://codeforces.com/contest/1033/problem/D
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
import java.util.Hashtable;
import java.util.Map;

public class Divisors_1033_D {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        long a[] = in.gla(n);
        Map<Long, Integer> map = new Hashtable<>();
        boolean done[] = new boolean[n];
        GCD g = new GCD();
        long prime[] = new long[n];
        Map<Long, Integer> freq = new Hashtable<>();
        for (int i = 0; i < n; i++) {
            freq.put(a[i], freq.getOrDefault(a[i], 0) + 1);
        }
        n = freq.size();
        a = new long[n];
        int index = 0;
        for (long l : freq.keySet()) {
            a[index++] = l;
        }
        for (int i = 0; i < n; i++) {
            long p = a[i];
            long sqrt = Math.max(0, (long) Math.sqrt(p) - 2);
            while (sqrt * sqrt < p) {
                sqrt++;
            }
            if (sqrt * sqrt == p) {
                long quad = Math.max(0, (long) Math.sqrt(sqrt) - 2);
                while (quad * quad < sqrt) {
                    quad++;
                }
                if (quad * quad == sqrt) {
                    prime[i] = quad;
                } else {
                    prime[i] = sqrt;
                }
                done[i] = true;
                continue;
            }
            long cubrt = Math.max(0, (long) Math.cbrt(p) - 2);
            while (cubrt * cubrt * cubrt < p) {
                cubrt++;
            }
            if (cubrt * cubrt * cubrt == p) {
                prime[i] = cubrt;
                done[i] = true;
            }
        }
        for (int i = 0; i < n; i++) {
            if (!done[i]) {
                for (int j = 0; j < n; j++) {
                    if (prime[j] != 0) {
                        if (a[i] % prime[j] == 0) {
                            prime[i] = prime[j];
                            break;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (!done[i] && !done[j]) {
                    long gcd = g.calc_gcd(a[i], a[j]);
                    if (gcd > 1) {
                        prime[i] = gcd;
                        prime[j] = gcd;
                    }
                }
            }
        }
        long ans = 1;
        for (int i = 0; i < n; i++) {
            if (prime[i] == 0) {
                ans = (ans * (freq.get(a[i]) + 1) * (freq.get(a[i]) + 1)) % 998244353;
            } else {
                int pow = 0;
                long temp = a[i];
                while (a[i] % prime[i] == 0) {
                    a[i] /= prime[i];
                    pow++;
                }
                map.put(prime[i], map.getOrDefault(prime[i], 0) + pow * freq.get(temp));
                if (a[i] != 1) {
                    map.put(a[i], map.getOrDefault(a[i], 0) + 1 * freq.get(temp));
                }
            }
        }
        for (long l : map.keySet()) {
            ans = (ans * (map.get(l) + 1)) % 998244353;
        }
        System.out.println(ans);
    }

    static class GCD {

        public GCD() {
            // Sry Nothing to initalise
        }

        public long a_inv_b(long a, long m) {
            if (gcd(a, m) == 1) {
                long m0 = m;
                long y = 0, x = 1;
                if (m == 1) {
                    return 0;
                }
                while (a > 1) {
                    long q = a / m;
                    long t = m;
                    m = a % m;
                    a = t;
                    t = y;
                    y = x - q * y;
                    x = t;
                }
                if (x < 0) {
                    x += m0;
                }
                return x;
            } else {
                return -1;
            }
        }

        public int calc_gcd(int a, int b) {
            if (b > a) {
                a = a ^ b;
                b = a ^ b;
                a = a ^ b;
            }
            return (int) gcd(a, b);
        }

        public long calc_gcd(long a, long b) {
            if (b > a) {
                a = a ^ b;
                b = a ^ b;
                a = a ^ b;
            }
            return gcd(a, b);
        }

        private long gcd(long a, long b) {
            if (b == 0) {
                return a;
            }
            long gcd = gcd(b, a % b);
            return gcd;
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
    }
}
