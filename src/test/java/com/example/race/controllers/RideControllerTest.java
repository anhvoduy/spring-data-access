package com.example.race.controllers;

import com.example.race.models.Ride;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

public class RideControllerTest {
    private String Url = "http://localhost:8083/uat/api/v1/ride";

    @Test
    public void testGetRides() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Ride>> rideResponse = restTemplate.exchange(
                Url + "/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Ride>>() {
                }
        );

        List<Ride> rides = rideResponse.getBody();
        for(Ride ride : rides) {
            System.out.println("ride name:" + ride.getRideName());
        }
    }

    @Test
    public void testGetRideById() {
        RestTemplate restTemplate = new RestTemplate();
        Ride ride = restTemplate.getForObject(Url + "/2", Ride.class);
        System.out.println("ride name:" + ride.getRideName());
    }

    @Test
    public void testCreateRide() {
        RestTemplate restTemplate = new RestTemplate();

        Ride ride = new Ride();
        ride.setRideName("Ride Test");
        ride.setDuration(500);

        ride = restTemplate.postForObject(Url + "/add", ride, Ride.class);

        System.out.println("Ride:" + ride);
    }

    @Test
    public void testUpdateRide() {
        RestTemplate restTemplate = new RestTemplate();

        Ride ride = new Ride();
        ride.setRideId(4L);
        ride.setRideName("Anissa Kate");
        ride.setDuration(2400);

        restTemplate.put(Url + "/edit", ride);

        ride = restTemplate.getForObject(Url + "/4", Ride.class);

        System.out.println("ride name:" + ride.getRideName());

        Assertions.assertEquals("Anissa Kate", ride.getRideName());
        Assertions.assertEquals(2400, ride.getDuration());
    }

    @Test
    public void testBatchUpdate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(Url + "/batch", Object.class);

        ResponseEntity<List<Ride>> rideResponse = restTemplate.exchange(
                Url + "/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Ride>>() {
                }
        );

        List<Ride> rides = rideResponse.getBody();
        for(Ride ride : rides) {
            Assertions.assertEquals(new Date().getDate(), ride.getRideDate().getDate());
        }
    }

    @Test
    public void testDelete() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(Url + "/delete/7");
    }

    @Test
    public void testException() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(Url + "/test", Ride.class);
    }
}
