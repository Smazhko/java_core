package web_shop.custom_exceptions;

import java.util.Arrays;

public class NonExistProductException extends Exception{

    public NonExistProductException() {
        super("<<  !  >> ОШИБКА: ТОВАР не найден.");
//        System.out.println(Arrays.toString(super.getStackTrace()));
    }
}
