
/**
 * Date: 13 May, 2018
 * Link: http://codeforces.com/contest/978/problem/F
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Mentors_978_F {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int n = in.ni();
        int k = in.ni();
        int r[] = in.gia(n);
        int count[] = new int[n];
        for (int i = 0; i < k; i++) {
            int a = in.ni() - 1;
            int b = in.ni() - 1;
            if (r[a] > r[b]) {
                count[a]++;
            }
            if (r[b] > r[a]) {
                count[b]++;
            }
        }
        Data d[] = new Data[n];
        for (int i = 0; i < n; i++) {
            d[i] = new Data(r[i], i, count[i]);
        }
        (new Heapsort()).sort(d);
        int sub[] = new int[n];
        for (int i = 1; i < n; i++) {
            if (d[i].a == d[i - 1].a) {
                sub[d[i].b] = sub[d[i - 1].b] + 1;
            }
        }
        int ans[] = new int[n];
        for (int i = 0; i < n; i++) {
            int index = d[i].b;
            ans[index] = i - d[i].c - sub[index];
        }
        for (int i : ans) {
            System.out.print(i + " ");
        }
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

    private static class Heapsort {

        private int n;
        private Item a[];

        public void sort(int a[]) {
            n = a.length;
            this.a = new Item[n];
            for (int i = 0; i < n; i++) {
                this.a[i] = new Item(a[i]);
            }
            sorting();
            n = a.length;
            for (int i = 0; i < n; i++) {
                a[i] = this.a[i].i;
            }
        }

        public void sort(long a[]) {
            n = a.length;
            this.a = new Item[n];
            for (int i = 0; i < n; i++) {
                this.a[i] = new Item(a[i]);
            }
            sorting();
            n = a.length;
            for (int i = 0; i < n; i++) {
                a[i] = this.a[i].l;
            }
        }

        public void sort(char a[]) {
            n = a.length;
            this.a = new Item[n];
            for (int i = 0; i < n; i++) {
                this.a[i] = new Item(a[i]);
            }
            sorting();
            n = a.length;
            for (int i = 0; i < n; i++) {
                a[i] = this.a[i].c;
            }
        }

        public void sort(double a[]) {
            n = a.length;
            this.a = new Item[n];
            for (int i = 0; i < n; i++) {
                this.a[i] = new Item(a[i]);
            }
            sorting();
            n = a.length;
            for (int i = 0; i < n; i++) {
                a[i] = this.a[i].d;
            }
        }

        public void sort(String a[]) {
            n = a.length;
            this.a = new Item[n];
            for (int i = 0; i < n; i++) {
                this.a[i] = new Item(a[i]);
            }
            sorting();
            n = a.length;
            for (int i = 0; i < n; i++) {
                a[i] = this.a[i].s;
            }
        }

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

    private static class Data implements Comparable<Data> {

        int a, b, c;

        public Data(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Data o) {
            if (this.a == o.a) {
                if (this.b == o.b) {
                    return Integer.compare(this.c, o.c);
                }
                return Integer.compare(this.b, o.b);
            }
            return Integer.compare(this.a, o.a);
        }
    }
}
