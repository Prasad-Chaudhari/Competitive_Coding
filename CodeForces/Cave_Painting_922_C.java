
/**
 * Date : 8 Feb, 2018
 * Link : http://codeforces.com/contest/922/problem/C
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.util.LinkedList;
import java.util.Scanner;

public class Cave_Painting_922_C {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        long k = in.nextLong();
        LinkedList<Long> l = new LinkedList<Long>();
        long ans = 1;
        GCD g = new GCD();
        int i = 1;
        l.add(0l);
        while (ans >= 0) {
            ans = ans * i / g.calc_gcd(ans, i);
            l.add(ans);
            i++;
        }
        try {
            long a = l.get((int) k);
            if ((n + 1) % a == 0) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        } catch (Exception e) {
            System.out.println("No");
            System.exit(0);
        }
    }
}

class GCD {

    public GCD() {
        // Sry Nothing to initalise
    }

    public long a_inv_b(long a, long m) {
        if (gcd(a, m) == 1) {
            long m0 = m;
            long y = 0, x = 1;
            if (m == 1) {
                return 0;
            }
            while (a > 1) {
                long q = a / m;
                long t = m;
                m = a % m;
                a = t;
                t = y;
                y = x - q * y;
                x = t;
            }
            if (x < 0) {
                x += m0;
            }
            return x;
        } else {
            return -1;
        }
    }

    public int calc_gcd(int a, int b) {
        if (b > a) {
            a = a ^ b;
            b = a ^ b;
            a = a ^ b;
        }
        return (int) gcd(a, b);
    }

    public long calc_gcd(long a, long b) {
        if (b > a) {
            a = a ^ b;
            b = a ^ b;
            a = a ^ b;
        }
        return gcd(a, b);
    }

    private long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        long gcd = gcd(b, a % b);
        return gcd;
    }
}
