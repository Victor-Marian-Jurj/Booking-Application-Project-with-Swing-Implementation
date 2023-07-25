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

//    public void addNewRoom(Room newRoom) {
//        List<Room> rooms = roomDataAccess.getAllRooms();
//        rooms.add(newRoom);
//        roomDataAccess.saveAllRooms(rooms);
//    }

//    public void removeExistingRoom(String roomId) {
//        List<Room> rooms = roomDataAccess.getAllRooms();
//        int index = getRoomIndex(rooms, roomId);
//        if (index != -1) { // if found
//            rooms.remove(index);
//            roomDataAccess.saveAllRooms(rooms);
//        }
//    }

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
