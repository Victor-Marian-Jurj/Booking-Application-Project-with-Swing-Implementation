package presentation.room;

import business.entities.Room;
import presentation.users.GridBagLayoutUsersInsert;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.UUID;

import static javax.swing.JOptionPane.showMessageDialog;
import static util.Utils.*;

public class GridBagLayoutRoomInsert extends JFrame {

    private JTextField fieldRoomId;
    private JTextField fieldRoomNumber;
    private JTextField fieldRoomType;
    private JTextField fieldRoomFloor;
    private JTextField fieldRoomPrice;
    private JTextField fieldHotelId;
    private JButton saveButtonRoomInsert;

    private JButton cancelButtonRoomInsert;
    private RoomController roomController = new RoomController();

    public GridBagLayoutRoomInsert() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        addItem(panel, new JLabel("Room Id: "), 0, 0, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Room Number: "), 0, 1, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Room Type: "), 0, 2, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Room Floor: "), 0, 3, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Room Price: "), 0, 4, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Hotel Id: "), 0, 5, GridBagConstraints.EAST);


        fieldRoomId = new JTextField(20);
        fieldRoomNumber = new JTextField(20);
        fieldRoomType = new JTextField(20);
        fieldRoomFloor = new JTextField(20);
        fieldRoomPrice = new JTextField(20);
        fieldHotelId = new JTextField(20);

        addItem(panel, fieldRoomId, 1, 0, GridBagConstraints.WEST);
        addItem(panel, fieldRoomNumber, 1, 1, GridBagConstraints.WEST);
        addItem(panel, fieldRoomType, 1, 2, GridBagConstraints.WEST);
        addItem(panel, fieldRoomFloor, 1, 3, GridBagConstraints.WEST);
        addItem(panel, fieldRoomPrice, 1, 4, GridBagConstraints.WEST);
        addItem(panel, fieldHotelId, 1, 5, GridBagConstraints.WEST);

        saveButtonRoomInsert = new JButton("Save new room");
        saveButtonRoomInsert.addActionListener(this::buttonInsertRoom);
        cancelButtonRoomInsert = new JButton("Cancel");
        cancelButtonRoomInsert.addActionListener(e -> this.dispose());


        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(saveButtonRoomInsert);
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(cancelButtonRoomInsert);
        cancelButtonRoomInsert.addActionListener(e -> this.dispose());


        addItem(panel, buttonBox, 1, 7, GridBagConstraints.WEST);
        setLocationRelativeTo(null);

        this.add(panel);
        this.pack();
        this.setSize(600, 300);
        this.setTitle("Insert new room");
        this.setVisible(true);

    }

    private void buttonInsertRoom(ActionEvent event) {

        boolean roomIdIsValid = Utils.containsOnlyNumbers(fieldRoomId.getText(), ALPHANUMERIC_REGEX);
        boolean roomNumberIsNumber = Utils.containsOnlyNumbers(fieldRoomNumber.getText(), NUMBER_REGEX);
        boolean roomTypeIsValid = Utils.containsOnlyNumbers(fieldRoomType.getText(), LETTERS_REGEX);
        boolean roomFloorIsNumber = Utils.containsOnlyNumbers(fieldRoomFloor.getText(), NUMBER_REGEX);
        boolean roomPriceIsNumber = Utils.containsOnlyNumbers(fieldRoomPrice.getText(), DECIMAL_DOUBLE_REGEX);
        boolean hotelIdIsValid = Utils.containsOnlyNumbers(fieldHotelId.getText(), ALPHANUMERIC_REGEX);

        String invalidFields = checkInvalidFields(roomIdIsValid, roomNumberIsNumber, roomTypeIsValid, roomFloorIsNumber, roomPriceIsNumber, hotelIdIsValid);
        if (fieldRoomId.getText().isEmpty() || fieldHotelId.getText().isEmpty() ||
                fieldRoomType.getText().isEmpty() || fieldRoomFloor.getText().isEmpty() ||
                fieldRoomPrice.getText().isEmpty()) {
            showMessageDialog(null, "Fields must not be empty");
        } else if (!roomIdIsValid || !roomNumberIsNumber || !roomTypeIsValid ||
                !roomFloorIsNumber || !roomPriceIsNumber || !hotelIdIsValid) {
            showMessageDialog(null, "Fields are not valid:\n" + invalidFields);
        } else {
            String roomId = fieldRoomId.getText();
            Integer roomNumber = Integer.parseInt(fieldRoomNumber.getText());
            String roomType = fieldRoomType.getText();
            Integer roomFloor = Integer.parseInt(fieldRoomFloor.getText());
            Integer roomPrice = Integer.parseInt(fieldRoomPrice.getText());
            String hotelId = fieldHotelId.getText();
            boolean isAvailable = true;


            roomController.handleClickButtonInsertRoom(new Room(roomId, roomNumber, roomType, roomFloor, roomPrice, hotelId, isAvailable));
            showMessageDialog(null, "Room added");
        }
    }



    private void addItem(JPanel panel, JComponent component, int x, int y, int align) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = align;
        panel.add(component, gbc);
    }
    private static String checkInvalidFields(boolean roomIdIsValid, boolean roomNumberIsNumber, boolean roomTypeIsValid, boolean roomFloorIsNumber, boolean roomPriceIsNumber, boolean hotelIdIsValid) {
        String roomIdInvalid = "";
        if(!roomIdIsValid) {
            roomIdInvalid = "- room id is invalid\n";
        }

        String hotelIdInvalid = "";
        if(!hotelIdIsValid) {
            hotelIdInvalid = "- hotel id is invalid\n";
        }

        String roomNumberInvalid = "";
        if(!roomNumberIsNumber) {
            roomNumberInvalid = "- room number is invalid\n";
        }

        String roomTypeInvalid = "";
        if(!roomTypeIsValid) {
            roomTypeInvalid = "- room type is invalid\n";
        }

        String roomFloorInvalid = "";
        if(!roomFloorIsNumber) {
            roomFloorInvalid = "- room floor is invalid\n";
        }

        String roomPriceInvalid = "";
        if(!roomPriceIsNumber) {
            roomPriceInvalid = "- room price is invalid\n";
        }
        String invalidFields = roomIdInvalid + roomNumberInvalid + roomTypeInvalid + roomFloorInvalid + roomPriceInvalid + hotelIdInvalid;
        return invalidFields;
    }

    public static void main(String[] args) {
        new GridBagLayoutRoomInsert();
    }
}
