
/**
 * Date: 26 Oct, 2018
 * Link: http://codeforces.com/problemset/problem/701/C
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

public class They_Are_Everywhere_701_C {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        char c[] = in.next().toCharArray();
        int l = 0;
        int r = n;
        int dis = 0;
        boolean p[] = new boolean[58];
        for (int i = 0; i < n; i++) {
            if (!p[c[i] - 'A']) {
                dis++;
                p[c[i] - 'A'] = true;
            }
        }
        while (l != r) {
            int mid = (l + r) / 2;
            int b[] = new int[58];
            int count = 0;
            for (int i = 0; i < mid; i++) {
                b[c[i] - 'A']++;
            }
            for (int i = 0; i < 58; i++) {
                if (b[i] > 0) {
                    count++;
                }
            }
            boolean ans = count == dis;
            for (int i = mid; i < n; i++) {
                if (ans) {
                    break;
                }
                b[c[i] - 'A']++;
                if (b[c[i] - 'A'] == 1) {
                    count++;
                }
                b[c[i - mid] - 'A']--;
                if (b[c[i - mid] - 'A'] == 0) {
                    count--;
                }
                ans = dis == count;
                if (ans) {
                    break;
                }
            }
            if (ans) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        int mid = (l + r) / 2;
        int b[] = new int[58];
        int count = 0;
        for (int i = 0; i < mid; i++) {
            b[c[i] - 'A']++;
        }
        for (int i = 0; i < 58; i++) {
            if (b[i] > 0) {
                count++;
            }
        }
        boolean ans = count == dis;
        for (int i = mid; i < n; i++) {
            if (ans) {
                break;
            }
            b[c[i] - 'A']++;
            if (b[c[i] - 'A'] == 1) {
                count++;
            }
            b[c[i - mid] - 'A']--;
            if (b[c[i - mid] - 'A'] == 0) {
                count--;
            }
            ans = dis == count;
            if (ans) {
                break;
            }
        }
        if (ans) {
            System.out.println(l);
        } else {
            System.out.println(l - 1);
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
