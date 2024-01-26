package arraysNfunctions;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        /*
        Задание 1.
        Задать одномерный массив. Написать методы поиска в нём минимального и максимального элемента.
        ** Привести функции к корректному виду и дополнительно написать ещё две функции так, чтобы получились
           (четыре) функции поиска минимального и максимального как значения, так и индекса.
        */
        System.out.println("ЗАДАЧА 1.-----------------------------");
        int[] arr = {2,5,10,80,12,0,-10,15,-9,0};
        System.out.println(findMin(arr));
        System.out.println(findMax(arr));
        System.out.println(findMinMax(arr, "min", false));
        System.out.println(findMinMax(arr, "max", true));
        System.out.println(findMinMax(arr, "man", true));


        /*
        Задание 2.
        Создать квадратный целочисленный массив (количество строк и столбцов одинаковое),
        заполнить его диагональные элементы единицами, используя цикл(ы).
        ** Дописать функцию вывода двумерного массива в консоль
        */
        System.out.println("ЗАДАЧА 2.-----------------------------");
        int size = 9;
        int[][] squareArr = new int[size][size];
        printArrDiagonals(squareArr);

        /*
        Задание 3.
        Написать метод, в который передается непустой одномерный целочисленный массив.
        Метод должен вернуть true если в массиве есть место, в котором сумма левой
        и правой части массива равны. Примеры:
            checkBalance([1, 1, 1, || 2, 1]) → true,
            checkBalance([2, 1, 1, 2, 1]) → false,
            checkBalance([10, || 1, 2, 3, 4]) → true.
        Абстрактная граница показана символами ||, эти символы в массив не входят.
        ** Написать этот же метод таким образом, чтобы в нём использовался только один цикл.
        */
        System.out.println("ЗАДАЧА 3.-----------------------------");
        int[] arr1 = {10,1,2,3,4};
        int[] arr2 = {2,1,1,2,1};
        int[] arr3 = {1,1,1,2,1};
        System.out.println(Arrays.toString(arr1) + " - " + checkBalance4(arr1));
        System.out.println(Arrays.toString(arr2) + " - " + checkBalance4(arr2));
        System.out.println(Arrays.toString(arr3) + " - " + checkBalance4(arr3));

    }

    public static String findMin(int[] arr){
        int min = arr[0];
        for (int i = 1; i < arr.length; i++){
            if (arr[i] < min) min = arr[i];
        }
        return String.format("Минимальное значение в массиве: %s", min);
    }

    public static String findMax(int[] arr){
        int max = arr[0];
        for (int i = 1; i < arr.length; i++){
            if (arr[i] > max) max = arr[i];
        }
        return String.format("Максимальное значение в массиве: %s", max);
    }

    public static String findMinMax(int[] arr, String minORmax, Boolean findIndex){
        String result;
        switch (minORmax){
            case "min":
                int minIndex = 0;
                for (int i = 1; i < arr.length; i++){
                if (arr[i] < arr[minIndex]) minIndex = i;
                }
                if (findIndex)
                    result = String.format("MIN в массиве: %s (индекс %s).", arr[minIndex], minIndex);
                else
                    result = String.format("MIN в массиве: %s.", arr[minIndex]);
                break;
            case "max":
                int maxIndex = 0;
                for (int i = 1; i < arr.length; i++){
                    if (arr[i] > arr[maxIndex]) maxIndex = i;
                }
                if (findIndex)
                    result = String.format("MAX в массиве: %s (индекс %s).", arr[maxIndex], maxIndex);
                else
                    result = String.format("MAX в массиве: %s.", arr[maxIndex]);
                break;
            default:
                result = "<< ! >> Неверно указаны параметры функции.";
        }

        return result;
    }



    public static void printArrDiagonals(int[][] arr) {
        int size = arr[0].length;
        for (int i = 0; i < size; i++) {
            arr[i][i] = 1;
            arr[i][size-i-1] = 1;
            System.out.println(Arrays.toString(arr[i])
                    .replaceAll("[\\[\\],]","")
                    .replaceAll("0","-")
                    .replaceAll("1","X"));
        }
    }

    // Cложность О(2n)
    // за один проход по массиву прибавляем к левой сумме и отнимаем от правой суммы
    // результаты сравниваем
    public static Boolean checkBalance(int[] array){
        int leftSum = array[0];
        int rightSum = 0;
        for (int i = 1; i < array.length; i++) {
            rightSum += array[i];
        }
        if (leftSum == rightSum) return true;
        for (int i = 1; i < array.length; i++) {
            leftSum += array[i];
            rightSum -= array[i];
            if (leftSum == rightSum) return true;
        }
        return false;
    }

    //    Сложность О(n^2) - моё      (О_О)
    public static boolean checkBalance1(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (sumArr(array,0, i) == sumArr(array,i + 1, array.length - 1))
                return true;
        }
        return false;
    }

    public static int sumArr (int[] arr, int beginInd, int stopInd){
        int sum = 0;
        for (int i = beginInd; i <=stopInd ; i++) {
            sum = sum + arr[i];
        }
        return sum;
    }

    //    Сложность О(n^2)
    private static boolean checkBalance2(int[] a) {
        int left = 0;
        for (int i = 0; i < a.length - 1; i++) {
            left += a[i];
            int right = 0;
            for (int j = i + 1; j < a.length; j++) {
                right += a[j];
            }
            if (left == right) return true;
        }
        return false;
    }

    // Сложность O(2n)
    // суммируем весь массив
    // если сумма не делится ровно пополам, то точно не будет лево = право
    // левая сумма = 0
    // проходим по массиву
    // к левой сумме прибавляем каждый следующий элемент
    // из общей суммы массива отнимаем каждый следующий (так правая сумма поэлементно уменьшается)
    // каждый раз сравниваем лево = право
    private static boolean checkBalance3(int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        if (sum % 2 != 0) return false;
        int left = 0;
        for (int i = 0; i < a.length; i++) {
            left += a[i];
            sum -= a[i];
            if (left == sum) return true;
        }
        return false;
    }

    // Сложность О(n)
    // Левая сумма = начало массива.
    // Правая сумма = конец массива.
    // Если левая сумма больше правой, то к правой сумме прибавляем предыдущий элемент.
    // И наоборот.
    // Прибавляем до тех пор, пока индексы не сравняются.
    // Проверяем суммы на равенство.
    private static boolean checkBalance4(int[] a) {
        int lbound = 0;
        int rbound = a.length - 1;
        int left = 0;
        int right = 0;
        while (lbound <= rbound) {
            if (left > right)
                right += a[rbound--];
            else
                left += a[lbound++];
        }
        return left == right;
    }

}