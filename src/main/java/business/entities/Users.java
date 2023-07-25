package business.entities;

import java.util.Objects;

public class Users implements Comparable<Users>{
    private String username;

    private String password;

    private boolean isAdmin;
    private String firstName;
    private String lastName;

    private String phoneNumber;

    private String emailAdress;


    public Users(String username, String password, boolean isAdmin, String firstName, String lastName, String phoneNumber, String emailAdress) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAdress = emailAdress;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }
    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin (boolean isAdmin){
        this.isAdmin = isAdmin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return isAdmin == users.isAdmin && Objects.equals(username, users.username) && Objects.equals(password, users.password) && Objects.equals(firstName, users.firstName) && Objects.equals(lastName, users.lastName) && Objects.equals(phoneNumber, users.phoneNumber) && Objects.equals(emailAdress, users.emailAdress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, isAdmin, firstName, lastName, phoneNumber, emailAdress);
    }

    @Override
    public String toString() {
        return "Client{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAdress='" + emailAdress + '\'' +
                '}';
    }

    @Override
    public int compareTo(Users o) {
        return this.username.compareTo(o.username);
    }
}

