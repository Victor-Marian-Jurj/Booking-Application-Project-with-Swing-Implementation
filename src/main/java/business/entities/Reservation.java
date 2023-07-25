package business.entities;


import java.util.Date;
import java.util.Objects;

public class Reservation {

    private String reservationId;

    private String username;

    private String hotelId;

    private String roomId;
    private String reservationStatus;

    private Date checkInDate;

    private Date checkOutDate;

    public Reservation(String reservationId, String username, String hotelId, String roomId, String reservationStatus, Date checkInDate, Date checkOutDate) {
        this.reservationId = reservationId;
        this.username = username;
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.reservationStatus = reservationStatus;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
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

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(reservationId, that.reservationId) && Objects.equals(username, that.username) && Objects.equals(hotelId, that.hotelId) && Objects.equals(roomId, that.roomId) && Objects.equals(reservationStatus, that.reservationStatus) && Objects.equals(checkInDate, that.checkInDate) && Objects.equals(checkOutDate, that.checkOutDate);
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