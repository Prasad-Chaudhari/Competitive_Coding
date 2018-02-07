package HackerRank;

/**
 * Link: https://www.hackerrank.com/contests/w36/challenges/ways-to-give-a-check
 * Date: 08/02/2018
 * HackerRankId: prasadc8897
 */
import java.util.*;

public class Ways_to_give_a_check {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int t = in.nextInt();
            for (int a0 = 0; a0 < t; a0++) {
                char[][] board = new char[8][8];
                for (int board_i = 0; board_i < 8; board_i++) {
                    String s = in.next();
                    for (int board_j = 0; board_j < 8; board_j++) {
                        board[board_i][board_j] = s.charAt(0);
                    }
                }
                int result = waysToGiveACheck(board);
                System.out.println(result);
            }
        }
    }
    private static int waysToGiveACheck(char[][] c) {
        LinkedList<Integer> P = new LinkedList<>();
        for (int i = 0; i < 8; i++) {
            if (c[1][i] == 'P') {
                P.add(i);
            }
        }
        int king = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (c[i][j] == 'k') {
                    king = i * 8 + j;
                }
            }
        }
        int count = 0;
        for (int i : P) {
            boolean c1 = shift('N', king, i, c);
            boolean c2 = shift('B', king, i, c);
            boolean c3 = shift('R', king, i, c);
            boolean c4 = shift('Q', king, i, c);
            if (c1) {
                count++;
            }
            if (c2) {
                count++;
            }
            if (c3) {
                count++;
            }
            if (c4) {
                count++;
            }
        }
        return count;
    }

    private static boolean checkByKnight(char[][] c, int king) {
        int x = king / 8;
        int y = king % 8;
        if (validPlace(x - 2, y + 1)) {
            if (c[x - 2][y + 1] == 'N') {
                return true;
            }
        }
        if (validPlace(x - 2, y - 1)) {
            if (c[x - 2][y - 1] == 'N') {
                return true;
            }
        }
        if (validPlace(x - 1, y + 2)) {
            if (c[x - 1][y + 2] == 'N') {
                return true;
            }
        }
        if (validPlace(x - 1, y - 2)) {
            if (c[x - 1][y - 2] == 'N') {
                return true;
            }
        }
        if (validPlace(x + 1, y + 2)) {
            if (c[x + 1][y + 2] == 'N') {
                return true;
            }
        }
        if (validPlace(x + 1, y - 2)) {
            if (c[x + 1][y - 2] == 'N') {
                return true;
            }
        }
        if (validPlace(x + 2, y + 1)) {
            if (c[x + 2][y + 1] == 'N') {
                return true;
            }
        }
        if (validPlace(x + 2, y - 1)) {
            if (c[x + 2][y - 1] == 'N') {
                return true;
            }
        }
        return false;
    }

    private static boolean shift(char white, int king, int pos, char[][] c) {
        c[0][pos] = white;
        c[1][pos] = '#';
        if (checkByKnight(c, king)) {
            return true;
        }
        boolean a1, a2, a3, a4, a5, a6, a7, a8;
        a1 = a2 = a3 = a4 = a5 = a6 = a7 = a8 = false;
        int x = king / 8;
        int y = king % 8;
        for (int i = 1; i < 8; i++) {
            if (a1 && a2 && a3 && a4 && a5 && a6 && a7 && a8) {
                break;
            }
            if (!a1 && validPlace(x - i, y)) {
                if (c[x - i][y] == 'Q' || c[x - i][y] == 'R') {
                    return true;
                }
                if (c[x - i][y] != '#') {
                    a1 = true;
                }
            } else {
                a1 = true;
            }
            if (!a2 && validPlace(x, y - i)) {
                if (c[x][y - i] == 'Q' || c[x][y - i] == 'R') {
                    return true;
                }
                if (c[x][y - i] != '#') {
                    a2 = true;
                }
            } else {
                a2 = true;
            }
            if (!a3 && validPlace(x + i, y)) {
                if (c[x + i][y] == 'Q' || c[x + i][y] == 'R') {
                    return true;
                }
                if (c[x + i][y] != '#') {
                    a3 = true;
                }
            } else {
                a3 = true;
            }
            if (!a4 && validPlace(x, y + i)) {
                if (c[x][y + i] == 'Q' || c[x][y + i] == 'R') {
                    return true;
                }
                if (c[x][y + i] != '#') {
                    a4 = true;
                }
            } else {
                a4 = true;
            }
            if (!a5 && validPlace(x - i, y - i)) {
                if (c[x - i][y - i] == 'B' || c[x - i][y - i] == 'Q') {
                    return true;
                }
                if (c[x - i][y - i] != '#') {
                    a5 = true;
                }
            } else {
                a5 = true;
            }
            if (!a6 && validPlace(x - i, y + i)) {
                if (c[x - i][y + i] == 'B' || c[x - i][y + i] == 'Q') {
                    return true;
                }
                if (c[x - i][y + i] != '#') {
                    a6 = true;
                }
            } else {
                a6 = true;
            }
            if (!a7 && validPlace(x + i, y - i)) {
                if (c[x + i][y - i] == 'B' || c[x + i][y - i] == 'Q') {
                    return true;
                }
                if (c[x + i][y - i] != '#') {
                    a7 = true;
                }
            } else {
                a7 = true;
            }
            if (!a8 && validPlace(x + i, y + i)) {
                if (c[x + i][y + i] == 'B' || c[x + i][y + i] == 'Q') {
                    return true;
                }
                if (c[x + i][y + i] != '#') {
                    a8 = true;
                }
            } else {
                a8 = true;
            }
        }
        return false;
    }

    private static boolean validPlace(int i, int j) {
        if (i < 0 || i >= 8) {
            return false;
        }
        return !(j < 0 || j >= 8);
    }
}
