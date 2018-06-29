
/**
 * Date: 3 Jun, 2018
 * Link: http://codeforces.com/contest/988/problem/E
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Divisibility_by_25_988_E {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        String s = in.next();
        char c[] = s.toCharArray();
        int a[] = new int[10];
        for (int i = 0; i < c.length; i++) {
            a[c[i] - '0']++;
        }
        if (c[0] == '0') {
            System.out.println(-1);
            return;
        }
        int ans = Integer.MAX_VALUE;
        int count = 0;
        if (a[0] >= 2) {
            count = ans(s, '0', '0');
            if (count < ans) {
                ans = count;
            }
        }
        if (a[2] >= 1 && a[5] >= 1) {
            count = ans(s, '2', '5');
            if (count < ans) {
                ans = count;
            }
        }
        if (a[0] >= 1 && a[5] >= 1) {
            count = ans(s, '5', '0');
            if (count < ans) {
                ans = count;
            }
        }
        if (a[7] >= 1 && a[5] >= 1) {
            count = ans(s, '7', '5');
            if (count < ans) {
                ans = count;
            }
        }
        if (ans == Integer.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(ans);
        }
    }

    private static int ans(String s, char c1, char c2) {
        int count = 0;
        char[] c = s.toCharArray();
        count = shift(c, c1, c2);
        if (count == Integer.MAX_VALUE) {
            count = 0;
            c = s.toCharArray();
            int p1 = shiftHelper(c, c1, c2);
            if (p1 != Integer.MAX_VALUE) {
                count += shift(c, c1, c2);
                count += p1;
                return count;
            } else {
                return Integer.MAX_VALUE;
            }
        } else {
            return count;
        }
    }

    private static boolean check(char c[]) {
        return c[0] == '0';
    }

    private static void swap(char c[], int i, int j) {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }

    private static int shiftHelper(char[] c, char c1, char c2) {
        if (c[0] == c2) {
            char temp = c1;
            c1 = c2;
            c2 = temp;
        }
        int in1 = -1;
        for (int i = c.length - 1; i >= 0; i--) {
            if (c[i] == c1) {
                in1 = i;
                break;
            }
        }
        int in2 = -1;
        for (int i = (c1 == c2 ? in1 : c.length) - 1; i >= 0; i--) {
            if (c[i] == c2) {
                in2 = i;
                break;
            }
        }
        boolean p = true;
        int helper = -1;
        for (int i = 1; i < c.length - 1; i++) {
            if (c[i] != '0') {
                if (p && in2 == i) {
                    p = false;
                } else {
                    helper = i;
                    break;
                }
            }
        }
        if (helper == -1) {
            return Integer.MAX_VALUE;
        } else {
            int count = 0;
            for (int i = helper; i > 1; i--) {
                swap(c, i, i - 1);
                count++;
            }
            if (check(c)) {
                return Integer.MAX_VALUE;
            } else {
                return count;
            }
        }
    }

    private static int shift(char c[], char c1, char c2) {
        int in1 = -1;
        for (int i = c.length - 1; i >= 0; i--) {
            if (c[i] == c1) {
                in1 = i;
                break;
            }
        }
        int in2 = -1;
        for (int i = (c1 == c2 ? in1 : c.length) - 1; i >= 0; i--) {
            if (c[i] == c2) {
                in2 = i;
                break;
            }
        }
        int count = 0;
        if (c1 == c2) {
            in2 ^= in1;
            in1 ^= in2;
            in2 ^= in1;
        }
        if (in1 + 1 == in2) {
            for (int i = in2 + 1; i < c.length; i++) {
                count += 2;
            }
            return count;
        }
        if (in1 < in2) {
            for (int i = in1; i < in2 - 1; i++) {
                swap(c, i, i + 1);
                count++;
            }
        } else {
            for (int i = in2; i <= in1 - 1; i++) {
                swap(c, i, i + 1);
                count++;
            }
        }
        if (check(c)) {
            return Integer.MAX_VALUE;
        }
        count += shift(c, c1, c2);
        return count;
    }

    static class FastIO {

        private final BufferedReader br;
        private final BufferedWriter bw;
        private String s[];
        private int index;

        public FastIO() throws IOException {
            br = new BufferedReader(new InputStreamReader(System.in));
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
            for (int i = 0; i < n; i++) {
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
}
