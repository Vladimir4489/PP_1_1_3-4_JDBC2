package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Statement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(id mediumint not null auto_increment, name VARCHAR(50), lastname VARCHAR(50), age tinyint, PRIMARY KEY (id))");
            System.out.println("Database has been created!");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ignore) {

                }
            }
            System.out.println("A table with the same name already exists!");
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignore) {

                }
            }
        }

    }

    public void dropUsersTable() {
        Statement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            System.out.println("Table has been deleted!");
            connection.commit();
        } catch (SQLException e ) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ignore) {

                }
            }
            System.out.println("Table with this name not found!");
        } finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ignore) {
                }
            }
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("INSERT INTO users (Name, LastName, Age) VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("User" + name + "added to table!");
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException ignore) {

                }
            }
            System.out.println("The user has not been added to the table!");
        } finally {
            if (preparedStatement!=null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ignore) {

                }
            }
        }

    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("DELETE  FROM users WHERE Id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("User removed from table!");
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException ignore) {

                }
            }
            System.out.println("User not removed from table!");
        } finally {
            if (preparedStatement!=null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ignore) {

                }
            }
        }

    }

    public List<User> getAllUsers() {
        Statement statement = null;
        List<User> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while(resultSet.next()){
                list.add(new User(resultSet.getString(2), resultSet.getString(3), (byte)resultSet.getInt(4)));
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException ignore) {

                }
            }
        } finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ignore) {
                }
            }
        }

        return list;
    }

    public void cleanUsersTable() {
        Statement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE users");
            System.out.println("Table cleared!");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException ignore) {

                }
            }
            System.out.println("The table has not been cleared!");
        } finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ignore) {
                }
            }
        }

    }
}
