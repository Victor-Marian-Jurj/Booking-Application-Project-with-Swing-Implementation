import business.entities.Hotel;
import business.entities.Payment;
import business.services.UsersService;
import business.services.PaymentService;
import persistance.UsersDataAccessSQL;
import persistance.PaymentDataAccessSQL;

import presentation.users.UsersController;
import presentation.users.UsersModel;
import presentation.users.UsersView;
import presentation.hotel.HotelController;
import presentation.hotel.HotelModel;
import presentation.hotel.HotelView;
import presentation.payment.PaymentController;
import presentation.payment.PaymentModel;
import presentation.payment.PaymentView;

public class Main {

    public static void main(String[] args) {

         UsersService service = new UsersService(new UsersDataAccessSQL());

         UsersModel model = new UsersModel(service.getAllUsers());

         UsersView view = new UsersView(model.getUsersList());

         UsersController controller = new UsersController(view, model, service);

         view.setController(controller);


      //  RoomService roomService = new RoomService(new RoomDataAccessSQL());
       // roomService.selectRoom("1");

      //  Room room = new Room("2", 11, "single", 4, 90, 1);
      //    roomService.insertRoom(room);

     //   roomService.updateRoom(String.valueOf(2), 50);
      //  roomService.deleteRoom("2");

      //  RoomModel roomModel = new RoomModel(roomService.getAllRooms());
     //   RoomView roomView = new RoomView(roomModel.getRoomList());
      //  RoomController roomController = new RoomController(roomView, roomModel, roomService);

      //  roomView.setControllerRoom(roomController);

       view.setController(controller);
         business.services.HotelService hotelService = new business.services.HotelService(new persistance.HotelDataAccessSQL());
         Hotel hotel =new Hotel ("H0088",55,5,"BlueaquaStar","Bacau");
        hotelService.insertHotel(hotel);
        hotelService.selectHotel("H0025");
        hotelService.updateHotel("H0095", "DuoMio");
        hotelService.deleteHotel("H0090");
        HotelModel hotelModel = new HotelModel(hotelService.getAllHotels());
        HotelView hotelView = new HotelView(hotelModel.getHotelList());
        HotelController hotelController = new HotelController(hotelView, hotelModel, hotelService);
        view.setController(controller);



        PaymentService paymentService = new PaymentService(new PaymentDataAccessSQL());
        PaymentModel paymentModel = new PaymentModel(paymentService.getPayments());
        PaymentView paymentView = new PaymentView(paymentModel.getPaymentList());
        PaymentController paymentController = new PaymentController(paymentView, paymentModel, paymentService);
        paymentView.setController(paymentController);

        Payment payment = new Payment("PM050", "C0080", "RE032", "350", "3", "1050", "Paid");

        paymentService.insertPaymentValues(payment);

        paymentService.updatePaymentValues("PM023", "Declined");

        paymentService.deletePaymentValues("PM022 ");
        paymentService.selectPaymentValues("PM023");

    }
}