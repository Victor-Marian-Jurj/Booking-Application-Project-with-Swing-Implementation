package presentation.hotel;
import business.entities.Hotel;
import java.util.List;

public class HotelModel {

    private List<business.entities.Hotel> hotelList;

    public HotelModel(List<business.entities.Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    public List<business.entities.Hotel> getHotelList() {
        return hotelList;
    }
    public void setHotelList(List<business.entities.Hotel> hotelList) {
        this.hotelList = hotelList;
    }

}