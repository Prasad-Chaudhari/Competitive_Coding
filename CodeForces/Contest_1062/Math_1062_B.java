
/**
 * Date: 14 Nov, 2018
 * Link: http://codeforces.com/contest/1062/problem/B
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

public class Math_1062_B {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        if (n == 1) {
            System.out.println("1 0");
        } else {
            int primes[] = Primes.getPrimes(1000001);
            int nop = primes.length;
            int freq[] = new int[nop];
            int temp = n;
            for (int i = 0; i < nop; i++) {
                while (temp % primes[i] == 0) {
                    temp /= primes[i];
                    freq[i]++;
                }
            }
            int max = 0;
            for (int i : freq) {
                max = Math.max(max, i);
            }
            int count = 0;
            boolean p = false;
            if (max != 1) {
                for (int i = 1; i < 30; i++) {
                    if (max <= Math.pow(2, i)) {
                        if (max < Math.pow(2, i)) {
                            count++;
                        } else {
                            p = true;
                        }
                        max = (int) Math.pow(2, i);
                        break;
                    }
                }
            }
            if (p) {
                for (int i = 0; i < nop; i++) {
                    if (freq[i] > 0) {
                        if (freq[i] != max) {
                            count++;
                            break;
                        }
                    }
                }
            }
            while (max > 1) {
                max /= 2;
                count++;
            }
            int pro = 1;
            for (int i = 0; i < nop; i++) {
                if (freq[i] != 0) {
                    pro *= primes[i];
                }
            }
            System.out.println(pro + " " + count);
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
