package com.example.race.util;

import com.example.race.models.Ride;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RideRowMapper implements RowMapper<Ride> {

    @Override
    public Ride mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ride ride = new Ride();
        ride.setRideId(rs.getLong("ride_id"));
        ride.setRideName(rs.getString("ride_name"));
        ride.setRideDate(rs.getDate("ride_date"));
        ride.setDuration(rs.getLong("duration"));
        return ride;
    }
}
