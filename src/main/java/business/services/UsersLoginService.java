package ro.fortech.academy.business;

import business.services.UserAccount;

public class UsersLoginService {

    public UserAccount getUserAccountByUsername(String username) {
        return new UserAccount(username, "secret", true);
    }
}