
/**
 * Date: 7 Sep, 2018
 * Link: http://codeforces.com/contest/1036/problem/C
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
import java.util.HashSet;

public class Classy_Numbers_1036_C {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int t = in.ni();
        HashSet<String> small = new HashSet<String>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    small.add("" + i + j + k);
                }
            }
        }
        HashSet<Long> large = new HashSet<>();
        String zeros[] = new String[16];
        zeros[0] = "";
        for (int i = 1; i <= 15; i++) {
            zeros[i] = zeros[i - 1] + "0";
        }
        for (String s : small) {
            char c[] = s.toCharArray();
            for (int i = 0; i <= 15; i++) {
                for (int j = 0; j <= 15 - i; j++) {
                    for (int k = 0; k <= 15 - i - j; k++) {
                        for (int l = 0; l <= 15 - i - j - k; l++) {
                            large.add(Long.parseLong(zeros[i] + c[0] + zeros[j] + c[1] + zeros[k] + c[2] + zeros[l]));
                        }
                    }
                }
            }
        }
        long a[] = new long[large.size() + 1];
        int index = 0;
        for (long p : large) {
            a[index++] = p;
        }
        a[index++] = 1000000000000000000l;
        Arrays.sort(a);
        while (t-- > 0) {
            long l = in.nl();
            long r = in.nl();
            int index1 = Arrays.binarySearch(a, l);
            if (index1 < 0) {
                index1 = -(index1 + 1);
            }
            int index2 = Arrays.binarySearch(a, r);
            if (index2 < 0) {
                index2 = -(index2 + 1);
                index2--;
            }
            System.out.println(index2 - index1 + 1);
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

    static class Data implements Comparable<Data> {

        int a, b;

        public Data(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Data o) {
            return Integer.compare(a, o.a);
        }
    }
}