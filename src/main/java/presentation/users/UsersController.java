package presentation.users;

import business.entities.Users;
import business.services.UsersService;

import java.util.List;

public class UsersController {

    private UsersView view;

    private UsersModel model;

    private UsersService service = new UsersService();


    public UsersController(UsersView view, UsersModel model, UsersService service) {
        this.view = view;
        this.model = model;
        this.service = service;
    }

    public UsersController() {

    }

    public void buttonRefreshPressed() {
        List<Users> usersList = service.getAllUsers();
        model.setUsersList(usersList);
        view.refreshTableUsers(usersList);
    }


//        buttonRefreshPressed();


    public void handleClickButtonInsertUsers(Users users){
        service.insertUsers(users);

    }

    public void handleClickButtonDeleteUsers (String username){
        service.deleteUsers(username);
    }
    public void handleClickButtonUpdateUsers(String username, String phoneNumber){
        service.updateUsers(username, phoneNumber);
    }

}


