
/**
 * Date: 29 Apr, 2018
 * Link: http://codeforces.com/contest/967/problem/B
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Watering_System_967_B {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int n = in.ni();
        int a = in.ni();
        int b = in.ni();
        int s[] = in.gia(n);
        long sum = 0;
        double first = s[0];
        Data d[] = new Data[n];
        for (int i = 0; i < n; i++) {
            sum += s[i];
            d[i] = new Data(s[i], i);
        }
        Arrays.sort(d, new Com());
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (d[i].b == 0) {
                continue;
            }
            if (b > (first * a) / (sum * 1.0)) {
                sum -= d[i].a;
                count++;
            } else {
                System.out.println(count);
                return;
            }
        }
        System.out.println(n - 1);
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

    private static class Data {

        int a, b;

        public Data(int a, int b) {
            this.a = a;
            this.b = b;
        }

    }

    private static class Com implements Comparator<Data> {

        @Override
        public int compare(Data a, Data b) {
            if (a.a == b.a) {
                if (a.b == b.b) {
                    return 0;
                } else {
                    return a.b - b.b;
                }
            } else {
                return a.a - b.a;
            }
        }
    }

}
