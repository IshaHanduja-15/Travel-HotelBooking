package com.nagp.microservices.hotelsearch.util;

import com.nagp.microservices.hotelsearch.exception.HotelSearchException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class HotelSearchValidation {

    public boolean validateForHotels(String hotelName, String city, String checkInDate, String checkOutDate) {

        boolean validationPass = true;

        if (hotelName.isEmpty() || city.isEmpty()
                || checkInDate.isEmpty() || checkOutDate.isEmpty())
            validationPass = false;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date = sdf.parse(checkInDate);
            Date date1 = sdf.parse(checkOutDate);
        } catch (ParseException e) {
            validationPass = false;
            throw new HotelSearchException("Date format is not correct");
        }

        return validationPass;

    }
}
