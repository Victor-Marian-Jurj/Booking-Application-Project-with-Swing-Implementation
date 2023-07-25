package presentation.users;

public class UsersLoginModel {

    private final String username;
    private final String password;

    public UsersLoginModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isFilled() {
        if ( username == null || password == null) {
            return false;
        }
        if(username.isEmpty() || password.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ClientLoginModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
