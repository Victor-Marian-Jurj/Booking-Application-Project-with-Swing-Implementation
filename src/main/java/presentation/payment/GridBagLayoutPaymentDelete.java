package presentation.payment;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;



public class GridBagLayoutPaymentDelete extends JFrame {

    private JTextField paymentIdField;

    private JButton deletePaymentButton;
    private JButton cancelPaymentButton;




    public GridBagLayoutPaymentDelete() {


        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel deletePaymentPanel = new JPanel();
        deletePaymentPanel.setLayout(new GridBagLayout());

        addItem(deletePaymentPanel, new JLabel("Delete Payment record: "), 0, 0, GridBagConstraints.EAST);

        paymentIdField = new JTextField(25);

        addItem(deletePaymentPanel, paymentIdField, 1, 0, GridBagConstraints.WEST);

        deletePaymentButton = new JButton("Delete Payment");

        cancelPaymentButton = new JButton("Cancel");

        deletePaymentButton.addActionListener(this::buttonDeletePaymentIsPressed);
        cancelPaymentButton.addActionListener(this::buttonCancelPaymentIsPressed);

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(deletePaymentButton);
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(cancelPaymentButton);

        addItem(deletePaymentPanel, buttonBox, 1, 1, GridBagConstraints.WEST);

        setLocationRelativeTo(null);


        this.add(deletePaymentPanel);
        this.pack();
        this.setTitle("Delete payment");
        this.setVisible(true);
    }

    private void buttonDeletePaymentIsPressed(ActionEvent actionEvent) {
        String paymentId = paymentIdField.getText();

        PaymentController paymentController = new PaymentController();
        paymentController.handleClickButtonDeleteUsers(paymentId);


    }


    private void buttonCancelPaymentIsPressed(ActionEvent actionEvent) {
        this.dispose();}





    private void addItem(JPanel panel, JComponent component, int x, int y, int align) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = align;
        panel.add(component, gbc);

    }

    public static void main(String[] args) {
        new GridBagLayoutPaymentDelete();
    }

}


