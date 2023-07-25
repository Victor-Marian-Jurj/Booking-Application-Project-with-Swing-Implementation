package persistance;

import business.entities.Reservation;
import util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationDataAccessSQL implements ReservationDataAccess{

    public List<Reservation>getAllReservations(){
        List<Reservation> reservations = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * from reservation");
            while (rs.next()) {
                String reservationId = rs.getString("reservation_id");
                String clientId = rs.getString(2);
                String hotelId = rs.getString(3);
                String roomId = rs.getString(4);
                String reservationStatus = rs.getString(5);
                Date checkInDate = rs.getDate(6);
                Date checkOutDate = rs.getDate(7);
                Reservation reservation = new Reservation(reservationId, clientId, hotelId, roomId, reservationStatus, checkInDate, checkOutDate);
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeConnection(connection);
        }
        return reservations;
    }

    @Override
    public void saveAllReservations(List<Reservation> reservations) {

    }

    @Override
    public void updateValuesReservation(String reservation_Id, String reservation_status) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("update Reservation set reservation_status = ? where username = ?");
            statement.setString(1, reservation_status);
            statement.setString(2, reservation_Id);
            int noOfUpdates = statement.executeUpdate();
            System.out.println("NUmber of updated records = " + noOfUpdates);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void insertValuesReservation(Reservation reservation) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("insert into reservation values(?, ?, ?, ?, ?, ?,?)");
            statement.setString(1, reservation.getReservationId());
            statement.setString(2, reservation.getUsername());
            statement.setString(3, reservation.getHotelId());
            statement.setString(4, reservation.getRoomId());
            statement.setString(5, reservation.getReservationStatus());
            statement.setDate(6, (Date) reservation.getCheckInDate());
            statement.setDate(7, (Date) reservation.getCheckOutDate());
            int noOfInserts = statement.executeUpdate();
            System.out.println("Number of inserted records = " + noOfInserts);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void deleteValuesReservation(String reservation_Id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("DELETE FROM users WHERE client_Id = ?");
            statement.setString(1, reservation_Id);
            int noOfInserts = statement.executeUpdate();
            System.out.println("Number of deleted records = " + noOfInserts);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection);
        }
    }

    public void selectValuesReservation(String room_Id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("SELECT * FROM users WHERE room_Id = ?");
            statement.setString(1, room_Id);
            rs = statement.executeQuery();
            while (rs.next()) {
                String reservation_id = rs.getString("reservation_id");
                String clientId = rs.getString("client_id");
                String hotelId = rs.getString("hotel_id");
                String roomId = rs.getString("room_id");
                String reservationStatus = rs.getString("reservation_status");
                String checkInDate = rs.getString("check_in_date");
                String checkOutDate = rs.getString("check_out_date");


                System.out.println("Selected Reservation:");
                System.out.println("ReservationId: " + reservation_id);
                System.out.println("ClientId: " + clientId);
                System.out.println("First Name: " + hotelId);
                System.out.println("Last Name: " + roomId);
                System.out.println("Phone: " + reservationStatus);
                System.out.println("Email: " + checkInDate);
                System.out.println("Birth Day: " + checkOutDate);
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