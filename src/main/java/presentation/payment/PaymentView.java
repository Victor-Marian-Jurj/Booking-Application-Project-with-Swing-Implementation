package presentation.payment;

import business.entities.Payment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PaymentView extends JFrame {
    private DefaultTableModel tableModel;

    private JButton refreshButton;


    private PaymentController paymentController;

    public PaymentView(List<Payment> paymentList) {
        this();
        refreshTable(paymentList);
    }

    public PaymentView() {
        super("Payment Records");
        refreshButton = new JButton("Refresh");

        JPanel content = new JPanel();
        JScrollPane pane = getTablePane();
        content.add(pane);
        content.add(refreshButton);
        refreshButton.addActionListener(e->
                paymentController.buttonRefreshPressed()
        );
        this.setContentPane(content);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }





    private JScrollPane getTablePane() {
        String[] header = {"payment_id", "client_id", "reservation_id", "room_price", "number_of_nights", "total", "payment_status"};
        tableModel = new DefaultTableModel(header, 0);
        JTable table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        JScrollPane pane = new JScrollPane(table);
        return pane;
    }


    public PaymentController getPaymentController () {
        return paymentController;
    }
    public void setController(PaymentController paymentController) {
        this.paymentController = paymentController;
    }

    public void refreshTable (List<Payment> paymentList) {
        tableModel.setRowCount(0);
        for(Payment payment :paymentList) {
            tableModel.addRow(new Object [] {payment.getPaymentId(), payment.getUsername(), payment.getReservationId(), payment.getRoomPrice(),payment.getTotal(), payment.getPaymentStatus(), payment.getNumberOfNights()});
        }
    }

}
