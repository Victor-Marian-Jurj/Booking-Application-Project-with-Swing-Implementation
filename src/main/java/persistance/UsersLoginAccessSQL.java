package persistance;

import business.services.UserAccount;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersLoginAccessSQL {
    public UserAccount getUserAccountByUsername(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();

            // Prepare the query to retrieve user account data
            String query = "SELECT username, password, isAdmin FROM users WHERE username = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);

            // Execute the query
            resultSet = statement.executeQuery();

            // Process the retrieved data
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
