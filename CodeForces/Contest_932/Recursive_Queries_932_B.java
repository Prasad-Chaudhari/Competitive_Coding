
/**
 * Date: 15 Feb, 2018
 * Link : http://codeforces.com/problemset/problem/932/B
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Recursive_Queries_932_B {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ans[] = new int[1000000 + 1];
        int root = 1000;
        int freq[][] = new int[root + 1][10];
        for (int i = 1; i <= 1000000; i++) {
            int gans = g(i);
            ans[i] = gans;
            freq[i / root][gans]++;
        }
        int q = Integer.parseInt(st.nextToken());
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            System.out.println(getAns(l, r, k, freq, ans));
        }
    }

    static int getAns(int l, int r, int k, int[][] freq, int[] gans) {
        int ans = 0;
        int root = 1000;
        int mull = l / root;
        int modl = l % root;
        int mulr = r / root;
        int modr = r % root;
        if (mull == mulr) {
            for (int i = l; i <= r; i++) {
                if (gans[i] == k) {
                    ans++;
                }
            }
        } else {
            for (int i = l; i < (mull + 1) * root; i++) {
                if (gans[i] == k) {
                    ans++;
                }
            }
            for (int i = mull + 1; i < mulr; i++) {
                ans += freq[i][k];
            }
            for (int i = mulr * root; i <= r; i++) {
                if (gans[i] == k) {
                    ans++;
                }
            }
        }
        return ans;
    }

    static int g(int n) {
        if (n < 10) {
            return n;
        } else {
            return g(f(n));
        }
    }

    static int f(int n) {
        int p = 1;
        while (n > 0) {
            if (n % 10 != 0) {
                p *= n % 10;
            }
            n /= 10;
        }
        return p;
    }
}
