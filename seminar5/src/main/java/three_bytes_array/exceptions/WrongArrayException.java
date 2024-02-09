package three_bytes_array.exceptions;

public class WrongArrayException extends IllegalArgumentException{
    public WrongArrayException(String message) {
        super("Передаваемый массив содержит невалидные значения (вне диапазона 0..3): " +
                message + ". Создан нулевой массив.");
    }
}
