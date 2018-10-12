
/**
 * Date: 5 Oct, 2018
 * Link: http://codeforces.com/contest/1059/problem/C
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

public class Sequence_Transformation_1059_C {
    
    static FastIO in;
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        in = new FastIO();
        int n = in.ni();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i + 1;
        }
        ans(a, 1, n);
        if (sb.length() > 0) {
            in.print(sb.toString());
        }
    }
    static StringBuilder sb = new StringBuilder();
    
    private static void ans(int[] a, int print, int n) throws IOException {
        switch (n) {
            case 0:
                break;
            case 1:
                sb.append(print).append("");
                break;
            case 2:
                sb.append(print).append(" ").append(2 * print);
                break;
            case 3:
                sb.append(print).append(" ").append(print).append(" ").append(3 * print);
                break;
            default:
                int index = 0;
                for (int i = 0; i < n; i++) {
                    if (a[i] % (2 * print) == 0) {
                        a[index++] = a[i];
                    } else {
                        sb.append(print).append(" ");
                    }
                }
                if (sb.length() > 1000) {
                    in.print(sb.toString());
                    sb = new StringBuilder();
                }
                ans(a, 2 * print, index);
                break;
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
