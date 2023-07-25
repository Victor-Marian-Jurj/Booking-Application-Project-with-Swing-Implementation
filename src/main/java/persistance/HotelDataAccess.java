package persistance;

import business.entities.Hotel;

import java.util.List;

public interface HotelDataAccess {
    List<Hotel> getAllHotels();

    void updateHotelValues(String hotel_id, String name);

    void insertHotelValues(Hotel hotel);

    void deleteHotelValues(String hotelId);

    void selectHotelValues(String hotelId);
}