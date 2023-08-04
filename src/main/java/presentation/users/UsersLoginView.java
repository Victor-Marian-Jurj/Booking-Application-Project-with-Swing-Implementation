package presentation.users;


import business.entities.Users;
import util.UserSession;
import persistance.UsersDataAccessSQL;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;


public class UsersLoginView extends JFrame {

    private UsersLoginController usersLoginController = new UsersLoginController();

    private JTextField fieldUsername;
    private JPasswordField fieldPassword;


    public UsersLoginView() {
        setTitle("ClientLoginView");
        setContentPane(createUsersLoginPanel());
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }


    public void setUsersLoginController(UsersLoginController usersLoginController) {
        this.usersLoginController = usersLoginController;
    }

    public void display() {
        setVisible(true);
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private JPanel createUsersLoginPanel() {
        JPanel clientLoginPanel = new JPanel();
        clientLoginPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        clientLoginPanel.setLayout(new GridLayout(3, 1, 10, 10));
        clientLoginPanel.add(createUsernamePanel());
        clientLoginPanel.add(createPasswordPanel());
        clientLoginPanel.add(createButtonPanel());
        return clientLoginPanel;
    }

    private JPanel createUsernamePanel() {
        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new GridLayout(2, 1, 0, 0));
        usernamePanel.add(new JLabel("Username"));
        fieldUsername = new JTextField();
        usernamePanel.add(fieldUsername);
        return usernamePanel;
    }

    private JPanel createPasswordPanel() {
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new GridLayout(2, 1, 0, 0));
        passwordPanel.add(new JLabel("Password"));
        fieldPassword = new JPasswordField();
        passwordPanel.add(fieldPassword);
        return passwordPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2, 10, 10));
        JButton buttonOk = new JButton("Login");
        JButton buttonRegister = new JButton("Register");
        buttonsPanel.add(buttonRegister);
        buttonRegister.addActionListener(this::clickButtonRegister);
        buttonOk.addActionListener(this::clickButtonOk);
        buttonsPanel.add(buttonOk);
        JButton buttonCancel = new JButton("Cancel");
        buttonCancel.addActionListener(this::clickButtonCancel);
        buttonCancel.addActionListener(e -> this.dispose());

        buttonsPanel.add(buttonCancel);
        return buttonsPanel;
    }


    private void clickButtonOk(ActionEvent event) {
        String username = fieldUsername.getText();
        String password = new String(fieldPassword.getPassword());
        try {
            usersLoginController.handleClickButtonOk(new UsersLoginModel(username, password));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clickButtonRegister(ActionEvent event) {
        GridBagLayoutUsersInsert gridBagLayoutUsersInsert = new GridBagLayoutUsersInsert(false);
    }


    private void clickButtonCancel(ActionEvent event) {
        if (usersLoginController != null) {
        String username = fieldUsername.getText();
        String password = new String(fieldPassword.getPassword());
        usersLoginController.handleClickButtonCancel(new UsersLoginModel(username, password));
    } else {
        // Handle the case when usersLoginController is null
        System.err.println("usersLoginController is null. Cannot handle cancel action.");
    }}

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
