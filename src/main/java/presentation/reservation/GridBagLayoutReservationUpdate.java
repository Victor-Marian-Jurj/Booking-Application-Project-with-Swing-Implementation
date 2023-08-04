package presentation.reservation;

import business.entities.Reservation;
import presentation.users.UsersController;

import javax.swing.*;
import java.awt.*;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.JOptionPane.showMessageDialog;
import static presentation.adminlogin.AdminManagementView.addItem;
import static util.Utils.NUMBER_REGEX;

public class GridBagLayoutReservationUpdate extends JFrame {

    private JTextField fieldhotel_id;
    private JTextField fieldroom_id;
    private JTextField fieldcheck_in_date;
    private JTextField fieldcheck_out_date;
    private JButton saveButtonReservationUpdate;

    private JButton cancelButtonReservationUpdate;

    private ReservationController reservationsController = new ReservationController();

    public GridBagLayoutReservationUpdate (){

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        addItem(panel, new JLabel("Select Hotel ID:"), 0, 0, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Select Room ID: "), 0, 1, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Checkin Date:"), 0, 2, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Checkout Date: "), 0, 3, GridBagConstraints.EAST);

        fieldhotel_id = new JTextField(20);
        fieldroom_id = new JTextField(20);
        fieldcheck_in_date = new JTextField(20);
        fieldcheck_out_date = new JTextField(20);

        addItem(panel, fieldhotel_id, 1, 0, GridBagConstraints.WEST);
        addItem(panel, fieldroom_id, 1, 1, GridBagConstraints.WEST);
        addItem(panel, fieldcheck_in_date, 1, 2, GridBagConstraints.WEST);
        addItem(panel, fieldcheck_out_date, 1, 3, GridBagConstraints.WEST);

        saveButtonReservationUpdate = new JButton("Save");
        saveButtonReservationUpdate.addActionListener(this::buttonUpdateReservation);
        cancelButtonReservationUpdate = new JButton( "Cancel");
        cancelButtonReservationUpdate.addActionListener(e-> this.dispose());

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(saveButtonReservationUpdate);
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(cancelButtonReservationUpdate);

        addItem(panel, buttonBox, 1, 4, GridBagConstraints.WEST);
        setLocationRelativeTo(null);

        this.add(panel);
        this.pack();
        this.setSize(600, 300);
        this.setTitle("Update Reservation data");
        this.setVisible(true);

    }

    private void buttonUpdateReservation(ActionEvent event) {

        boolean hotel_idIsValid = Utils.containsOnlyNumbers(fieldhotel_id.getText(), Utils.ALPHANUMERIC_REGEX);
        boolean room_idIsValid = Utils.containsOnlyNumbers(fieldroom_id.getText(), Utils.DECIMAL_DOUBLE_REGEX);
        boolean check_in_dateIsNumber = Utils.containsOnlyNumbers(fieldcheck_in_date.getText(), NUMBER_REGEX);
        boolean check_out_dateIsNumber = Utils.containsOnlyNumbers(fieldcheck_out_date.getText(), NUMBER_REGEX);

        String invalidFields = checkInvalidFields(hotel_idIsValid, room_idIsValid, check_in_dateIsNumber, check_out_dateIsNumber);
        if (fieldhotel_id.getText().isEmpty() || fieldroom_id.getText().isEmpty() || fieldcheck_in_date.getText().isEmpty() ||
                fieldcheck_out_date.getText().isEmpty()) {
            showMessageDialog(null, "Fields must not be empty");

        } else if (!hotel_idIsValid || !room_idIsValid || !check_in_dateIsNumber || !check_out_dateIsNumber) {
            showMessageDialog(null, "Fields are not valid:\n" + invalidFields);
        } else {


            String hotel_id = fieldhotel_id.getText();
            String room_id = fieldroom_id.getText();
            java.sql.Date check_in_date = null;
            java.sql.Date check_out_date = null;

            reservationsController.handleClickButtonInsertReservation(new Reservation(hotel_id, room_id, check_in_date, check_out_date));
            showMessageDialog(null, "Reservation updated");
        }
    }
    private void addItem(JPanel panel,JComponent component,int x,int y,int align){
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=x;
        gbc.gridy=y;
        gbc.insets=new Insets(5,5,5,5);
        gbc.anchor=align;
        panel.add(component,gbc);
    }

    private static String checkInvalidFields( boolean hotel_idIsValid, boolean room_idIsValid, boolean check_in_dateIsNumber, boolean check_out_dateIsNumber){




        String hotel_idInvalid = "";
        if(!hotel_idIsValid) {
            hotel_idInvalid = "- Hotel ID is invalid\n";
        }

        String room_idInvalid = "";
        if(!room_idIsValid) {
            room_idInvalid = "- Room ID is invalid\n";
        }


        String check_in_dateInvalid = "";
        if(!check_in_dateIsNumber) {
            check_in_dateInvalid = "- Check In Date is invalid\n";
        }

        String check_out_dateInvalid = "";
        if(!check_out_dateIsNumber) {
            check_out_dateInvalid = "- Check Out Date is invalid\n";
        }

        String invalidFields = hotel_idInvalid + room_idInvalid + check_in_dateInvalid + check_out_dateInvalid;

        return invalidFields;
    }
    public static void main (String[]args){
        new GridBagLayoutReservationUpdate();}
    }

