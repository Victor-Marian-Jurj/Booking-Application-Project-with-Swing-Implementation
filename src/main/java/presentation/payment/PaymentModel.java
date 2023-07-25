package presentation.payment;

import business.entities.Payment;
import business.entities.Room;

import java.util.List;

public class PaymentModel {
    private List<Payment> paymentList;

    public PaymentModel(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }
}


