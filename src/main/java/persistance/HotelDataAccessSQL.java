package persistance;

import business.entities.Hotel;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDataAccessSQL implements persistance.HotelDataAccess {


    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * from hotel");
            while (rs.next()) {
                String hotel_Id = rs.getString("hotel_Id");
                int number_of_rooms = rs.getInt(2);
                int rating = rs.getInt(3);
                String name = rs.getString(4);
                String location = rs.getString(5);

                Hotel accomodation = new Hotel(hotel_Id, number_of_rooms, rating, name, location);
                hotels.add(accomodation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeConnection(connection);
        }
        return hotels;
    }

    @Override
    public void updateHotelValues(String hotel_Id, int number_of_rooms, int rating, String name, String location) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("UPDATE hotel SET number_of_rooms = ?, rating = ?, name = ?, location = ? where hotel_Id = ?");
            statement.setInt(1, number_of_rooms);
            statement.setInt(2, rating);
            statement.setString(3, name);
            statement.setString(4, location);
            statement.setString(5, hotel_Id);
            int noOfUpdates = statement.executeUpdate();
            System.out.println("Number of updated records = " + noOfUpdates);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void insertHotelValues(Hotel hotel) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("insert into hotel values(?, ?, ?, ?, ?)");
            statement.setString(1, hotel.getHotelId());
            statement.setInt(2, hotel.getNumber_of_rooms());
            statement.setInt(3, hotel.getRating());
            statement.setString(4, hotel.getName());
            statement.setString(5, hotel.getLocation());
            int noOfInserts = statement.executeUpdate();
            System.out.println("Number of inserted records = " + noOfInserts);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void deleteHotelValues(String hotel_Id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("DELETE FROM hotel WHERE hotel_Id = ?");
            statement.setString(1, hotel_Id);
            int noOfInserts = statement.executeUpdate();

            System.out.println("Number of deleted records = " + noOfInserts);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection);
        }
    }

    public void selectHotelValues(String hotel_Id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("SELECT * FROM hotel WHERE hotel_Id = ?");
            statement.setString(1, hotel_Id);
            rs = statement.executeQuery();
            while (rs.next()) {
                String hotel_id = rs.getString("hotel_id");
                String number_of_rooms = rs.getString("number_of_rooms");
                String rating = rs.getString("rating");
                String name = rs.getString("name");
                String location = rs.getString("location");


                System.out.println("Selected Hotel:");
                System.out.println("HotelId: " + hotel_Id);
                System.out.println("Number_of_rooms: " + number_of_rooms);
                System.out.println("Rating: " + rating);
                System.out.println("Name: " + name);
                System.out.println("Location: " + location);

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
    public boolean isfieldHotelIdExists(String fieldhotelId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();

            String query = "SELECT COUNT(*) FROM hotel WHERE hotel_id = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, fieldhotelId);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // Return true if count > 0 (hotel exists), false otherwise
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database resources
            DBUtil.closeConnection(connection);
        }

        return false;
    }
}