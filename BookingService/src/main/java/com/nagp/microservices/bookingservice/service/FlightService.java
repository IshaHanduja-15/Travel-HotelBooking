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
public class FlightService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(FlightService.class);

    @CircuitBreaker(name="FLIGHT_API", fallbackMethod="fallbackForFlightAPI")
    @Retry(name="FLIGHT_API_RETRY", fallbackMethod = "retryForFlightAPI")
    public boolean checkAvailability(String flightNumber,String departure, String arrival, String departureDate,
                                        String flightClass, int numPassengers) {

        URI flightSearchURI = discoveryClient.getInstances("FLIGHTSEARCH").get(0).getUri();
       // String url = "http://localhost:9002/flightSearch";

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(String.valueOf(flightSearchURI)+"/flightSearch")
                .queryParam("departureLocation", departure)
                .queryParam("arrivalLocation", arrival)
                .queryParam("flightClass", flightClass)
                .queryParam("flightDate", departureDate);
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

    public boolean retryForFlightAPI(String flightNumber,String departure, String arrival, String departureDate,
                                     String flightClass, int numPassengers,Exception e) {
      LOG.info("Retrying flight API");
      return false;
    }

    public boolean fallbackForFlightAPI(String flightNumber, String departure, String arrival, String departureDate,
                                        String flightClass, int numPassengers, CallNotPermittedException e) {
        LOG.info("Fallback for flight API");
        return false;
    }

}
