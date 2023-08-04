package presentation.room;

import business.entities.Hotel;
import business.entities.Room;
import business.services.HotelService;
import presentation.reservation.ReservationController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class RoomView extends JFrame{


    private JButton refreshButton;

    private RoomController roomController;
    private ReservationController reservationController;
    HotelService hotelService = new HotelService();

    public RoomView(List<Room> roomList) {
        this();
        refreshTableRoom(roomList);
    }

    public RoomView() {
        super("Room List");
        refreshButton = new JButton("Refresh");


        JPanel content = new JPanel();
        JScrollPane pane = getTablePaneRoom();
        content.add(pane);
        content.add(refreshButton);
        refreshButton.addActionListener(e->
                roomController.buttonRefreshPressedRoom()
        );



        this.setContentPane(content);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }

    private JTable table;
    private DefaultTableModel tableModel;
    private JPopupMenu popupMenu;

    private JScrollPane getTablePaneRoom() {

        String[] header = {"Room number", "Room type", "Room floor", "Room price", "Hotel name", "Is available"};
        tableModel = new DefaultTableModel(header, 0);
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        table.getColumnModel().getColumn(1).setPreferredWidth(300);

        table.setCellSelectionEnabled(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        List<Hotel> hotelList = hotelService.getAllHotels();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = table.rowAtPoint(e.getPoint());
                    if (row >= 0 && row < table.getRowCount()) {
                        table.setRowSelectionInterval(row, row);
                    }
                    showContextMenu(e.getX(), e.getY());
                }
            }
        });
        popupMenu = new JPopupMenu();
        JMenuItem deleteRowItem = new JMenuItem("Delete");
        JMenuItem updateRowItem = new JMenuItem("Update");
        popupMenu.add(deleteRowItem);
        popupMenu.add(updateRowItem);
        deleteRowItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Room room = new Room();
                    room.setRoomNumber((Integer) table.getValueAt(selectedRow, 0));
                    room.setRoomType(table.getValueAt(selectedRow, 1).toString());
                    room.setRoomFloor(Integer.parseInt(table.getValueAt(selectedRow, 2).toString()));
                    room.setRoomPrice(Integer.parseInt(table.getValueAt(selectedRow, 3).toString()));
                    String hotelName = table.getValueAt(selectedRow, 4).toString();
                    room.setAvailable(Boolean.parseBoolean(table.getValueAt(selectedRow, 5).toString()));

                    for(Hotel hotel : hotelList){
                        if(hotelName.trim().equals(hotel.getName())){
                            room.setHotelId(hotel.getHotelId());
                        }
                    }

                    roomController.handleClickButtonDeleteRoomByRoomNumberAndHotelId(room.getRoomNumber(), room.getHotelId());
                    tableModel.removeRow(selectedRow);
                }
            }
        });

        updateRowItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Room room = new Room();
                    room.setRoomNumber((Integer) table.getValueAt(selectedRow, 0));
                    room.setRoomType(table.getValueAt(selectedRow, 1).toString());
                    room.setRoomFloor(Integer.parseInt(table.getValueAt(selectedRow, 2).toString()));
                    room.setRoomPrice(Integer.parseInt(table.getValueAt(selectedRow, 3).toString()));
                    String hotelName = table.getValueAt(selectedRow, 4).toString();
                    room.setAvailable(Boolean.parseBoolean(table.getValueAt(selectedRow, 5).toString()));

                    for(Hotel hotel : hotelList){
                        if(hotelName.trim().equals(hotel.getName())){
                            room.setHotelId(hotel.getHotelId());
                        }
                    }

                    roomController.handleClickButtonUpdateRoomByRoomNumberAndHotelId(room.getRoomNumber(),room.getHotelId(),room.getRoomPrice());
                }
            }
        });
        JScrollPane pane = new JScrollPane(table);


        return pane;
    }

    private void showContextMenu(int x, int y) {
        popupMenu.show(table, x, y);
    }

    public RoomController getControllerRoom() {
        return roomController;
    }
    public void setControllerRoom(RoomController controller) {
        this.roomController = controller;
    }

    public void refreshTableRoom(List<Room> roomList) {
        tableModel.setRowCount(0);
        List<Hotel> hotelList = hotelService.getAllHotels();
        String hotelName = "";
        for(Room room :roomList) {
            for(Hotel hotel : hotelList){
                if(room.getHotelId().trim().equals(hotel.getHotelId())){
                    hotelName = hotel.getName();
                }
            }

            tableModel.addRow(new Object [] {room.getRoomNumber(), room.getRoomType(), room.getRoomFloor(), room.getRoomPrice(), hotelName, room.isAvailable()});
        }
    }

}
