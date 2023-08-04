package presentation.reservation;
import business.entities.Reservation;
import presentation.hotel.HotelController;
import presentation.hotel.HotelInsertView;
import util.DatePicker.JDatePicker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.UUID;

import static javax.swing.JOptionPane.showMessageDialog;
public class ReservationInsertView extends JPanel {

    private ReservationController reservationController = new ReservationController();
    private JTextField fieldReservationUsername;
    private JTextField fieldReservationHotelId;
    private JTextField fieldReservationHotelName;
    private JTextField fieldReservationRoomType;
    private JTextField fieldReservationRoomId;
    private JTextField fieldReservationReservationStatus;
    private java.sql.Date fieldReservationCheckInDate;
    private java.sql.Date fieldReservationCheckOutDate;
    private JTextField fieldReservationButtonPanel;

    public ReservationInsertView() {


    }

    public void setReservationController(ReservationController reservationController) {
        this.reservationController = reservationController;
    }

    public void display() {
        setVisible(true);
    }

    public JPanel createNewReservation() {
        JPanel newReservation = new JPanel();
        JLabel reservationTitle = new JLabel("Insert Reservation");
        reservationTitle.setHorizontalAlignment(SwingConstants.CENTER);
        reservationTitle.setForeground(Color.BLACK);
        reservationTitle.setFont(new Font("Arial", Font.BOLD, 15));
        reservationTitle.setBorder(new EmptyBorder(10, 10, 10, 10));

        newReservation.add(reservationTitle);
        newReservation.setBorder(new EmptyBorder(10, 10, 10, 10));
        newReservation.setLayout(new GridLayout(8, 1, 10, 10));
        newReservation.add(createReservationUsername());
        newReservation.add(createReservationHotelId());
        newReservation.add(createReservationRoomId());
        newReservation.add(createReservationReservationStatus());
        newReservation.add(createReservationCheckInDate());
        newReservation.add(createReservationCheckOutDate());
        newReservation.add(insertReservationButtonPanel());

        return newReservation;
    }
private JPanel createReservationUsername(){

        JPanel reservationUsername = new JPanel();
    reservationUsername.add(new JLabel("Reservation Username"));
    fieldReservationUsername = new JTextField(50);
    reservationUsername.add(fieldReservationUsername);
    return reservationUsername;
}

    private JPanel createReservationHotelId() {
        JPanel usernamePanel = new JPanel();
        usernamePanel.add(new JLabel("Hotel ID"));
        fieldReservationHotelId = new JTextField(50);
        usernamePanel.add(fieldReservationHotelId);
        return usernamePanel;
    }

    private JPanel createReservationRoomId() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Room ID"));
        fieldReservationRoomId = new JTextField(50);
        panel.add(fieldReservationRoomId);
        return panel;
    }

    private JPanel createReservationReservationStatus() {
        JPanel usernamePanel = new JPanel();
        usernamePanel.add(new JLabel("Reservation Status"));
        fieldReservationReservationStatus = new JTextField(50);
        usernamePanel.add(fieldReservationReservationStatus);
        return usernamePanel;
    }

    private JPanel createReservationCheckInDate() {
        JPanel usernamePanel = new JPanel();
        JDatePicker dp = new JDatePicker();

        usernamePanel.add(new JLabel("Checkin Date"));
        usernamePanel.add(dp.getJDatePicker());
        return usernamePanel;
    }

    private JPanel createReservationCheckOutDate() {
        JPanel usernamePanel = new JPanel();
        usernamePanel.add(new JLabel("Room Hotel Id"));

        return usernamePanel;
    }

    private JPanel insertReservationButtonPanel() {
        JPanel buttonsPanel = new JPanel();
        JButton buttonOk = new JButton("OK");
        buttonOk.addActionListener(this::buttonInsert);
        buttonsPanel.add(buttonOk);
        return buttonsPanel;
    }

    private void buttonInsert(ActionEvent event) {
        String reservation_id = UUID.randomUUID().toString().substring(0, 5);
        int reservationUsername = Integer.parseInt(fieldReservationUsername.getText());
        String reservationHotelId = fieldReservationHotelId.getText();
        String reservationRoomID = fieldReservationRoomId.getText();
        String reservationStatus = fieldReservationReservationStatus.getText();
        java.sql.Date reservationCheckInDate_id = null;
        java.sql.Date reservationCheckOutDate_id = null;
        reservationController.handleClickButtonInsertReservation(new Reservation(reservation_id, String.valueOf(reservationUsername), reservationHotelId, reservationRoomID, reservationStatus, reservationCheckInDate_id, reservationCheckOutDate_id ));
        showMessageDialog(null, "Reservation added");
    }

    public static void main(String[] args) {
        ReservationInsertView reservationInsertView = new ReservationInsertView();

        ReservationController reservationCon = new ReservationController();

        reservationInsertView.setReservationController(reservationCon);

        reservationCon.setReservationInsertView(reservationInsertView);

        reservationCon.start();
    }
}


