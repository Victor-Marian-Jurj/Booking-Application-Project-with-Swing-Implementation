package persistance;

import business.entities.Reservation;
import business.entities.Room;

import java.util.List;

public interface ReservationDataAccess {

    public List<Reservation> getAllReservations();

    public void saveAllReservations(List<Reservation> reservations);

    void updateValuesReservation(String reservation_Id, String reservation_status);

    void insertValuesReservation(Reservation reservation);

    void deleteValuesReservation(String reservationId);

    void selectValuesReservation(String reservationId);


}