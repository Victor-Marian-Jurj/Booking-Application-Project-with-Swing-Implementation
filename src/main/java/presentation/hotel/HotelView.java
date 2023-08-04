package presentation.hotel;
import business.entities.Hotel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HotelView extends JFrame {

    private DefaultTableModel tableModel;

    private JButton refreshButton;

    private HotelController controller;

    public HotelView(List<Hotel> hotelList) {
        this();
        refreshTableHotel(hotelList);
    }

    public HotelView() {
        super("Academy MVC with SWING");
        refreshButton = new JButton("Refresh");

        JPanel content = new JPanel();
        JScrollPane pane = getTablePane();
        content.add(pane);
        content.add(refreshButton);
        refreshButton.addActionListener(e->controller.buttonRefreshPressedHotel());
        this.setContentPane(content);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }

    private JScrollPane getTablePane() {
        String[] header = {"name", "location", "rating", "number of rooms" };
        tableModel = new DefaultTableModel(header, 0);
        JTable table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        JScrollPane pane = new JScrollPane(table);
        return pane;
    }

    public HotelController getController () {
        return controller;
    }
    public void setController(HotelController controller) {
        this.controller = controller;
    }

    public void refreshTableHotel(List<Hotel> hotelList) {
        tableModel.setRowCount(0);
        for(Hotel hotel:hotelList) {
            tableModel.addRow(new Object [] {hotel.getName(), hotel.getLocation(), hotel.getRating(), hotel.getNumber_of_rooms()});
        }
    }

}