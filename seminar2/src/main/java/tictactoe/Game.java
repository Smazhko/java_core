package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class Game {
    //private static final int WIN_COUNT = 3;
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '.';

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();

    private static char[][] field;
    private static final int SIZE_X = 5;
    private static final int SIZE_Y = 5;

    public static void main(String[] args) {
        initialize();
        printField();
        while(true) {
            humanTurn();
            printField();
            if (gameCheck(DOT_HUMAN, "You won!"))
                break;

            aiTurn();
            printField();
            if (gameCheck(DOT_AI, "Computer won!"))
                break;
        }
    }

    private static void initialize(){
        field = new char[SIZE_X][SIZE_Y];
        for(int x = 0; x < SIZE_X; x++){
            for(int y = 0; y < SIZE_Y; y++){
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    private static void printField() {
        System.out.print("+");
        for (int i = 0; i < SIZE_X * 2 + 1; i++){
            System.out.print((i % 2 == 0) ? "-" : i / 2 + 1);
        }
        System.out.println();

        for (int i = 0; i < SIZE_X; i++){
            System.out.print(i + 1 + "|");

            for (int j = 0; j <  SIZE_Y; j++)
                System.out.print(field[i][j] + "|");

            System.out.println();
        }

        for (int i = 0; i < SIZE_X * 2 + 2; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Enter the coordinates Х и Y  (1 to 3) space separated: ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while(!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
    }

    private static boolean isCellEmpty(int x, int y) {
        return field[x][y] == DOT_EMPTY;
    }

    private static boolean isCellValid(int x, int y){
        return x >= 0 && x < SIZE_X
                && y >= 0 && y < SIZE_Y;
    }

    private static void aiTurn() {
        int x, y;
        do {
            x = RANDOM.nextInt(SIZE_X);
            y = RANDOM.nextInt(SIZE_Y);
        } while(!isCellEmpty(x, y));
        field[x][y] = DOT_AI;
    }

    private static boolean gameCheck(char symbol, String message){
        if(checkWin(symbol)){
            System.out.println(message);
            return true;
        }
        if(checkDraw()){
            System.out.println("Draw");
            return true;
        }
        return false;
    }

    private static boolean checkDraw() {
        for(int x = 0; x < SIZE_X; x++){
            for(int y = 0; y < SIZE_Y; y++){
               if(isCellEmpty(x, y)) return false;
            }
        }

        return true;
    }

    private static boolean checkWin1(char symbol) {
        // ПЕРВОНАЧАЛЬНЫЙ ВАРИАНТ------------------------------------------------------------
        // Проверка по трем горизонталям
        if (field[0][0] == symbol && field[0][1] == symbol && field[0][2] == symbol) return true;
        if (field[1][0] == symbol && field[1][1] == symbol && field[1][2] == symbol) return true;
        if (field[2][0] == symbol && field[2][1] == symbol && field[2][2] == symbol) return true;

        // Проверка по диагоналям
        if (field[0][0] == symbol && field[1][1] == symbol && field[2][2] == symbol) return true;
        if (field[0][2] == symbol && field[1][1] == symbol && field[2][0] == symbol) return true;

        // Проверка по трем вертикалям
        if (field[0][0] == symbol && field[1][0] == symbol && field[2][0] == symbol) return true;
        if (field[0][1] == symbol && field[1][1] == symbol && field[2][1] == symbol) return true;
        if (field[0][2] == symbol && field[1][2] == symbol && field[2][2] == symbol) return true;

        return false;
    }

    private static boolean checkWin2(char symbol) {
        // ВАРИАНТ 2 - проверка отдельным циклом по каждому условию
        // Проверка по трем горизонталям
        int size = field.length;
        boolean checkResult = false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j] == symbol)
                    checkResult = true;
                else {
                    checkResult = false;
                    break;
                }
            }
            if (checkResult)
                return true;
            else
                checkResult = false;
        }

        // Проверка по одной диагонали
        for (int i = 0; i < size; i++) {
            if (field[i][i] == symbol)
                checkResult = true;
            else {
                checkResult = false;
                break;
            }
        }
        if (checkResult) return true;

        // Проверка по другой диагонали
        for (int i = 0; i < size; i++) {
            if (field[size - i - 1][size - i - 1] == symbol)
                checkResult = true;
            else {
                checkResult = false;
                break;
            }
        }
        if (checkResult) return true;


        // Проверка по трем вертикалям
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                if (field[i][j] == symbol)
                    checkResult = true;
                else {
                    checkResult = false;
                    break;
                }
            }
            if (checkResult)
                return true;
            else
                checkResult = false;
        }

        return false;
    }

    private static boolean checkWin(char symbol) {
        /* ВАРИАНТ 3
        СОЗДАНИЕ СЧЁТЧИКА для каждого варианта
        */
        int size = field.length;
        int[] rowsWin = new int[size];
        int[] columnsWin = new int[size];
        int[] diagonalsWin = new int[size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j] == symbol) {
                    rowsWin[i] +=1;
                    columnsWin[j] +=1;
                }
            }
            if (field[i][i] == symbol)
                diagonalsWin[0] +=1;
            if (field[size - i - 1][size - i - 1] == symbol)
                diagonalsWin[1] +=1;
        }

        for (int i = 0; i < size; i++) {
            if (rowsWin[i] == size || columnsWin[i] == size || diagonalsWin[i] == size)
                return true;
        }
        return false;
    }
}
