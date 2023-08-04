package presentation.room;

import business.entities.Hotel;
import business.entities.Room;
import business.services.HotelService;
import business.services.RoomService;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;
import static util.Utils.NUMBER_REGEX;

public class GridBagLayoutRoomUpdate extends JFrame {
    private JTextField fieldRoomNumber;
    private JTextField fieldRoomPrice;

    private JButton saveButtonRoomUpdate;

    private JButton cancelButtonRoomUpdate;
    private RoomController roomController = new RoomController();

    JComboBox<String> comboBoxHotel;
    JComboBox<Integer> comboBoxRoom;
    List<Hotel> hotelList;
    List<Room> roomList;
    HotelService hotelService = new HotelService();
    RoomService roomService = new RoomService();

    public GridBagLayoutRoomUpdate() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        addItem(panel, new JLabel("Hotel Name: "), 0, 0, GridBagConstraints.EAST);

        addItem(panel, new JLabel("Select Room Number:"), 0, 1, GridBagConstraints.EAST);

        addItem(panel, new JLabel("New room price: "), 0, 2, GridBagConstraints.EAST);

        List<String> hotelNameList = new ArrayList<>();

        hotelList = hotelService.getAllHotels();
        for(Hotel hotel: hotelList) {
            hotelNameList.add(hotel.getName());
        }


        comboBoxHotel = new JComboBox<>(hotelNameList.toArray(new String[0]));
        comboBoxHotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRoomNumberComboBox();
            }
        });


        List<Integer> roomNumberList = new ArrayList<>();
        String hotelId = String.valueOf(hotelList.get(comboBoxHotel.getSelectedIndex()).getHotelId());
        roomList = roomService.getAllRooms();
        for(Room room: roomList) {
            if(room.getHotelId().trim().equals(hotelId)){
                roomNumberList.add(room.getRoomNumber());
            }
        }

        comboBoxRoom = new JComboBox<>(roomNumberList.toArray(new Integer[0]));
        fieldRoomPrice = new JTextField(20);

        addItem(panel, comboBoxHotel, 1, 0, GridBagConstraints.WEST);
        addItem(panel, comboBoxRoom, 1, 1, GridBagConstraints.WEST);
        addItem(panel, fieldRoomPrice, 1, 2, GridBagConstraints.WEST);



        saveButtonRoomUpdate = new JButton("Save");
        saveButtonRoomUpdate.addActionListener(this::buttonUpdateRoom);
        cancelButtonRoomUpdate = new JButton("Cancel");
        cancelButtonRoomUpdate.addActionListener(e -> this.dispose());


        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(saveButtonRoomUpdate);
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(cancelButtonRoomUpdate);


        addItem(panel, buttonBox, 1, 3, GridBagConstraints.WEST);
        setLocationRelativeTo(null);

        this.add(panel);
        this.pack();
        this.setSize(600, 300);
        this.setTitle("Update room data");
        this.setVisible(true);

    }


    private void updateRoomNumberComboBox() {

        String hotelId = String.valueOf(hotelList.get(comboBoxHotel.getSelectedIndex()).getHotelId());
        List<Integer> roomNumberList = new ArrayList<>();
        roomList = roomService.getAllRooms();
        for(Room room: roomList) {
            if(room.getHotelId().trim().equals(hotelId)){
                roomNumberList.add(room.getRoomNumber());
            }
        }

        DefaultComboBoxModel<Integer> roomComboBox = new DefaultComboBoxModel<>();
        for (Integer roomNumber : roomNumberList) {
            roomComboBox.addElement(roomNumber);
        }
        comboBoxRoom.setModel(roomComboBox);
    }


    private void buttonUpdateRoom(ActionEvent event) {

        boolean roomPriceIsValid = Utils.containsOnlyNumbers(fieldRoomPrice.getText(), Utils.DECIMAL_DOUBLE_REGEX);

        String invalidFields = checkInvalidFields(roomPriceIsValid);
        if (fieldRoomPrice.getText().isEmpty()) {
            showMessageDialog(null, "Fields must not be empty");
        } else if (!roomPriceIsValid) {
            showMessageDialog(null, "Fields are not valid:\n" + invalidFields);
        } else {

            String hotelId = String.valueOf(hotelList.get(comboBoxHotel.getSelectedIndex()).getHotelId());
            Integer roomNumber = Integer.valueOf(comboBoxRoom.getSelectedItem().toString());

            Integer roomPrice = Integer.parseInt(fieldRoomPrice.getText());
            roomController.handleClickButtonUpdateRoomByRoomNumberAndHotelId(roomNumber, hotelId, roomPrice);
            showMessageDialog(null, "Room updated");

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

    private static String checkInvalidFields(boolean roomPriceIsNumber) {

        String roomPriceInvalid = "";
        if (!roomPriceIsNumber) {
            roomPriceInvalid = "- room price is invalid\n";
        }
        String invalidFields = roomPriceInvalid;
        return invalidFields;
    }

    public static void main(String[] args) {
        new GridBagLayoutRoomUpdate();
    }
}
