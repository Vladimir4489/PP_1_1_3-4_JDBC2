package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private String sqlCreate = "CREATE TABLE `users`.`users` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `Name` VARCHAR(45) NOT NULL,\n" +
            "  `LastName` VARCHAR(45) NOT NULL,\n" +
            "  `Age` INT NOT NULL,\n" +
            "  PRIMARY KEY (`id`))\n" +
            "ENGINE = InnoDB\n" +
            "DEFAULT CHARACTER SET = utf8";
    private String sqlDrop = "DROP TABLE users";
    private String sqlClean = "TRUNCATE TABLE users";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/users", "root", "12345678")){
//            conn.setAutoCommit(false);
            Statement statement = conn.createStatement();
//            conn.commit();
            // создание таблицы
            statement.executeUpdate(sqlCreate);
//            conn.rollback();

            System.out.println("Database has been created!");
        } catch (SQLException ignore) {
            //throw new RuntimeException(e);
            System.out.println("A table with the same name already exists!");
        }

    }

    public void dropUsersTable() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/users", "root", "12345678")){
//            conn.setAutoCommit(false);
            Statement statement = conn.createStatement();
//            conn.commit();
            statement.executeUpdate(sqlDrop);
//            conn.rollback();

            System.out.println("Database has been deleted!");
        } catch (SQLException ignore ) {
            //throw new RuntimeException(e);
            System.out.println("Table with this name not found!");
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (Name, LastName, Age) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/users", "root", "12345678")){
//            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
//            conn.commit();
            preparedStatement.executeUpdate();
//            conn.rollback();

            System.out.println("User" + name + "added to table!");
        } catch (SQLException ignore ) {
            //throw new RuntimeException(e);
            System.out.println("The user has not been added to the table!");
        }

    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE Id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/users", "root", "12345678")){
//            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);
//            conn.commit();
            preparedStatement.executeUpdate();
//            conn.rollback();

            System.out.println("User removed from table!");
        } catch (SQLException ignore ) {
            //throw new RuntimeException(e);
            System.out.println("User not removed from table!");
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/users", "root", "12345678")){
//            conn.setAutoCommit(false);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while(resultSet.next()){

                int id = resultSet.getInt(1);
                String Name = resultSet.getString(2);
                String LastName = resultSet.getString(3);
                int Age = resultSet.getInt(4);
//                conn.commit();
                list.add(new User(Name, LastName, (byte)Age));
//                conn.rollback();

//                System.out.printf("%d. %s %s %d \n", id, Name, LastName, Age);
            }
        } catch (SQLException ignore ) {
            //throw new RuntimeException(e);

        }

        return list;
    }

    public void cleanUsersTable() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/users", "root", "12345678")){
//            conn.setAutoCommit(false);
            Statement statement = conn.createStatement();
//            conn.commit();
            statement.executeUpdate(sqlClean);
//            conn.rollback();


            System.out.println("Table cleared!");
        } catch (SQLException ignore ) {
            //throw new RuntimeException(e);
            System.out.println("The table has not been cleared!");
        }

    }
}
