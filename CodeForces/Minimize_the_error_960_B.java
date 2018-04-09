
/**
 * Date: 7 Apr, 2018
 * Link: http://codeforces.com/contest/960/problem/B
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Minimize_the_error_960_B {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FastIO2 in = new FastIO2();
        int n = in.ni();
        PriorityQueue<Data> pq = new PriorityQueue<Data>(1, (Comparator<? super Data>) new Com());
        int k1 = in.ni();
        int k2 = in.ni();
        int a[] = in.gia(n);
        int b[] = in.gia(n);
        for(int i=0;i<n;i++){
            pq.add(new Data(a[i],b[i]));
        }
        while(k1>0||k2>0){
            Data d= pq.poll();
            if(k1>0&&k2>0){
                if(d.a>d.b){
                    d.a--;
                    k1--;
                }
                else{
                    d.b--;
                    k2--;
                }
            }
            else if(k1>0){
                if(d.a>d.b){
                    d.a--;
                }
                else{
                    d.a++;
                }
                k1--;
            }
            else if(k2>0){
                if(d.b>d.a){
                    d.b--;
                }
                else{
                    d.b++;
                }
                k2--;
            }
            pq.add(d);
        }
        long ans = 0;
        for(int i=0;i<n;i++){
            Data d = pq.poll();
            ans+=(long)(d.a-d.b)*(d.a-d.b);
        }
        System.out.println(ans);
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

class Data {

    int a, b;

    public Data(int a, int b) {
        this.a = a;
        this.b = b;
    }
}

class Com implements Comparator<Data> {

    private long f(Data a){
        return -((long)(a.a-a.b))*(a.a-a.b);
    }
    public int compare(Data a, Data b) {
        if(f(a)-f(b)>0){
            return 1;
        }
        else if(f(a)-f(b)<0){
            return -1;
        }
        else{
            return 0;
        }
    }
}
