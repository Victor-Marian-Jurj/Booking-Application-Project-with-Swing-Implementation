package presentation.userlogin;

import business.entities.Users;
import business.services.ReservationService;
import business.services.RoomService;
import persistance.ReservationDataAccessSQL;
import persistance.RoomDataAccessSQL;
import persistance.UsersDataAccessSQL;
import presentation.hotel.HotelController;
import presentation.hotel.HotelModel;
import presentation.hotel.HotelView;
import presentation.reservation.GridBagLayoutReservationInsert;
import presentation.reservation.ReservationController;
import presentation.reservation.ReservationModel;
import presentation.reservation.ReservationView;
import presentation.room.RoomController;
import presentation.room.RoomModel;
import presentation.room.RoomView;
import presentation.users.*;
import util.UserSession;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;


public class UserView extends JFrame {
    private Users loggedInUser;
    private final String userType = "user";

    public UserView() throws MalformedURLException {

        loggedInUser = UserSession.getInstance().getLoggedInUser();


//        Users loggedInUser = UserSession.getInstance().getLoggedInUser();



        if (loggedInUser != null) {
            UpdateUserProfileDialog updateDialog = new UpdateUserProfileDialog(this, loggedInUser);
            updateDialog.setCurrentUser(loggedInUser);
            updateDialog.setVisible(true);
        }



        JPanel userPanel = new JPanel();
        this.setContentPane(userPanel);

        JMenuBar menuBar = new JMenuBar();
        JMenu disconnect =new JMenu("Disconnect");

        JMenuItem logOut = new JMenuItem("LogOut");
        disconnect.add(logOut);
        userPanel.add(disconnect);

        logOut.addActionListener(e -> {
            try {
                buttonLogoutPressed();
                UserSession userSession = UserSession.getInstance();
                userSession.setUsername(null);
                userSession.setPassword(null);
            } catch (MalformedURLException ex) {
                throw new RuntimeException(ex);
            }
        });

        menuBar.add(disconnect);
        this.setJMenuBar(menuBar);

        userPanel.setLayout(new GridBagLayout());
        userPanel.setBackground(Color.LIGHT_GRAY);


        JLabel userTitleLabel = new JLabel("Welcome to your User Profile", JLabel.CENTER);
        userTitleLabel.setForeground(Color.BLACK);
        userTitleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel userPhoto = new JLabel();
        userPhoto.setIcon(new ImageIcon(new URL("https://cdn.landesa.org/wp-content/uploads/default-user-image.png")));
        userPhoto.setPreferredSize(new Dimension(350, 400));

        JButton showAllHotelsButton = new JButton("Show All Hotels");
        JButton showAvailableRoomsButton = new JButton("Show Available Rooms");


        JButton createReservationButton = new JButton("Make a Reservation");
        JButton updateUserInformationButton = new JButton("Update User Profile Information");

        JButton checkMyReservation = new JButton("Check my Reservation");


        Box buttonBox = Box.createVerticalBox();
        buttonBox.add(Box.createVerticalStrut(20));
//        buttonBox.add(showAvailableRoomsButton);
//        buttonBox.add(Box.createVerticalStrut(20));
//        buttonBox.add(showAllHotelsButton);
//        buttonBox.add(Box.createVerticalStrut(20));
        buttonBox.add(createReservationButton);
        buttonBox.add(Box.createVerticalStrut(20));
        buttonBox.add(checkMyReservation);
        buttonBox.add(Box.createVerticalStrut(200));
        buttonBox.add(updateUserInformationButton);

        addItem(userPanel, userTitleLabel, 0,0, GridBagConstraints.PAGE_START, 0.1, 0.1,1,2,GridBagConstraints.BOTH,0);
        addItem(userPanel, userPhoto, 0,1, GridBagConstraints.NORTHWEST, 1, 0.8,1,1,GridBagConstraints.HORIZONTAL,1);
        addItem(userPanel, buttonBox, 1,1, GridBagConstraints.NORTHEAST, 0.1, 0.8,1,1,GridBagConstraints.HORIZONTAL,1);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        this.setTitle("User View");
        this.setSize(800,530);
        this.setLocationRelativeTo(null);

        updateUserInformationButton.addActionListener(e -> {
            buttonUpdateIsPressed();
        });
        showAllHotelsButton.addActionListener(e -> buttonShowHotelsPressed());
        showAvailableRoomsButton.addActionListener(e -> buttonShowAvailableRoomPressed());
        checkMyReservation.addActionListener(e -> buttonCheckMyReservationPressed());
        createReservationButton.addActionListener(e -> buttonCreateReservationPressed());

    }
    private void buttonUpdateIsPressed() {
        Users loggedInUser = UserSession.getInstance().getLoggedInUser();
        UpdateUserProfileDialog updateDialog = new UpdateUserProfileDialog(this, loggedInUser);
        updateDialog.setVisible(true);
    }

