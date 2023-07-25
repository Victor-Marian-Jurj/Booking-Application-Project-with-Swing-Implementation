package presentation.payment;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;



public class GridBagLayoutPaymentUpdate extends JFrame {

    private JTextField paymentIdField;

    private JTextField paymentStatusField;

    private JButton savePaymentUpdateButton;

    private JButton cancelPaymentUpdateButton;


    public GridBagLayoutPaymentUpdate() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        addItem(panel, new JLabel("Select Payment ID:"), 0,0, GridBagConstraints.EAST);
        addItem(panel, new JLabel("New payment Status: "), 0,1, GridBagConstraints.EAST);

        paymentIdField = new JTextField(20);
        paymentStatusField = new JTextField(15);

        addItem(panel, paymentIdField, 1, 0, GridBagConstraints.WEST);
        addItem(panel, paymentStatusField, 1, 1, GridBagConstraints.WEST);

        savePaymentUpdateButton = new JButton("Save");
        cancelPaymentUpdateButton = new JButton("Cancel");

        cancelPaymentUpdateButton.addActionListener(this::buttonCancelUpdatePaymentIsPressed);
        savePaymentUpdateButton.addActionListener(this::buttonSaveUpdatePaymentIsPressed);


        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(savePaymentUpdateButton);
        buttonBox.add(Box.createVerticalStrut(20));
        buttonBox.add(cancelPaymentUpdateButton);

        addItem(panel, buttonBox, 1, 2, GridBagConstraints.WEST);
        setLocationRelativeTo(null);

        this.add(panel);
        this.pack();
        this.setTitle("Update payment data");
        this.setVisible(true);

    }

    private void buttonSaveUpdatePaymentIsPressed(ActionEvent actionEvent) {
        String paymentId= paymentIdField.getText();
        String paymentStatus = paymentStatusField.getText();

        PaymentController paymentController=new PaymentController();
        paymentController.handleClickButtonUpdatePayment(paymentId, paymentStatus);
    }

    private void buttonCancelUpdatePaymentIsPressed(ActionEvent actionEvent) {
        this.dispose();
    }

    private void addItem( JPanel panel, JComponent component, int x, int y, int align) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = align;
        panel.add(component, gbc);
    }

    public static void main(String[] args) {
        new GridBagLayoutPaymentUpdate();
    }
}
