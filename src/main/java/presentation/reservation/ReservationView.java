package presentation.reservation;

import business.entities.Reservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ReservationView extends JFrame {
    private String userType;
    private DefaultTableModel tableModel;
    private JButton refreshButton;
    private ReservationController reservationController;
    public ReservationView(String userType, List<Reservation> reservationList) {
        this();
        this.userType = userType;
        refreshTable(reservationList);
    }

    public ReservationView (){
        super("Reservations");
        refreshButton = new JButton("Refresh");
        JPanel content = new JPanel();
        JScrollPane pane = getTablePane();
        content.add(pane);
        content.add(refreshButton);
        refreshButton.addActionListener(e->
                reservationController.buttonRefreshPressed(userType)
        );
        this.setContentPane(content);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(1150, 555));
        this.setVisible(true);
        this.pack();
    }

    public ReservationView (String userType, List<Reservation> reservationList, boolean isUser){
        super("My Reservations");
        refreshButton = new JButton("Refresh");
        JPanel content = new JPanel();
        JScrollPane pane = getTablePaneUserReservation();
        content.add(pane);
        content.add(refreshButton);
        refreshButton.addActionListener(e->
                reservationController.buttonRefreshPressedUser()
        );
        this.setContentPane(content);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(1150, 555));
        this.setVisible(true);
        this.pack();
        refreshTableUserReservation(reservationList);

    }

    private JScrollPane getTablePane() {
        String[] header = {"Username", "Reservation Status", "Check-in date", "Check-out date"};
        tableModel = new DefaultTableModel(header, 0);
        JTable table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        JScrollPane pane = new JScrollPane(table);
        return pane;
    }

    private JScrollPane getTablePaneUserReservation() {
        String[] header = {"Hotel name", "Room number", "Reservation Status", "Check-in date", "Check-out date"};
        tableModel = new DefaultTableModel(header, 0);
        JTable table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        JScrollPane pane = new JScrollPane(table);
        return pane;
    }

    public ReservationController getReservationController() {
        return reservationController;
    }
    public void setReservationController(ReservationController reservationController) {
        this.reservationController = reservationController;
    }

    public void refreshTable (List<Reservation> reservationList) {
        tableModel.setRowCount(0);
        for (Reservation reservation : reservationList) {
            tableModel.addRow(new Object[]{reservation.getUsername(), reservation.getReservationStatus(), reservation.getCheckInDate(), reservation.getCheckOutDate()});
        }
    }

    public void refreshTableUserReservation(List<Reservation> reservationList) {
        tableModel.setRowCount(0);
        for (Reservation reservation : reservationList) {
            tableModel.addRow(new Object[]{reservation.getHotelId(), reservation.getRoomId(), reservation.getReservationStatus(), reservation.getCheckInDate(), reservation.getCheckOutDate()});
        }
    }
}