package business.services;

import business.entities.Users;
import persistance.UsersDataAccess;
import persistance.UsersDataAccessSQL;

import java.util.List;
import java.util.stream.Collectors;

public class UsersService {


private UsersDataAccess usersDataAccess = new UsersDataAccessSQL();

    public UsersService(UsersDataAccess usersDataAccess) {
        this.usersDataAccess = usersDataAccess;
    }

    public UsersService() {

    }

    public List<Users> getAllUsers() {
        List<Users> list = usersDataAccess.getAllUsers();
        return list.stream().sorted().collect(Collectors.toList());
    }

    public void updateUsers(String username, String phoneNumber) {
        usersDataAccess.updateUsersValues(username, phoneNumber);
    }

    public void insertUsers(Users users) {
        usersDataAccess.insertClientValues(users);
    }

    public void deleteUsers(String username) {
        usersDataAccess.deleteUsersValues(username);
    }

    public void selectUsers(String username) {
        usersDataAccess.selectClientValues(username);
    }


}
