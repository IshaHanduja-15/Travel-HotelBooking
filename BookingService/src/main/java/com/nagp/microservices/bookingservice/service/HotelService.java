package com.nagp.microservices.bookingservice.service;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
@Service
public class HotelService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DiscoveryClient discoveryClient;

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(HotelService.class);

    @CircuitBreaker(name="HOTEL_API", fallbackMethod="fallbackForHotelAPI")
    @Retry(name="HOTEL_API_RETRY", fallbackMethod = "retryForHotelAPI")
    public boolean checkAvailability(String hotelName, String roomsCount,
                                     String city, String checkInDate, String checkOutDate) {

        URI hotelSearchURI = discoveryClient.getInstances("HOTELSEARCH").get(0).getUri();

        //String url = "http://localhost:9003/hotels";

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(String.valueOf(hotelSearchURI)+"/hotels")
                .queryParam("hotelName", hotelName)
                .queryParam("city", city)
                .queryParam("checkInDate", checkInDate)
                .queryParam("checkOutDate", checkOutDate);
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        //System.out.println(builder.toUriString());

        List list = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, List.class).getBody();
        if(list.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean bookFlight(){
        return true;
    }

    public boolean retryForHotelAPI(String hotelName, String roomsCount,
                                    String city, String checkInDate, String checkOutDate,Exception e) {
        LOG.info("Retrying hotel API");
        return false;
    }

    public boolean fallbackForHotelAPI(String hotelName, String roomsCount,
                                       String city, String checkInDate, String checkOutDate, CallNotPermittedException e) {
        LOG.info("Fallback for hotel API");
        return false;
    }
}
