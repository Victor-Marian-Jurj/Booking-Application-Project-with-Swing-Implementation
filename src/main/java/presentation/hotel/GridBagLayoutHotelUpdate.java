package presentation.hotel;
import business.entities.Hotel;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.JOptionPane.showMessageDialog;
import static util.Utils.*;
import static util.Utils.LETTERS_REGEX;

public class GridBagLayoutHotelUpdate extends JFrame {

    private JTextField fieldhotel_id;
    private JTextField fieldnumber_of_rooms;
    private JTextField fieldrating;
    private JTextField fieldname;
    private JTextField fieldlocation;

    private JButton saveButtonHotelUpdate;

    private JButton cancelButtonHotelUpdate;

    private HotelController hotelController = new HotelController();

    public GridBagLayoutHotelUpdate() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        addItem(panel, new JLabel("Select hotel_id:"), 0,0, GridBagConstraints.EAST);
        addItem(panel, new JLabel("New number_of_rooms:"), 0,1, GridBagConstraints.EAST);
        addItem(panel, new JLabel("New rating:"), 0, 2, GridBagConstraints.EAST);
        addItem(panel, new JLabel("New name:"), 0,3, GridBagConstraints.EAST);
        addItem(panel, new JLabel("New location:"), 0, 4, GridBagConstraints.EAST);

        fieldhotel_id = new JTextField(20);
        fieldnumber_of_rooms = new JTextField(20);
        fieldrating = new JTextField(20);
        fieldname = new JTextField(20);
        fieldlocation = new JTextField(20);

        addItem(panel, fieldhotel_id, 1, 0, GridBagConstraints.WEST);
        addItem(panel, fieldnumber_of_rooms, 1, 1, GridBagConstraints.WEST);
        addItem(panel, fieldrating, 1, 2, GridBagConstraints.WEST);
        addItem(panel, fieldname, 1, 3, GridBagConstraints.WEST);
        addItem(panel, fieldlocation, 1, 4, GridBagConstraints.WEST);


        saveButtonHotelUpdate = new JButton("Save");
        saveButtonHotelUpdate.addActionListener(this::buttonUpdateHotel);

        cancelButtonHotelUpdate = new JButton("Cancel");
        cancelButtonHotelUpdate.addActionListener(e-> this.dispose());


        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(saveButtonHotelUpdate);
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(cancelButtonHotelUpdate);

        addItem(panel, buttonBox, 1, 6, GridBagConstraints.WEST);
        setLocationRelativeTo(null);

        this.add(panel);
        this.pack();
        this.setTitle("Update hotel data");
        this.setVisible(true);

    }
    private void buttonUpdateHotel(ActionEvent event) {
        boolean fieldhotel_idIsValid = Utils.containsOnlyNumbers(fieldhotel_id.getText(), ALPHANUMERIC_REGEX);
        boolean fieldnumber_of_roomsIsNumber = Utils.containsOnlyNumbers(fieldnumber_of_rooms.getText(), NUMBER_REGEX);
        boolean fieldratingIsNumber = Utils.containsOnlyNumbers(fieldrating.getText(), NUMBER_REGEX);
        boolean fieldnameIsNumber = Utils.containsOnlyNumbers(fieldname.getText(), LETTERS_REGEX);
        boolean fieldlocationIsNumber = Utils.containsOnlyNumbers(fieldlocation.getText(), LETTERS_REGEX);


        String invalidFields = checkInvalidFields(fieldhotel_idIsValid, fieldnumber_of_roomsIsNumber, fieldratingIsNumber, fieldnameIsNumber, fieldlocationIsNumber);
        if (fieldhotel_id.getText().isEmpty() || fieldnumber_of_rooms.getText().isEmpty() ||
                fieldrating.getText().isEmpty() || fieldname.getText().isEmpty() ||
                fieldlocation.getText().isEmpty()) {
            showMessageDialog(null, "Fields must not be empty");
        } else if (!fieldhotel_idIsValid || !fieldnumber_of_roomsIsNumber || !fieldratingIsNumber ||
                !fieldnameIsNumber || !fieldlocationIsNumber) {
            showMessageDialog(null, "Fields are not valid:\n" + invalidFields);
        } else {
            String hotel_id = fieldhotel_id.getText();
            Integer number_of_rooms = Integer.parseInt(fieldnumber_of_rooms.getText());
            Integer rating = Integer.parseInt(fieldrating.getText());
            String name = fieldname.getText();
            String location = fieldlocation.getText();



            hotelController.handleClickButtonUpdateHotel(hotel_id, number_of_rooms, rating, name, location);
            showMessageDialog(null, "Hotel updated");
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

    private static String checkInvalidFields(boolean fieldhotel_idIsValid, boolean fieldnumber_of_roomsIsNumber, boolean fieldratingIsNumber, boolean fieldnameIsNumber, boolean fieldlocationIsNumber) {
        String fieldhotel_idInvalid = "";
        if(!fieldhotel_idIsValid) {
            fieldhotel_idInvalid = "- fieldhotel_id is invalid\n";
        }

        String fieldnumber_of_roomsInvalid= "";
        if(!fieldnumber_of_roomsIsNumber) {
            fieldnumber_of_roomsInvalid = "- fieldnumber_of_rooms is invalid\n";
        }

        String fieldratingInvalid = "";
        if(!fieldratingIsNumber) {
            fieldratingInvalid = "- fieldrating is invalid\n";
        }

        String fieldnameInvalid = "";
        if(!fieldnameIsNumber) {
            fieldnameInvalid = "- fieldname is invalid\n";
        }

        String fieldlocationInvalid = "";
        if(!fieldlocationIsNumber) {
            fieldlocationInvalid = "- fieldlocation is invalid\n";
        }

        String invalidFields = fieldhotel_idInvalid  + fieldnumber_of_roomsInvalid + fieldratingInvalid + fieldnameInvalid + fieldlocationInvalid;
        return invalidFields;
    }

    public static void main(String[] args) {
        new GridBagLayoutHotelUpdate();
    }
}
