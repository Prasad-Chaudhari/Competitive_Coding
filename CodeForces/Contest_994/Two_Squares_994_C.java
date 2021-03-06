
/**
 * Date: 16 Jun, 2018
 * Link: https://codeforces.com/contest/994/problem/C
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Two_Squares_994_C {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        Point a1 = new Point(in.nd(), in.nd());
        Point a2 = new Point(in.nd(), in.nd());
        Point a3 = new Point(in.nd(), in.nd());
        Point a4 = new Point(in.nd(), in.nd());

        Point b1 = new Point(in.nd(), in.nd());
        Point b2 = new Point(in.nd(), in.nd());
        Point b3 = new Point(in.nd(), in.nd());
        Point b4 = new Point(in.nd(), in.nd());

        if (getArea(a1, a2, b1) + getArea(a2, a3, b1) + getArea(a3, a4, b1) + getArea(a4, a1, b1) == 2 * getArea(a1, a2, a3)) {
            System.out.println("Yes");
        } else if (getArea(a1, a2, b2) + getArea(a2, a3, b2) + getArea(a3, a4, b2) + getArea(a4, a1, b2) == 2 * getArea(a1, a2, a3)) {
            System.out.println("Yes");
        } else if (getArea(a1, a2, b4) + getArea(a2, a3, b4) + getArea(a3, a4, b4) + getArea(a4, a1, b4) == 2 * getArea(a1, a2, a3)) {
            System.out.println("Yes");
        } else if (getArea(a1, a2, b3) + getArea(a2, a3, b3) + getArea(a3, a4, b3) + getArea(a4, a1, b3) == 2 * getArea(a1, a2, a3)) {
            System.out.println("Yes");
        } else if (getArea(b1, b2, a3) + getArea(b2, b3, a3) + getArea(b3, b4, a3) + getArea(b4, b1, a3) == 2 * getArea(b1, b2, b3)) {
            System.out.println("Yes");
        } else if (getArea(b1, b2, a1) + getArea(b2, b3, a1) + getArea(b3, b4, a1) + getArea(b4, b1, a1) == 2 * getArea(b1, b2, b3)) {
            System.out.println("Yes");
        } else if (getArea(b1, b2, a2) + getArea(b2, b3, a2) + getArea(b3, b4, a2) + getArea(b4, b1, a2) == 2 * getArea(b1, b2, b3)) {
            System.out.println("Yes");
        } else if (getArea(b1, b2, a4) + getArea(b2, b3, a4) + getArea(b3, b4, a4) + getArea(b4, b1, a4) == 2 * getArea(b1, b2, b3)) {
            System.out.println("Yes");
        } else if (lineIntersect(a1, a2, b1, b2)) {
            System.out.println("Yes");
        } else if (lineIntersect(a1, a2, b2, b3)) {
            System.out.println("Yes");
        } else if (lineIntersect(a1, a2, b3, b4)) {
            System.out.println("Yes");
        } else if (lineIntersect(a1, a2, b4, b1)) {
            System.out.println("Yes");
        } else if (lineIntersect(a2, a3, b1, b2)) {
            System.out.println("Yes");
        } else if (lineIntersect(a2, a3, b2, b3)) {
            System.out.println("Yes");
        } else if (lineIntersect(a2, a3, b3, b4)) {
            System.out.println("Yes");
        } else if (lineIntersect(a2, a3, b4, b1)) {
            System.out.println("Yes");
        } else if (lineIntersect(a3, a4, b1, b2)) {
            System.out.println("Yes");
        } else if (lineIntersect(a3, a4, b2, b3)) {
            System.out.println("Yes");
        } else if (lineIntersect(a3, a4, b3, b4)) {
            System.out.println("Yes");
        } else if (lineIntersect(a3, a4, b4, b1)) {
            System.out.println("Yes");
        } else if (lineIntersect(a4, a1, b1, b2)) {
            System.out.println("Yes");
        } else if (lineIntersect(a4, a1, b2, b3)) {
            System.out.println("Yes");
        } else if (lineIntersect(a4, a1, b3, b4)) {
            System.out.println("Yes");
        } else if (lineIntersect(a4, a1, b4, b1)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    private static double getArea(Point a, Point b, Point c) {
        double ans = Math.abs(((a.x * (b.y - c.y)) + (b.x * (c.y - a.y)) + (c.x * (a.y - b.y))));
        return ans / 2.0;
    }

    private static boolean lineIntersect(Point a, Point b, Point c, Point d) {
        double ans1 = a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y);
        double ans2 = a.x * (b.y - d.y) + b.x * (d.y - a.y) + d.x * (a.y - b.y);
        double ans3 = c.x * (d.y - a.y) + d.x * (a.y - c.y) + a.x * (c.y - d.y);
        double ans4 = c.x * (d.y - b.y) + d.x * (b.y - c.y) + b.x * (c.y - d.y);
        return ans1 * ans2 <= 0 && ans3 * ans4 <= 0;
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

class Point {

    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
