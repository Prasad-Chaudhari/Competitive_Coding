
/**
 * Date: 11 Jun, 2019
 * Link: https://codeforces.com/contest/1182/problem/C
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
import java.util.LinkedList;
import java.util.Map;

public class Beautiful_Lyrics_1182_C {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO in = new FastIO();
        int n = in.ni();

        char c[][] = new char[n][0];
        for (int i = 0; i < n; i++) {
            c[i] = in.next().toCharArray();
        }
        int numberOfVowels[] = new int[n];
        int lastVowelIndex[] = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < c[i].length; j++) {
                if (c[i][j] == 'a' || c[i][j] == 'e' || c[i][j] == 'i' || c[i][j] == 'o' || c[i][j] == 'u') {
                    numberOfVowels[i]++;
                    lastVowelIndex[i] = c[i][j];
                }
            }
        }

        Map<Integer, LinkedList<Integer>> sameNumberOfVowels = new Hashtable<>();
        LinkedList<Data> second = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (!sameNumberOfVowels.keySet().contains(numberOfVowels[i])) {
                sameNumberOfVowels.put(numberOfVowels[i], new LinkedList<>());
            }
            sameNumberOfVowels.get(numberOfVowels[i]).add(i);
        }

        boolean b[] = new boolean[n];

        for (int i : sameNumberOfVowels.keySet()) {
            Map<Integer, LinkedList<Integer>> map = new Hashtable<>();
            for (int j : sameNumberOfVowels.get(i)) {
                if (!map.containsKey(lastVowelIndex[j])) {
                    map.put(lastVowelIndex[j], new LinkedList<>());
                }
                map.get(lastVowelIndex[j]).add(j);
                if (map.get(lastVowelIndex[j]).size() == 2) {
                    second.add(new Data(map.get(lastVowelIndex[j]).remove(), map.get(lastVowelIndex[j]).remove()));
                    b[second.peekLast().a] = true;
                    b[second.peekLast().b] = true;
                }
            }
        }

        LinkedList<Data> first = new LinkedList<>();
        for (int i : sameNumberOfVowels.keySet()) {
            int p = -1;
            int q = -1;
            while (sameNumberOfVowels.get(i).size() > 0) {
                if (sameNumberOfVowels.get(i).size() > 0 && p == -1) {
                    p = sameNumberOfVowels.get(i).removeFirst();
                    if (b[p]) {
                        p = -1;
                    }
                }

                if (sameNumberOfVowels.get(i).size() > 0 && q == -1) {
                    q = sameNumberOfVowels.get(i).removeFirst();
                    if (b[q]) {
                        q = -1;
                    }
                }

                if (p != -1 && q != -1) {
                    first.add(new Data(p, q));
                    p = -1;
                    q = -1;
                }
            }
        }
//        for (Data d : first) {
//            System.out.println(d.a + " " + d.b);
//        }
//        System.out.println("");
//
//        for (Data d : second) {
//            System.out.println(d.a + " " + d.b);
//        }

        LinkedList<String> ff = new LinkedList<>();
        LinkedList<String> ss = new LinkedList<>();
        while (!first.isEmpty() && !second.isEmpty()) {
            Data d1 = first.remove();
            Data d2 = second.remove();
            ff.add(new String(c[d1.a]) + " " + new String(c[d2.a]));
            ss.add(new String(c[d1.b]) + " " + new String(c[d2.b]));
//            b[d1.a] = true;
//            b[d1.b] = true;
//            b[d2.a] = true;
//            b[d2.b] = true;
        }

        while (second.size() > 1) {
            Data d1 = second.remove();
            Data d2 = second.remove();
//            if (!b[d1.a] && !b[d1.b] && !b[d2.a] && !b[d2.b]) {
            ff.add(new String(c[d1.a]) + " " + new String(c[d2.a]));
            ss.add(new String(c[d1.b]) + " " + new String(c[d2.b]));
//            }
        }

        in.println(ff.size());
        while (!ff.isEmpty()) {
            in.println(ff.removeFirst());
            in.println(ss.removeFirst());
        }
        in.bw.flush();
    }

    static class FastIO {

        private final BufferedReader br;
        private final BufferedWriter bw;
        private String s[];
        private int index;
        private final StringBuilder sb;

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

        public int[][][] gwtree(int n) throws IOException {
            int m = n - 1;
            int adja[][] = new int[n + 1][];
            int weight[][] = new int[n + 1][];
            int from[] = new int[m];
            int to[] = new int[m];
            int count[] = new int[n + 1];
            int cost[] = new int[n + 1];
            for (int i = 0; i < m; i++) {
                from[i] = i + 1;
                to[i] = ni();
                cost[i] = ni();
                count[from[i]]++;
                count[to[i]]++;
            }
            for (int i = 0; i <= n; i++) {
                adja[i] = new int[count[i]];
                weight[i] = new int[count[i]];
            }
            for (int i = 0; i < m; i++) {
                adja[from[i]][count[from[i]] - 1] = to[i];
                adja[to[i]][count[to[i]] - 1] = from[i];
                weight[from[i]][count[from[i]] - 1] = cost[i];
                weight[to[i]][count[to[i]] - 1] = cost[i];
                count[from[i]]--;
                count[to[i]]--;
            }
            return new int[][][]{adja, weight};
        }

        public int[][][] gwg(int n, int m) throws IOException {
            int adja[][] = new int[n + 1][];
            int weight[][] = new int[n + 1][];
            int from[] = new int[m];
            int to[] = new int[m];
            int count[] = new int[n + 1];
            int cost[] = new int[n + 1];
            for (int i = 0; i < m; i++) {
                from[i] = ni();
                to[i] = ni();
                cost[i] = ni();
                count[from[i]]++;
                count[to[i]]++;
            }
            for (int i = 0; i <= n; i++) {
                adja[i] = new int[count[i]];
                weight[i] = new int[count[i]];
            }
            for (int i = 0; i < m; i++) {
                adja[from[i]][count[from[i]] - 1] = to[i];
                adja[to[i]][count[to[i]] - 1] = from[i];
                weight[from[i]][count[from[i]] - 1] = cost[i];
                weight[to[i]][count[to[i]] - 1] = cost[i];
                count[from[i]]--;
                count[to[i]]--;
            }
            return new int[][][]{adja, weight};
        }

        public int[][] gtree(int n) throws IOException {
            int adja[][] = new int[n + 1][];
            int from[] = new int[n - 1];
            int to[] = new int[n - 1];
            int count[] = new int[n + 1];
            for (int i = 0; i < n - 1; i++) {
                from[i] = i + 1;
                to[i] = ni();
                count[from[i]]++;
                count[to[i]]++;
            }
            for (int i = 0; i <= n; i++) {
                adja[i] = new int[count[i]];
            }
            for (int i = 0; i < n - 1; i++) {
                adja[from[i]][--count[from[i]]] = to[i];
                adja[to[i]][--count[to[i]]] = from[i];
            }
            return adja;
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

        public void print(int s) throws IOException {
            bw.write(s + "");
        }

        public void println(int s) throws IOException {
            bw.write(s + "");
            bw.newLine();
        }

        public void print(long s) throws IOException {
            bw.write(s + "");
        }

        public void println(long s) throws IOException {
            bw.write(s + "");
            bw.newLine();
        }

        public void print(double s) throws IOException {
            bw.write(s + "");
        }

        public void println(double s) throws IOException {
            bw.write(s + "");
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
