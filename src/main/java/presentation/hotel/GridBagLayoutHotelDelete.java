package presentation.hotel;

import javax.swing.*;
import java.awt.*;


public class GridBagLayoutHotelDelete extends JFrame {
    private JTextField hotel_id;

    private JButton saveButton;

    private JButton cancelButton;

    public GridBagLayoutHotelDelete() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel() ;
        panel.setLayout(new GridBagLayout());

        addItem(panel, new JLabel("Delete hotel_id: "), 0, 0, GridBagConstraints.EAST);

        hotel_id = new JTextField(25);

        addItem(panel, hotel_id, 1, 0, GridBagConstraints.WEST);

        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(saveButton);
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(cancelButton);

        addItem(panel, buttonBox, 1, 1, GridBagConstraints.WEST);
        setLocationRelativeTo(null);


        this.add(panel);
        this.pack();
        this.setTitle("Delete hotel");
        this.setVisible(true);
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
        new GridBagLayoutHotelDelete();
    }

}
