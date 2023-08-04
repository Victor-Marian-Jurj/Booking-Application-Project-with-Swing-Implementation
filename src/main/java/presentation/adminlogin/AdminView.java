
package presentation.adminlogin;


import business.services.*;

import persistance.*;
import presentation.hotel.*;

import business.services.UsersService;

import persistance.UsersDataAccessSQL;
import presentation.reservation.*;
import presentation.payment.*;
import presentation.room.*;

import presentation.users.*;
import util.UserSession;


import javax.swing.*;
import java.awt.*;

import java.net.MalformedURLException;
import java.net.URL;

public class AdminView extends JFrame {

    private final String userType = "admin";

    public AdminView() throws MalformedURLException {
        super("Admin View");
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout(20, 20));
        content.setAlignmentX(Component.CENTER_ALIGNMENT);
        JMenuBar menuBar = new JMenuBar();
        content.add(menuBar);

        setLocationRelativeTo(null);

        JMenu database = new JMenu("Database");
        JMenuItem showPayments = new JMenuItem("Show All Payments");
        JMenuItem showReservations = new JMenuItem("Show All Reservations");
        JMenuItem showClients = new JMenuItem("Show All Clients");
        JMenuItem showHotels = new JMenuItem("Show All Hotels");
        JMenuItem showRooms = new JMenuItem("Show All Rooms");

        database.add(showPayments);
        database.add(showReservations);
        database.add(showClients);
        database.add(showHotels);
        database.add(showRooms);


        showClients.addActionListener(e -> buttonShowClientsPressed());
        showRooms.addActionListener(e -> buttonShowAllRoomsPressed());
        showReservations.addActionListener(e -> buttonShowAllReservationsPressed());
        showPayments.addActionListener(e -> buttonShowAllPaymentsIsPressed());

        JMenu clientsOperations = new JMenu("Clients Operations");
        JMenuItem deleteClient = new JMenuItem("Delete Client");
        JMenuItem insertClient = new JMenuItem("New Client");
        JMenuItem updateClient = new JMenuItem("Update Client Data");
        clientsOperations.add(deleteClient);
        clientsOperations.add(insertClient);
        clientsOperations.add(updateClient);

        insertClient.addActionListener(e -> buttonInsertUserPressed());
        updateClient.addActionListener(e -> buttonUpdateUserPressed());
        deleteClient.addActionListener(e -> buttonDeleteUserPressed());

        JMenu reservationsOperations = new JMenu("Reservation Operations");
        JMenuItem deleteReservation = new JMenuItem("Delete Reservation");
        JMenuItem insertReservation = new JMenuItem("New Reservation");
        JMenuItem updateReservation = new JMenuItem("Update Reservation Data");
        reservationsOperations.add(deleteReservation);
        reservationsOperations.add(insertReservation);
        reservationsOperations.add(updateReservation);

        insertReservation.addActionListener(e -> buttonInsertReservationPressed());
        updateReservation.addActionListener(e -> buttonUpdateReservationPressed());
        deleteReservation.addActionListener(e -> buttonDeleteReservationPressed());

        JMenu roomsOperations = new JMenu("Room Operations");
        JMenuItem deleteRoom = new JMenuItem("Delete Room");
        JMenuItem insertRoom = new JMenuItem("New Room");
        JMenuItem updateRoom = new JMenuItem("Update Room Data");
        roomsOperations.add(deleteRoom);
        roomsOperations.add(insertRoom);
        roomsOperations.add(updateRoom);

        insertRoom.addActionListener(e -> buttonInsertRoomPressed());
        updateRoom.addActionListener(e -> buttonUpdateRoomPressed());
        deleteRoom.addActionListener(e -> buttonDeleteRoomPressed());


        JMenu hotelsOperations = new JMenu("Hotels Operations");
        JMenuItem deleteHotel = new JMenuItem("Delete Hotel");
        JMenuItem insertHotel = new JMenuItem("New Hotel");
        JMenuItem updateHotel = new JMenuItem("Update Hotel Data");
        hotelsOperations.add(deleteHotel);
        hotelsOperations.add(insertHotel);
        hotelsOperations.add(updateHotel);


