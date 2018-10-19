/*
 ********************************************************************************************************************
 * Project: comp3095_Team_Sky_Assignment1
 * Assignment: assignment 1
 * Author(s): Leila Jalali Abyaneh - Sogol Ganji Haghighi
 * Student Number: 101088286 - 100902745
 * Date: October/04/2018
 * Description: The helper class for the project
 ********************************************************************************************************************
 */
package helper;

import model.UserModel;

import java.sql.*;

public class DataBaseHelper {
    private String username = "root";
    ;
    private String password = "";
    private String database = "COMP3095";



    public DataBaseHelper() {
        Connection connection = connectDataBase();
        if (connection ==null){
            createDatabase();
            createUserTable();
        }else {
            try {
                connection.close() ;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection connectDataBase() {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
           Connection connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/" + database + "?"
                            + "user=" + username + "&password=" + password);

            return connect;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean checkUserExist(String username) {
        String sql = "Select 1 from USERS where email = ?";
        Connection dbConn = null;
        try {
            dbConn = connectDataBase();
            PreparedStatement ps = dbConn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            boolean next = rs.next();
            dbConn.close();
            return next;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkTableExists(String tableName) {
        DatabaseMetaData dbm = null;
        try {
            Connection connection = connectDataBase();
            dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, tableName, null);
            connection.close();
            return tables.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private int runUpdateQuery(String query) {
        try {
            Connection connection = connectDataBase();
            Statement statement = connection.createStatement();

            int i = statement.executeUpdate(query);

            connection.close();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;

    }

    public void createDatabase() {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            Connection connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/" + "?"
                            + "user=" + username + "&password=" + password);
            Statement s = connect.createStatement();
            int Result = s.executeUpdate("CREATE DATABASE IF NOT EXISTS " + database + ";");
            s.executeUpdate("USE " + database + ";");
            s.executeUpdate("grant all on " + database + ".* to '" + username + "' identified by 'admin';  ");
            connect.close();
        } catch (Exception e) {

        }
    }

    public void createUserTable() {
        runUpdateQuery("CREATE TABLE USERS \n" +
                "( \n" +
                "\tid int(11) AUTO_INCREMENT PRIMARY KEY, \n" +
                "\tfirstname varchar(255),\n" +
                "\tlastname varchar(255),\n" +
                "\temail varchar(255), \n" +
                "\taddress varchar(255), \n" +
                "\trole varchar(20),\n" +
                "\tcreated timestamp default current_timestamp,\n" +
                "\tpassword varchar(20)\t\n" +
                ");");
    }

    public int addUserToDatabase(UserModel user) {
        return runUpdateQuery("INSERT INTO `USERS` (`firstname`, `lastname`, `email`,`address`, `role`, `password`) VALUES\n" +
                "('" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getEmail() +"','"+user.getAddress()+ "','"+user.getRole()+"', '" + user.getPassword() + "');");
    }

    public UserModel getUserFromDataBase(String username, String password) {
        String query = "select * from `USERS` where email='" + username + "' and password='" + password + "'";
        try {
            Connection connection = connectDataBase();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                UserModel user = new UserModel();
                user.setId(resultSet.getInt("id"));
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                String password1 = resultSet.getString("password");
                String address = resultSet.getString("address");
                user.setFirstName(firstname);
                user.setLastName(lastname);
                user.setEmail(email);
                user.setRole(role);
                user.setPassword(password1);
                user.setAddress(address);
                return user;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
