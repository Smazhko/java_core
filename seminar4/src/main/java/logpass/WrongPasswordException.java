package logpass;

public class WrongPasswordException extends Exception{
    public WrongPasswordException(String mes) {
        super(mes);
    }

}
