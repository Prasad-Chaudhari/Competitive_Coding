
/**
 * Date: 17 Sep, 2018
 * Link: http://codeforces.com/contest/1042/problem/C
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

public class Array_Product_1042_C {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int neg = 0;
        int zero = 0;
        int pos = 0;
        int a[] = in.gia(n);
        for (int i : a) {
            if (i == 0) {
                zero++;
            }
            if (i < 0) {
                neg++;
            }
            if (i > 0) {
                pos++;
            }
        }
        Data d_neg[] = new Data[neg];
        Data d_zero[] = new Data[zero];
        Data d_pos[] = new Data[pos];
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == 0) {
                d_zero[index++] = new Data(a[i], i + 1);
            }
        }
        index = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] > 0) {
                d_pos[index++] = new Data(a[i], i + 1);
            }
        }
        index = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] < 0) {
                d_neg[index++] = new Data(a[i], i + 1);
            }
        }
        Arrays.sort(d_neg);
        Arrays.sort(d_zero);
        Arrays.sort(d_pos);
        if (pos == 0) {
            if (neg == 0) {
                for (int i = 1; i < n; i++) {
                    in.println("1 " + i + " " + (i + 1));
                }
            } else {
                if (zero == 0) {
                    if (neg % 2 == 0) {
                        for (int i = 0; i < neg - 1; i++) {
                            in.println("1 " + d_neg[i].b + " " + d_neg[i + 1].b);
                        }
                    } else {
                        System.out.println("2 " + d_neg[neg - 1].b);
                        for (int i = 0; i < neg - 2; i++) {
                            in.println("1 " + d_neg[i].b + " " + d_neg[i + 1].b);
                        }
                    }
                } else {
                    if (neg % 2 == 0) {
                        for (int i = 0; i < zero - 1; i++) {
                            in.println("1 " + d_zero[i].b + " " + d_zero[i + 1].b);
                        }
                        in.println("2 " + d_zero[zero - 1].b);
                        for (int i = 0; i < neg - 1; i++) {
                            in.println("1 " + d_neg[i].b + " " + d_neg[i + 1].b);
                        }
                    } else {
                        in.println("1 " + d_neg[neg - 1].b + " " + d_zero[0].b);
                        for (int i = 0; i < zero - 1; i++) {
                            in.println("1 " + d_zero[i].b + " " + d_zero[i + 1].b);
                        }
                        if (neg - 2 >= 0) {
                            in.println("2 " + d_zero[zero - 1].b);
                            for (int i = 0; i < neg - 2; i++) {
                                in.println("1 " + d_neg[i].b + " " + d_neg[i + 1].b);
                            }
                        }
                    }
                }
            }
        } else {
            if (neg == 0) {
                if (zero == 0) {
                    for (int i = 0; i < pos - 1; i++) {
                        in.println("1 " + d_pos[i].b + " " + d_pos[i + 1].b);
                    }
                } else {
                    for (int i = 0; i < zero - 1; i++) {
                        in.println("1 " + d_zero[i].b + " " + d_zero[i + 1].b);
                    }
                    in.println("2 " + d_zero[zero - 1].b);
                    for (int i = 0; i < pos - 1; i++) {
                        in.println("1 " + d_pos[i].b + " " + d_pos[i + 1].b);
                    }
                }
            } else {
                if (zero == 0) {
                    if (neg % 2 == 0) {
                        for (int i = 0; i < neg - 1; i++) {
                            in.println("1 " + d_neg[i].b + " " + d_neg[i + 1].b);
                        }
                        for (int i = 0; i < pos - 1; i++) {
                            in.println("1 " + d_pos[i].b + " " + d_pos[i + 1].b);
                        }
                        in.println("1 " + d_neg[neg - 1].b + " " + d_pos[pos - 1].b);
                    } else {
                        for (int i = 0; i < neg - 2; i++) {
                            in.println("1 " + d_neg[i].b + " " + d_neg[i + 1].b);
                        }
                        for (int i = 0; i < pos - 1; i++) {
                            in.println("1 " + d_pos[i].b + " " + d_pos[i + 1].b);
                        }
                        if (neg - 2 >= 0) {
                            in.println("1 " + d_neg[neg - 2].b + " " + d_pos[pos - 1].b);
                        }
                        in.println("2 " + d_neg[neg - 1].b);
                    }
                } else {
                    if (neg % 2 == 0) {
                        for (int i = 0; i < zero - 1; i++) {
                            in.println("1 " + d_zero[i].b + " " + d_zero[i + 1].b);
                        }
                        in.println("2 " + d_zero[zero - 1].b);
                        for (int i = 0; i < neg - 1; i++) {
                            in.println("1 " + d_neg[i].b + " " + d_neg[i + 1].b);
                        }
                        for (int i = 0; i < pos - 1; i++) {
                            in.println("1 " + d_pos[i].b + " " + d_pos[i + 1].b);
                        }
                        in.println("1 " + d_neg[neg - 1].b + " " + d_pos[pos - 1].b);
                    } else {
                        for (int i = 0; i < zero - 1; i++) {
                            in.println("1 " + d_zero[i].b + " " + d_zero[i + 1].b);
                        }
                        in.println("1 " + d_neg[neg - 1].b + " " + d_zero[zero - 1].b);
                        in.println("2 " + d_zero[zero - 1].b);
                        for (int i = 0; i < neg - 2; i++) {
                            in.println("1 " + d_neg[i].b + " " + d_neg[i + 1].b);
                        }
                        for (int i = 0; i < pos - 1; i++) {
                            in.println("1 " + d_pos[i].b + " " + d_pos[i + 1].b);
                        }
                        if (neg - 2 >= 0) {
                            in.println("1 " + d_neg[neg - 2].b + " " + d_pos[pos - 1].b);
                        }
                    }
                }
            }
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
            if (a == o.a) {
                return Integer.compare(b, o.b);
            }
            return Integer.compare(a, o.a);
        }
    }
}
