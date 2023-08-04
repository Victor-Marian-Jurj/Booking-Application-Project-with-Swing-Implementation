package persistance;

import business.entities.Reservation;
import util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class ReservationDataAccessSQL implements ReservationDataAccess{

    public List<Reservation>getAllReservations(){
        List<Reservation> reservationList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * from reservation");
            while (rs.next()) {
                String reservationId = rs.getString(1);
                String username = rs.getString(2);
                String hotelId = rs.getString(3);
                String roomId = rs.getString(4);
                String reservationStatus = rs.getString(5);
                Date checkInDate = rs.getDate(6);
                Date checkOutDate = rs.getDate(7);


                Reservation reservation = new Reservation(reservationId, username, hotelId, roomId, reservationStatus, checkInDate, checkOutDate);
                reservationList.add(reservation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeConnection(connection);
        }
        return reservationList;
    }

    @Override
    public List<Reservation> getReservationForUser(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Reservation> reservations = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("SELECT reservation.reservation_id, username, hotel.name, room.room_number, reservation_status, check_in_date, check_out_date\n" +
                    "    FROM public.reservation\n" +
                    "    INNER JOIN hotel ON hotel.hotel_id = reservation.hotel_id\n" +
                    "    INNER JOIN room ON room.room_id = reservation.room_id WHERE username = ?");
            statement.setString(1, username);
            rs = statement.executeQuery();
            while (rs.next()) {
                String reservationId = rs.getString("reservation_id");
                username = rs.getString("username");
                String hotelId = rs.getString("name");
                String roomId = rs.getString("room_number");
                String reservationStatus = rs.getString("reservation_status");
                Date checkInDate = rs.getDate("check_in_date");
                Date checkOutDate = rs.getDate("check_out_date");

                Reservation reservation = new Reservation(reservationId, username, hotelId, roomId, reservationStatus, checkInDate, checkOutDate);
                reservations.add(reservation);

                System.out.println("Selected Reservation:");
                System.out.println("Reservation Id: " + reservationId);
                System.out.println("Username: " + username);
                System.out.println("Hotel Id: " + hotelId);
                System.out.println("Room Id: " + roomId);
                System.out.println("Reservation Status: " + reservationStatus);
                System.out.println("Check-in-date: " + checkInDate);
                System.out.println("Check-out-date: " + checkOutDate);

            }
            return reservations;
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
            statement = connection.prepareStatement("INSERT INTO reservation VALUES(?, ?, ?, ?, ?, ?,?)");
            statement.setString(1, reservation.getReservationId());
            statement.setString(2, reservation.getUsername());
            statement.setString(3, reservation.getHotelId());
            statement.setString(4, reservation.getRoomId());
            statement.setString(5, reservation.getReservationStatus());
            statement.setDate(  6, reservation.getCheckInDate());
            statement.setDate(  7, reservation.getCheckOutDate());
            System.out.println(statement);
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
            statement = connection.prepareStatement("DELETE FROM reservation WHERE reservation_id = ?");
            statement.setString(1, reservation_Id);
            int noOfInserts = 0;

            try {
                noOfInserts = statement.executeUpdate();
            } catch (SQLException e) {
                showMessageDialog(null, "Unable to delete reservation.");
                throw new RuntimeException(e);
            }

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
                String username = rs.getString("username");
                String hotelId = rs.getString("hotel_id");
                String roomId = rs.getString("room_id");
                String reservationStatus = rs.getString("reservation_status");
                String checkInDate = rs.getString("check_in_date");
                String checkOutDate = rs.getString("check_out_date");

                System.out.println("Selected Reservation:");
                System.out.println("ReservationId: " + reservation_id);
                System.out.println("Name: " + username);
                System.out.println("Hotel ID: " + hotelId);
                System.out.println("Room ID: " + roomId);
                System.out.println("Reservation Status: " + reservationStatus);
                System.out.println("Checkin Date: " + checkInDate);
                System.out.println("Checkout DAte: " + checkOutDate);
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