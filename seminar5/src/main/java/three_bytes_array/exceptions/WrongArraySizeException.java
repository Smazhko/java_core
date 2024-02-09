package three_bytes_array.exceptions;

public class WrongArraySizeException extends IllegalArgumentException{
    public WrongArraySizeException(String message) {
        super("Передаваемый массив неверного размера (" + message +
                "). Создан нулевой массив.");
    }
}
