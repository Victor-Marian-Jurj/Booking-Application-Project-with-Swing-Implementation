package business.services;

import business.entities.Reservation;
import business.entities.Room;
import persistance.ReservationDataAccess;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReservationService {
    private  ReservationDataAccess reservationDataAccess;

    public ReservationService(ReservationDataAccess reservationDataAccess) {
        this.reservationDataAccess = reservationDataAccess;
    }

    public List<Reservation> getReservations() {
        List<Reservation> list = reservationDataAccess.getAllReservations();
        return list.stream().sorted().collect(Collectors.toList());
    }

   // public void addNewReservation(Reservation newReservation) {
      //  List<Reservation> reservations = reservationDataAccess.loadAllReservations();
      //  reservations.add(newReservation);
       // reservationDataAccess.saveAllReservations(reservations);
   // }

    //public void removeExistingReservation(String reservationId) {
       // List<Reservation> reservations = reservationDataAccess.loadAllReservations();
      //  int index = getReservationIndex(reservations, reservationId);
       // if (index != -1) { // if found
        //    reservations.remove(index);
        //    reservationDataAccess.saveAllReservations(reservations);
       // }
    //}


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

