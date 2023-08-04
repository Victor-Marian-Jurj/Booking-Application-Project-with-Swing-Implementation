package presentation.reservation;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.JOptionPane.showMessageDialog;
import static util.Utils.ALPHANUMERIC_REGEX;

public class GridBagLayoutReservationDelete extends JFrame{
    private JTextField fieldreservation_id;

    private JButton saveButtonReservationDelete;

    private JButton cancelButtonReservationDelete;
    private ReservationController reservationController = new ReservationController();

    public GridBagLayoutReservationDelete(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel() ;
        panel.setLayout(new GridBagLayout());

        addItem(panel, new JLabel("Delete Reservation ID: "), 0, 0, GridBagConstraints.EAST);

        fieldreservation_id = new JTextField(25);

        addItem(panel, fieldreservation_id, 1, 0, GridBagConstraints.WEST);

        saveButtonReservationDelete = new JButton("Save");
        saveButtonReservationDelete.addActionListener(this::buttonDeleteReservation);
        cancelButtonReservationDelete = new JButton("Cancel");
        cancelButtonReservationDelete.addActionListener(e -> this.dispose());

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(saveButtonReservationDelete);
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(cancelButtonReservationDelete);

        addItem(panel, buttonBox, 1, 1, GridBagConstraints.WEST);
        setLocationRelativeTo(null);

        this.add(panel);
        this.pack();
        this.setTitle("Delete reservation");
        this.setVisible(true);
    }

    private void buttonDeleteReservation(ActionEvent actionEvent) {
        boolean reservation_idIsValid = Utils.containsOnlyNumbers(fieldreservation_id.getText(), ALPHANUMERIC_REGEX);
        String invalidFields = checkInvalidFields(reservation_idIsValid);
        if (fieldreservation_id.getText().isEmpty()) {
            showMessageDialog(null, "Field must not be empty");
        } else if (!reservation_idIsValid) {
            showMessageDialog(null, "Field is not valid:\n" + invalidFields);

        } else {
            String reservation_id = fieldreservation_id.getText();
            reservationController.handleClickButtonReservationDelete(reservation_id);
            showMessageDialog(null, "Reservation deleted");
        }



    }

    private String checkInvalidFields(boolean reservation_idIsValid) {
        String reservation_idInvalid = "";
        if(!reservation_idIsValid) {
            reservation_idInvalid = "- Reservation ID is invalid\n";
        }
        String invalidFields = reservation_idInvalid;
        return invalidFields;
    }

    private void addItem(JPanel panel, JComponent component, int x, int y, int align){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = align;
        panel.add(component, gbc);

    }

    public static void main(String[] args) {
        new GridBagLayoutReservationDelete();
    }

}






