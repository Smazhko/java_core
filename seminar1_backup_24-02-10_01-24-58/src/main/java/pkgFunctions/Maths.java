package pkgFunctions;

/**
 * Класс содержит методы, вычисляющие основные математические операции
 * <br>сложение
 * <br>вычитание
 * <br>умножение
 * <br>деление
 */
public class Maths {

    /**
     * Вычисление суммы двух чисел.
     * @param var1 - первое слагаемое.
     * @param var2 - второе слагаемое.
     * Выводит на экран декорированный ответ - сумма 2х чисел.
     */
    public void sum(Integer var1, Integer var2) {
        System.out.printf("Сумма чисел %s и %s = %s %n", var1, var2, var1 + var2);
    }

    /**
     * Вычисление разницы двух чисел.
     * @param var1 - уменьшаемое.
     * @param var2 - вычитаемое.
     * Выводит на экран декорированный ответ - разность 2х чисел.
     */
    public void diff(Integer var1, Integer var2) {
        System.out.printf("Разность чисел %s и %s = %s %n", var1, var2, var1 - var2);
    }

    /**
     * Вычисление произведения двух чисел.
     * @param var1 - первый множитель.
     * @param var2 - второй множитель.
     * Выводит на экран декорированный ответ - произведение 2х чисел.
     */
    public void mult(Integer var1, Integer var2) {
        System.out.printf("Произведение чисел %s и %s = %s %n", var1, var2, var1 * var2);
    }

    /**
     * Вычисление результата деления двух чисел.
     * @param var1 - делимое.
     * @param var2 - делитель.
     * Выводит на экран декорированный ответ - частное 2х чисел.
     */
    public void div(Integer var1, Integer var2) {
        System.out.printf("Результат деления %s на %s = %s %n", var1, var2, var1 / var2);
    }


}
