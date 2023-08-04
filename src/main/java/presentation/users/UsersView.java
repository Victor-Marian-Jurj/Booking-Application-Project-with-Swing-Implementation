package presentation.users;

import business.entities.Users;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


public class UsersView extends JFrame {

    private DefaultTableModel tableModel;

    private JButton refreshButton;
    private UsersController controller;

    public UsersView(List<Users> usersList) {
        this();
        refreshTableUsers(usersList);
    }

    public UsersView() {
        super("Clients List");
        refreshButton = new JButton("Refresh");


        JPanel content = new JPanel();
        JScrollPane pane = getTablePane();
        content.add(pane);
        content.add(refreshButton);
        refreshButton.addActionListener(e->
                controller.buttonRefreshPressed()
        );


        this.setContentPane(content);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }

    private JScrollPane getTablePane() {
        String[] header = {"username", "isAdmin", "first name", "last name", "phone number", "email adress"};
        tableModel = new DefaultTableModel(header, 0);
        JTable table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        JScrollPane pane = new JScrollPane(table);
        return pane;

    }

    public UsersController getController () {
        return controller;
    }
    public void setController(UsersController controller) {
        this.controller = controller;
    }

    public void refreshTableUsers(List<Users> usersList) {
        tableModel.setRowCount(0);
        for(Users users : usersList) {
            tableModel.addRow(new Object [] {users.getUsername(), users.getIsAdmin(), users.getFirstName(), users.getLastName(), users.getPhoneNumber(), users.getEmailAdress()});
        }
    }

}
