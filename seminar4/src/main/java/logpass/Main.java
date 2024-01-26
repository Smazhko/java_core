package logpass;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String[]> allusers = LogPassReader.loadData();
        for( String[] userinfo: allusers){
            System.out.println(LogPassReader.checkInputData(userinfo));
        }
    }
}
