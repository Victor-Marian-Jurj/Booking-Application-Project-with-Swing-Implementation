package presentation.reservation;

import business.entities.Reservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ReservationView extends JFrame {
        private DefaultTableModel tableModel;
        private JButton refreshButton;
        private ReservationController controller;
        public ReservationView(List<Reservation> reservationList) {
            this();
            refreshTable(reservationList);
        }
        public ReservationView (){
            super("Academy MVC with SWING");
            refreshButton = new JButton("Refresh");
            JPanel content = new JPanel();
            JScrollPane pane = getTablePane();
            content.add(pane);
            content.add(refreshButton);
            refreshButton.addActionListener(e->
                    controller.buttonRefreshPressed()
            );
            this.setContentPane(content);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
            this.pack();
        }
        private JScrollPane getTablePane() {
            String[] header = {"room_id", "room_number", "room_type", "room_floor", "room_price"};
            tableModel = new DefaultTableModel(header, 0);
            JTable table = new JTable(tableModel);
            table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
            table.getColumnModel().getColumn(1).setPreferredWidth(300);
            JScrollPane pane = new JScrollPane(table);
            return pane;
        }
        public ReservationController getController () {
            return controller;
        }
        public void setController(ReservationController controller) {
            this.controller = controller;
        }
        public void refreshTable (List<Reservation> reservationList) {
            tableModel.setRowCount(0);
            for (Reservation reservation : reservationList) {
                tableModel.addRow(new Object[]{reservation.getReservationId(), reservation.getUsername(), reservation.getHotelId(), reservation.getRoomId(), reservation.getReservationStatus(), reservation.getCheckInDate(), reservation.getCheckOutDate()});
            }
        }
    }

