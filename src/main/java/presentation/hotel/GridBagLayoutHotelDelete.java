package presentation.hotel;
import business.entities.Hotel;
import persistance.HotelDataAccessSQL;
import persistance.HotelDataAccess;
import presentation.hotel.GridBagLayoutHotelDelete;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.JOptionPane.showMessageDialog;


public class GridBagLayoutHotelDelete extends JFrame {
    private JTextField fieldHotelId;

    private JButton saveButtonDeleteHotel;

    private JButton cancelButtonDeleteHotel;
    private HotelController hotelController = new HotelController();

    public GridBagLayoutHotelDelete() {
        hotelController = new HotelController(new HotelDataAccessSQL());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel() ;
        panel.setLayout(new GridBagLayout());

        addItem(panel, new JLabel("Hotel_id: "), 0, 0, GridBagConstraints.EAST);

        fieldHotelId = new JTextField(25);

        addItem(panel, fieldHotelId, 1, 0, GridBagConstraints.WEST);



        saveButtonDeleteHotel = new JButton("Save");
        saveButtonDeleteHotel.addActionListener(this::buttonDeleteHotel);

        cancelButtonDeleteHotel = new JButton("Cancel");
        cancelButtonDeleteHotel.addActionListener(e -> this.dispose());

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(saveButtonDeleteHotel);
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(cancelButtonDeleteHotel);


        addItem(panel, buttonBox, 1, 1, GridBagConstraints.WEST);
        setLocationRelativeTo(null);


        this.add(panel);
        this.pack();
        setSize(600, 300);
        this.setTitle("Delete hotel");
        this.setVisible(true);
    }


    private void buttonDeleteHotel(ActionEvent actionEvent) {
        if (hotelController == null) {
            System.err.println("hotelController is null. Cannot handle delete user action.");
            return;
        }

        String fieldHotelIdToDelete = fieldHotelId.getText();
        if (fieldHotelIdToDelete.isEmpty()) {
            showMessageDialog(null, "Field must not be empty");
            return;
        }

        if (hotelController.isfieldHotelIdExists(fieldHotelIdToDelete)) {
            hotelController.handleClickButtonDeleteHotel(fieldHotelIdToDelete);
            showMessageDialog(null, "Hotel_id deleted");
        } else {
            showMessageDialog(null, "Hotel_id does not exist in the database.");
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
        new GridBagLayoutHotelDelete();
    }

}
