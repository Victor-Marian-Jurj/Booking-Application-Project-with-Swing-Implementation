package presentation.users;

import business.entities.Users;


import java.util.List;

public class UsersModel {

    private List<Users> usersList;

    public UsersModel(List<Users> usersList) {
        this.usersList = usersList;
    }

    public List<Users> getUsersList() {
        return usersList;
    }
        public void setUsersList(List<Users> usersList) {
            this.usersList = usersList;
        }

}
