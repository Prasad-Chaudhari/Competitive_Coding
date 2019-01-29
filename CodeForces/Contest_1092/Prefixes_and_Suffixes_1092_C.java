
/**
 * Date: 22 Dec, 2018
 * Link: https://codeforces.com/contest/1092/problem/C
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

public class Prefixes_and_Suffixes_1092_C {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int f = 2 * n - 2;
        String s[] = new String[f];
        String sample[][] = new String[f][4];
        int index[] = new int[f];
        for (int i = 0; i < f; i++) {
            s[i] = in.next();
        }
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < f; j++) {
                if (i != j) {
                    if (s[i].length() + s[j].length() == n) {
                        sample[i][index[i]++] = s[i] + s[j];
                        sample[i][index[i]++] = s[j] + s[i];
                    }
                }
            }
        }
        String ans = "";
        for (int i = 0; i < 4; i++) {
            int count = 0;
            for (int j = 0; j < f; j++) {
                for (int k = 0; k < 4; k++) {
                    if (sample[0][i].equals(sample[j][k])) {
                        count++;
                        break;
                    }
                }
            }
            if (count == f) {
                ans = sample[0][i];
                break;
            }
        }
        char c[] = new char[f];
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < f; j++) {
                if (s[i].length() + s[j].length() == n) {
                    if (c[i] != 'P' && c[i] != 'S') {
                        if (ans.equals(s[i] + s[j])) {
                            c[i] = 'P';
                            c[j] = 'S';
                        } else if (ans.equals(s[j] + s[i])) {
                            c[j] = 'P';
                            c[i] = 'S';
                        }
                    }
                }
            }
        }
        System.out.println(c);
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
        String c;

        public Data(int a, int b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Data o) {
            return Integer.compare(c.length(), o.c.length());
        }
    }
}
