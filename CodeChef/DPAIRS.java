
/**
 * Date: 7 Jan, 2019
 * Link: https://www.codechef.com/JAN19A/problems/DPAIRS
 *
 * @author Prasad-Chaudhari
 * @linkedIn: https://www.linkedin.com/in/prasad-chaudhari-841655a6/
 * @git: https://github.com/Prasad-Chaudhari
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class DPAIRS {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        int m = in.ni();

        int a[] = in.gia(n);
        int shift_a[] = new int[n];
        Data da[] = new Data[n];
        int index_a = 0;
        for (int i = 0; i < n; i++) {
            da[i] = new Data(a[i], i);
        }
        Arrays.sort(da);
        for (int i = 0; i < n - 1; i++) {
            if (da[i].a != da[i + 1].a) {
                a[index_a] = da[i].a;
                shift_a[index_a] = da[i].b;
                index_a++;
            }
        }
        a[index_a] = da[n - 1].a;
        shift_a[index_a] = da[n - 1].b;
        index_a++;

        int b[] = in.gia(m);
        Data db[] = new Data[m];
        for (int i = 0; i < m; i++) {
            db[i] = new Data(b[i], i);
        }
        Arrays.sort(db);
        int index_b = 0;
        int shift_b[] = new int[m];
        for (int i = 0; i < m - 1; i++) {
            if (db[i].a != db[i + 1].a) {
                b[index_b] = db[i].a;
                shift_b[index_b] = db[i].b;
                index_b++;
            }
        }
        b[index_b] = db[m - 1].a;
        shift_b[index_b] = db[m - 1].b;
        index_b++;

        int count = a.length + b.length - 1;
        Set<Integer> s = new HashSet<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < index_b && count > 0; i++) {
            if (!s.contains(a[0] + b[i])) {
                s.add(a[0] + b[i]);
                count--;
                max = Math.max(max, a[0] + shift_b[i]);
                in.println(shift_a[0] + " " + shift_b[i]);
            }
        }
        for (int i = 1; i < index_a && count > 0; i++) {
            int index = Arrays.binarySearch(b, 0, index_b, max - a[i]);
            if (index < 0) {
                index = -(index + 1);
            }
            for (int j = index; j < index_b && count > 0; j++) {
                if (!s.contains(a[i] + b[j])) {
                    s.add(a[i] + b[j]);
                    count--;
                    in.println(shift_a[i] + " " + shift_b[j]);
                    max = Math.max(max, a[i] + b[j]);
                }
            }
        }
        if (count > 0) {
            max = Integer.MIN_VALUE;
            int temp[];
            temp = a;
            a = b;
            b = temp;
            temp = shift_a;
            shift_a = shift_b;
            shift_b = temp;
            index_a ^= index_b;
            index_b ^= index_a;
            index_a ^= index_b;
            for (int i = 0; i < index_b && count > 0; i++) {
                if (!s.contains(a[0] + b[i])) {
                    s.add(a[0] + b[i]);
                    count--;
                    max = Math.max(max, a[0] + a[0] + b[i]);
                    in.println(shift_b[i] + " " + shift_a[0]);
                }
            }
            for (int i = 1; i < index_a && count > 0; i++) {
                int index = Arrays.binarySearch(b, 0, index_b, max - a[i]);
                if (index < 0) {
                    index = -(index + 1);
                }
                for (int j = index; j < index_b && count > 0; j++) {
                    if (!s.contains(a[i] + b[j])) {
                        s.add(a[i] + b[j]);
                        count--;
                        in.println(shift_b[j] + " " + shift_a[i]);
                        max = Math.max(max, a[i] + b[j]);
                    }
                }
            }
        }
        in.bw.flush();
    }

    static class FastIO {

        private final BufferedReader br;
        private final BufferedWriter bw;
        private String s[];
        private int index;
        private StringBuilder sb;

        public FastIO() throws IOException {
            br = new BufferedReader(new InputStreamReader(System.in));
//            br = new BufferedReader(new InputStreamReader(new FileInputStream("input")));
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
