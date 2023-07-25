package presentation.payment;

import business.entities.Payment;
import business.services.PaymentService;

public class PaymentController {
    private PaymentView paymentView;

    private PaymentModel paymentModel;

    private PaymentService paymentService;

    public PaymentController(PaymentView paymentView, PaymentModel paymentModel, PaymentService paymentService) {
        this.paymentView = paymentView;
        this.paymentModel = paymentModel;
        this.paymentService = paymentService;
    }

    public PaymentController() {

    }

    public void buttonRefreshPressed() {
        paymentModel.setPaymentList(paymentService.getPayments());
        paymentView.refreshTable(paymentModel.getPaymentList());

    }

    public void handleClickButtonDeleteUsers(String paymentId) {
        PaymentService paymentService = new PaymentService();
        paymentService.deletePaymentValues(paymentId);
    }

    public void handleClickButtonInsertPayment(Payment payment) {
        PaymentService paymentService = new PaymentService();
        paymentService.insertPaymentValues(payment);
    }

    public void handleClickButtonUpdatePayment(String paymentId, String paymentStatus) {
        PaymentService paymentService = new PaymentService();
        paymentService.updatePaymentValues(paymentId, paymentStatus);
    }
}
