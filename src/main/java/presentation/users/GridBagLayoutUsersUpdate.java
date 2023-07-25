package presentation.users;

import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.JOptionPane.showMessageDialog;

public class GridBagLayoutUsersUpdate extends JFrame {

    private JTextField username;

    private JTextField phoneNumber;

    private JButton saveButtonUserUpdate;

    private JButton cancelButtonUserUpdate;

    private UsersController usersController = new UsersController();

    public GridBagLayoutUsersUpdate() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        addItem(panel, new JLabel("Select username: "), 0,0, GridBagConstraints.EAST);
        addItem(panel, new JLabel("New phone number: "), 0,1, GridBagConstraints.EAST);

        username = new JTextField(20);
        phoneNumber = new JTextField(15);

        addItem(panel, username, 1, 0, GridBagConstraints.WEST);
        addItem(panel, phoneNumber, 1, 1, GridBagConstraints.WEST);

        saveButtonUserUpdate = new JButton("Save");
        cancelButtonUserUpdate = new JButton("Cancel");

        saveButtonUserUpdate.addActionListener(this::buttonUpdateClient);
        cancelButtonUserUpdate.addActionListener(e -> this.dispose());


        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(saveButtonUserUpdate);
        buttonBox.add(Box.createVerticalStrut(20));
        buttonBox.add(cancelButtonUserUpdate);

        addItem(panel, buttonBox, 1, 2, GridBagConstraints.WEST);
        setLocationRelativeTo(null);

        this.add(panel);
        this.pack();
        this.setTitle("Update user data");
        this.setVisible(true);

    }

    private void buttonUpdateClient(ActionEvent actionEvent) {

        boolean phoneNumberIsValid = Utils.containsOnlyNumbers(phoneNumber.getText(), Utils.DECIMAL_DOUBLE_REGEX);

        String invalidField = checkInvalidField(phoneNumberIsValid);

        if(username.getText().isEmpty() || phoneNumber.getText().isEmpty()) {
            showMessageDialog(null, "Fields must not be empty");
        } else if(!phoneNumberIsValid) {
            showMessageDialog(null, "Field is not valid: " + invalidField);
        } else {
            String usernameField = username.getText();
            String phoneNumberField = phoneNumber.getText();
            usersController.handleClickButtonUpdateUsers(usernameField, phoneNumberField);
            showMessageDialog(null, "User updated");
        }
    }

    private void addItem( JPanel panel, JComponent component, int x, int y, int align) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = align;
        panel.add(component, gbc);
    }

    private static String checkInvalidField(boolean phoneNumberIsValid) {
        String invalidField = "";
        if(!phoneNumberIsValid) {
            invalidField ="phone number is invalid";
        }
        return invalidField;

    }

    public static void main(String[] args) {
        new GridBagLayoutUsersUpdate();
    }
}
