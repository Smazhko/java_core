package three_bytes_array.exceptions;

public class WrongArrayIndexException extends IllegalArgumentException{
    public WrongArrayIndexException(Integer value, Integer position) {
        super("Добавление значения невозможно - неверная позиция (вне диапазона 0..8). Значение [" +
                value + "] в позиции [" +
                position + "].");
    }
}