    private void buttonUpdateUserInformationIsPressed() throws MalformedURLException {
        Users loggedInUser = UserSession.getInstance().getLoggedInUser();
        UpdateUserProfileDialog updateDialog = new UpdateUserProfileDialog(this, loggedInUser);
        updateDialog.setVisible(true);
//        new GridBagLayoutUsersUpdate().setVisible(true);
    }

    private void buttonLogoutPressed () throws MalformedURLException {
        this.dispose();
        retryLogin();
   }
    private void retryLogin(){
        UsersDataAccessSQL clientLoginService = new UsersDataAccessSQL();
        UsersLoginView usersLoginView = new UsersLoginView();
        UsersLoginController usersLoginController = new UsersLoginController();
        usersLoginView.setUsersLoginController(usersLoginController);
        usersLoginController.setClientLoginView(usersLoginView);
        usersLoginController.setClientLoginService(clientLoginService);
        usersLoginController.start();
    }

    private void buttonShowHotelsPressed(){
        business.services.HotelService hotelService = new business.services.HotelService(new persistance.HotelDataAccessSQL());
        HotelModel hotelModel = new HotelModel(hotelService.getAllHotels());
        HotelView hotelView = new HotelView(hotelModel.getHotelList());
        HotelController hotelController = new HotelController(hotelView, hotelModel, hotelService);
        hotelView.setController(hotelController);

   }

    private void buttonShowAvailableRoomPressed(){
        RoomService roomService = new RoomService(new RoomDataAccessSQL());
        RoomModel roomModel = new RoomModel(roomService.getAllAvailableRooms());
        RoomView roomView = new RoomView(roomModel.getRoomList());
        RoomController roomController = new RoomController(roomView, roomModel, roomService);
        roomView.setControllerRoom(roomController);
    }

    private void buttonCheckMyReservationPressed(){
        UserSession userSession = UserSession.getInstance();
        String username = userSession.getUsername();

        ReservationService reservationService = new ReservationService(new ReservationDataAccessSQL());
        ReservationModel reservationModel = new ReservationModel(reservationService.getReservationForUser(username));
        ReservationView reservationView = new ReservationView(userType, reservationModel.getReservationList(), true);
        ReservationController reservationController = new ReservationController(userType, reservationView, reservationModel, reservationService);
        reservationView.setReservationController(reservationController);
    }

    private void buttonCreateReservationPressed(){
        ReservationService reservationService = new ReservationService(new ReservationDataAccessSQL());
        GridBagLayoutReservationInsert gridBagLayoutReservationInsert = new GridBagLayoutReservationInsert(userType);
    }

    private void addItem( JPanel panel, JComponent component, int x, int y, int align, double weightX,double weightY,int gridHeight, int gridWidth, int filling, int padding) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = align;
        gbc.weightx=weightX;
        gbc.weighty=weightY;
        gbc.gridheight= gridHeight;
        gbc.gridwidth= gridWidth;
        gbc.fill=filling;
        gbc.ipady=padding;

        panel.add(component, gbc);
    }

    public static void main(String[] args) {
        try {
            UserView userView =new UserView();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}