import business.services.UsersService;
import persistance.UsersDataAccessSQL;
import presentation.users.*;


public class Main {

    public static void main(String[] args) {

        UsersDataAccessSQL clientLoginService = new UsersDataAccessSQL();
        UsersLoginView usersLoginView = new UsersLoginView();
        UsersLoginController usersLoginController = new UsersLoginController();

        usersLoginView.setUsersLoginController(usersLoginController);
        usersLoginController.setClientLoginView(usersLoginView);
        usersLoginController.setClientLoginService(clientLoginService);

        usersLoginController.start();

    }
}