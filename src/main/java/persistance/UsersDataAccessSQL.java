package persistance;

import business.entities.Users;
import business.services.UserAccount;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsersDataAccessSQL implements UsersDataAccess {

    public List<Users> getAllUsers() {
        List<Users> users = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * from users");
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString(2);
                boolean isAdmin = rs.getBoolean(3);
                String firstName = rs.getString(4);
                String lastName = rs.getString(5);
                String emailAddress = rs.getString(6);
                String phoneNumber = rs.getString(7);


                Users person = new Users(username, password, isAdmin, firstName, lastName, emailAddress, phoneNumber);
                users.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeConnection(connection);
        }
        return users;
    }

    @Override
    public void updateUsersValues(String username, String phoneNumber) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("update users set phone_number = ? where username = ?");
            statement.setString(1, phoneNumber);
            statement.setString(2, username);
            int noOfUpdates = statement.executeUpdate();
            System.out.println("Number of updated records = " + noOfUpdates);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void insertClientValues(Users users) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("insert into users values(?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, users.getUsername());
            statement.setString(2, users.getPassword());
            statement.setBoolean(3, users.getIsAdmin());
            statement.setString(4, users.getFirstName());
            statement.setString(5, users.getLastName());
            statement.setString(6, users.getPhoneNumber());
            statement.setString(7, users.getEmailAdress());
            int noOfInserts = statement.executeUpdate();
            System.out.println("Number of inserted records = " + noOfInserts);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void deleteUsersValues(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("DELETE FROM users WHERE username = ?");
            statement.setString(1, username);
            int noOfInserts = statement.executeUpdate();
            System.out.println("Number of deleted records = " + noOfInserts);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection);
        }
    }




    public void selectClientValues(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            statement.setString(1, username);
            rs = statement.executeQuery();
            while (rs.next()) {
                username = rs.getString("username");
                String password = rs.getString("password");
                boolean isAdmin = rs.getBoolean("isAdmin");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String phone = rs.getString("phone_number");
                String email = rs.getString("email_adress");
                String birthDay = rs.getString("birth_day");


                System.out.println("Selected User:");
                System.out.println("username: " + username);
                System.out.println("password: " + password);
                System.out.println("isAdmin: " + isAdmin);
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("Phone: " + phone);
                System.out.println("Email: " + email);
                System.out.println("Birth Day: " + birthDay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection);
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public UserAccount getUserAccountByUsername(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();

            String query = "SELECT username, password, isAdmin FROM users WHERE username = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String retrievedUsername = resultSet.getString("username");
                String retrievedPassword = resultSet.getString("password");
                boolean retrievedIsAdmin = resultSet.getBoolean("isAdmin");
                return new UserAccount(retrievedUsername, retrievedPassword, retrievedIsAdmin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database resources
            DBUtil.closeConnection(connection);
        }

        return null; // User account not found
    }


    public boolean isUsernameExists(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();

            String query = "SELECT COUNT(*) FROM users WHERE username = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // Return true if count > 0 (username exists), false otherwise
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database resources
            DBUtil.closeConnection(connection);
        }

        return false; // User account not found
    }

    public UserAccount getUserAccountByEmail(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();

            String query = "SELECT username, password, isAdmin FROM users WHERE email_adress = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, email);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String retrievedUsername = resultSet.getString("username");
                String retrievedPassword = resultSet.getString("password");
                boolean retrievedIsAdmin = resultSet.getBoolean("isAdmin");
                return new UserAccount(retrievedUsername, retrievedPassword, retrievedIsAdmin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database resources
            DBUtil.closeConnection(connection);
        }

        return null; // User account not found
    }
}
