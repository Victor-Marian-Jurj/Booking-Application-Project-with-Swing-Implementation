package presentation.reservation;
import business.entities.Reservation;

import util.DatePicker.JDatePicker;
import util.ReservationComboBox;
import util.IDRandomiser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import static javax.swing.JOptionPane.showMessageDialog;
import static util.Utils.*;


public class GridBagLayoutReservationInsert extends JFrame {
    ReservationComboBox reservationComboBox = new ReservationComboBox();
    private static String userType;
    private JTextField fieldReservationId;
    private JTextField fieldUserName;
    private JTextField fieldHotelId;
    private JTextField fieldRoomId;
    private JTextField fieldLocation;
    private JTextField fieldHotelName;
    private JTextField fieldroom_type;
    private JTextField fieldreservation_status;
    private JTextField fieldCheckInDate;
    private JTextField fieldCheckOutDate;
    private JButton saveButtonReservationInsert;
    private JButton cancelButtonReservationInsert;
    private ReservationController reservationController = new ReservationController();
    private JDatePicker checkInDate;
    private JDatePicker checkOutDate;
    private JPanel checkInDatePickerPanel;
    private JPanel checkOutDatePickerPanel;
    private ArrayList<String> fieldLabels = new ArrayList<>();
    private ArrayList<JComponent> fieldPanels = new ArrayList<>();
    private HashMap<String, Boolean> validatedFieldsMap = new HashMap<String, Boolean>();
    private ArrayList<String> fieldValues = new ArrayList<>();
    private JPanel locationPanel;
    private JPanel hotelNamePanel;
    private JPanel roomTypePanel;


    public GridBagLayoutReservationInsert (String userType){

        GridBagLayoutReservationInsert.userType = userType;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        fieldLabels.add("Name: ");
        fieldLabels.add("Location: ");
        fieldLabels.add("Hotel: ");
        fieldLabels.add("Room Type: ");
        fieldLabels.add("Check-In Date: ");
        fieldLabels.add("Check-Out Date: ");

        int fieldLabelIndex = 0;
        for (String label:fieldLabels){
            addItem(panel, new JLabel(label), 0, fieldLabelIndex, GridBagConstraints.EAST);
            fieldLabelIndex++;
        }

        fieldReservationId = new JTextField();
        fieldUserName = new JTextField(22);
        fieldLocation = new JTextField();
        fieldHotelId = new JTextField();
        fieldRoomId = new JTextField();
        fieldreservation_status = new JTextField();
        fieldHotelName = new JTextField();
        fieldroom_type = new JTextField();
        fieldCheckInDate = new JTextField();
        fieldCheckOutDate = new JTextField();

        checkInDate = new JDatePicker();
        checkOutDate = new JDatePicker();
        checkInDatePickerPanel = checkInDate.getJDatePicker();
        checkOutDatePickerPanel = checkOutDate.getJDatePicker();

        locationPanel = reservationComboBox.getCb1Panel();
        hotelNamePanel = reservationComboBox.getCb2Panel();
        roomTypePanel = reservationComboBox.getCb3Panel();

        fieldPanels.add(fieldUserName);
        fieldPanels.add(locationPanel);
        fieldPanels.add(hotelNamePanel);
        fieldPanels.add(roomTypePanel);
        fieldPanels.add(checkInDatePickerPanel);
        fieldPanels.add(checkOutDatePickerPanel);

        int fieldPanelIndex = 0;
        for (JComponent fieldPanel:fieldPanels){
            addItem(panel, fieldPanel, 1, fieldPanelIndex, GridBagConstraints.WEST);
            fieldPanelIndex++;
        }

        saveButtonReservationInsert = new JButton("Save new Reservation");
        saveButtonReservationInsert.addActionListener(this::buttonInsertReservation);
        cancelButtonReservationInsert = new JButton("Cancel");
        cancelButtonReservationInsert.addActionListener(e -> this.dispose());

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(saveButtonReservationInsert);
        buttonBox.add(Box.createVerticalStrut(20));
        buttonBox.add(cancelButtonReservationInsert);
        cancelButtonReservationInsert.addActionListener(e -> this.dispose());

        addItem(panel, buttonBox, 1, 7, GridBagConstraints.WEST);
        setLocationRelativeTo(null);

        this.add(panel);
        this.pack();
        this.setSize(450, 300);
        this.setTitle("Insert new Reservation");
        this.setVisible(true);
    }

    private void buttonInsertReservation(ActionEvent actionEvent) {
        fieldReservationId.setText(IDRandomiser.getId());
        fieldLocation.setText(reservationComboBox.getSelectedCategory());
        fieldHotelName.setText(reservationComboBox.getSelectedItem());
        fieldroom_type.setText(reservationComboBox.getSelectedSubItem());
        fieldCheckInDate.setText(String.valueOf(checkInDate.getSelectedDate()));
        fieldCheckOutDate.setText(String.valueOf(checkOutDate.getSelectedDate()));

        boolean usernameIsValid = containsOnlyNumbers(fieldUserName.getText(), LETTERS_REGEX);

        validatedFieldsMap.put("User Name", usernameIsValid);

        fieldValues.clear();
        fieldValues.add(fieldReservationId.getText());
        fieldValues.add(fieldUserName.getText());
        fieldValues.add(fieldHotelName.getText());
        fieldValues.add(fieldroom_type.getText());
        fieldValues.add(fieldCheckInDate.getText());
        fieldValues.add(fieldCheckOutDate.getText());

        for(String value:fieldValues){
            System.out.println(value);
        }

        if (fieldValues.contains("null") || fieldValues.contains("")){
            showMessageDialog(null, "Fields must not be empty");
        } else if (validatedFieldsMap.containsValue(false)){
            String invalidFields = chequeInvalidFields(validatedFieldsMap);
            showMessageDialog(null, "Fields are not valid:\n" + invalidFields);
        } else{
            String resId = fieldReservationId.getText();
            String userName = fieldUserName.getText();
            String hotelId = reservationComboBox.getSelectedHotelId();
            String roomId = reservationComboBox.getSelectedRoomId();
            String reservationStatus = "Pending";
            java.sql.Date checkIn_date = checkInDate.getSelectedDate();
            java.sql.Date checkOut_date = checkOutDate.getSelectedDate();

            System.out.println(resId);
            System.out.println(userName);
            System.out.println(hotelId);
            System.out.println(roomId);
            System.out.println(reservationStatus);
            System.out.println(checkIn_date);
            System.out.println(checkOut_date);



            reservationController.handleClickButtonInsertReservation(new Reservation(resId, userName, hotelId, roomId, reservationStatus, checkIn_date, checkOut_date));
            showMessageDialog(null, "Reservation added");
        }
    }

    private void addItem (JPanel panel, JComponent component,int x, int y, int align){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(1, 1, 1, 5);
        gbc.anchor = align;
        panel.add(component, gbc);
    }

    private static String chequeInvalidFields(HashMap<String, Boolean> validatedFieldsMap){

        ArrayList<String> valMessage = new ArrayList<>();

        validatedFieldsMap.forEach((key, value) -> {
            if(!value) {
                String validationMessage = "- " +  key + "\n";
                valMessage.add(validationMessage);
            }
        });

        return String.valueOf(valMessage).replace("[", " ").replace("]", "").replace(",", "");
    }

    public static void main (String[]args){
        new GridBagLayoutReservationInsert(userType);
    }
}
