package com.example.race.services;

import com.example.race.models.Ride;
import com.example.race.repositories.IRideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("rideService")
public class RideService implements IRideService {
    @Autowired
    private IRideRepository rideRepository;

    @Override
    public Ride add(Ride ride) {
        return rideRepository.add(ride);
    }

    @Override
    public Ride edit(Ride ride) {
        return rideRepository.edit(ride);
    }

    @Override
    public List<Ride> getList() {
        return rideRepository.getRides();
    }

    @Override
    public Ride getById(long id) { return rideRepository.getById(id); }

    @Override
    @Transactional
    public void batch() {
        List<Ride> rides = rideRepository.getRides();
        List<Object[]> pairs = new ArrayList<>();

        for(Ride ride:rides) {
            Object[] tmp = { new Date(), ride.getRideId() };
            pairs.add(tmp);
        }

        rideRepository.editRides(pairs);

        /*throw new DataAccessException("Testing Exception Handling") {
        };*/
    }

    @Override
    public boolean deleteById(long id) {
        return rideRepository.deleteById(id);
    }
}
