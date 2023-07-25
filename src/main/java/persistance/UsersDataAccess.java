package persistance;

import business.entities.Users;

import java.util.List;

public interface UsersDataAccess {

    List<Users> getAllUsers();

    void updateUsersValues(String username, String phoneNumber);

    void insertClientValues(Users users);

    void deleteUsersValues(String username);

    void selectClientValues(String username);


}
