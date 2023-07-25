package presentation.users;

import persistance.UsersLoginAccessSQL;
import business.services.UserAccount;
import presentation.adminlogin.AdminView;
import presentation.userlogin.UserView;

import java.net.MalformedURLException;

public class UsersLoginController {
    private UsersLoginView usersLoginView;
    private UsersLoginAccessSQL clientLoginService;
    private UserAccount currentUserAccount = null;

    public void setClientLoginView(UsersLoginView usersLoginView) {
        this.usersLoginView = usersLoginView;
    }

    public void setClientLoginService(UsersLoginAccessSQL clientLoginService) {
        this.clientLoginService = clientLoginService;
    }

    public void start() {
        usersLoginView.display();
    }

    public void handleClickButtonOk(UsersLoginModel usersLoginModel) throws MalformedURLException {
        System.out.println("handleClickButtonOk " + usersLoginModel.toString());
        if (isValidUserAccount(usersLoginModel)) {
            usersLoginView.displayMessage(createWelcomeMessage());

            if (currentUserAccount.isAdmin()) {
                // User is an admin, show admin panel
                AdminView adminView = null;
                try {
                    adminView = new AdminView();
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                adminView.setVisible(true);
            } else {
                // User is not an admin, show user panel
                UserView userView = new UserView();
                userView.setVisible(true);
            }

            usersLoginView.dispose();
        } else {
            usersLoginView.displayMessage("Bad credentials!");
        }
    }
    private String createWelcomeMessage() {
        return "Welcome! " + currentUserAccount.getUsername() + " " + currentUserAccount.isAdmin();
    }

    private boolean isValidUserAccount(UsersLoginModel usersLoginModel) {
        if (usersLoginModel.isFilled()) {
            return matchWithPersistentRecords(usersLoginModel);
        } else {
            return false;
        }
    }

    private boolean matchWithPersistentRecords(UsersLoginModel usersLoginModel) {
        UserAccount userAccount = clientLoginService.getUserAccountByUsername(usersLoginModel.getUsername());
        if (userAccount != null && userAccount.matchPassword(usersLoginModel.getPassword())) {
            this.currentUserAccount = userAccount;
            return true;
        }
        return false;
    }

    public void handleClickButtonCancel(UsersLoginModel usersLoginModel) {
        System.out.println("handleClickButtonCancel " + usersLoginModel.toString());
    }
    }

