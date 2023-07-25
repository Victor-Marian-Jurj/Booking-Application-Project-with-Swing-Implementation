package business.services;

public class UserAccount {

    private final String username;

    private final String password;

    private final boolean isAdmin;



    public UserAccount(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean matchPassword(String password) {
        if (password == null) {
            return false;
        }
        return password.equals(this.password);
    }
}
