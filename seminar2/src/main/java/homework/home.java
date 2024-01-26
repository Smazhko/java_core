package homework;

import java.util.Arrays;

public class home {
    public static void main(String[] args) {
        /*
        Написать метод, возвращающий количество чётных элементов массива.
        countEvens([2, 1, 2, 3, 4]) → 3
        countEvens([2, 2, 0]) → 3
        countEvens([1, 3, 5]) → 0
        */

        int[] arr1 = {2, 1, 2, 3, 4};
        int[] arr2 = {2, 2, 0};
        int[] arr3 = {1, 3, 5};
        System.out.printf("Массив %s. Кол-во чётных: %s %n", Arrays.toString(arr1), countEvens(arr1));
        System.out.printf("Массив %s. Кол-во чётных: %s %n", Arrays.toString(arr2), countEvens(arr2));
        System.out.printf("Массив %s. Кол-во чётных: %s %n", Arrays.toString(arr3), countEvens(arr3));

        /*
        Написать функцию, возвращающую разницу между самым большим
        и самым маленьким элементами переданного не пустого массива.
        */

        int[] arr4 = {5, 7, 9, 0, -5, 10, 1, 5, -8, 13};
        System.out.printf("Массив %s. Разница мин и макс: %s %n", Arrays.toString(arr4), getMinMaxDifference(arr4));


        /*
        Написать функцию, возвращающую истину, если в переданном массиве
        есть два соседних элемента, с нулевым значением.
         */
        int[] arr5 = {5, 7, 9, 0, 0, 10, 1, 5, -8, 13};
        int[] arr6 = {0, 0, 9, 7, 9, 10, 1, 5, -8, 13};
        int[] arr7 = {5, 7, 9, 7, 8, 10, 1, 5, 0, 0};

        System.out.printf("Массив %s. Наличие двух нулей подряд: %s %n", Arrays.toString(arr4), checkDoubleZero(arr4));
        System.out.printf("Массив %s. Наличие двух нулей подряд: %s %n", Arrays.toString(arr5), checkDoubleZero(arr5));
        System.out.printf("Массив %s. Наличие двух нулей подряд: %s %n", Arrays.toString(arr6), checkDoubleZero(arr6));
        System.out.printf("Массив %s. Наличие двух нулей подряд: %s %n", Arrays.toString(arr7), checkDoubleZero(arr7));

    }

    public static int countEvens(int[] arr) {
        int evenCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) evenCount += 1;
        }
        return evenCount;
    }

    public static int getMinMaxDifference(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (min < arr[i]) min = arr[i];
            if (max > arr[i]) max = arr[i];
        }
        return max - min;
    }

    public static boolean checkDoubleZero(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == 0 && arr [i + 1] == 0)
                return true;
        }
        return false;
    }

}
