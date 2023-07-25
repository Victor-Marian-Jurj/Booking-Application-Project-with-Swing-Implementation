package persistance;

import business.entities.Payment;

import java.util.List;

public interface PaymentDataAccess {
    public List<Payment> getPayments();

    void updatePaymentValues ( String payment_status, String payment_id);

    void insertPaymentValues(Payment paymentId);

    void deletePaymentValues(String paymentId);

    void selectPaymentValues(String paymentId);


    public List<Payment> loadAllPayments();
    public void saveAllPayments (List<Payment> payments);
}
