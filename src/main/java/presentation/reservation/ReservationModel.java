package presentation.reservation;

import business.entities.Reservation;

import java.util.List;

public class ReservationModel {
    private List<Reservation> reservationList;
    public ReservationModel(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }
    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

}
