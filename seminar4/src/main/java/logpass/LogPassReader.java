package logpass;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LogPassReader {

    public static ArrayList<String[]> loadData (){
        File dataFile = new File("userdata.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(dataFile)))) {
            ArrayList<String[]> userdata = new ArrayList<>();
            while (reader.ready()) {
                String line = reader.readLine();
                userdata.add(line.split(" "));
            }
            return userdata;
        } catch(IOException e){
            savinglog(e.getMessage());
        }
        return null;
    }

    public static boolean checkInputData (String[] userdata){
        String login = userdata[0];
        String passwd = userdata[1];
        String confirmPasswd = userdata[2];
        try{
            if (login.length() > 20)
                throw new WrongLogException("Длина логина ("+ login + ") превышает 20 символов. Попробуйте ещё раз.");
            if (passwd.length() > 20)
                throw new WrongPassException("Длина пароля ("
                        + passwd.substring(0,2) + "###"
                        + passwd.substring(passwd.length()-2, passwd.length())
                        + ") превышает 20 символов. Попробуйте ещё раз.");
            if (!passwd.equals(confirmPasswd))
                throw new WrongPassException("Подтверждение пароля не совпадает с паролем.");
            savinglog(String.format("Логин (%s) и пароль (%s) в порядке", login, passwd));

        } catch(WrongLogException | WrongPassException e){
                savinglog(e.getMessage());
                return false;
        }
        return true;
    }

    public static void savinglog(String msg){
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy (EEE), HH:mm:ss");
        String logDataFileName = "log.txt";
        try (FileWriter writer = new FileWriter(logDataFileName, true)){ // true - флаг о дописывании файла
            writer.write( dateFormat.format(new Date()) + ": " + msg + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}

