package logpass;

import java.util.ArrayList;
import java.util.Arrays;

/*
    Программа имеет два собственных типа исключений - WrongLoginException и WrongPasswordException.java.
    В классе LogPassReader происходит загрузка данных из внешнего файла userdata.txt.
    Во время загрузки файла (с помощью ресурсного try-catch) происходит парсинг полученных данных -
    разбивка по переменным.
    Предусмотрена обработка исключения IOException - запись в ЛОГ log.txt.
    Также имеется отдельный метод сохранения лога - в файл log.txt - также с использованием ресурсного try-catch.
    Метод checkInputData производит проверки полученных данных.
    - длина логина и пароля не должны превышать 20 символов,
    - пароль должен совпадать с подтверждением пароля.
    При несоответствии - пробрасывается исключение,
    которое отлавливается в catch, в результате чего происходит запись сообщения в лог-файл,
    а также возврат true-false о том, прошли данные проверку или нет.
    Файлы userinfo.txt и log.txt располагаются в корневом каталоге проекта java_core.
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Проверка данных пользователя");
        ArrayList<String[]> allusers = LogPassReader.loadData();
        for(String[] userinfo: allusers){
            System.out.print(Arrays.toString(userinfo) + " - ");
            System.out.println(LogPassReader.checkInputData(userinfo));
        }
    }
}
