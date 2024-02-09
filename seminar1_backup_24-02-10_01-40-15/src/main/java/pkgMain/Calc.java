package pkgMain;

import pkgFunctions.Maths;

/**
 * Основной класс программы - простейший калькулятор
 */
public class Calc {

    /**
     * Метод main - точка входа, главный метод программы,
     * где происходят вычисления с помощью метолов класса Maths.
     * @param args список аргументов
     */
    public static void main(String[] args) {
        Maths mathematics = new Maths();
        mathematics.sum(10, 12);
        mathematics.diff(25, 13);
        mathematics.mult(5, 10);
        mathematics.div(77, 11);
        mathematics.div(12, 6);

    }
}

