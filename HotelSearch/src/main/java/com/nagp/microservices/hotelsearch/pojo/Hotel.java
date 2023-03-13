package com.nagp.microservices.hotelsearch.pojo;

public class Hotel {
    private String hotelName;
    private String city;
    private String checkInDate;
    private String checkOutDate;

    public Hotel(String hotelName, String city, String checkInDate, String checkOutDate){
        this.hotelName = hotelName;
        this.city = city;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
