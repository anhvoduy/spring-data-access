package com.example.race.repositories;

import com.example.race.models.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ISessionRepository extends JpaRepository<Session, Long> {
    List<Session> findBySessionNameContains(String name);
    List<Session> findBySessionLengthNot(Integer length);
    List<Session> findBySessionNameNotLike(String name);
    List<Session> findBySessionLengthLessThanEqual(Integer length);

    @Query(value = "select s from Session s where s.sessionName like %:name")
    List<Session> querySessionsWithName(@Param("name") String name);

    @Query(value = "select s from Session s where s.sessionName like %:name")
    Page<Session> querySessionsWithNamePaging(@Param("name") String name, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update Session s set s.sessionName = ?2, s.sessionDescription = ?3 where s.sessionId = ?1")
    int updateSessionName(Long id, String name, String desc);

    Session findFirstBySessionNameContains(String name);
    Long countBySessionNameContains(String name);
}
