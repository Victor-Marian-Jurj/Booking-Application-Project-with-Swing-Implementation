package presentation.users;

import business.entities.Users;
import business.services.UserAccount;
import business.services.UsersService;
import persistance.UsersDataAccessSQL;

import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class UsersController {

    private UsersDataAccessSQL usersDataAccess = new UsersDataAccessSQL();
    private UsersView usersView;

    private UsersModel usersModel;

    private UsersService usersService = new UsersService();
    private UsersLoginView usersLoginView = new UsersLoginView();


    public UsersController(UsersDataAccessSQL usersDataAccess) {
        this.usersDataAccess = usersDataAccess;
    }

    public UsersController(UsersView usersView, UsersModel usersModel, UsersService usersService) {
        this.usersView = usersView;
        this.usersModel = usersModel;
        this.usersService = usersService;
    }

    public UsersController() {

    }

    public void buttonRefreshPressed() {
        List<Users> usersList = usersService.getAllUsers();
        usersModel.setUsersList(usersList);
        usersView.refreshTableUsers(usersList);
    }

    public void handleClickButtonInsertUsers(Users users) {
        usersService.insertUsers(users);

    }

    public void handleClickButtonRegisterUser(Users user) {
        System.out.println("handleClickButtonRegister " + user.toString());

        boolean isFoundByEmail = matchWithExistingEmail(user);
        boolean isFoundByUsermane = matchWithExistingUsername(user);
        if (isFoundByEmail) {
            usersLoginView.displayMessage("Email already exists! Try another!");

        } else if (isFoundByUsermane) {
            usersLoginView.displayMessage("Username already exists! Try another!");
        } else {
            usersService.insertUsers(user);
            showMessageDialog(null, "User added");
            usersLoginView.dispose();
        }
    }

    private boolean matchWithExistingUsername(Users user) {
        UserAccount userAccount = usersDataAccess.getUserAccountByUsername(user.getUsername());
        if (userAccount != null) {
            return true;
        }
        return false;
    }

    private boolean matchWithExistingEmail(Users user) {
        UserAccount userAccount = usersDataAccess.getUserAccountByEmail(user.getEmailAdress());
        if (userAccount != null) {
            return true;
        }
        return false;
    }

    public void handleClickButtonDeleteUsers(String username) {
        usersService.deleteUsers(username);
    }

    public void handleClickButtonUpdateUsers(String username, String phoneNumber) {
        usersService.updateUsers(username, phoneNumber);
    }

    public boolean isUsernameExists(String usernameToDelete) {
        return usersDataAccess != null && usersDataAccess.isUsernameExists(usernameToDelete);

    }
}


