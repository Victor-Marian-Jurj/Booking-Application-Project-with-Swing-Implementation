package business.services;


import business.entities.Room;
import persistance.RoomDataAccess;
import persistance.RoomDataAccessSQL;

import java.util.List;

public class RoomService {
    private RoomDataAccess roomDataAccess = new RoomDataAccessSQL();

    public RoomService(RoomDataAccess roomDataAccess) {
        this.roomDataAccess = roomDataAccess;
    }

    public RoomService() {
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = roomDataAccess.getAllRooms();
        return rooms;
    }

    public List<Room> getAllAvailableRooms(){
        List<Room> rooms = roomDataAccess.getAllAvailableRooms();
        return rooms;
    }
    private int getRoomIndex(List<Room> rooms, String roomId) {
        for (int currentIndex = 0; currentIndex < rooms.size(); currentIndex++) {
            Room currentRoom = rooms.get(currentIndex);
            if (currentRoom.getRoomId().equals(roomId)) {
                return currentIndex;
            }
            return currentIndex;
        }
        return -1;
    }

    public void updateRoom(String roomId, int roomPrice) {
        roomDataAccess.updateValuesRoom(roomId, roomPrice);
    }

    public void updateRoomByRoomNumberAndHotelId(int roomNumber, String hotelId, int roomPrice) {
        roomDataAccess.updateRoomByRoomNumberAndHotelId(roomNumber, hotelId, roomPrice);
    }

    public void deleteRoomByRoomNumberAndHotelId(int roomNumber, String hotelId) {
        roomDataAccess.deleteRoomByRoomNumberAndHotelId(roomNumber,hotelId);
    }


    public void insertRoom(Room room) {
        roomDataAccess.insertValuesRoom(room);
    }

    public void deleteRoom(String roomId) {
        roomDataAccess.deleteValuesRoom(roomId);
    }

    public void selectRoom(String roomId) {
        roomDataAccess.selectValuesRoom(roomId);
    }
}
