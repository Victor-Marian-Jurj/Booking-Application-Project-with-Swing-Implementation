package presentation.users;

import persistance.UsersDataAccessSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.JOptionPane.showMessageDialog;


public class GridBagLayoutUsersDelete extends JFrame {
    private JTextField username;

    private JButton saveButtonDeleteUser;

    private JButton cancelButtonDeleteUser;

    private UsersController usersController;

    public GridBagLayoutUsersDelete() {
        usersController = new UsersController(new UsersDataAccessSQL());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel() ;
        panel.setLayout(new GridBagLayout());

        addItem(panel, new JLabel("Delete username: "), 0, 0, GridBagConstraints.EAST);

        username = new JTextField(25);

        addItem(panel, username, 1, 0, GridBagConstraints.WEST);

        saveButtonDeleteUser = new JButton("Save");
        cancelButtonDeleteUser = new JButton("Cancel");

        saveButtonDeleteUser.addActionListener(this::buttonDeleteUser);
        cancelButtonDeleteUser.addActionListener(e -> this.dispose());

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(saveButtonDeleteUser);
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(cancelButtonDeleteUser);

        addItem(panel, buttonBox, 1, 1, GridBagConstraints.WEST);
        setLocationRelativeTo(null);

        this.add(panel);
        this.pack();
        this.setTitle("Delete user");
        this.setVisible(true);
    }

    private void buttonDeleteUser(ActionEvent actionEvent) {
        if (usersController == null) {
            System.err.println("usersController is null. Cannot handle delete user action.");
            return;
        }

        String usernameToDelete = username.getText();
        if (usernameToDelete.isEmpty()) {
            showMessageDialog(null, "Field must not be empty");
            return;
        }

        if (usersController.isUsernameExists(usernameToDelete)) {
            usersController.handleClickButtonDeleteUsers(usernameToDelete);
            showMessageDialog(null, "User deleted");
        } else {
            showMessageDialog(null, "Username does not exist in the database.");
        }
    }



    private void addItem(JPanel panel, JComponent component, int x, int y, int align){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = align;
        panel.add(component, gbc);

    }

    public static void main(String[] args) {
        new GridBagLayoutUsersDelete();
    }

}
