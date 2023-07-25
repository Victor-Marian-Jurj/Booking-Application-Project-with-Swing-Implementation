package persistance;

import business.entities.Payment;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDataAccessSQL implements PaymentDataAccess {

    @Override
    public List<Payment> loadAllPayments() {

        return null;
    }
    public void saveAllPayments(List<Payment> payments) {
        System.out.println("saveAllRooms - dummy SQL implementation");
    }

    public List<Payment> getPayments() {
        List<Payment> payments = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * from payment");
            while (rs.next()) {
                String paymentId = rs.getString("payment_Id");
                String clientId = rs.getString(2);
                String reservationId = rs.getString(3);
                String roomPrice = rs.getString(4);
                String numberOfNights = rs.getString(5);
                String total = rs.getString(6);
                String paymentStatus=rs.getString(7);

                Payment payment = new Payment(paymentId,clientId, reservationId, roomPrice, numberOfNights, total, paymentStatus);
                payments.add(payment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeConnection(connection);
        }
        return payments;
    }

    @Override
    public void updatePaymentValues(String payment_id , String payment_status) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("update payment set payment_status = ? where payment_id = ?");
            statement.setString(1, payment_status);
            statement.setString(2, payment_id);
            int noOfUpdates = statement.executeUpdate();
            System.out.println("NUmber of updated records = " + noOfUpdates);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void insertPaymentValues(Payment payment) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("insert into payment values (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, payment.getPaymentId());
            statement.setString(2, payment.getUsername());
            statement.setString(3, payment.getReservationId());
            statement.setString(4, payment.getRoomPrice());
            statement.setString(5, payment.getNumberOfNights());
            statement.setString(6, payment.getTotal());
            statement.setString(7, payment.getPaymentStatus());
            int noOfInserts = statement.executeUpdate();
            System.out.println("Number of inserted records = " + noOfInserts);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection);
        }

    }

    @Override
    public void deletePaymentValues(String payment_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("DELETE FROM payment WHERE payment_id = ?");
            statement.setString(1, payment_id);
            int noOfInserts = statement.executeUpdate();
            System.out.println("Number of deleted records = " + noOfInserts);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection);
        }


    }

    @Override
    public void selectPaymentValues(String payment_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("SELECT * FROM payment WHERE payment_id = ?");
            statement.setString(1, payment_id);
            rs = statement.executeQuery();
            while (rs.next()) {
                String paymentId = rs.getString("payment_id");
                String username = rs.getString("username");
                String reservationId = rs.getString("reservation_id");
                String roomPrice = rs.getString("room_price");
                String numberOfNights = rs.getString("number_of_nights");
                String total = rs.getString("total");
                String paymentStatus = rs.getString("payment_status");


                System.out.println("Selected Client:");
                System.out.println("PaymentId: " + paymentId);
                System.out.println("username: " + username);
                System.out.println("reservation_id: " + reservationId);
                System.out.println("room_price: " + roomPrice);
                System.out.println("number_of_nights: " + numberOfNights);
                System.out.println("total: " + total);
                System.out.println("payment_status: " + paymentStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection);
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
