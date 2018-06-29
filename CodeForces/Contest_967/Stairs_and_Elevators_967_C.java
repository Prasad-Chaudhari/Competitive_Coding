
/**
 * Date: 29 Apr, 2018
 * Link: http://codeforces.com/contest/967/problem/C
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Stairs_and_Elevators_967_C {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int n = in.ni();
        int m = in.ni();
        int cl = in.ni();
        int ce = in.ni();
        int v = in.ni();
        int stairs[] = new int[cl];
        if (cl == 0) {
            String s = in.next();
        } else {
            for (int i = 0; i < cl; i++) {
                int p = in.ni() - 1;
                stairs[i] = p;
            }
        }
        int lifts[] = new int[ce];
        if (ce == 0) {
            String s = in.next();
        } else {
            for (int i = 0; i < ce; i++) {
                int p = in.ni() - 1;
                lifts[i] = p;
            }
        }
        int q = in.ni();
        while (q-- > 0) {
            int x1 = in.ni() - 1;
            int y1 = in.ni() - 1;
            int x2 = in.ni() - 1;
            int y2 = in.ni() - 1;
            if (x2 == x1) {
                System.out.println(Math.abs(y1 - y2));
            } else {
                long time = Long.MAX_VALUE;
                int index1 = Arrays.binarySearch(stairs, y1);
                int index2 = Arrays.binarySearch(lifts, y1);
                if (ce != 0) {
                    if (index2 < 0) {
                        index2 = -(index2 + 1);
                        if (index2 != ce) {
                            time = Math.min(time, lift(x1, y1, x2, y2, lifts[index2], v));
                        }
                        if (index2 != 0) {
                            time = Math.min(time, lift(x1, y1, x2, y2, lifts[index2 - 1], v));
                        }

                    } else {
                        time = Math.min(time, lift(x1, y1, x2, y2, lifts[index2], v));
                    }
                }
                if (cl != 0) {
                    if (index1 < 0) {
                        index1 = -(index1 + 1);
                        if (index1 != cl) {
                            time = Math.min(time, floor(x1, y1, x2, y2, stairs[index1]));
                        }
                        if (index1 != 0) {
                            time = Math.min(time, floor(x1, y1, x2, y2, stairs[index1 - 1]));
                        }
                    } else {
                        time = Math.min(time, floor(x1, y1, x2, y2, stairs[index1]));
                    }
                }
                System.out.println(time);
            }
        }
    }

    private static long lift(int x1, int y1, int x2, int y2, long elevator, int v) {
        long time = Math.abs(y1 - y2);
        if (Math.min(y1, y2) > elevator) {
            time += 2 * (Math.min(y1, y2) - elevator);
        }
        if (Math.max(y1, y2) < elevator) {
            time += 2 * (elevator - Math.max(y1, y2));
        }
        time += Math.abs(x1 - x2) / v;
        if (Math.abs(x1 - x2) % v != 0) {
            time++;
        }
        return time;
    }

    private static long floor(int x1, int y1, int x2, int y2, long stairs) {
        long time = Math.abs(x2 - x1) + Math.abs(y2 - y1);
        if (Math.min(y2, y1) > stairs) {
            time += 2 * (Math.min(y1, y2) - stairs);
        }
        if (Math.max(y2, y1) < stairs) {
            time += (2 * (stairs - Math.max(y2, y1)));
        }
        return time;
    }

    static class FastIO2 {

        private final BufferedReader br;
        private String s[];
        private int index;

        public FastIO2() throws IOException {
            br = new BufferedReader(new InputStreamReader(System.in));
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
