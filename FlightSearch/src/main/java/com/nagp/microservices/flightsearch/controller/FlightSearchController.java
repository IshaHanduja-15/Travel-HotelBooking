package com.nagp.microservices.flightsearch.controller;

import com.nagp.microservices.flightsearch.exception.FlightSearchException;
import com.nagp.microservices.flightsearch.pojo.Flight;
import com.nagp.microservices.flightsearch.util.FlightSearchValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/flightSearch")
public class FlightSearchController {

    @Autowired
    private FlightSearchValidation flightSearchValidation;
    private static List<Flight> flights = null;
    
    static{
        flights = new ArrayList<>();
        flights.add(new Flight("Indigo","NewDelhi", "Bangalore",
                "31/03/2023", "Economy", "5053"));
        flights.add(new Flight("Spicejet","NewDelhi", "Bangalore",
                "31/03/2023", "Economy", "6053"));
        flights.add(new Flight("AirIndia","NewDelhi", "Bangalore",
                "31/03/2023", "Economy", "4500"));
        flights.add(new Flight("Vistara","Goa", "Kolkata",
                "07/05/2023", "Business", "7000"));
        flights.add(new Flight("Spicejet","Goa", "Kolkata",
                "07/05/2023", "Business", "6053"));
        flights.add(new Flight("Emirates","Chennai", "NewDelhi",
                "10/04/2023", "Economy", "7553"));
        flights.add(new Flight("AirAsia","Chennai", "NewDelhi",
                "10/04/2023", "Business", "10053"));
        flights.add(new Flight("GoFirst","Bangalore", "Goa",
                "31/03/2023", "Economy", "5053"));
        flights.add(new Flight("Vistara","Coimbatore", "Bangalore",
                "10/05/2023", "Economy", "5694"));
        flights.add(new Flight("AirAsia","NewDelhi", "Mumbai",
                "10/04/2023", "Economy", "3025"));
        flights.add(new Flight("Indigo","Chennai", "NewDelhi",
                "10/04/2023", "Business", "11000"));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Flight>> listOfFlights(@RequestParam(name="departureLocation") String departureLocation,
                                                @RequestParam(name="arrivalLocation") String arrivalLocation,
                                                @RequestParam(name="flightClass") String flightClass,
                                                @RequestParam(name="flightDate") String flightDate){
       boolean validated=  flightSearchValidation.validateForFlight(departureLocation,arrivalLocation,
                flightClass,flightDate);
       if (validated){
           List<Flight> flightsList = new ArrayList<>();
           for(Flight flight : flights){
               if(flight.getDepartLocation().equalsIgnoreCase(departureLocation)
                       && flight.getArrivalLocation().equalsIgnoreCase(arrivalLocation)
                       && flight.getFlightClass().equalsIgnoreCase(flightClass)
                       && flight.getFlightDate().equalsIgnoreCase(flightDate)){

                   flightsList.add(flight);

               }
           }
           ResponseEntity<List<Flight>> flightList = new ResponseEntity<>(flightsList, HttpStatus.OK);
           return flightList;
       }else{
           throw new FlightSearchException("Validation Failed! PLEASE CAREFULLY CHECK ALL THE FIELDS");
       }

    }

}