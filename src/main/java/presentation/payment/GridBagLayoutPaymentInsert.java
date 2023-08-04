package presentation.payment;





import business.entities.Payment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GridBagLayoutPaymentInsert extends JFrame {
    private JTextField paymentIdField;

    private JTextField usernameField;

    private JTextField reservationIdField;

    private JTextField roomPriceField;

    private JTextField numberOfNightsField;

    private JTextField totalField;

    private JTextField paymentStatusField;

    private JButton insertPaymentButton;
    private JButton cancelPaymentButton;

    public GridBagLayoutPaymentInsert() {

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel insertPaymentPanel = new JPanel();
        insertPaymentPanel.setLayout(new GridBagLayout());
        setLocationRelativeTo(null);


        addItem(insertPaymentPanel, new JLabel("Payment ID: "), 0, 0, GridBagConstraints.EAST);
        addItem(insertPaymentPanel, new JLabel("Username: "), 0, 1, GridBagConstraints.EAST);
        addItem(insertPaymentPanel, new JLabel("Reservation ID: "), 0, 2,GridBagConstraints.EAST);
        addItem(insertPaymentPanel, new JLabel("Room Price: "), 0, 3, GridBagConstraints.EAST);
        addItem(insertPaymentPanel, new JLabel("Number of Nights: "), 0, 4, GridBagConstraints.EAST);
        addItem(insertPaymentPanel, new JLabel("Total: "), 0, 5, GridBagConstraints.EAST);
        addItem(insertPaymentPanel, new JLabel("Payment Status: "), 0, 6, GridBagConstraints.EAST);


        paymentIdField = new JTextField(10);
        usernameField = new JTextField(20);
        reservationIdField = new JTextField(10);
        roomPriceField = new JTextField(10);
        numberOfNightsField = new JTextField(5);
        totalField = new JTextField(10);
        paymentStatusField = new JTextField(5);


        addItem(insertPaymentPanel, paymentIdField, 1, 0, GridBagConstraints.WEST);
        addItem(insertPaymentPanel, usernameField, 1, 1, GridBagConstraints.WEST);
        addItem(insertPaymentPanel, reservationIdField, 1, 2, GridBagConstraints.WEST);
        addItem(insertPaymentPanel, roomPriceField, 1, 3, GridBagConstraints.WEST);
        addItem(insertPaymentPanel, numberOfNightsField, 1, 4, GridBagConstraints.WEST);
        addItem(insertPaymentPanel, totalField, 1, 5, GridBagConstraints.WEST);
        addItem(insertPaymentPanel, paymentStatusField, 1, 6, GridBagConstraints.WEST);


        insertPaymentButton = new JButton("Save");
        cancelPaymentButton = new JButton("Cancel");



        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(insertPaymentButton);
        buttonBox.add(Box.createVerticalStrut(20));
        buttonBox.add(cancelPaymentButton);

        cancelPaymentButton.addActionListener(this::cancelPaymentButtonIsPressed);
        insertPaymentButton.addActionListener(this::insertPaymentButtonIsPressed);

        addItem(insertPaymentPanel, buttonBox, 1, 7, GridBagConstraints.WEST);
        this.setLocationRelativeTo(null);

        this.add(insertPaymentPanel);
        this.pack();
        this.setSize(600, 300);
        this.setTitle("Insert new payment");
        this.setVisible(true);

    }

    private void insertPaymentButtonIsPressed(ActionEvent actionEvent) {
        String paymentId = paymentIdField.getText();
        String username = usernameField.getText();
        String reservationId = reservationIdField.getText();
        String roomPrice = roomPriceField.getText();
        String numberOfNights=numberOfNightsField.getText();
        String total = totalField.getText();
        String paymentStatus = paymentStatusField.getText();

        PaymentController paymentController=new PaymentController();
        paymentController.handleClickButtonInsertPayment(new Payment(paymentId,username,reservationId,roomPrice,numberOfNights, total,paymentStatus));
    }

    private void cancelPaymentButtonIsPressed (ActionEvent actionEvent) {
        this.dispose();

    }

    private void addItem (JPanel panel, JComponent component,int x, int y, int align){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = align;
        panel.add(component, gbc);
    }

    public static void main (String[]args){
        new GridBagLayoutPaymentInsert();
    }
}

