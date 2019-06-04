
/**
 * Date: 3 Feb, 2019
 * Link: https://codeforces.com/contest/1111/problem/C
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
import java.util.Hashtable;
import java.util.Map;

public class Creative_Snap_1111_C {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int k = in.ni();
        long A = in.ni();
        long B = in.ni();
        int a[] = in.gia(k);
        Data.sort(a);
        Map<Integer, Integer> map = new Hashtable<>();
        for (int i = 0; i < k; i++) {
            a[i]--;
        }
        for (int i = 0; i < k; i++) {
            map.put(a[i], map.getOrDefault(a[i], 0) + 1);
        }
        int b[] = new int[k];
        int sum[] = new int[k];
        int index = 0;
        b[index] = a[0];
        sum[index] = map.get(a[0]);
        index++;
        for (int i = 1; i < k; i++) {
            if (a[i] != a[i - 1]) {
                b[index] = a[i];
                sum[index] = map.get(a[i]);
                index++;
            }
        }
        for (int i = 1; i < index; i++) {
            sum[i] += sum[i - 1];
        }
        int l = (int) (Math.pow(2, n) - 1);
        System.out.println(ans(b, 0, l, A, B, sum, index));
    }

    private static long ans(int a[], int start, int end, long A, long B, int sum[], int index) {
        if (end == start) {
            if (Arrays.binarySearch(a, 0, index, end) >= 0) {
                int inr = Arrays.binarySearch(a, 0, index, end);
                int total = 0;
                if (inr >= 0 && inr < index) {
                    total += sum[inr];
                }
                inr--;
                if (inr >= 0 && inr < index) {
                    total -= sum[inr];
                }
                return B * total * 1;
            } else {
                return A;
            }
        }
        int ins = Arrays.binarySearch(a, 0, index, start);
        if (ins < 0) {
            ins = -(ins + 1);
            ins--;
        } else {
            ins--;
        }
        int inr = Arrays.binarySearch(a, 0, index, end);
        if (inr < 0) {
            inr = -(inr + 1);
            inr--;
        }
        if (inr - ins == 0) {
            return A;
        } else {
            int mid = (start + end) / 2;

            long ans1 = ans(a, start, mid, A, B, sum, index);
            ans1 += ans(a, mid + 1, end, A, B, sum, index);

            int total = 0;
            if (inr >= 0 && inr < index) {
                total += sum[inr];
            }
            if (ins >= 0 && ins < index) {
                total -= sum[ins];
            }
            long ans2 = 1l * B * (total) * (end - start + 1);
            return Math.min(ans1, ans2);
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
//            br = new BufferedReader(new FileReader(new File("input")));
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
