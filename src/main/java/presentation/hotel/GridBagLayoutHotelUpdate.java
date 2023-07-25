package presentation.hotel;

import javax.swing.*;
import java.awt.*;

public class GridBagLayoutHotelUpdate extends JFrame {

    private JTextField hotel_id;
    private JTextField number_of_rooms;
    private JTextField rating;
    private JTextField name;
    private JTextField location;

    private JButton saveButton;

    private JButton cancelButton;

    public GridBagLayoutHotelUpdate() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        addItem(panel, new JLabel("Select hotel_id:"), 0,0, GridBagConstraints.EAST);
        addItem(panel, new JLabel("New number_of_rooms:"), 0,1, GridBagConstraints.EAST);

        hotel_id = new JTextField(20);
        number_of_rooms = new JTextField(15);

        addItem(panel, hotel_id, 1, 0, GridBagConstraints.WEST);
        addItem(panel, number_of_rooms, 1, 1, GridBagConstraints.WEST);


        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(saveButton);
        buttonBox.add(Box.createVerticalStrut(20));
        buttonBox.add(cancelButton);

        addItem(panel, buttonBox, 1, 2, GridBagConstraints.WEST);
        setLocationRelativeTo(null);

        this.add(panel);
        this.pack();
        this.setTitle("Update hotel data");
        this.setVisible(true);

    }

    private void addItem( JPanel panel, JComponent component, int x, int y, int align) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = align;
        panel.add(component, gbc);
    }

    public static void main(String[] args) {
        new GridBagLayoutHotelUpdate();
    }
}
