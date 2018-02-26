
/**
 * Date: 24 Feb, 2018
 * Link : http://codeforces.com/problemset/problem/5/B
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.*;
import java.util.*;

public class Center_Alignment_5_B {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        ArrayList<String> lstStr = new ArrayList<>();
        String str = null;
        while ((str = reader.readLine()) != null) {
            lstStr.add(str);
        }

        int mxLen = 0;
        for (String s : lstStr) {
            mxLen = Math.max(mxLen, s.length());
        }

        int cnt = 0;
        for (int i = 0; i < lstStr.size(); i++) {
            String s = lstStr.get(i);
            int spaceLeft = (mxLen - s.length()) / 2;
            int spaceRight = mxLen - s.length() - spaceLeft;

            if (spaceLeft != spaceRight) {
                cnt++;
                if (cnt % 2 == 0) {
                    int tmp = spaceLeft;
                    spaceLeft = spaceRight;
                    spaceRight = tmp;
                }
            }
            for (int j = 0; j < spaceLeft; j++) {
                s = " ".concat(s);
            }
            for (int j = 0; j < spaceRight; j++) {
                s = s.concat(" ");
            }
            s = "*".concat(s);
            s = s.concat("*");
            lstStr.set(i, s);
        }

        String newStr = "";
        for (int i = 0; i < mxLen + 2; i++) {
            newStr = newStr.concat("*");
        }

        System.out.println(newStr);
        for (String s : lstStr) {
            System.out.println(s);
        }
        System.out.println(newStr);
    }
}
