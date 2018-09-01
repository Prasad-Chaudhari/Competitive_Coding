
/**
 * Date: 31 Aug, 2018
 * Link: http://codeforces.com/contest/1028/problem/C
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

public class Rectangles_1028_C {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        Rectangle r[] = new Rectangle[n];
        for (int i = 0; i < n; i++) {
            r[i] = new Rectangle(in.ni(), in.ni(), in.ni(), in.ni());
        }
        Rectangle pre[] = new Rectangle[n];
        Rectangle suf[] = new Rectangle[n];
        pre[0] = r[0];
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1].intersect(r[i]);
        }
        if (pre[n - 1].x1 != Integer.MAX_VALUE) {
            System.out.println(pre[n - 1].x1 + " " + pre[n - 1].y1);
            return;
        }
        suf[n - 1] = r[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suf[i] = suf[i + 1].intersect(r[i]);
        }
        Rectangle ans;
        for (int i = 1; i < n - 1; i++) {
            ans = pre[i - 1].intersect(suf[i + 1]);
            if (ans.x1 != Integer.MAX_VALUE) {
                System.out.println(ans.x1 + " " + ans.y1);
                return;
            }
        }
        if (pre[n - 2].x1 != Integer.MAX_VALUE) {
            System.out.println(pre[n - 2].x1 + " " + pre[n - 2].y1);
            return;
        }
        if (suf[1].x1 != Integer.MAX_VALUE) {
            System.out.print(suf[1].x1 + " " + suf[1].y1);
        }
    }

    static class Rectangle implements Comparable<Rectangle> {

        int x1, y1, x2, y2;

        @Override
        public String toString() {
            return x1 + " " + y1 + " " + x2 + " " + y2;
        }

        public Rectangle(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public Rectangle intersect(Rectangle r) {
            if (x1 > r.x2 || x2 < r.x1 || y1 > r.y2 || y2 < r.y1) {
                return new Rectangle(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
            }
            return new Rectangle(Math.max(x1, r.x1), Math.max(y1, r.y1), Math.min(x2, r.x2), Math.min(y2, r.y2));
        }

        @Override
        public int compareTo(Rectangle r) {
            if (Integer.compare(x1, r.x1) == 0) {
                if (Integer.compare(x2, r.x2) == 0) {
                    if (Integer.compare(y1, r.y1) == 0) {
                        return Integer.compare(y2, r.y2);
                    }
                    return Integer.compare(y1, r.y1);
                }
                return Integer.compare(x2, r.x2);
            }
            return Integer.compare(x1, r.x1);
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
