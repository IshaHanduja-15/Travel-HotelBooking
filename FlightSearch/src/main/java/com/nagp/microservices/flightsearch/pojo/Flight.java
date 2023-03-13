package com.nagp.microservices.flightsearch.pojo;

public class Flight {

    private String flightName;
    private String departLocation;
    private String arrivalLocation;
    private String flightClass;
    private String flightDate;
    private String price;

    public Flight(String flightName, String departureLocation, String arrivalLocation,
                  String departureDate, String flightClass, String price){

        this.flightName = flightName;
        this.departLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.flightDate = departureDate;
        this.flightClass = flightClass;
        this.price = price;

    }

    public String getDepartLocation() {
        return departLocation;
    }

    public void setDepartLocation(String departLocation) {
        this.departLocation = departLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