        showHotels.addActionListener(e -> buttonShowHotelsPressed());

        insertHotel.addActionListener(e -> buttonInsertHotelPressed());
        updateHotel.addActionListener(e -> buttonUpdateHotelPressed());
        deleteHotel.addActionListener(e -> buttonDeleteHotelPressed());


        JMenu paymentsOperations = new JMenu("Payments Operations");
        JMenuItem deletePayment = new JMenuItem("Delete Payment");
        JMenuItem insertPayment = new JMenuItem("New Payment");
        JMenuItem updatePayment = new JMenuItem("Update Payment Data");
        paymentsOperations.add(deletePayment);
        paymentsOperations.add(insertPayment);
        paymentsOperations.add(updatePayment);

        deletePayment.addActionListener(e -> buttonDeletePaymentIsPressed());
        insertPayment.addActionListener(e -> buttonInsertPaymentIsPressed());
        updatePayment.addActionListener(e -> buttonUpdatePaymentIsPressed());

        JMenu disconnect =new JMenu("Disconnect");
        JMenuItem logOut = new JMenuItem("LogOut");
        disconnect.add(logOut);

        menuBar.add(database);
        menuBar.add(clientsOperations);
        menuBar.add(reservationsOperations);
        menuBar.add(roomsOperations);
        menuBar.add(hotelsOperations);
        menuBar.add(paymentsOperations);
        menuBar.add(disconnect);

        logOut.addActionListener(e -> {
            try {
                buttonLogoutPressed();
            } catch (MalformedURLException ex) {
                throw new RuntimeException(ex);
            }
        });


        JLabel adminPhoto = new JLabel();
        adminPhoto.setIcon(new ImageIcon(new URL("https://www.lansweeper.com/wp-content/uploads/2018/05/ASSET-USER-ADMIN.png")));
        adminPhoto.setLayout(new BorderLayout(20, 20));
        adminPhoto.setHorizontalAlignment(JLabel.CENTER);
        content.add(adminPhoto, BorderLayout.CENTER);


