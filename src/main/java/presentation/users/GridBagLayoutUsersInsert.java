package presentation.users;

import business.entities.Users;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.JOptionPane.showMessageDialog;

public class GridBagLayoutUsersInsert extends JFrame {

    private JTextField username;

    private JTextField password;

    private JTextField isAdmin;

    private JTextField firstName;

    private JTextField lastName;

    private JTextField phoneNumber;

    private JTextField emailAdress;

    private JButton saveButtonUserInsert;

    private JButton cancelButtonUserInsert;

    private UsersController usersController = new UsersController();
    private UsersLoginController usersLoginController = new UsersLoginController();

    public GridBagLayoutUsersInsert() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        setLocationRelativeTo(null);


        addItem(panel, new JLabel("Username: "), 0, 0, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Password: "), 0, 1, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Is Admin: "), 0, 2, GridBagConstraints.EAST);
        addItem(panel, new JLabel("First name: "), 0, 3, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Last name: "), 0, 4, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Phone number: "), 0, 5, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Email address: "), 0, 6, GridBagConstraints.EAST);


        username = new JTextField(20);
        password = new JTextField(20);
        isAdmin = new JTextField(10);
        firstName = new JTextField(30);
        lastName = new JTextField(20);
        phoneNumber = new JTextField(15);
        emailAdress = new JTextField(40);


        addItem(panel, username, 1, 0, GridBagConstraints.WEST);
        addItem(panel, password, 1, 1, GridBagConstraints.WEST);
        addItem(panel, isAdmin, 1, 2, GridBagConstraints.WEST);
        addItem(panel, firstName, 1, 3, GridBagConstraints.WEST);
        addItem(panel, lastName, 1, 4, GridBagConstraints.WEST);
        addItem(panel, phoneNumber, 1, 5, GridBagConstraints.WEST);
        addItem(panel, emailAdress, 1, 6, GridBagConstraints.WEST);


        saveButtonUserInsert = new JButton("Save");
        cancelButtonUserInsert = new JButton("Cancel");

        saveButtonUserInsert.addActionListener(this::buttonInsertUser);
        cancelButtonUserInsert.addActionListener(e -> this.dispose());

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(saveButtonUserInsert);
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(cancelButtonUserInsert);

        addItem(panel, buttonBox, 1, 7, GridBagConstraints.WEST);
        setLocationRelativeTo(null);

        this.add(panel);
        this.pack();
        this.setSize(600, 300);
        this.setTitle("Insert new user");
        this.setVisible(true);

    }

    public GridBagLayoutUsersInsert(boolean isAdminFieldNeeded) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        if (isAdminFieldNeeded) {
            addItem(panel, new JLabel("Is Admin: "), 0, 6, GridBagConstraints.EAST);
            isAdmin = new JTextField(10);
            addItem(panel, isAdmin, 1, 2, GridBagConstraints.WEST);
        }


        addItem(panel, new JLabel("Username: "), 0, 0, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Password: "), 0, 1, GridBagConstraints.EAST);
        addItem(panel, new JLabel("First name: "), 0, 2, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Last name: "), 0, 3, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Phone number: "), 0, 4, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Email address: "), 0, 5, GridBagConstraints.EAST);


        username = new JTextField(20);
        password = new JTextField(20);
        firstName = new JTextField(30);
        lastName = new JTextField(20);
        phoneNumber = new JTextField(15);
        emailAdress = new JTextField(40);


        addItem(panel, username, 1, 0, GridBagConstraints.WEST);
        addItem(panel, password, 1, 1, GridBagConstraints.WEST);
        addItem(panel, firstName, 1, 2, GridBagConstraints.WEST);
        addItem(panel, lastName, 1, 3, GridBagConstraints.WEST);
        addItem(panel, phoneNumber, 1, 4, GridBagConstraints.WEST);
        addItem(panel, emailAdress, 1, 5, GridBagConstraints.WEST);


        saveButtonUserInsert = new JButton("Save");
        cancelButtonUserInsert = new JButton("Cancel");

        saveButtonUserInsert.addActionListener(this::buttonRegisterUser);
        cancelButtonUserInsert.addActionListener(e -> this.dispose());

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(saveButtonUserInsert);
        buttonBox.add(Box.createVerticalStrut(20));
        buttonBox.add(cancelButtonUserInsert);

        addItem(panel, buttonBox, 1, 7, GridBagConstraints.WEST);
        setLocationRelativeTo(null);

        this.add(panel);
        this.pack();
        this.setSize(600, 300);
        this.setTitle("Insert new user");
        this.setVisible(true);
    }


    private void buttonInsertUser(ActionEvent actionEvent) {

        boolean usernameIsValid = Utils.containsOnlyNumbersAndLetters(username.getText(), Utils.NUMBERS_CHARACTERS_REGEX);
        boolean isAdminValid = Utils.containsOnlyBoolean(isAdmin.getText(), Utils.BOOLEAN_REGEX);
        boolean firstNameIsValid = Utils.containsOnlyLetters(firstName.getText(), Utils.LETTERS_REGEX);
        boolean lastNameIsValid = Utils.containsOnlyLetters(lastName.getText(), Utils.LETTERS_REGEX);
        boolean phoneNumberIsValid = Utils.containsOnlyNumbers(phoneNumber.getText(), Utils.DECIMAL_DOUBLE_REGEX);
        boolean emailIsValid = Utils.containsOnlyNumbers(emailAdress.getText(), Utils.EMAIL_REGEX);
        boolean passwordIsValid = Utils.containsOnlyNumbers(password.getText(), Utils.PASSWORD_REGEX);

        String invalidFields = checkInvalidUsersFields(usernameIsValid, isAdminValid, firstNameIsValid, lastNameIsValid, phoneNumberIsValid, emailIsValid, passwordIsValid);
        if (username.getText().isEmpty() || password.getText().isEmpty() || isAdmin.getText().isEmpty() || firstName.getText().isEmpty() || lastName.getText().isEmpty() || phoneNumber.getText().isEmpty() || emailAdress.getText().isEmpty()) {
            showMessageDialog(null, "Fields must not be empty");
        } else if (!isAdminValid || !firstNameIsValid || !lastNameIsValid || !phoneNumberIsValid) {
            showMessageDialog(null, "Fields are not valid" + invalidFields);
        } else {
            String usernameField = username.getText();
            String passwordField = password.getText();
            boolean isAdminField = Boolean.parseBoolean(isAdmin.getText());
            String firstNameField = firstName.getText();
            String lastNameField = lastName.getText();
            String phoneNumberField = phoneNumber.getText();
            String emailAdressField = emailAdress.getText();
            usersController.handleClickButtonInsertUsers(new Users(usernameField, passwordField, isAdminField, firstNameField, lastNameField, phoneNumberField, emailAdressField));
            showMessageDialog(null, "User added");
        }
    }

    private void buttonRegisterUser(ActionEvent actionEvent) {

        boolean usernameIsValid = Utils.containsOnlyNumbersAndLetters(username.getText(), Utils.NUMBERS_CHARACTERS_REGEX);
        boolean firstNameIsValid = Utils.containsOnlyLetters(firstName.getText(), Utils.LETTERS_REGEX);
        boolean lastNameIsValid = Utils.containsOnlyLetters(lastName.getText(), Utils.LETTERS_REGEX);
        boolean phoneNumberIsValid = Utils.containsOnlyNumbers(phoneNumber.getText(), Utils.DECIMAL_DOUBLE_REGEX);
        boolean emailIsValid = Utils.containsOnlyNumbers(emailAdress.getText(), Utils.EMAIL_REGEX);
        boolean passwordIsValid = Utils.containsOnlyNumbers(password.getText(), Utils.PASSWORD_REGEX);


        String invalidFields = checkInvalidUsersRegistrationFields(usernameIsValid, firstNameIsValid, lastNameIsValid, phoneNumberIsValid, emailIsValid, passwordIsValid);
        if (username.getText().isEmpty() || password.getText().isEmpty()  || firstName.getText().isEmpty() ||
                lastName.getText().isEmpty() || phoneNumber.getText().isEmpty() || emailAdress.getText().isEmpty()) {
            showMessageDialog(null, "Fields must not be empty.");
        } else if (!firstNameIsValid || !lastNameIsValid || !phoneNumberIsValid || !emailIsValid || !passwordIsValid) {
            showMessageDialog(null, " Fields are not valid. - " + invalidFields);
        } else {
            String usernameField = username.getText();
            String passwordField = password.getText();
            boolean isAdminField = false;
            String firstNameField = firstName.getText();
            String lastNameField = lastName.getText();
            String phoneNumberField = phoneNumber.getText();
            String emailAdressField = emailAdress.getText();

            usersController.handleClickButtonRegisterUser(new Users(usernameField, passwordField, isAdminField, firstNameField, lastNameField, phoneNumberField, emailAdressField));
            dispose();
        }
    }


    private static String checkInvalidUsersFields(boolean usernameIsValid, boolean isAdminValid, boolean firstNameIsValid,
                                                  boolean lastNameIsValid, boolean phoneNumberIsValid, boolean emailAdressIsValid, boolean passwordIsValid) {

        StringBuilder invalidFields = new StringBuilder();

        if (!isAdminValid) {
            invalidFields.append(" Admin is invalid. ");
        }

        if (!firstNameIsValid) {
            invalidFields.append(" First name is invalid. ");
        }

        if (!lastNameIsValid) {
            invalidFields.append(" Last name is invalid. ");
        }

        if (!phoneNumberIsValid) {
            invalidFields.append(" Phone number is invalid. ");
        }

        if(!emailAdressIsValid) {
            invalidFields.append(" Email address is invalid. ");

        }

        if(!passwordIsValid) {
            invalidFields.append(" Password is invalid, need to be al least 6 characters long. ");
        }

        if(!usernameIsValid) {
            invalidFields.append(" Username is not valid. It can contain a combination of letters, numbers and symbols. ");
        }

        return invalidFields.toString();
    }

    private static String checkInvalidUsersRegistrationFields(boolean usernameIsValid, boolean firstNameIsValid,
                                                              boolean lastNameIsValid, boolean phoneNumberIsValid,
                                                              boolean email, boolean password) {

        StringBuilder invalidFields = new StringBuilder();

        if (!firstNameIsValid) {
            invalidFields.append("First name is invalid");
        }

        if (!lastNameIsValid) {
            invalidFields.append("Last name is invalid");
        }

        if (!phoneNumberIsValid) {
            invalidFields.append("Phone number is invalid");
        }

        if (!email) {
            invalidFields.append("Email is invalid");
        }
        if (!password) {
            invalidFields.append("Password is invalid");
        }
        if(!usernameIsValid) {
            invalidFields.append(" Username is not valid. It can contain only letters, numbers and symbols. ");
        }

        return invalidFields.toString();
    }

    private void addItem(JPanel panel, JComponent component, int x, int y, int align) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = align;
        panel.add(component, gbc);
    }

    public static void main(String[] args) {
        new GridBagLayoutUsersInsert();
    }
}





