package homework;

import java.util.Arrays;
import java.util.function.*;

public class FunctInterfaces {
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
        System.out.println(">>> Подсчёт количества чётных в массиве");
        printResult(arr1, FunctInterfaces::countEvens);
        printResult(arr2, FunctInterfaces::countEvens);
        printResult(arr3, FunctInterfaces::countEvens);
        /* ИЗНАЧАЛЬНАЯ ЗАПИСЬ СТРОК БЫЛА ТАКАЯ
        System.out.printf("Массив %s. Кол-во чётных: %s %n", Arrays.toString(arr1), countEvens(arr1));
        System.out.printf("Массив %s. Кол-во чётных: %s %n", Arrays.toString(arr2), countEvens(arr2));
        System.out.printf("Массив %s. Кол-во чётных: %s %n", Arrays.toString(arr3), countEvens(arr3));

        Чтобы не писать каждый раз одну и ту же команду,
        мы создаём функцию печати на экран, куда передаём массив
        и в качестве ещё одного параметра - ФУНКЦИОНАЛЬНЫЙ ИНТЕРФЕЙС,
        который встроен в JAVA (их много Consumer, Supplier, Predicate и Function и их производные).
        у каждого такого интерфейса есть ОДИН метод с заразервированным названием
        (Consumer.accept(), Supplier.get(), Predicate.test(), Function.apply()).
        Этот метод мы и вызываем внутри НАШЕГО метода, чтобы функция выполнилась.

        В нашем случае выбор функционального интерфейса пал на Function, так как он принимает аргументы
        одного типа, а выдаёт - другого. Но Function всегда должен принимать ДВА аргумента.
        IDEA подсказала заменить FUNCTION на ToIntFunction. Он принимает один тип, а на выходе возвращает другой.

        Теперь при вызове нашего декорирующего метода можно передавать ДВА аргумента:
        ПЕРВЫЙ - массив, который надо обработать,
        ВТОРОЙ - ЛЯМБДА-ФУНКЦИЮ, которая будет использовать сложные методы, написанные ранее.
        Запись ЛЯМБДЫ в случае с интерфейсом ToIntFunction должна выглядеть x -> countEvens(x).
        Но такая запись не хочет работать с перегруженным методом.
        Запись x -> countEvens(x) IDEA в свете нововведений перестраивает в FunctInterfaces::countEvens
        (то есть {имя нашего главного класса}::{имя функции}).
        Такой вид отлично сочетается с перегруженным методом и всё работает на УРА!

        ВАЖНО: в программе используются методы с различными выходными данными - int и bool.
        Пришла на выручку ПЕРЕГРУЗКА метода printResult другим функциональным интерфейсом
        (типы у передаваемой функции отличаются, поэтому перегрузка возможна).

        ВАЖНО: вся эта махинация возножна благодаря тому, что ЛЯМБДА-ФУНКЦИЯ это объект.
        Тип этого объекта - функциональный интерфейс.
        Именно поэтому в функцию  printResult мы фактически передаём ОБЪЕКТ.
        Статья, объясняющая эту связь:
        https://skillbox.ru/media/base/funktsionalnye_interfeysy_i_lyambda_vyrazheniya_v_java/
        Статья по типам функциональных интерфейсов, и как следствие - типам ЛЯМБД:
        https://habr.com/ru/articles/677610/


        РАДУЕМСЯ РЕЗУЛЬТАТУ :)
        */

        /*
        Написать функцию, возвращающую разницу между самым большим
        и самым маленьким элементами переданного не пустого массива.
        */

        int[] arr4 = {5, 7, 9, 0, -5, 10, 1, 5, -8, 13};
        System.out.println(">>> Разница мин и макс в массиве");
        //System.out.printf("Массив %s. : %s %n", Arrays.toString(arr4), getMinMaxDifference(arr4));
        printResult(arr4,FunctInterfaces::getMinMaxDifference);


        /*
        Написать функцию, возвращающую истину, если в переданном массиве
        есть два соседних элемента, с нулевым значением.
         */
        int[] arr5 = {5, 7, 9, 0, 0, 10, 1, 5, -8, 13};
        int[] arr6 = {0, 0, 9, 7, 9, 10, 1, 5, -8, 13};
        int[] arr7 = {5, 7, 9, 7, 8, 10, 1, 5, 0, 0};
        System.out.println(">>> Наличие двух нулей подряд в массиве");
        printResult(arr4, FunctInterfaces::checkDoubleZero);
        printResult(arr5, FunctInterfaces::checkDoubleZero);
        printResult(arr6, FunctInterfaces::checkDoubleZero);
        printResult(arr7, FunctInterfaces::checkDoubleZero);
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
            if (arr[i] < min) min = arr[i];
            if (arr[i] > max) max = arr[i];
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

    public static void printResult(int[] arr, ToIntFunction<int[]> function){
        System.out.printf("Массив %s. Результат: %s %n", Arrays.toString(arr), function.applyAsInt(arr));
    }

    public static void printResult(int[] arr, Predicate<int[]> function){
        System.out.printf("Массив %s. Результат: %s %n", Arrays.toString(arr), function.test(arr));
    }

}
