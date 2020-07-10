import java.util.Scanner;

public class Main {
    final static Scanner scanner = new Scanner(System.in);
    static char[][] grid = {{'_', '_', '_'}, {'_', '_', '_'}, {'_', '_', '_'}};
    public static void print() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    public static String analyze() {
        int xCount = 0;
        int oCount = 0;
        int bCount = 0;
        boolean xWon = false;
        boolean oWon = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (grid[i][j]) {
                    case 'X' :
                        xCount++;
                        break;
                    case 'O' :
                        oCount++;
                        break;
                    case '_' :
                        bCount++;
                }
            }
        }
        if (Math.abs(xCount - oCount) > 1) {
            return "Impossible";
        }
        int xD1 = 0;
        int xD2 = 0;
        int oD1 = 0;
        int oD2 = 0;
        for (int i = 0; i < 3; ++i) {
            int xRow = 0;
            int oRow = 0;
            int xCol = 0;
            int oCol = 0;
            for (int j = 0; j < 3; ++j) {
                if (grid[i][j] == 'X') {
                    xRow++;
                } else if (grid[i][j] == 'O') {
                    oRow++;
                }
                if (grid[j][i] == 'X') {
                    xCol++;
                } else if (grid[j][i] == 'O') {
                    oCol++;
                }
            }
            if (grid[i][i] == 'X') {
                xD1++;
            } else if (grid[i][i] == 'O') {
                oD1++;
            }
            if (grid[i][3 - 1 - i] == 'X') {
                xD2++;
            } else if (grid[i][3 - 1 - i] == 'O') {
                oD2++;
            }
            if (xCol == 3 || xRow == 3) {
                xWon = true;
            }
            if (oCol == 3 || oRow == 3) {
                oWon = true;
            }
        }
        if (xD1 == 3 || xD2 == 3) {
            xWon = true;
        }
        if (oD1 == 3 || oD2 == 3) {
            oWon = true;
        }
        if (xWon && oWon) {
            return "Impossible";
        }
        if (xWon && !oWon) {
            return "X wins";
        }
        if (!xWon && oWon) {
            return "O wins";
        }
        // Nobody won. It maybe draw or the game is not finished yet.
        if (bCount == 0) {
            return "Draw";
        } else {
            return "Game not finished";
        }
    }
    public static void init(String input) {
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = input.charAt(k++);
            }
        }
    }
    public static void moveX() {
        boolean loop = true;
        while (loop) {
            loop = false;
            System.out.print("Enter the coordinates: ");
            int x, y;
            try {
                x = scanner.nextInt();
                y = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
                loop = true;
                continue;
            }
            if (x >= 4 || x <= 0 || y >=4 || y <= 0) {
                System.out.println("Coordinates should be from 1 to 3!");
                loop = true;
                continue;
            }
            if(grid[2 - (y - 1)][x - 1] == '_') {
                grid[2 - (y - 1)][x - 1] = 'X';
                break;
            } else {
                System.out.println("This cell is occupied! Choose another one!");
                loop = true;
                continue;
            }
        }
    }
    public static void moveO() {
        boolean loop = true;
        while (loop) {
            loop = false;
            System.out.print("Enter the coordinates: ");
            int x, y;
            try {
                x = scanner.nextInt();
                y = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
                loop = true;
                continue;
            }
            if (x >= 4 || x <= 0 || y >=4 || y <= 0) {
                System.out.println("Coordinates should be from 1 to 3!");
                loop = true;
                continue;
            }
            if(grid[2 - (y - 1)][x - 1] == '_') {
                grid[2 - (y - 1)][x - 1] = 'O';
                break;
            } else {
                System.out.println("This cell is occupied! Choose another one!");
                loop = true;
                continue;
            }
        }
    }
    public static void main(String[] args) {
        // write your code here

        print();
        String ans = null;
        boolean xTurn = true;
        boolean loop =true;
        while (loop) {
            loop = false;
            if (xTurn) {
                moveX();
            } else {
                moveO();
            }
            ans = analyze();
            if ("Game not finished".equals(ans)) {
                loop = true;
            }
            xTurn ^= true;
            print();
        }
        System.out.println(ans);
    }
}
