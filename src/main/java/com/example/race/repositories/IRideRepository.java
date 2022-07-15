package com.example.race.repositories;

import com.example.race.models.Ride;

import java.util.List;

public interface IRideRepository {
    Ride add(Ride ride);

    Ride add_jdbc(Ride ride);

    Ride add_statement(Ride ride);

    Ride edit(Ride ride);

    void editRides(List<Object[]> pairs);

    List<Ride> getRides();

    Ride getById(long id);

    boolean deleteById(long id);

    boolean deleteById_jdbc(long id);
}
