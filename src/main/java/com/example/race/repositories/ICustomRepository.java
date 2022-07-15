package com.example.race.repositories;

import com.example.race.models.Session;

import java.util.List;

public interface ICustomRepository {
    List<Session> customQuerySessions();
}
