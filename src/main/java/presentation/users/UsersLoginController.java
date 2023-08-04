package presentation.users;

import business.services.UserAccount;
import persistance.UsersDataAccessSQL;
import presentation.adminlogin.AdminView;
import presentation.userlogin.UserView;
import util.UserSession;

import java.net.MalformedURLException;

public class UsersLoginController {
    private UsersLoginView usersLoginView;
    private UsersDataAccessSQL clientLoginService;
    private UserAccount currentUserAccount = null;

    public void setClientLoginView(UsersLoginView usersLoginView) {
        this.usersLoginView = usersLoginView;
    }

    public void setClientLoginService(UsersDataAccessSQL clientLoginService) {
        this.clientLoginService = clientLoginService;
    }

    public void start() {
        usersLoginView.display();
    }

    public void handleClickButtonOk(UsersLoginModel usersLoginModel) throws MalformedURLException {
        System.out.println("handleClickButtonOk " + usersLoginModel.getUsername());
        if (isValidUserAccount(usersLoginModel)) {
            usersLoginView.displayMessage(createWelcomeMessage());

            UserSession userSession = UserSession.getInstance();
            userSession.setUsername(usersLoginModel.getUsername());
            userSession.setPassword(usersLoginModel.getPassword());

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
                UserView userView = new UserView();
                userView.setVisible(true);
            }
            usersLoginView.dispose();
        } else {
            usersLoginView.displayMessage("Bad credentials!");
        }
    }
    private String createWelcomeMessage() {
        return "Welcome, " + currentUserAccount.getUsername() + "!";
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

