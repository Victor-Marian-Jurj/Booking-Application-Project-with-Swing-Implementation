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

        boolean isAdminValid = Utils.containsOnlyBoolean(isAdmin.getText(), Utils.BOOLEAN_REGEX);
        boolean firstNameIsValid = Utils.containsOnlyLetters(firstName.getText(), Utils.LETTERS_REGEX);
        boolean lastNameIsValid = Utils.containsOnlyLetters(lastName.getText(), Utils.LETTERS_REGEX);
        boolean phoneNumberIsValid = Utils.containsOnlyNumbers(phoneNumber.getText(), Utils.DECIMAL_DOUBLE_REGEX);


        String invalidFields = checkInvalidUsersFields(isAdminValid, firstNameIsValid, lastNameIsValid, phoneNumberIsValid);
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

        private void addItem (JPanel panel, JComponent component,int x, int y, int align){
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = x;
            gbc.gridy = y;
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = align;
            panel.add(component, gbc);
        }

        private static String checkInvalidUsersFields ( boolean isAdminValid, boolean firstNameIsValid,
        boolean lastNameIsValid, boolean phoneNumberIsValid){

            StringBuilder invalidFields = new StringBuilder();

            if (!isAdminValid) {
                invalidFields.append("Admin is invalid");
            }

            if (!firstNameIsValid) {
                invalidFields.append("First name is invalid");
            }

            if (!lastNameIsValid) {
                invalidFields.append("Last name is invalid");
            }

            if (!phoneNumberIsValid) {
                invalidFields.append("Phone number is invalid");
            }

            return invalidFields.toString();
        }


        public static void main (String[]args){
            new GridBagLayoutUsersInsert();
        }
    }





