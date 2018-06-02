
/**
 * Date: 24 May, 2018
 * Link: https://www.hackerearth.com/challenge/competitive/may-circuits-18/algorithm/plus-plus-60bcac48/
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.util.*;

public class Plus_Plus {

    public static void main(String[] args) throws java.lang.Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int a[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = in.nextInt();
            }
        }
        int max = 0;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                Plus p1 = new Plus(a, i, j);
                for (int k = 1; k < n - 1; k++) {
                    for (int l = 1; l < m - 1; l++) {
                        Plus p2 = new Plus(a, k, l);
                        if (!p1.overlap(p2)) {
                            max = Math.max(max, p1.dotProduct(p2));
                        }
                    }
                }
            }
        }
        System.out.println(max);
    }
}

class Plus {

    int a, b, c, d, e;
    int x, y;

    public Plus(int a[][], int x, int y) {
        this.a = a[x - 1][y];
        b = a[x + 1][y];
        c = a[x][y + 1];
        d = a[x][y - 1];
        e = a[x][y];
        this.x = x;
        this.y = y;
    }

    public boolean overlap(Plus p) {
        return Math.abs(p.x - x) + Math.abs(p.y - y) < 3;
    }

    public int dotProduct(Plus p) {
        return p.a * a + p.b * b + p.c * c + p.d * d + p.e * e;
    }
}
