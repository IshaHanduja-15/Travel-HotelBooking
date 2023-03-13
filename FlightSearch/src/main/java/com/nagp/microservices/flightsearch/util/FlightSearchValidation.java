package com.nagp.microservices.flightsearch.util;

import com.nagp.microservices.flightsearch.exception.FlightSearchException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FlightSearchValidation {

    public boolean validateForFlight(String departureDate, String arrivalDate, String flightClass, String fligthDate) {

        boolean validationPass = true;

        if (departureDate.isEmpty() || arrivalDate.isEmpty()
                || flightClass.isEmpty() || fligthDate.isEmpty())
            validationPass = false;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date = sdf.parse(fligthDate);
        } catch (ParseException e) {
            validationPass = false;
            throw new FlightSearchException("Date format is not correct");
        }

        return validationPass;

    }
}
