
/**
 * Date: 1 Jun, 2018
 * Link: http://codeforces.com/contest/988/problem/B
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Substrings_Sort_988_B {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();
        String s[] = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = in.next();
        }
        Data d[] = new Data[n];
        for (int i = 0; i < n; i++) {
            d[i] = new Data(s[i], 0);
        }
        (new Heapsort()).sort(d);
        boolean p = true;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (d[j].s.replace(d[i].s, "").length() == d[j].s.length()) {
                    p = false;
                }
            }
        }
        if (p) {
            System.out.println("YES");
            for (int i = 0; i < n; i++) {
                System.out.println(d[i].s);
            }
        } else {
            System.out.println("NO");
        }
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
            for (int i = 0; i < n; i++) {
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

    static class Heapsort {

        private int n;
        private Item a[];

        public void sort(Data a[]) {
            n = a.length;
            this.a = new Item[n];
            for (int i = 0; i < n; i++) {
                this.a[i] = new Item(a[i]);
            }
            sorting();
            n = a.length;
            for (int i = 0; i < n; i++) {
                a[i] = this.a[i].data;
            }
        }

        private void sorting() {
            heapify();
            for (int i = n; i > 0; i--) {
                swap(0, i);
                n = n - 1;
                maxheap(0);
            }
        }

        private void heapify() {
            n = a.length - 1;
            for (int i = n / 2; i >= 0; i--) {
                maxheap(i);
            }
        }

        private void maxheap(int i) {
            int left = 2 * i;
            int right = 2 * i + 1;
            int max = i;
            if (left <= n && a[left].compareTo(a[i]) > 0) {
                max = left;
            }
            if (right <= n && a[right].compareTo(a[max]) > 0) {
                max = right;
            }
            if (max != i) {
                swap(i, max);
                maxheap(max);
            }
        }

        private void swap(int i, int j) {
            Item temp = a[j];
            a[j] = a[i];
            a[i] = temp;
        }

        private class Item implements Comparable<Item> {

            Integer i;
            Long l;
            Character c;
            Double d;
            String s;
            Data data;

            public Item(int a) {
                i = a;
            }

            public Item(long a) {
                l = a;
            }

            public Item(char a) {
                c = a;
            }

            public Item(double a) {
                d = a;
            }

            public Item(Data a) {
                data = a;
            }

            public Item(String a) {
                s = a;
            }

            @Override
            public int compareTo(Item item) {
                if (i != null) {
                    return i.compareTo(item.i);
                } else if (l != null) {
                    return l.compareTo(item.l);
                } else if (c != null) {
                    return c.compareTo(item.c);
                } else if (d != null) {
                    return d.compareTo(item.d);
                } else if (s != null) {
                    return s.compareTo(item.s);
                } else {
                    return data.compareTo(item.data);
                }
            }
        }
    }

    static class Data implements Comparable<Data> {

        String s;
        int a, b;

        public Data(String a, int b) {
            this.s = a;
            this.b = b;
        }

        @Override
        public int compareTo(Data o) {
            if (this.s.length() == o.s.length()) {
                return this.s.compareTo(o.s);
            } else {
                return Integer.compare(this.s.length(), o.s.length());
            }
        }
    }

}
