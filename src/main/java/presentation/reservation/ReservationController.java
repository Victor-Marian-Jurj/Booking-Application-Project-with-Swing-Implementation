package presentation.reservation;
import business.services.ReservationService;


public class ReservationController {
    private ReservationView view;
    private ReservationModel model;
    private ReservationService service;

    public ReservationController ( ReservationView view, ReservationModel model, ReservationService service){
        this.view = view;
        this.model = model;
        this.service = service;

    }
    public void buttonRefreshPressed() {
        model.setReservationList(service.getReservations());
        view.refreshTable(model.getReservationList());
    }

}
