package business.services;


import business.entities.Reservation;
import persistance.ReservationDataAccess;
import persistance.ReservationDataAccessSQL;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationService {
    private  ReservationDataAccess reservationDataAccess = new ReservationDataAccessSQL();

    public ReservationService(ReservationDataAccess reservationDataAccess) {
        this.reservationDataAccess = reservationDataAccess;
    }
    public ReservationService (){

    }

    public List<Reservation> getReservations() {
        return reservationDataAccess.getAllReservations();
    }

    public List<Reservation> getReservationForUser(String username){
        return reservationDataAccess.getReservationForUser(username);
    }

    public void updateReservation(String reservation_id, String reservation_status) {
        reservationDataAccess.updateValuesReservation(reservation_id, reservation_status);
    }

    public void insertReservation(Reservation reservation) {
        reservationDataAccess.insertValuesReservation(reservation);
    }

    public void deleteReservation(String reservation_Id) {
        reservationDataAccess.deleteValuesReservation(reservation_Id);
    }

    public void selectReservation(String reservation_Id) {
        reservationDataAccess.selectValuesReservation(reservation_Id);
    }
}

