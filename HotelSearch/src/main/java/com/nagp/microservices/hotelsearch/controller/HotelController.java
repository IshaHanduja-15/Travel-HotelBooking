package com.nagp.microservices.hotelsearch.controller;

import com.nagp.microservices.hotelsearch.exception.HotelSearchException;
import com.nagp.microservices.hotelsearch.pojo.Hotel;
import com.nagp.microservices.hotelsearch.util.HotelSearchValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private static List<Hotel> hotels = null;

    @Autowired
    private HotelSearchValidation hotelSearchValidation;

    static{
        hotels = new ArrayList<>();
        hotels.add(new Hotel("TajResorts","NewDelhi","31/03/2023","05/04/2023"));
        hotels.add(new Hotel("Oberoi","Gurgaon","01/04/2023","05/04/2023"));
        hotels.add(new Hotel("Hyatt","Gurgaon","25/03/2023","30/03/2023"));
        hotels.add(new Hotel("Lemeridian","Bangalore","27/04/2023","29/04/2023"));
        hotels.add(new Hotel("Chapslee","HimachalPradesh","31/03/2023","05/04/2023"));
        hotels.add(new Hotel("JuhuPlaza","Mumbai","12/04/2023","15/04/2023"));
        hotels.add(new Hotel("Lindsay","Kolkata","05/05/2023","06/05/2023"));

    }
    @GetMapping
    public ResponseEntity<List<Hotel>> searchHotels(
            @RequestParam(name = "hotelName") String hotelName,
            @RequestParam(name = "city") String city,
            @RequestParam(name = "checkInDate") String checkInDate,
            @RequestParam(name = "checkOutDate") String checkOutDate) {

        boolean validated =  hotelSearchValidation.validateForHotels(hotelName,city,
                checkInDate,checkOutDate);
        if (validated){

            List<Hotel> hotelsList = new ArrayList<>();
            for(Hotel hotel : hotels){
                if(hotel.getHotelName().equalsIgnoreCase(hotelName) && hotel.getCity().equalsIgnoreCase(city)
                        && hotel.getCheckInDate().equalsIgnoreCase(checkInDate)
                        && hotel.getCheckOutDate().equalsIgnoreCase(checkOutDate)){

                    hotelsList.add(hotel);

                }
            }
            ResponseEntity<List<Hotel>> hotelList = new ResponseEntity<>(hotelsList, HttpStatus.OK);
            return hotelList;
        }else{
            throw new HotelSearchException("Validation Failed! PLEASE CAREFULLY CHECK ALL THE FIELDS");
        }

    }
}
