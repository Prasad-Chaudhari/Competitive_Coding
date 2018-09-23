
/**
 * Date: 17 Sep, 2018
 * Link: http://codeforces.com/contest/1042/problem/B
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

public class Vitamins_1042_B {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int min_a = 10000000;
        int min_b = 10000000;
        int min_c = 10000000;
        int c[] = new int[n];
        String s[] = new String[n];
        boolean p_a = false;
        boolean p_b = false;
        boolean p_c = false;
        for (int i = 0; i < n; i++) {
            c[i] = in.ni();
            s[i] = in.next();
            char k[] = s[i].toCharArray();
            Arrays.sort(k);
            s[i] = new String(k);
            if (s[i].contains("A")) {
                p_a = true;
            }
            if (s[i].contains("B")) {
                p_b = true;
            }
            if (s[i].contains("C")) {
                p_c = true;
            }
        }
        if (!(p_a && p_b && p_c)) {
            System.out.println("-1");
            return;
        }
        long cost = 30000000;
        for (int i = 0; i < n; i++) {
            if (s[i].length() == 3) {
                if (c[i] < cost) {
                    cost = c[i];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (s[i].equals("A") && (s[j].equals("BC"))) {
                    if (c[i] + c[j] < cost) {
                        cost = c[i] + c[j];
                    }
                }
                if (s[i].equals("B") && (s[j].equals("AC"))) {
                    if (c[i] + c[j] < cost) {
                        cost = c[i] + c[j];
                    }
                }
                if (s[i].equals("C") && s[j].equals("AB")) {
                    if (c[i] + c[j] < cost) {
                        cost = c[i] + c[j];
                    }
                }
                if (s[i].equals("A") && s[j].equals("ABC")) {
                    if (c[i] + c[j] < cost) {
                        cost = c[i] + c[j];
                    }
                }
                if (s[i].equals("C") && s[j].equals("ABC")) {
                    if (c[i] + c[j] < cost) {
                        cost = c[i] + c[j];
                    }
                }
                if (s[i].equals("B") && s[j].equals("ABC")) {
                    if (c[i] + c[j] < cost) {
                        cost = c[i] + c[j];
                    }
                }
                if (s[i].equals("AB") && s[j].equals("BC")) {
                    if (c[i] + c[j] < cost) {
                        cost = c[i] + c[j];
                    }
                }
                if (s[i].equals("BC") && s[j].equals("AC")) {
                    if (c[i] + c[j] < cost) {
                        cost = c[i] + c[j];
                    }
                }
                if (s[i].equals("AC") && s[j].equals("AB")) {
                    if (c[i] + c[j] < cost) {
                        cost = c[i] + c[j];
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (s[i].equals("A")) {
                if (c[i] < min_a) {
                    min_a = c[i];
                }
            }
            if (s[i].equals("B")) {
                if (c[i] < min_b) {
                    min_b = c[i];
                }
            }
            if (s[i].equals("C")) {
                if (c[i] < min_c) {
                    min_c = c[i];
                }
            }
        }
        cost = Math.min(min_a + min_b + min_c, cost);
        System.out.println(cost);
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
            if (a != o.a) {
                return Integer.compare(b, o.b);
            }
            return Integer.compare(a, o.a);
        }
    }
}
