package presentation.hotel;
import business.entities.Hotel;
import business.services.HotelService;
import persistance.HotelDataAccessSQL;
import persistance.HotelDataAccess;

import java.util.List;

public class HotelController {
    private HotelDataAccessSQL hotelDataAccess;
    private HotelView hotelView;
    private HotelInsertView hotelInsertView;
    private HotelModel hotelModel;

    private HotelService hotelService = new HotelService();

    public HotelController(HotelDataAccessSQL hotelDataAccess) {
        this.hotelDataAccess = hotelDataAccess;
    }

    public HotelController(HotelView hotelView, HotelModel hotelModel, HotelService hotelService) {
        this.hotelView = hotelView;
        this.hotelModel = hotelModel;
        this.hotelService = hotelService;
    }

    public HotelController() {
    }


    public void setHotelInsertView(HotelInsertView hotelInsertView) {
        this.hotelInsertView = hotelInsertView;
    }

    public void buttonRefreshPressedHotel() {
        List<Hotel> hotelList = hotelService.getAllHotels();
        hotelModel.setHotelList(hotelService.getAllHotels());
        hotelView.refreshTableHotel(hotelModel.getHotelList());
    }


    public void handleClickButtonInsertHotel(Hotel hotel) {
        hotelService.insertHotel(hotel);
    }

    public void handleClickButtonInsert(Hotel hotel) {
        hotelService.insertHotel(hotel);
    }


    public void handleClickButtonDeleteHotel(String hotel_id) {
        hotelService.deleteHotel(hotel_id);
    }

    public void handleClickButtonUpdateHotel(String hotel_id, int number_of_rooms, int rating, String name, String location) {
        hotelService.updateHotel(hotel_id, number_of_rooms, rating, name, location);
    }


    public void start() {
        hotelInsertView.display();
    }

    public boolean isfieldHotelIdExists(String fieldHotelIdToDelete) {
        System.out.println(hotelDataAccess);
        System.out.println(hotelDataAccess.isfieldHotelIdExists(fieldHotelIdToDelete));
        return hotelDataAccess != null && hotelDataAccess.isfieldHotelIdExists(fieldHotelIdToDelete);

    }
}

