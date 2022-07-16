package com.example.race.services;

import com.example.race.models.Ride;

import java.util.List;

public interface IRideService {
    Ride add(Ride ride);

    Ride edit(Ride ride);

    List<Ride> getList();

    Ride getById(long id);

    void batch();

    boolean deleteById(long id);
}
