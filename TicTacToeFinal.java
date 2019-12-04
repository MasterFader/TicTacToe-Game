package tictactoe;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] matrix = new char[3][3];
        char[][] inputMatrix = new char[3][3];

        int x = 0;
        int y = 0;

        boolean stringCheck = false;
        String str1 = null;
        String str2 = null;

        int xCount = 0;
        int oCount = 0;

        boolean xWins = false;
        boolean oWins = false;
        boolean emptyCells = false;
        boolean impossible = false;

        printTicTacToe(matrix);

    for(int turn=0;turn<9;turn++){
        do{
            do{
                do{
                    do{

                        System.out.println("Enter the coordinates: ");
                        stringCheck = false;
                        str1 = scanner.next();
                        if(isNumber(str1)){
                            stringCheck = true;
                            x = Integer.parseInt(str1);
                            continue;
                        }
                        System.out.println("You should enter numbers!");
                    }while(!stringCheck);

                    stringCheck = false;
                    str2 = scanner.next();
                    if(isNumber(str2)){
                        stringCheck = true;
                        y = Integer.parseInt(str2);
                        continue;
                    }
                    System.out.println("You should enter numbers!");
                }while(!stringCheck);

            }while(!inputCheck(x,y));

        }while(!checkCell(matrix,x,y));

        inputPlayerTurn(matrix, inputMatrix, x, y, turn);

        //CHECK DIAGONAL FOR X WINNER
        if(matrix[0][0] == 'X' && matrix[0][0] == matrix[1][1] &&  matrix[1][1] == matrix[2][2] ){
            xWins = true;
        }else if(matrix[0][2] == 'X' && matrix[0][2] == matrix[1][1] &&  matrix[1][1] == matrix[2][0]){
            xWins = true;
        }
        //CHECK DIAGONAL FOR O WINNER
        if(matrix[0][0] == 'O' && matrix[0][0] == matrix[1][1] &&  matrix[1][1] == matrix[2][2] ){
            oWins = true;
        }else if(matrix[0][2] == 'O' && matrix[0][2] == matrix[1][1] &&  matrix[1][1] == matrix[2][0]){
            oWins = true;
        }

        //CHECK HORIZONTAL FOR WINNER
        for(int i=0;i<matrix.length;i++){
            int counterX = 0;
            int counterO = 0;
            for(int j=0;j<matrix.length;j++){
                if(matrix[i][j] == 'X'){
                    counterX++;
                    counterO = 0;
                }else if(matrix[i][j] == 'O'){
                    counterO++;
                    counterX = 0;
                }
                if(counterX == 3){
                    xWins = true;
//
                }else if(counterO == 3){
                    oWins = true;
//
                }
            }
        }

        //CHECK VERTICAL FOR WINNER
        for(int i=0;i<matrix.length;i++){
            int counterX = 0;
            int counterO = 0;
            for(int j=0;j<matrix.length;j++){
                if(matrix[j][i] == 'X'){
                    counterX++;
                    counterO = 0;
                }else if(matrix[j][i] == 'O'){
                    counterO++;
                    counterX = 0;
                }
                if(counterX == 3){
                    xWins = true;
//
                }else if(counterO == 3){
                    oWins = true;
//
                }
            }
        }

        //
        if(xWins&&oWins){
            impossible = true;
            System.out.println("Impossible");
        }
        if(xWins && !impossible){
            System.out.println("X wins");
            break;
        }else if(oWins && !impossible){
            System.out.println("O wins");
            break;
//        }else if(xWins && oWins){
//            System.out.println("Draw");
//            break;
        }
        printTicTacToe(matrix);
    }
        if(xWins == false && oWins == false){
            System.out.println("Draw");
        }else {
            printTicTacToe(matrix);
        }

    }

        private static void inputPlayerTurn(char[][] matrix, char[][] inputMatrix, int x, int y, int turn) {
        if(turn%2 == 0){
            inputMatrix[(x-1)][(y-1)] = 'X';
        }else{
            inputMatrix[(x-1)][(y-1)] = 'O';
        }

        rotateMatrixLeft(matrix);
        rotateMatrixLeft(matrix);
        rotateMatrixLeft(matrix);
        matrix[(x-1)][(y-1)] = inputMatrix[(x-1)][(y-1)];
        rotateMatrixLeft(matrix);
    }
        private static boolean checkCell(char[][] matrix,int x,int y) {
        char check;
        rotateMatrixLeft(matrix);
//        printTicTacToe(matrix);
        rotateMatrixLeft(matrix);
//        printTicTacToe(matrix);
        rotateMatrixLeft(matrix);
//        printTicTacToe(matrix);
            check = matrix[(x - 1)][(y - 1)];
        if (check != ' ' && check != '\u0000'){
            System.out.println("This cell is occupied! Choose another one!");
            rotateMatrixLeft(matrix);
            return false;
        }
        rotateMatrixLeft(matrix);
        return true;
    }
        static void rotateMatrixLeft (char[][] mat){
            int matrixLenght = 3;
            // Consider all squares one by one
            for (int x = 0; x < matrixLenght / 2; x++) {
                // Consider elements in group of 4 in
                // current square
                for (int y = x; y < matrixLenght - x - 1; y++) {
                    // store current cell in temp variable
                    int temp = mat[x][y];

                    // move values from right to top
                    mat[x][y] = mat[y][matrixLenght - 1 - x];

                    // move values from bottom to right
                    mat[y][matrixLenght - 1 - x] = mat[matrixLenght - 1 - x][matrixLenght - 1 - y];

                    // move values from left to bottom
                    mat[matrixLenght - 1 - x][matrixLenght - 1 - y] = mat[matrixLenght - 1 - y][x];

                    // assign temp to left
                    mat[matrixLenght - 1 - y][x] = (char) temp;
                }
            }
        }
        private static void printTicTacToe ( char[][] matrix){
            System.out.println("---------");
            System.out.println("| " + matrix[0][0] + " " + matrix[0][1] + " " + matrix[0][2] + " |");
            System.out.println("| " + matrix[1][0] + " " + matrix[1][1] + " " + matrix[1][2] + " |");
            System.out.println("| " + matrix[2][0] + " " + matrix[2][1] + " " + matrix[2][2] + " |");
            System.out.println("---------");
        }
        private static boolean inputCheck ( int x, int y){

            if (x > 0 && x < 4) {
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
                return false;
            }
            if (y > 0 && y < 4) {
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
                return false;
            }

            return true;
        }
        static boolean isNumber(String s){
        for (int i = 0; i < s.length(); i++)
            if (Character.isDigit(s.charAt(i)) == false)
                return false;

            return true;
    }
}

