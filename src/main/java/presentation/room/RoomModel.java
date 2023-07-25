package presentation.room;

import business.entities.Room;

import java.util.List;

public class RoomModel {
    private List<Room> roomList;

    public RoomModel(List<Room> roomList) {
        this.roomList = roomList;
    }

    public List<Room> getRoomList() {
        return roomList;
    }
    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

}
