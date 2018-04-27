
/**
 * Date: 20 Apr, 2018
 * Link: http://codeforces.com/contest/960/problem/D
 * Comment : Never underestimate System.out.print, it can shoot up time limits,
 *           Never overestimate StringBuilder, it can shoot up memory limits.
 * 
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Full_Binary_Tree_Queries_960_D {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        long changev[] = new long[65];
        long noOfNode[] = new long[63];
        for (int i = 0; i < 63; i++) {
            noOfNode[i] = (long) Math.pow(2, i);
        }
        int q = in.ni();
        StringBuilder sb = new StringBuilder();
        long lee = 0;
        while (q-- > 0) {
            int t = in.ni();
            long x = in.nl();
            if (t == 1 || t == 2) {
                long k = in.nl();
                int level_x = findLevel(x);
                k = (k + noOfNode[level_x]) % noOfNode[level_x];
                if (t == 1) {
                    changev[level_x] += (k % noOfNode[level_x] + noOfNode[level_x]) % noOfNode[level_x];
                    changev[level_x] %= noOfNode[level_x];
                } else {
                    for (int i = level_x; i <= 62; i++) {
                        changev[i] += ((noOfNode[i - level_x]) * ((k % noOfNode[i] + noOfNode[i]) % noOfNode[i])) % noOfNode[i];
                        changev[i] %= noOfNode[i];
                    }
                }
            } else {
                long nodePos = findNodePos(x, changev, noOfNode);
                while (nodePos > 0) {
                    long ans = findValueAt(nodePos, changev, noOfNode);
                    sb.append(ans).append(" ");
                    lee += lenngth(ans);
                    lee++;
                    nodePos /= 2;
                }
                sb.append("\n");
                lee++;
                if (lee > 100000) {
                    System.out.print(sb.toString());
                    sb = new StringBuilder();
                    lee = 0;
                }
            }
        }
        System.out.println(sb.toString());
    }

    private static int findLevel(long x) {
        return (int) (Math.log(x) / Math.log(2));
    }

    private static long findNodePos(long x, long[] changev, long[] noofNode) {
        int level_x = findLevel(x);
        return (x + changev[level_x] + noofNode[level_x]) % noofNode[level_x] + noofNode[level_x];
    }

    private static long findValueAt(long nodePos, long[] changev, long[] noOfNode) {
        int level_x = findLevel(nodePos);
        return (nodePos - changev[level_x]) % noOfNode[level_x] + noOfNode[level_x];
    }

    private static int lenngth(long x) {
        return (x + "").length();
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
