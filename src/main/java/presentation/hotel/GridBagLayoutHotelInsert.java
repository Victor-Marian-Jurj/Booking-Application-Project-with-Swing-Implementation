package presentation.hotel;

import javax.swing.*;
import java.awt.*;

public class GridBagLayoutHotelInsert extends JFrame {

    private JTextField hotel_id;

    private JTextField number_of_rooms;

    private JTextField rating;

    private JTextField name;

    private JTextField location;

    private JButton saveButton;

    private JButton cancelButton;

    public GridBagLayoutHotelInsert() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        setLocationRelativeTo(null);


        addItem(panel, new JLabel("HotelId: "), 0, 0, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Number_of_rooms: "), 0, 1, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Rating: "), 0, 2,GridBagConstraints.EAST);
        addItem(panel, new JLabel("Name: "), 0, 3, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Location: "), 0, 4, GridBagConstraints.EAST);


        hotel_id = new JTextField(20);
        number_of_rooms = new JTextField(20);
        rating = new JTextField(10);
        name = new JTextField(30);
        location = new JTextField(20);



        addItem(panel, hotel_id, 1, 0, GridBagConstraints.WEST);
        addItem(panel, number_of_rooms, 1, 1, GridBagConstraints.WEST);
        addItem(panel, rating, 1, 2, GridBagConstraints.WEST);
        addItem(panel, name, 1, 3, GridBagConstraints.WEST);
        addItem(panel, location, 1, 4, GridBagConstraints.WEST);



        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(saveButton);
        buttonBox.add(Box.createVerticalStrut(20));
        buttonBox.add(cancelButton);

        addItem(panel, buttonBox, 1, 7, GridBagConstraints.WEST);
        setLocationRelativeTo(null);

        this.add(panel);
        this.pack();
        this.setSize(600, 300);
        this.setTitle("Insert new hotel");
        this.setVisible(true);

    }
    private void addItem (JPanel panel, JComponent component,int x, int y, int align){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = align;
        panel.add(component, gbc);
    }

    public static void main (String[]args){
        new GridBagLayoutHotelInsert();
    }
}

