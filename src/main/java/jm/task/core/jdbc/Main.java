package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        Util.getSessionFactory();
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.dropUsersTable();
        userDao.createUsersTable();
        userDao.saveUser("Vlad", "Pupkin", (byte) 30);
        userDao.saveUser("Igor", "Smirnov", (byte) 30);
        userDao.saveUser("Vladimir", "Karpov", (byte) 30);
        userDao.saveUser("Zaur", "Tregulov", (byte) 30);
        System.out.println(userDao.getAllUsers());
        userDao.removeUserById(2);
        userDao.cleanUsersTable();


//        Util.getConnection();
//        UserDao userDao = new UserDaoJDBCImpl();

//        userDao.createUsersTable();
//
//        userDao.saveUser("Name1", "LastName1", (byte) 20);
//        userDao.saveUser("Name2", "LastName2", (byte) 25);
//        userDao.saveUser("Name3", "LastName3", (byte) 31);
//        userDao.saveUser("Name4", "LastName4", (byte) 38);
////
//        userDao.removeUserById(1);
//        userDao.getAllUsers();
//        userDao.cleanUsersTable();
//        userDao.dropUsersTable();

        // реализуйте алгоритм здесь
    }
}
