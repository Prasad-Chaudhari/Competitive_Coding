
/**
 * Date: 14 Apr, 2018
 * Link: http://codeforces.com/contest/962/problem/D
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Merge_Equals_962_D {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int n = in.ni();
        long a[] = in.gla(n);
        PriorityQueue<Long> pq = new PriorityQueue<>();
        Map<Long, PriorityQueue<Long>> map = new HashMap<>();
        Set<Long> s = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (s.contains(a[i])) {
                map.get(a[i]).add((long) i);
            } else {
                pq.add(a[i]);
                s.add(a[i]);
                map.put(a[i], new PriorityQueue<>());
                map.get(a[i]).add((long) i);
            }
        }
        while (!pq.isEmpty()) {
            long p = pq.remove();
            PriorityQueue<Long> pq1 = map.get(p);
            while (pq1.size() > 1) {
                pq1.remove();
                Long pw = pq1.remove();
                if (s.contains(2 * p)) {
                    map.get(2 * p).add(pw);
                } else {
                    s.add(2 * p);
                    pq.add(2 * p);
                    map.put(p * 2, new PriorityQueue<Long>());
                    map.get(2 * p).add(pw);
                }
            }
        }
        int count = 0;
        PriorityQueue<Data2> pq3 = new PriorityQueue<>(new Comp2());
        for (long i : map.keySet()) {
            PriorityQueue<Long> pq1 = map.get(i);
            while (!pq1.isEmpty()) {
                pq3.add(new Data2(pq1.remove(), i));
                count++;
            }
        }
        System.out.println(count);
        while (!pq3.isEmpty()) {
            Data2 d = pq3.remove();
            System.out.print(d.b + " ");
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
}

class Data2 {

    long a, b;

    public Data2(long a, long b) {
        this.a = a;
        this.b = b;
    }
}

class Comp2 implements Comparator<Data2> {

    @Override
    public int compare(Data2 a, Data2 b) {
        if (a.a == b.a) {
            return Long.compare(a.b, b.b);
        } else {
            return Long.compare(a.a, b.a);
        }
    }
}
