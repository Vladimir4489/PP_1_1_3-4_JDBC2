package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Util {


    public static void getConnection() {
        try{
            String url = "jdbc:mysql://localhost/users";
            String username = "root";
            String password = "12345678";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
//                conn.setAutoCommit(false);
                System.out.println("Connection to Store DB successful!");
                Statement statement = conn.createStatement();
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }


    }


    // реализуйте настройку соеденения с БД
}
