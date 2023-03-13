package com.nagp.microservices.bookingservice.controller;

import com.nagp.microservices.bookingservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nagp.microservices.bookingservice.service.FlightService;


@RestController
public class BookingController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private HotelService hotelService;

    @GetMapping(value="/bookFlight")
    public String bookFlight(@RequestParam(name="flightNumber") String flightNumber,
                             @RequestParam(name="departure") String departure,
                             @RequestParam(name="arrival") String arrival,
                             @RequestParam(name="departDate") String departureDate,
                             @RequestParam(name="passengers") int numPassengers,
                             @RequestParam(name="flightClass") String flightClass,
                             @RequestParam(name="payment") Boolean payment) {

        boolean isAvailable = flightService.checkAvailability(flightNumber, departure, arrival, departureDate, flightClass, numPassengers);

        if (!isAvailable) {
            return "This Flight is not available";
        }

        if (!payment) {
            return "Payment is not received, hence refunding the amount and cancelling the booking";
        }

        boolean isBooked = flightService.bookFlight();

        if (isBooked) {
            return "Your flight is booked. Please find flight details: "+flightNumber + " will take off from: "+ departure
                    +" to: "+arrival+" on: "+departureDate;
        } else {
            return "Unable to book your flight";
        }
    }

    @GetMapping(value="/bookHotel")
    public String bookHotel(@RequestParam(name="hotelName") String hotelName,
                             @RequestParam(name="roomsCount") String roomsCount,
                             @RequestParam(name="city") String city,
                             @RequestParam(name="checkInDate") String checkInDate,
                             @RequestParam(name="checkOutDate") String checkOutDate,
                             @RequestParam(name="payment") Boolean payment) {

        boolean isAvailable = hotelService.checkAvailability(hotelName, roomsCount, city, checkInDate, checkOutDate);

        if (!isAvailable) {
            return "This Hotel is not available";
        }

        if (!payment) {
            return "Payment is not received, hence refunding the amount and cancelling the booking";
        }

        boolean isBooked = hotelService.bookFlight();

        if (isBooked) {
            return "Your hotel is booked. HotelName: " + hotelName + " No of Rooms: "+roomsCount
                    + " from: "+checkInDate+" to: "+checkOutDate;
        } else {
            return "Unable to book your hotel";
        }
    }

}
