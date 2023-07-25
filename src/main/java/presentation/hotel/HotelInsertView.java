package presentation.hotel;

import business.entities.Hotel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.UUID;

import static javax.swing.JOptionPane.showMessageDialog;

public class HotelInsertView  extends JPanel {

    private HotelController hotelController = new HotelController();

    private JTextField fieldNumberOfRooms;
    private JTextField fieldRating;
    private JTextField fieldName;
    private JTextField fieldLocation;


    public HotelInsertView() {

    }

    public void setHotelController(HotelController hotelController) {
        this.hotelController = hotelController;
    }

    public void display() {
        setVisible(true);
    }

    public JPanel createNewHotel() {
        JPanel newHotel = new JPanel();
        JLabel hotelTitle = new JLabel("Insert Hotel");
        hotelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        hotelTitle.setForeground(Color.BLACK);
        hotelTitle.setFont(new Font("Arial", Font.BOLD, 15));
        hotelTitle.setBorder(new EmptyBorder(10, 10, 10, 10));

        newHotel.add(hotelTitle);
        newHotel.setBorder(new EmptyBorder(5, 10, 10, 10));
        newHotel.setLayout(new GridLayout(7, 1, 10, 10));

        newHotel.add(createNumberOfRooms());
        newHotel.add(createRating());
        newHotel.add(createName());
        newHotel.add(createHotelLocation());

        // newRoom.add(createRoomHotelId());
        newHotel.add(insertHotelButtonPanel());

        return newHotel;
    }

    private JPanel createNumberOfRooms() {
        JPanel numberOfRooms = new JPanel();
        numberOfRooms.add(new JLabel("Number of rooms"));
        fieldNumberOfRooms = new JTextField(50);
        numberOfRooms.add(fieldNumberOfRooms);
        return numberOfRooms;
    }


    private JPanel createRating() {
        JPanel ratingOfHotel = new JPanel();
        ratingOfHotel.add(new JLabel(" Hotel rating"));
        fieldRating = new JTextField(50);
        ratingOfHotel.add(fieldRating);
        return ratingOfHotel;
    }

    private JPanel createName() {
        JPanel nameOfHotel = new JPanel();
        nameOfHotel.add(new JLabel("Hotel Name"));
        fieldName = new JTextField(50);
        nameOfHotel.add(fieldName);
        return nameOfHotel;
    }

    private JPanel createHotelLocation() {
        JPanel locationOfHotel = new JPanel();
        locationOfHotel.add(new JLabel("Hotel Location"));
        fieldLocation = new JTextField(50);
        locationOfHotel.add(fieldLocation);
        return locationOfHotel;
    }


    private JPanel insertHotelButtonPanel() {
        JPanel buttonsPanel = new JPanel();
        JButton buttonOk = new JButton("OK");
        buttonOk.addActionListener(this::buttonInsert);
        buttonsPanel.add(buttonOk);
        return buttonsPanel;
    }

    private void buttonInsert(ActionEvent event) {
        String hotelId = UUID.randomUUID().toString().substring(0, 5);
        int numberOfRooms = Integer.parseInt(fieldNumberOfRooms.getText());
        int rating = Integer.parseInt(fieldRating.getText());
        String nameOfHotel = fieldName.getText();
        String locationOfHotel = fieldLocation.getText();


        hotelController.handleClickButtonInsertHotel(new Hotel(hotelId, numberOfRooms, rating, nameOfHotel, locationOfHotel));
        showMessageDialog(null, "Hotel added");

    }

    public static void main(String[] args) {
        HotelInsertView hotelInsertView = new HotelInsertView();

        HotelController hotelController = new HotelController();

        hotelInsertView.setHotelController(hotelController);

        hotelController.setHotelInsertView(hotelInsertView);

        hotelController.start();
    }
}
