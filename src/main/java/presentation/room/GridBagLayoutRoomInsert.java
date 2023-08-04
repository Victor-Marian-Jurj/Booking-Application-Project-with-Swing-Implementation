package presentation.room;

import business.entities.Hotel;
import business.entities.Room;
import business.services.HotelService;
import business.services.RoomService;
import presentation.users.GridBagLayoutUsersInsert;
import util.IDRandomiser;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;
import static util.Utils.*;

public class GridBagLayoutRoomInsert extends JFrame {

    private JTextField fieldRoomNumber;
    private JTextField fieldRoomType;
    private JTextField fieldRoomFloor;
    private JTextField fieldRoomPrice;
    private JTextField fieldHotelId;

    JComboBox<String> comboBox;
    List<Hotel> hotelList;
    HotelService hotelService = new HotelService();
    private JButton saveButtonRoomInsert;

    private JButton cancelButtonRoomInsert;
    private RoomController roomController = new RoomController();

    public GridBagLayoutRoomInsert() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        addItem(panel, new JLabel("Room Number: "), 0, 1, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Room Type: "), 0, 2, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Room Floor: "), 0, 3, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Room Price: "), 0, 4, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Hotel Name: "), 0, 5, GridBagConstraints.EAST);

        List<String> hotelNameList = new ArrayList<>();

        hotelList = hotelService.getAllHotels();
        for(Hotel hotel: hotelList) {
            hotelNameList.add(hotel.getName());
        }

        fieldRoomNumber = new JTextField(20);
        fieldRoomType = new JTextField(20);
        fieldRoomFloor = new JTextField(20);
        fieldRoomPrice = new JTextField(20);
        fieldHotelId = new JTextField(20);
        comboBox = new JComboBox<>(hotelNameList.toArray(new String[0]));

        addItem(panel, fieldRoomNumber, 1, 1, GridBagConstraints.WEST);
        addItem(panel, fieldRoomType, 1, 2, GridBagConstraints.WEST);
        addItem(panel, fieldRoomFloor, 1, 3, GridBagConstraints.WEST);
        addItem(panel, fieldRoomPrice, 1, 4, GridBagConstraints.WEST);
        addItem(panel, comboBox, 1, 5, GridBagConstraints.WEST);


        saveButtonRoomInsert = new JButton("Save");
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

        boolean roomNumberIsNumber = Utils.containsOnlyNumbers(fieldRoomNumber.getText(), NUMBER_REGEX);
        boolean roomTypeIsValid = Utils.containsOnlyNumbers(fieldRoomType.getText(), LETTERS_REGEX);
        boolean roomFloorIsNumber = Utils.containsOnlyNumbers(fieldRoomFloor.getText(), NUMBER_REGEX);
        boolean roomPriceIsNumber = Utils.containsOnlyNumbers(fieldRoomPrice.getText(), DECIMAL_DOUBLE_REGEX);

        String invalidFields = checkInvalidFields(roomNumberIsNumber, roomTypeIsValid, roomFloorIsNumber, roomPriceIsNumber);
        if (fieldRoomType.getText().isEmpty() || fieldRoomFloor.getText().isEmpty() ||
                fieldRoomPrice.getText().isEmpty()) {
            showMessageDialog(null, "Fields must not be empty");
        } else if (!roomNumberIsNumber || !roomTypeIsValid ||
                !roomFloorIsNumber || !roomPriceIsNumber ) {
            showMessageDialog(null, "Fields are not valid:\n" + invalidFields);
        } else {
            String roomId = IDRandomiser.getId();
            Integer roomNumber = Integer.parseInt(fieldRoomNumber.getText());
            String roomType = fieldRoomType.getText();
            Integer roomFloor = Integer.parseInt(fieldRoomFloor.getText());
            Integer roomPrice = Integer.parseInt(fieldRoomPrice.getText());

            int indexHotel = comboBox.getSelectedIndex();
            Hotel hotelSelected = hotelList.get(indexHotel);
            String hotelId = String.valueOf(hotelSelected.getHotelId());
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
    private static String checkInvalidFields(boolean roomNumberIsNumber, boolean roomTypeIsValid, boolean roomFloorIsNumber, boolean roomPriceIsNumber) {


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
        String invalidFields = roomNumberInvalid + roomTypeInvalid + roomFloorInvalid + roomPriceInvalid;
        return invalidFields;
    }

    public static void main(String[] args) {
        new GridBagLayoutRoomInsert();
    }
}
