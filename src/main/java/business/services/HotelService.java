package business.services;

import business.entities.Hotel;
import persistance.HotelDataAccess;
import persistance.HotelDataAccessSQL;

import java.util.List;
import java.util.stream.Collectors;

public class HotelService {
    public HotelService(){}


    private HotelDataAccess hotelDataAccess = new HotelDataAccessSQL() {
    };

    public HotelService(HotelDataAccess hotelDataAccess) {
        this.hotelDataAccess = hotelDataAccess;
    }

    public List<Hotel> getAllHotels() {
        List<business.entities.Hotel> list = hotelDataAccess.getAllHotels();
        return list.stream().sorted().collect(Collectors.toList());
    }
    public void updateHotel(String hotel_id, String name) {
        hotelDataAccess.updateHotelValues(hotel_id, name);
    }

    public void insertHotel(Hotel hotel) {hotelDataAccess.insertHotelValues(hotel);
    }

    public void deleteHotel(String hotel_Id) {hotelDataAccess.deleteHotelValues(hotel_Id);
    }

    public void selectHotel(String hotel_Id) {hotelDataAccess.selectHotelValues(hotel_Id);
    }
}