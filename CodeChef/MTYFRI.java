
/**
 * Date: 14 May, 2018
 * Link: https://www.codechef.com/MAY18B/problems/MTYFRI
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MTYFRI {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int t = in.ni();
        while (t-- > 0) {
            int n = in.ni();
            int k = in.ni();
            int a[] = in.gia(n);
            Data d[] = new Data[n];
            for (int i = 0; i < n; i++) {
                d[i] = new Data(a[i], i);
            }
            (new Heapsort()).sort(d);
            boolean p[] = new boolean[n];
            int count = n / 2;
            for (int i = n - 1; i >= 0; i--) {
                if (d[i].b % 2 == 1 && count > 0) {
                    p[d[i].b] = true;
                    count--;
                } else {
                    if (k > 0 && count > 0) {
                        p[d[i].b] = true;
                        count--;
                        k--;
                    }
                }
            }
            long sum1 = 0;
            long sum2 = 0;
            for (int i = 0; i < n; i++) {
                if (p[i]) {
                    sum1 += a[i];
                }
            }
            System.out.println("");
            for (int i = 0; i < n; i++) {
                if (!p[i]) {
                    sum2 += a[i];
                }
            }
            if (sum1 > sum2) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        a[i] ^= a[j];
        a[j] ^= a[i];
        a[i] ^= a[j];
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

        int a, b;

        public Data(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Data o) {
            if (this.a == o.a) {
                return Integer.compare(this.b, o.b);
            }
            return Integer.compare(this.a, o.a);
        }
    }
}
