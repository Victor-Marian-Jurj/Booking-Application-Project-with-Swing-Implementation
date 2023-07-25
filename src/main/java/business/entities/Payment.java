package business.entities;

import java.util.Objects;

public class Payment implements Comparable<Payment>{

private String paymentId;

private String username;

private String reservationId;

private String roomPrice;

private String numberOfNights;

private String total;

private String paymentStatus;

    public Payment(String paymentId, String username, String reservationId, String roomPrice, String numberOfNights, String total, String paymentStatus) {
        this.paymentId = paymentId;
        this.username = username;
        this.reservationId = reservationId;
        this.roomPrice = roomPrice;
        this.numberOfNights = numberOfNights;
        this.total = total;
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(String numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return roomPrice == payment.roomPrice && numberOfNights == payment.numberOfNights && total == payment.total && Objects.equals(paymentId, payment.paymentId) && Objects.equals(username, payment.username) && Objects.equals(reservationId, payment.reservationId) && Objects.equals(paymentStatus, payment.paymentStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId, username, reservationId, roomPrice, numberOfNights, total, paymentStatus);
    }

    @Override
    public String toString() {
        return "Payment {" +
                "paymentId='" + paymentId + '\'' +
                ", username='" + username + '\'' +
                ", reservationId='" + reservationId + '\'' +
                ", roomPrice=" + roomPrice +
                ", numberOfNights=" + numberOfNights +
                ", total=" + total +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }

    @Override
    public int compareTo(Payment o) {
        return this.paymentId.compareTo(o.paymentId);
    }
}
