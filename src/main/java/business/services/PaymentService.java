package business.services;

import business.entities.Payment;
import business.entities.Room;
import persistance.PaymentDataAccess;
import persistance.PaymentDataAccessSQL;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PaymentService {
    private PaymentDataAccess paymentDataAccess = new PaymentDataAccessSQL();

    public PaymentService(PaymentDataAccess paymentDataAccess) {
        this.paymentDataAccess = paymentDataAccess;
    }

    public PaymentService() {

    }

    public List<Payment> getPayments() {
        List<Payment> list = paymentDataAccess.getPayments();
        //payments.sort(new PaymentComparator());
       return list;
        //return list.stream().sorted().collect(Collectors.toList());
    }

    public void addNewPayment(Payment newPayment) {
        List<Payment> payments = paymentDataAccess.loadAllPayments();
        payments.add(newPayment);
        paymentDataAccess.saveAllPayments(payments);
    }

    public void removeExistingPayment(String paymentId) {
        List<Payment> payments = paymentDataAccess.loadAllPayments();
        int index = getPaymentIndex(payments, paymentId);
        if (index != -1) { // if found
            payments.remove(index);
            paymentDataAccess.saveAllPayments(payments);
        }
    }

    private int getPaymentIndex(List<Payment> payments, String paymentId) {
        for (int currentIndex = 0; currentIndex < payments.size(); currentIndex++) {
            Payment currentHotel = payments.get(currentIndex);
            if (!Objects.equals(currentHotel.getPaymentId(), paymentId)) {
                continue;
            }
            return currentIndex;
        }
        return -1;
    }

    public void insertPaymentValues(Payment payment) {
        paymentDataAccess.insertPaymentValues(payment);
    }

    public void deletePaymentValues(String paymentId) {
        paymentDataAccess.deletePaymentValues(paymentId);

    }

    public void selectPaymentValues(String paymentId) {
        paymentDataAccess.selectPaymentValues(paymentId);
    }

    public void updatePaymentValues(String paymentId, String paymentStatus) {
        paymentDataAccess.updatePaymentValues(paymentId,paymentStatus);
    }
}
