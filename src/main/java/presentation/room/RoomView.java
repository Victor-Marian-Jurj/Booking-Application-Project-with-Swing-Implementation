package presentation.room;

import business.entities.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RoomView extends JFrame{
    private DefaultTableModel tableModel;

    private JButton refreshButton;

    private RoomController controller;

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
                controller.buttonRefreshPressedRoom()
        );



        this.setContentPane(content);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }

    private JScrollPane getTablePaneRoom() {
        String[] header = {"room id", "room number", "room type", "room floor", "room price", "hotel id", "is available"};
        tableModel = new DefaultTableModel(header, 0);
        JTable table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        JScrollPane pane = new JScrollPane(table);
        return pane;
    }


    public RoomController getControllerRoom() {
        return controller;
    }
    public void setControllerRoom(RoomController controller) {
        this.controller = controller;
    }

    public void refreshTableRoom(List<Room> roomList) {
        tableModel.setRowCount(0);
        for(Room room :roomList) {
            tableModel.addRow(new Object [] {room.getRoomId(), room.getRoomNumber(), room.getRoomType(), room.getRoomFloor(), room.getRoomPrice(), room.getHotelId(), room.isAvailable()});
        }
    }

}
