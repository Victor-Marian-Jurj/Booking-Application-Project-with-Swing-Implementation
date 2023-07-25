package presentation.hotel;
import business.entities.Hotel;
import business.services.HotelService;
public class HotelController {
    private HotelView hotelView;
    private HotelInsertView hotelInsertView;
    private HotelModel hotelModel;

    private HotelService hotelService = new HotelService();

    public HotelController(HotelView hotelView, HotelModel hotelModel, HotelService hotelService) {
        this.hotelView = hotelView;
        this.hotelModel = hotelModel;
        this.hotelService = hotelService;
    }
    public void setHotelInsertView(HotelInsertView hotelInsertView) {this.hotelInsertView = hotelInsertView;}

    public HotelController(){
    }

    public void buttonRefreshPressed() {
        hotelModel.setHotelList(hotelService.getAllHotels());
        hotelView.refreshTableHotel(hotelModel.getHotelList());
    }

    public void buttonRefreshPressedHotel() {
        hotelModel.setHotelList(hotelService.getAllHotels());
        hotelView.refreshTableHotel(hotelModel.getHotelList());
    }
    public void handleClickButtonInsertHotel(Hotel hotel){
        hotelService.insertHotel(hotel);

    }
    public void start() {
        hotelInsertView.display();
    }
}
