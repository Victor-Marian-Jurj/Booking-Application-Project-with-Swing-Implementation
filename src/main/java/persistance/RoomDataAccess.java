package persistance;

import business.entities.Room;

import java.util.List;

public interface RoomDataAccess {
   List<Room> getAllRooms();

    List<Room> getAllAvailableRooms();

    void updateValuesRoom(String roomId, int roomPrice);

    void insertValuesRoom(Room room);

    void deleteValuesRoom(String roomId);

    void selectValuesRoom(String roomId);
}
