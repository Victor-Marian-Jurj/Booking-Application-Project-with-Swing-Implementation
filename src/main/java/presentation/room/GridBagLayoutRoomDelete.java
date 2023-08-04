package presentation.room;
import business.entities.Hotel;
import business.entities.Room;
import business.services.HotelService;
import business.services.RoomService;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;


public class GridBagLayoutRoomDelete extends JFrame {

    private JButton saveButtonDeleteRoom;

    private JButton cancelButtonDeleteRoom;
    private RoomController roomController = new RoomController();
    private RoomService roomService = new RoomService();

    JComboBox<String> comboBoxHotel;
    JComboBox<Integer> comboBoxRoom;
    List<Hotel> hotelList;
    List<Room> roomList;
    HotelService hotelService = new HotelService();


    public GridBagLayoutRoomDelete() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        addItem(panel, new JLabel("Hotel Name: "), 0, 0, GridBagConstraints.EAST);

        addItem(panel, new JLabel("Select Room Number:"), 0, 1, GridBagConstraints.EAST);

        java.util.List<String> hotelNameList = new ArrayList<>();

        hotelList = hotelService.getAllHotels();
        for(Hotel hotel: hotelList) {
            hotelNameList.add(hotel.getName());
        }


        comboBoxHotel = new JComboBox<>(hotelNameList.toArray(new String[0]));
        comboBoxHotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRoomNumberComboBox();
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

        addItem(panel, comboBoxHotel, 1, 0, GridBagConstraints.WEST);
        addItem(panel, comboBoxRoom, 1, 1, GridBagConstraints.WEST);



        saveButtonDeleteRoom = new JButton("Save");
        saveButtonDeleteRoom.addActionListener(this::buttonDeleteRoom);
        cancelButtonDeleteRoom = new JButton("Cancel");
        cancelButtonDeleteRoom.addActionListener(e -> this.dispose());


        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(saveButtonDeleteRoom);
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(cancelButtonDeleteRoom);

        addItem(panel, buttonBox, 1, 2, GridBagConstraints.WEST);
        setLocationRelativeTo(null);

        this.add(panel);
        this.pack();
        this.setSize(600, 300);
        this.setTitle("Delete room");
        this.setVisible(true);
    }

    private void deleteRoomNumberComboBox() {

        String hotelId = String.valueOf(hotelList.get(comboBoxHotel.getSelectedIndex()).getHotelId());
        java.util.List<Integer> roomNumberList = new ArrayList<>();
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


    private void buttonDeleteRoom(ActionEvent event) {
            String hotelId = String.valueOf(hotelList.get(comboBoxHotel.getSelectedIndex()).getHotelId());
            Integer roomNumber = Integer.valueOf(comboBoxRoom.getSelectedItem().toString());

            roomController.handleClickButtonDeleteRoomByRoomNumberAndHotelId(roomNumber, hotelId);
            showMessageDialog(null, "Room deleted");
        }



    private void addItem(JPanel panel, JComponent component, int x, int y, int align) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = align;
        panel.add(component, gbc);

    }

    public static void main(String[] args) {
        new GridBagLayoutRoomDelete();


    }
}
