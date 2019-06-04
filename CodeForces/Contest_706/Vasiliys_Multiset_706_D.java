
/**
 * Date: 20 Nov, 2018
 * Link:
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

public class Vasiliys_Multiset_706_D {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        Trie base = new Trie();
        int q = in.ni();
        int x = 0;
        char cc[] = new char[32];
        for (int i = 31; i >= 0; i--) {
            cc[i] = (char) (x % 2 + '0');
            x /= 2;
        }
        Trie curr = base;
        for (int i = 0; i < 32; i++) {
            if (cc[i] == '0') {
                if (curr.left == null) {
                    curr.left = new Trie();
                }
                curr.left.count++;
                curr = curr.left;
            } else {
                if (curr.right == null) {
                    curr.right = new Trie();
                }
                curr.right.count++;
                curr = curr.right;
            }
        }
        while (q-- > 0) {
            char c = in.next().charAt(0);
            if (c == '+') {
                curr = base;
                x = in.ni();
                cc = new char[32];
                for (int i = 31; i >= 0; i--) {
                    cc[i] = (char) (x % 2 + '0');
                    x /= 2;
                }
//                System.out.println(cc);
                for (int i = 0; i < 32; i++) {
                    if (cc[i] == '0') {
                        if (curr.left == null) {
                            curr.left = new Trie();
                        }
                        curr.left.count++;
                        curr = curr.left;
                    } else {
                        if (curr.right == null) {
                            curr.right = new Trie();
                        }
                        curr.right.count++;
                        curr = curr.right;
                    }
                }
            } else if (c == '-') {
                curr = base;
                x = in.ni();
                cc = new char[32];
                for (int i = 31; i >= 0; i--) {
                    cc[i] = (char) (x % 2 + '0');
                    x /= 2;
                }
//                System.out.println(cc);
                for (int i = 0; i < 32; i++) {
                    if (cc[i] == '0') {
                        curr.left.count--;
                        curr = curr.left;
                    } else {
                        curr.right.count--;
                        curr = curr.right;
                    }
                }
            } else {
                curr = base;
                x = in.ni();
                int temp = x;
                cc = new char[32];
                for (int i = 31; i >= 0; i--) {
                    cc[i] = (char) (x % 2 + '0');
                    x /= 2;
                }
//                System.out.println(cc);
                long p = 0;
                for (int i = 0; i < 32; i++) {
                    p *= 2;
                    if (cc[i] == '1') {
                        if (curr.left == null || (curr.left != null && curr.left.count == 0)) {
                            curr = curr.right;
                            p++;
                        } else {
                            curr = curr.left;
                        }
                    } else {
                        if (curr.right == null || (curr.right != null && curr.right.count == 0)) {
                            curr = curr.left;
                        } else {
                            curr = curr.right;
                            p++;
                        }
                    }
                }
                System.out.println(p ^ temp);
            }
        }
    }

    static class Trie {

        Trie left, right;
        int count = 0;

        public Trie() {
            left = null;
            right = null;
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
