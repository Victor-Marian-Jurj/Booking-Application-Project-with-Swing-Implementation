package presentation.room;

import business.entities.Room;
import business.services.RoomService;

public class RoomController {
    private RoomView roomView;
    private RoomInsertView roomInsertView;

    private RoomModel roomModel;

    private RoomService roomService = new RoomService();

    public RoomController(RoomView roomView, RoomModel roomModel, RoomService roomService) {
        this.roomView = roomView;
        this.roomModel = roomModel;
        this.roomService = roomService;
    }

    public void setRoomInsertView(RoomInsertView roomInsertView) {
        this.roomInsertView = roomInsertView;
    }

    public RoomController() {

    }
    public void buttonRefreshPressedRoom() {
        roomModel.setRoomList(roomService.getAllRooms());
        roomView.refreshTableRoom(roomModel.getRoomList());
    }
    public void handleClickButtonInsertRoom(Room room){
        roomService.insertRoom(room);

    }
    public void handleClickButtonInsert(Room room){
        roomService.insertRoom(room);

    }

    public void handleClickButtonDeleteRoom (String roomId){
        roomService.deleteRoom(roomId);
    }
    public void handleClickButtonUpdateRoom (String roomId, int price){
        roomService.updateRoom(roomId, price);
    }
    public void start() {
        roomInsertView.display();
    }
}
