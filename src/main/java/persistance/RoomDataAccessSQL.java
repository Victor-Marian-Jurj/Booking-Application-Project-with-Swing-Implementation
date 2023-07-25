package persistance;

import business.entities.Room;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDataAccessSQL implements RoomDataAccess {

    public List<Room> getAllRooms() {
        List<Room> roomList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * from room");
            while (rs.next()) {
                String roomId = rs.getString("room_Id");
                int roomNumber = Integer.parseInt(String.valueOf((2)));
                String roomType = rs.getString(3);
                int roomFloor = Integer.parseInt(String.valueOf((4)));
                int roomPrice = Integer.parseInt(String.valueOf((5)));
                String hotelId = rs.getString(6);
                boolean isAvailable = rs.getBoolean(7);


                Room room= new Room(roomId, roomNumber, roomType, roomFloor,  roomPrice,  hotelId, isAvailable);
                roomList.add(room);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeConnection(connection);
        }
        return roomList;
    }

    @Override
    public List<Room> getAllAvailableRooms() {
        List<Room> roomList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM room WHERE is_available = true");
            while (rs.next()) {
                String roomId = rs.getString("room_Id");
                int roomNumber = Integer.parseInt(String.valueOf((2)));
                String roomType = rs.getString(3);
                int roomFloor = Integer.parseInt(String.valueOf((4)));
                int roomPrice = Integer.parseInt(String.valueOf((5)));
                String hotelId = rs.getString(6);
                boolean isAvailable = rs.getBoolean(7);


                Room room= new Room(roomId, roomNumber, roomType, roomFloor,  roomPrice,  hotelId, isAvailable);
                roomList.add(room);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeConnection(connection);
        }
        return roomList;
    }

    @Override
    public void updateValuesRoom(String roomId, int roomPrice) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("update room set room_price = ? where room_Id = ?");
            statement.setInt(1,roomPrice);
            statement.setString(2, roomId);
            int noOfUpdates = statement.executeUpdate();
            System.out.println("NUmber of updated records = " + noOfUpdates);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void insertValuesRoom(Room room) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("insert into room values(?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, room.getRoomId());
            statement.setInt(2, room.getRoomNumber());
            statement.setString(3, room.getRoomType());
            statement.setInt(4, room.getRoomFloor());
            statement.setInt(5,room.getRoomPrice());
            statement.setString(6, room.getHotelId());
            statement.setBoolean(7, room.isAvailable());

            int noOfInserts = statement.executeUpdate();
            System.out.println("Number of inserted records = " + noOfInserts);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void deleteValuesRoom(String roomId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("DELETE FROM room WHERE room_Id = ?");
            statement.setString(1, roomId);
            int noOfInserts = statement.executeUpdate();
            System.out.println("Number of deleted records = " + noOfInserts);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection);
        }
    }




    public void selectValuesRoom(String roomId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("SELECT * FROM room WHERE room_Id = ?");
            statement.setString(1, roomId);
            rs = statement.executeQuery();
            while (rs.next()) {
                String id = rs.getString("room_id");
                String roomNumber = rs.getString("room_number");
                String roomType = rs.getString("room_type");
                String roomFloor = rs.getString("room_floor");
                String roomPrice = rs.getString("room_price");
                String hotelId = rs.getString("hotel_id");
                boolean isAvailable = rs.getBoolean("is_available");



                System.out.println("Selected Room:");
                System.out.println("RoomId: " + id);
                System.out.println("Room Number: " + roomNumber);
                System.out.println("Room Type: " + roomType);
                System.out.println("Room Floor: " + roomFloor);
                System.out.println("Price: " + roomPrice);
                System.out.println("Hotel id: " + hotelId);
                System.out.println("Availability: " + isAvailable);

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
