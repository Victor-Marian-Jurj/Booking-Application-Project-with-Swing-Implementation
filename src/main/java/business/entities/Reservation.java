package business.entities;


import java.sql.Date;
import java.util.Objects;

public class Reservation {

    private String reservationId;

    private String username;

    private String hotelId;

    private String roomId;
    private String roomType;
    private String hotelName;
    private String reservationStatus;

    private java.sql.Date checkInDate;

    private java.sql.Date checkOutDate;

    public Reservation(String reservationId, String username, String hotelId, String roomId, String reservationStatus, java.sql.Date checkInDate, java.sql.Date checkOutDate) {
        this.reservationId = reservationId;
        this.username = username;
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.reservationStatus = reservationStatus;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Reservation(String username, String hotelName, String roomType, java.sql.Date checkInDate, java.sql.Date checkOutDate){
        this.username = username;
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Reservation(String hotel_id, String room_id, java.sql.Date check_in_date, java.sql.Date check_out_date){

    }



    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public java.sql.Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public java.sql.Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation reservation = (Reservation) o;
        return username == reservation.username && checkInDate == reservation.checkInDate && checkOutDate == reservation.checkOutDate && Objects.equals(hotelId, reservation.hotelId) && Objects.equals(roomId, reservation.roomId) && Objects.equals(reservationStatus, reservation.reservationStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, username, hotelId, roomId, reservationStatus, checkInDate, checkOutDate);
    }

    @Override
    public String toString() {
        return "Reservation {" +
                "reservationId='" + reservationId + '\'' +
                ", username='" + username + '\'' +
                ", hotelId='" + hotelId + '\'' +
                ", roomId='" + roomId + '\'' +
                ", reservationStatus='" + reservationStatus + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}