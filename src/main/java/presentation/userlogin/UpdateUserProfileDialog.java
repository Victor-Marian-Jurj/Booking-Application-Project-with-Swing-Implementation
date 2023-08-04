package presentation.userlogin;

import business.entities.Users;
import persistance.UsersDataAccessSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UpdateUserProfileDialog extends JDialog {

    private UserView userView;
    private Users currentUser;

    private UsersDataAccessSQL usersDataAccess = new UsersDataAccessSQL();

    private JCheckBox updatePasswordCheckBox;
    private JCheckBox updatePhoneNumberCheckBox;
    private JCheckBox updateEmailAddressCheckBox;

    private JTextField newPasswordTextField;
    private JTextField newPhoneNumberTextField;
    private JTextField newEmailAddressTextField;

    public UpdateUserProfileDialog(UserView userView, Users currentUser) {
        this.userView = userView;
        this.currentUser = currentUser;

         UsersDataAccessSQL usersDataAccess = new UsersDataAccessSQL();

         JButton btnUpdate = new JButton("Update");


        JLabel lblUsername = new JLabel("Username: " + currentUser.getUsername());
        JLabel lblPhoneNumber = new JLabel("Phone Number:");
        JTextField txtPhoneNumber = new JTextField(currentUser.getPhoneNumber());
        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField();
        JLabel lblEmail = new JLabel("Email Address:");
        JTextField txtEmail = new JTextField(currentUser.getEmailAdress());

        setModalityType(ModalityType.APPLICATION_MODAL);
        setTitle("Update User Profile");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        contentPane.add(lblUsername, BorderLayout.NORTH);
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(lblPhoneNumber);
        inputPanel.add(txtPhoneNumber);
        // ... (add other components to inputPanel)
        contentPane.add(inputPanel, BorderLayout.CENTER);
        contentPane.add(btnUpdate, BorderLayout.SOUTH);

        setContentPane(contentPane);
        pack();

        setModalityType(ModalityType.APPLICATION_MODAL);
        setTitle("Update User Profile");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        // Create and add components to the dialog
        JLabel lblUsername = new JLabel("Username: " + currentUser.getUsername());
        JLabel lblPhoneNumber = new JLabel("Phone Number:");
        JTextField txtPhoneNumber = new JTextField(currentUser.getPhoneNumber());
        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField();
        JLabel lblEmail = new JLabel("Email Address:");
        JTextField txtEmail = new JTextField(currentUser.getEmailAdress());

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> handleUpdateButtonPressed(txtPassword.getText()));

        // Add components to the content pane
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(lblUsername, BorderLayout.NORTH);
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(lblPhoneNumber);
        inputPanel.add(txtPhoneNumber);
        inputPanel.add(lblPassword);
        inputPanel.add(txtPassword);
        inputPanel.add(lblEmail);
        inputPanel.add(txtEmail);
        contentPane.add(inputPanel, BorderLayout.CENTER);
        contentPane.add(btnUpdate, BorderLayout.SOUTH);

        // Set the content pane and pack the dialog
        setContentPane(contentPane);
        pack();
    }

    private void handleUpdateButtonPressed(String newPassword) {
        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "User not logged in!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String newPhoneNumber = newPhoneNumberTextField.getText();
        String newEmailAddress = newEmailAddressTextField.getText();

        if (updatePasswordCheckBox.isSelected() && !newPassword.isEmpty()) {
            // Update the password in the database
            usersDataAccess.updatePassword(currentUser.getUsername(), newPassword);
        }

        if (updatePhoneNumberCheckBox.isSelected() && !newPhoneNumber.isEmpty()) {
            // Update the phone number in the database
            usersDataAccess.updatePhoneNumber(currentUser.getUsername(), newPhoneNumber);
        }

        if (updateEmailAddressCheckBox.isSelected() && !newEmailAddress.isEmpty()) {
            // Update the email address in the database
            usersDataAccess.updateEmailAddress(currentUser.getUsername(), newEmailAddress);
        }

        JOptionPane.showMessageDialog(this, "User profile updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    // Setter to set the currently logged-in user
    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }
}
