package presentation.adminlogin;

import presentation.hotel.HotelInsertView;
import presentation.room.RoomInsertView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminManagementView extends JFrame {

    public AdminManagementView(){
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setTitle("Admin Home Page");
//        //setSize(1024, 768);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//
//        setLocationRelativeTo(null); // Center the JFrame on the screen
//        setResizable(true);
//        setVisible(true);
//        setContentPane(createAdminPanel());
    }
    public JPanel createAdminPanel() {
        JFrame frame = new JFrame("Insert room and hotel panel");
        JPanel parentPanel = new JPanel(new GridBagLayout());

        JPanel headerPanel = new JPanel();
        JLabel titleLabel = new JLabel("Admin Home Page");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        titleLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.add(titleLabel);

        JMenuBar menuBar = new JMenuBar();

        // Create the main menu
        JMenu mainMenu = new JMenu("ADMIN");

        // Create the submenus
        JMenu logOut = new JMenu("Log Out");

        // Add submenu to the main menu
        mainMenu.add(logOut);

        // Add the main menu to the menu bar
        menuBar.add(mainMenu);

        // Add the menu bar to the header panel
       // headerPanel.add(menuBar, BorderLayout.EAST);




        RoomInsertView roomInsertView = new RoomInsertView();
        HotelInsertView hotelInsertView = new HotelInsertView();


        addItem(parentPanel, headerPanel, 0,0, GridBagConstraints.NORTH,
                2,
                2,
                0.1);

        addItemTop(parentPanel, menuBar, 0, 1,
                0,
                0.3,
                0.1);


        addItem(parentPanel, roomInsertView.createNewRoom(), 0,2, GridBagConstraints.EAST, 1, 0.5, 0.8);
        addItem(parentPanel, hotelInsertView.createNewHotel(), 1,2, GridBagConstraints.WEST, 1, 0.5, 0.8);

        frame.getContentPane().add(parentPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 680);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        return parentPanel;
    }

    private static void addItem(JPanel panel, JComponent component, int x, int y, int align, int gWidth, double weightX, double weightY) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = align;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = gWidth; // Span 1 column
        gbc.weightx = weightX; // Occupy half of horizontal space
        gbc.weighty = weightY; // Occupy most of vertical space
        panel.add(component, gbc);
    }

    private static void addItemTop(JPanel panel, JComponent component, int x, int y, int gWidth, double weightX, double weightY) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = gWidth; // Span 1 column
        gbc.weightx = weightX; // Occupy half of horizontal space
        gbc.weighty = weightY; // Occupy most of vertical space
        panel.add(component, gbc);
    }

    public static void main(String[] args) {
        AdminManagementView adminManagementView = new AdminManagementView();
        adminManagementView.createAdminPanel();

    }
}