        this.setContentPane(content);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        this.setJMenuBar(menuBar);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

    }

    private void buttonShowAllPaymentsIsPressed() {
        PaymentService paymentService = new PaymentService(new PaymentDataAccessSQL());
        PaymentModel paymentModel = new PaymentModel(paymentService.getPayments());
        PaymentView paymentView= new PaymentView(paymentModel.getPaymentList());
        PaymentController paymentController = new PaymentController(paymentView, paymentModel,paymentService);
        paymentView.setController(paymentController);
    }

    private void buttonUpdatePaymentIsPressed() {
        new GridBagLayoutPaymentUpdate();
    }

    private void buttonInsertPaymentIsPressed() {
        new GridBagLayoutPaymentInsert();
    }

    private void buttonDeletePaymentIsPressed() {
        new GridBagLayoutPaymentDelete();

    }

    public void buttonShowClientsPressed() {
        UsersService usersService = new UsersService(new UsersDataAccessSQL());
        UsersModel usersModel = new UsersModel(usersService.getAllUsers());
        UsersView usersView = new UsersView(usersModel.getUsersList());
        UsersController usersController = new UsersController(usersView, usersModel, usersService);
        usersView.setController(usersController);

    }

    private void buttonInsertUserPressed() {
        UsersService usersService = new UsersService(new UsersDataAccessSQL());
        GridBagLayoutUsersInsert gridBagLayoutUsersInsert = new GridBagLayoutUsersInsert();
    }

    private void buttonUpdateUserPressed() {
        UsersService usersService = new UsersService(new UsersDataAccessSQL());
        GridBagLayoutUsersUpdate gridBagLayoutUsersUpdate = new GridBagLayoutUsersUpdate();
    }

    public void buttonDeleteUserPressed() {
        UsersService usersService = new UsersService(new UsersDataAccessSQL());
        GridBagLayoutUsersDelete gridBagLayoutUsersDelete = new GridBagLayoutUsersDelete();
    }

    private void buttonShowAllRoomsPressed() {
        RoomService roomService = new RoomService(new RoomDataAccessSQL());
        RoomModel roomModel = new RoomModel(roomService.getAllRooms());
        RoomView roomView = new RoomView(roomModel.getRoomList());
        RoomController roomController = new RoomController(roomView, roomModel, roomService);
        roomView.setControllerRoom(roomController);
    }

    private void buttonInsertRoomPressed() {
        RoomService roomService = new RoomService(new RoomDataAccessSQL());
        GridBagLayoutRoomInsert gridBagLayoutRoomInsert = new GridBagLayoutRoomInsert();
    }

    private void buttonUpdateRoomPressed() {
        RoomService roomService = new RoomService(new RoomDataAccessSQL());
        GridBagLayoutRoomUpdate gridBagLayoutRoomUpdate = new GridBagLayoutRoomUpdate();
    }

    public void buttonDeleteRoomPressed() {
        RoomService roomService = new RoomService(new RoomDataAccessSQL());
        GridBagLayoutRoomDelete gridBagLayoutRoomDelete = new GridBagLayoutRoomDelete();
    }

    public void buttonShowHotelsPressed() {
        HotelService hotelService = new HotelService(new HotelDataAccessSQL());
        HotelModel hotelModel = new HotelModel(hotelService.getAllHotels());
        HotelView hotelView = new HotelView(hotelModel.getHotelList());
        HotelController hotelController = new HotelController(hotelView, hotelModel, hotelService);
        hotelView.setController(hotelController);

    }

    private void buttonInsertHotelPressed() {
        HotelService hotelService = new HotelService(new HotelDataAccessSQL());
        GridBagLayoutHotelInsert gridBagLayoutHotelInsert = new GridBagLayoutHotelInsert();
    }

    private void buttonUpdateHotelPressed() {
        HotelService hotelService = new HotelService(new HotelDataAccessSQL());
        GridBagLayoutHotelUpdate gridBagLayoutHotelUpdate = new GridBagLayoutHotelUpdate();
    }

    public void buttonDeleteHotelPressed() {
        HotelService hotelService = new HotelService(new HotelDataAccessSQL());
        GridBagLayoutHotelDelete gridBagLayoutHotelDelete = new GridBagLayoutHotelDelete();
    }

    private void buttonShowAllReservationsPressed(){
        ReservationService reservationService = new ReservationService(new ReservationDataAccessSQL());
        ReservationModel reservationModel = new ReservationModel(reservationService.getReservations());
        ReservationView reservationView = new ReservationView(userType, reservationModel.getReservationList());
        ReservationController reservationController = new ReservationController(userType, reservationView, reservationModel, reservationService);
        reservationView.setReservationController(reservationController);
    }

    public void buttonInsertReservationPressed() {
        ReservationService reservationService = new ReservationService(new ReservationDataAccessSQL());
        GridBagLayoutReservationInsert gridBagLayoutReservationInsert = new GridBagLayoutReservationInsert(userType);
    }

    public void buttonUpdateReservationPressed (){
        ReservationService reservationService =  new ReservationService(new ReservationDataAccessSQL());
        GridBagLayoutReservationUpdate gridBagLayoutReservationUpdate = new GridBagLayoutReservationUpdate();

    }

    public void buttonDeleteReservationPressed () {
        ReservationService reservationService = new ReservationService(new ReservationDataAccessSQL());
        GridBagLayoutReservationDelete gridBagLayoutReservationDelete = new GridBagLayoutReservationDelete();

    }
    private void buttonLogoutPressed () throws MalformedURLException {
         this.dispose();
         retryLogin();
    }
    private void retryLogin (){
        UsersDataAccessSQL clientLoginService = new UsersDataAccessSQL();
        UsersLoginView usersLoginView = new UsersLoginView();
        UsersLoginController usersLoginController = new UsersLoginController();
        usersLoginView.setUsersLoginController(usersLoginController);
        usersLoginController.setClientLoginView(usersLoginView);
        usersLoginController.setClientLoginService(clientLoginService);
        usersLoginController.start();
    }

    public static void main(String[] args) {

        try {
            AdminView adminView = new AdminView();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}


