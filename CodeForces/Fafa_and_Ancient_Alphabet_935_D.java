
/**
 * Date: 20 Feb, 2018
 * Link: http://codeforces.com/contest/935/problem/D
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.util.Scanner;

public class Fafa_and_Ancient_Alphabet_935_D {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        long n = in.nextInt();
        long m = in.nextInt();
        long mod = 1000000007;
        long powers[] = new long[200001];
        powers[0] = 1;
        for (int i = 1; i <= 200000; i++) {
            powers[i] = (powers[i - 1] * m) % mod;
        }
        int s1[] = new int[(int) n];
        int s2[] = new int[(int) n];
        int noofz = 0;
        for (int i = 0; i < n; i++) {
            s1[i] = in.nextInt();
            if (s1[i] == 0) {
                noofz++;
            }
        }
        for (int i = 0; i < n; i++) {
            s2[i] = in.nextInt();
            if (s2[i] == 0) {
                noofz++;
            }
        }
        int temp = noofz;
        long ans = 0;
        long equal = 1;
        long all_possible = (long) m * (m - 1) / 2;
        for (int i = 0; i < n; i++) {
            if (s1[i] == 0 && s2[i] == 0) {
                long p = equal * all_possible;
                p %= mod;
                p = p * powers[noofz - 2];
                p %= mod;
                ans += p;
                ans %= mod;
                equal = (equal * m) % mod;
                noofz -= 2;
                continue;
            }
            if (s1[i] == 0 && s2[i] != 0) {
                long p = equal * (m - s2[i]);
                p %= mod;
                p = p * powers[noofz - 1];
                p %= mod;
                ans += p;
                ans %= mod;
                noofz--;
                continue;
            }
            if (s1[i] != 0 && s2[i] == 0) {
                long p = equal * (s1[i] - 1);
                p %= mod;
                p = p * powers[noofz - 1];
                p %= mod;
                ans += p;
                ans %= mod;
                noofz--;
                continue;
            }

            if (s2[i] > s1[i]) {
                break;
            } else if (s2[i] < s1[i]) {
                long p = equal * powers[noofz];
                p %= mod;
                ans += p;
                ans %= mod;
                break;
            }
        }
        ans *= Expo.a_pow_n_mod_m(powers[temp], mod - 2, mod);
        ans %= mod;
        System.out.println(ans);
    }
}

class Expo {

    static long a_pow_n_mod_m(long a, long n, long m) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return a;
        }
        long p = a_pow_n_mod_m(a, n / 2, m);
        if (n % 2 == 0) {
            return (p * p) % m;
        } else {
            return ((p * p) % m * a) % m;
        }
    }
}
