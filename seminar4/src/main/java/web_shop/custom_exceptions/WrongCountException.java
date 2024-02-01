package web_shop.custom_exceptions;

public class WrongCountException extends IllegalArgumentException{

    public WrongCountException(String message) {
        super("<<  !  >> ОШИБКА: Неверное количество товара в заказе: " + message);
    }
}
