package com.example.race.repositories;

import com.example.race.models.Ride;
import com.example.race.util.RideRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("rideRepository")
public class RideRepository implements IRideRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Ride add(Ride ride) {
        String sql = "INSERT INTO ride_tracker(ride_name, duration) VALUES (?, ?)";
        jdbcTemplate.update(sql, ride.getRideName(), ride.getDuration());

        sql = "SELECT * FROM ride_tracker WHERE ride_id = (SELECT MAX(ride_id) max_id FROM ride_tracker)";
        return jdbcTemplate.queryForObject(sql, new RideRowMapper());
    }

    @Override
    public Ride add_jdbc(Ride ride) {
        SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(jdbcTemplate);

        simpleInsert.setGeneratedKeyName("ride_id");

        Map<String, Object> data = new HashMap<>();
        data.put("ride_name", ride.getRideName());
        data.put("duration", ride.getDuration());

        List<String> columns = new ArrayList<>();
        columns.add("ride_name");
        columns.add("duration");

        simpleInsert.setTableName("ride_tracker"); // table name in DB
        simpleInsert.setColumnNames(columns);

        Number newKey = simpleInsert.executeAndReturnKey(data);
        System.out.println("newKey:" + newKey);

        ride.setRideId(newKey.longValue());
        return ride;
    }

    @Override
    public Ride add_statement(Ride ride) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                String sql = "INSERT INTO ride_tracker(ride_name, duration) VALUES (?, ?)";
                PreparedStatement ps = con.prepareStatement(sql, new String[]{ "ride_id" });
                ps.setString(1, ride.getRideName());
                ps.setDouble(2, ride.getDuration());
                return ps;
            }
        }, keyHolder);

        long newId = keyHolder.getKey().longValue();
        return getById(newId);
    }

    @Override
    public Ride edit(Ride ride) {
        String sqlUpdate = "UPDATE ride_tracker SET ride_name = ?, duration = ? WHERE ride_id = ?";
        jdbcTemplate.update(sqlUpdate, ride.getRideName(), ride.getDuration(), ride.getRideId());
        return getById(ride.getRideId());
    }

    @Override
    public void editRides(List<Object[]> pairs) {
        String sqlEdit = "UPDATE ride_tracker SET ride_date = ? WHERE ride_id = ?";
        jdbcTemplate.batchUpdate(sqlEdit, pairs);
    }

    @Override
    public boolean deleteById_jdbc(long rideId) {
        String sql = "DELETE FROM ride_tracker WHERE ride_id = ?";
        int result = jdbcTemplate.update(sql, rideId);

        if (result == 1) return true;
        else return false;
    }

    @Override
    public boolean deleteById(long rideId) {
        String sql = "DELETE FROM ride_tracker WHERE ride_id = :ride_id";

        Map<String, Object> params = new HashMap<>();
        params.put("ride_id", rideId);

        NamedParameterJdbcTemplate nameTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        int result = nameTemplate.update(sql, params);

        if (result == 1) return true;
        else return false;
    }

    @Override
    public List<Ride> getRides() {
        String sqlQuery = "SELECT * FROM ride_tracker ORDER BY ride_id DESC";
        List<Ride> rides = jdbcTemplate.query(sqlQuery, new RideRowMapper());
        return rides;
    }

    @Override
    public Ride getById(long id) {
        try {
            String sql = "SELECT * FROM ride_tracker WHERE ride_id = ?";
            Ride ride = jdbcTemplate.queryForObject(sql, new RideRowMapper(), id);
            System.out.println("ride:" + ride.getRideName());
            return ride;
        } catch (EmptyResultDataAccessException exception) {
            System.out.println("EmptyResultDataAccessException:" + exception.toString());
            return null;
        }
    }

}