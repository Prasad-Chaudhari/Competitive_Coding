
/**
 * Date: 02 Jul, 2018
 * Link: https://www.codeforces.com/contest/129/problem/C
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

public class Statues_129_C {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        char c[][] = new char[8][8];
        for (int i = 0; i < 8; i++) {
            c[i] = in.next().toCharArray();
        }
        int m_x = -1;
        int m_y = -1;
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (c[i][j] == 'M') {
                    m_x = i;
                    m_y = j;
                } else if (c[i][j] == 'S') {
                    count++;
                }
            }
        }
        int ss[] = new int[count];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (c[i][j] == 'S') {
                    ss[--count] = i * 8 + j;
                }
            }
        }
        boolean b[][] = new boolean[8][8];
        b[m_x][m_y] = true;
        if (ans(m_x, m_y, ss, 1, b)) {
            System.out.println("WIN");
        } else {
            System.out.println("LOSE");
        }
    }

    private static boolean ans(int m_x, int m_y, int[] ss, int depth, boolean b[][]) {
        // System.out.println(m_x+" "+m_y+" "+depth);
        if (m_x < 0 || m_x > 7 || m_y < 0 || m_y > 7) {
            return false;
        }
        if (m_x == 0 && m_y == 7) {
            return true;
        }
        if (m_x + 1 < 8 && m_y + 1 < 8 && !b[m_x + 1][m_y + 1]) {
            int pos = (m_x + 1) * 8 + m_y + 1;
            boolean p = false;
            for (int i = 0; i < ss.length; i++) {
                if (pos == ss[i] + (depth - 1) * 8) {
                    p = true;
                    break;
                }
                if (pos == ss[i] + depth * 8) {
                    p = true;
                    break;
                }

            }
            if (!p) {
                b[m_x + 1][m_y + 1] = true;
                if (ans(m_x + 1, m_y + 1, ss, depth + 1, b)) {
                    return true;
                }
                b[m_x + 1][m_y + 1] = false;
            }
        }
        if (m_x + 1 < 8 && m_y < 8 && !b[m_x + 1][m_y]) {
            int pos = (m_x + 1) * 8 + m_y;
            boolean p = false;
            for (int i = 0; i < ss.length; i++) {
                if (pos == ss[i] + (depth - 1) * 8) {
                    p = true;
                    break;
                }
                if (pos == ss[i] + depth * 8) {
                    p = true;
                    break;
                }
            }
            if (!p) {
                b[m_x + 1][m_y] = true;
                if (ans(m_x + 1, m_y, ss, depth + 1, b)) {
                    return true;
                }
                b[m_x + 1][m_y] = false;
            }
        }
        if (m_x + 1 < 8 && m_y - 1 >= 0 && !b[m_x + 1][m_y - 1]) {
            int pos = (m_x + 1) * 8 + m_y - 1;
            boolean p = false;
            for (int i = 0; i < ss.length; i++) {
                if (pos == ss[i] + (depth - 1) * 8) {
                    p = true;
                    break;
                }
                if (pos == ss[i] + depth * 8) {
                    p = true;
                    break;
                }
            }
            if (!p) {
                b[m_x + 1][m_y - 1] = true;
                if (ans(m_x + 1, m_y - 1, ss, depth + 1, b)) {
                    return true;
                }
                b[m_x + 1][m_y - 1] = false;
            }
        }
        if (m_x < 8 && m_y - 1 >= 0 && !b[m_x][m_y - 1]) {
            int pos = (m_x) * 8 + m_y - 1;
            boolean p = false;
            for (int i = 0; i < ss.length; i++) {
                if (pos == ss[i] + (depth - 1) * 8) {
                    p = true;
                    break;
                }
                if (pos == ss[i] + depth * 8) {
                    p = true;
                    break;
                }
            }
            if (!p) {
                b[m_x][m_y - 1] = true;
                if (ans(m_x, m_y - 1, ss, depth + 1, b)) {
                    return true;
                }
                b[m_x][m_y - 1] = false;
            }
        }
        if (m_x - 1 >= 0 && m_y - 1 >= 0 && !b[m_x - 1][m_y - 1]) {
            int pos = (m_x - 1) * 8 + m_y - 1;
            boolean p = false;
            for (int i = 0; i < ss.length; i++) {
                if (pos == ss[i] + (depth - 1) * 8) {
                    p = true;
                    break;
                }
                if (pos == ss[i] + depth * 8) {
                    p = true;
                    break;
                }
            }
            if (!p) {
                b[m_x - 1][m_y - 1] = true;
                if (ans(m_x - 1, m_y - 1, ss, depth + 1, b)) {
                    return true;
                }
                b[m_x - 1][m_y - 1] = false;
            }
        }
        if (m_x - 1 >= 0 && m_y < 8 && !b[m_x - 1][m_y]) {
            int pos = (m_x - 1) * 8 + m_y;
            boolean p = false;
            for (int i = 0; i < ss.length; i++) {
                if (pos == ss[i] + (depth - 1) * 8) {
                    p = true;
                    break;
                }
                if (pos == ss[i] + depth * 8) {
                    p = true;
                    break;
                }
            }
            if (!p) {
                b[m_x - 1][m_y] = true;
                if (ans(m_x - 1, m_y, ss, depth + 1, b)) {
                    return true;
                }
                b[m_x - 1][m_y] = false;
            }
        }
        if (m_x - 1 >= 0 && m_y + 1 < 8 && !b[m_x - 1][m_y + 1]) {
            int pos = (m_x - 1) * 8 + m_y + 1;
            boolean p = false;
            for (int i = 0; i < ss.length; i++) {
                if (pos == ss[i] + (depth - 1) * 8) {
                    p = true;
                    break;
                }
                if (pos == ss[i] + depth * 8) {
                    p = true;
                    break;
                }
            }
            if (!p) {
                b[m_x - 1][m_y + 1] = true;
                if (ans(m_x - 1, m_y + 1, ss, depth + 1, b)) {
                    return true;
                }
                b[m_x - 1][m_y + 1] = false;
            }
        }
        if (m_x < 8 && m_y + 1 < 8 && !b[m_x][m_y + 1]) {
            int pos = (m_x) * 8 + m_y + 1;
            boolean p = false;
            for (int i = 0; i < ss.length; i++) {
                if (pos == ss[i] + (depth - 1) * 8) {
                    p = true;
                    break;
                }
                if (pos == ss[i] + depth * 8) {
                    p = true;
                    break;
                }
            }
            if (!p) {
                b[m_x][m_y + 1] = true;
                if (ans(m_x, m_y + 1, ss, depth + 1, b)) {
                    return true;
                }
                b[m_x][m_y + 1] = false;
            }
        }
        if (depth < 9) {
            int pos = (m_x) * 8 + m_y;
            boolean p = false;
            for (int i = 0; i < ss.length; i++) {
                if (pos == ss[i] + (depth) * 8) {
                    p = true;
                    break;
                }
            }
            if (!p) {
                b[m_x][m_y] = true;
                if (ans(m_x, m_y, ss, depth + 1, b)) {
                    return true;
                }
                b[m_x][m_y] = false;
            }
        }
        return false;
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
}
