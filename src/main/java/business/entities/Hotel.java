package business.entities;

import java.util.Objects;

public class Hotel implements Comparable<Hotel>{

    private String hotelId;

    private int number_of_rooms;
    private int rating;

    private String name;

    private String location;


    public Hotel(String hotelId, int number_of_rooms, int rating, String name, String location) {
        this.hotelId = hotelId;
        this.number_of_rooms = number_of_rooms;
        this.rating = rating;
        this.name = name;
        this.location = location;

    }



    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public int getNumber_of_rooms() {
        return number_of_rooms;
    }

    public void setNumber_of_rooms(int number_of_rooms) {
        this.number_of_rooms = number_of_rooms;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return number_of_rooms == hotel.number_of_rooms && rating == hotel.rating && Objects.equals(hotelId, hotel.hotelId) && Objects.equals(name, hotel.name) && Objects.equals(location, hotel.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, number_of_rooms, rating, name, location);
    }

    @Override
    public String toString() {
        return "Hotel {" +
                "hotelId='" + hotelId + '\'' +
                ", number_of_rooms=" + number_of_rooms +
                ", rating=" + rating +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';


    }
    @Override
    public int compareTo(Hotel o){
        return this.hotelId.compareTo(o.hotelId);
    }

}