package three_bytes_array.exceptions;

public class WrongValueException extends IllegalArgumentException{
    public WrongValueException(Integer value, Integer position) {
        super("Добавление значения невозможно - неверное значение (вне диапазона 0..3). Значение [" +
                value + "] в позиции [" +
                position + "].");
    }
}
