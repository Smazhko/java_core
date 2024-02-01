package web_shop.custom_exceptions;

import java.util.Arrays;

public class NonExistClientException extends Exception{

    public NonExistClientException() {
        super("<<  !  >> ОШИБКА: КЛИЕНТ не найден.");
//        System.out.println(Arrays.toString(super.getStackTrace()));
    }
}
