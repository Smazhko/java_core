package three_bytes_array.exceptions;

public class FullArrayException extends IndexOutOfBoundsException{
    public FullArrayException(String message) {
        super("Добавление нового значения невозможно - массив переполнен. Значение [" +
                message + "].");
    }
}
