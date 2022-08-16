package com.example.race.models;

import java.time.LocalDate;

public class FlightInformation {
    private String id;
    private String departureCity;
    private String destinationCity;
    private FlightType type;
    private boolean isDelayed;
    private int durationMin;
    private LocalDate departureDate;
    private Aircraft aircraft;
    private LocalDate createdAt;
}
