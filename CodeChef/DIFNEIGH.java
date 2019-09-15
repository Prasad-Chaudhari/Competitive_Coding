
/**
 * Date: 9 Jan, 2019
 * Link: https://www.codechef.com/JAN19A/problems/DIFNEIGH
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
import java.util.Set;

class DIFNEIGH {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int t = in.ni();
        while (t-- > 0) {
            int n = in.ni();
            int m = in.ni();
            int a[][] = new int[n][m];
            if (n == 1) {
                for (int i = 0; i < m; i++) {
                    switch (i % 4) {
                        case 0:
                            a[0][i] = 1;
                            break;
                        case 1:
                            a[0][i] = 1;
                            break;
                        case 2:
                            a[0][i] = 2;
                            break;
                        case 3:
                            a[0][i] = 2;
                            break;
                        default:
                            break;
                    }
                }
            } else if (m == 1) {
                for (int i = 0; i < n; i++) {
                    switch (i % 4) {
                        case 0:
                            a[i][0] = 1;
                            break;
                        case 1:
                            a[i][0] = 1;
                            break;
                        case 2:
                            a[i][0] = 2;
                            break;
                        case 3:
                            a[i][0] = 2;
                            break;
                        default:
                            break;
                    }
                }
            } else if (n == 2) {
                if (m == 2) {
                    a[0][0] = 1;
                    a[0][1] = 1;
                    a[1][0] = 2;
                    a[1][1] = 2;
                } else {
                    for (int i = 0; i < m; i++) {
                        switch (i % 6) {
                            case 0:
                                a[0][i] = 1;
                                a[1][i] = 3;
                                break;
                            case 1:
                                a[0][i] = 2;
                                a[1][i] = 2;
                                break;
                            case 2:
                                a[0][i] = 3;
                                a[1][i] = 1;
                                break;
                            case 3:
                                a[0][i] = 3;
                                a[1][i] = 1;
                                break;
                            case 4:
                                a[0][i] = 2;
                                a[1][i] = 2;
                                break;
                            case 5:
                                a[0][i] = 1;
                                a[1][i] = 3;
                                break;
                            default:
                                break;
                        }
                    }
                }
            } else if (m == 2) {
                for (int i = 0; i < n; i++) {
                    switch (i % 6) {
                        case 0:
                            a[i][0] = 1;
                            a[i][1] = 3;
                            break;
                        case 1:
                            a[i][0] = 2;
                            a[i][1] = 2;
                            break;
                        case 2:
                            a[i][0] = 3;
                            a[i][1] = 1;
                            break;
                        case 3:
                            a[i][0] = 3;
                            a[i][1] = 1;
                            break;
                        case 4:
                            a[i][0] = 2;
                            a[i][1] = 2;
                            break;
                        case 5:
                            a[i][0] = 1;
                            a[i][1] = 3;
                            break;
                        default:
                            break;
                    }
                }
            } else {
                int p1 = 3, p2 = 1, p3 = 4, p4 = 2;
                for (int i = 0; i < n; i++) {
                    if (i % 2 == 0) {
                        p1 ^= p2;
                        p2 ^= p1;
                        p1 ^= p2;
                        p3 ^= p4;
                        p4 ^= p3;
                        p3 ^= p4;
                    }
                    if (i % 2 == 0) {
                        for (int j = 0; j <= m / 2; j++) {
                            if (j % 2 == 0) {
                                if (2 * j < m) {
                                    a[i][2 * j] = p1;
                                }
                                if (2 * j + 1 < m) {
                                    a[i][2 * j + 1] = p1;
                                }
                            } else {
                                if (2 * j < m) {
                                    a[i][2 * j] = p2;
                                }
                                if (2 * j + 1 < m) {
                                    a[i][2 * j + 1] = p2;
                                }
                            }
                        }
                    } else {
                        for (int j = 0; j <= m / 2; j++) {
                            if (j % 2 == 0) {
                                if (2 * j < m) {
                                    a[i][2 * j] = p3;
                                }
                                if (2 * j + 1 < m) {
                                    a[i][2 * j + 1] = p4;
                                }
                            }
                            if (j % 2 == 1) {
                                if (2 * j < m) {
                                    a[i][2 * j] = p4;
                                }
                                if (2 * j + 1 < m) {
                                    a[i][2 * j + 1] = p3;
                                }
                            }
                        }
                    }
                }
            }
            int max = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    max = Math.max(max, a[i][j]);
                }
            }
            System.out.println(max);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(a[i][j] + " ");
                }
                System.out.println("");
            }
        }
    }

    private static Set<Integer> neigh(int x, int y, int a[][]) {
        int n = a.length;
        int m = a[0].length;
        Set<Integer> s = new HashSet<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (Math.abs(i + j) == 1) {
                    if (x + i >= 0 && x + i < n && y + j >= 0 && y + j < m) {
                        s.add(a[x + i][y + j]);
                    }
                }
            }
        }
        return s;
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

        public static void sort(int a[]) {
            Data d[] = new Data[a.length];
            for (int i = 0; i < a.length; i++) {
                d[i] = new Data(a[i], 0);
            }
            Arrays.sort(d);
            for (int i = 0; i < a.length; i++) {
                a[i] = d[i].a;
            }
        }
    }
}
//                
