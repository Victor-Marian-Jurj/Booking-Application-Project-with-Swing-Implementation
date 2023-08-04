package presentation.reservation;
import business.entities.Reservation;
import business.services.ReservationService;
import util.UserSession;


public class ReservationController {
    private String userType;
    private ReservationView view;
    private ReservationModel model;
    private ReservationService service = new ReservationService();
    private ReservationInsertView reservationInsertView;

    public ReservationController (String userType, ReservationView view, ReservationModel model, ReservationService service){
        this.view = view;
        this.model = model;
        this.service = service;
        this.userType = userType;
    }

    public ReservationController (){}

    public void buttonRefreshPressed(String userType) {
        this.userType = userType;

        switch (userType) {
            case "user" -> {
                UserSession userSession = UserSession.getInstance();
                model.setReservationList(service.getReservationForUser(userSession.getUsername()));
            }
            case "admin" -> {
                model.setReservationList(service.getReservations());
            }
        }

        view.refreshTable(model.getReservationList());
    }

    public void buttonRefreshPressedUser() {
        UserSession userSession = UserSession.getInstance();
        model.setReservationList(service.getReservationForUser(userSession.getUsername()));
        view.refreshTableUserReservation(model.getReservationList());
    }

    public  void handleClickButtonInsertReservation(Reservation reservation) {
        service.insertReservation(reservation);
    }

    public void setReservationInsertView(ReservationInsertView reservationInsertView) {
        this.reservationInsertView = reservationInsertView;
    }

    public void handleClickButtonReservationDelete(String reservationId) {
        service.deleteReservation(reservationId);
    }

    public void start() {
        reservationInsertView.display();
    }
}