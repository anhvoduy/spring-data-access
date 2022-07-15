package com.example.race.models;

import com.example.race.repositories.ISpeakerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SpeakerTest {
    @Autowired
    private ISpeakerRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testFind() throws Exception {
        Speaker speaker = repository.getById(1L);

        assertNotNull(speaker);
        assertEquals(1, speaker.getSpeakerId());
    }

    @Test
    public void testList() throws Exception {
        List<Speaker> speakers = repository.findAll();

        assertNotNull(speakers);
        assert(speakers.size()) >= 1;
    }

    @Test
    public void testDslQueryAnd() throws Exception {
        List<Speaker> speakers = repository.findByFirstNameAndLastName("James", "King");

        assertNotNull(speakers);
        assert(speakers.size()) >= 1;
    }

    @Test
    public void testDslQueryOr() throws Exception {
        List<Speaker> speakers = repository.findByFirstNameOrLastName("James", "Duke");

        assertNotNull(speakers);
        assert(speakers.size()) >= 2;
    }

    @Test
    public void testDslQueryIsNull() throws Exception {
        List<Speaker> speakers = repository.findBySpeakerPhotoIsNull();

        assertNotNull(speakers);
        assert(speakers.size()) > 0;
    }

    @Test
    public void testDslQueryIn() throws Exception {
        List<String> companies = new ArrayList<>();
        companies.add("MicroOcean Software");
        companies.add("Contoso");
        companies.add("Adventureworks");
        List<Speaker> speakers = repository.findByCompanyIn(companies);

        assertNotNull(speakers);
        assertTrue(speakers.size() > 0);
    }

    @Test
    public void testDslQueryIgnoreCase() throws Exception {
        List<Speaker> speakers = repository.findByCompanyIgnoreCase("national bank");

        assertNotNull(speakers);
        assert(speakers.size()) > 0;
        assert(speakers.get(0).getCompany().equals("National Bank"));
    }

    @Test
    public void testDslQueryOrderByAsc() throws Exception {
        List<Speaker> speakers = repository.findByLastNameOrderByFirstNameAsc("Becker");

        assertNotNull(speakers);
        assert(speakers.size()) > 0;
    }

    @Test
    public void testDslQueryOrderByDesc() throws Exception {
        List<Speaker> speakers = repository.findByLastNameOrderByFirstNameDesc("Becker");

        assertNotNull(speakers);
        assert(speakers.size()) > 0;
    }

    @Test
    public void testDslQueryTop5() throws Exception {
        List<Speaker> speakers = repository.findByFirstNameOrderByFirstNameAsc("James").subList(0, 5);

        assertNotNull(speakers);
        assert(speakers.size()) == 5;
        assertTrue(speakers.get(0).getFirstName().equals("James"));
        assertTrue(speakers.get(1).getFirstName().equals("James"));
        assertTrue(speakers.get(2).getFirstName().equals("James"));
        assertTrue(speakers.get(3).getFirstName().equals("James"));
        assertTrue(speakers.get(4).getFirstName().equals("James"));
    }

    @Test
    @Transactional
    public void testSaveAndGetAndDelete() throws Exception {
        Speaker s = new Speaker();
        s.setFirstName("Dan");
        s.setLastName("Bunker");
        s.setTitle("Author");
        s.setCompany("Pluralsight");
        s.setSpeakerBio("Consulting and mentoring");
        Speaker s1 = repository.saveAndFlush(s);

        // clear the persistence context so we don't return the previously cached location object
        // this is a test only thing and normally doesn't need to be done in prod code
        entityManager.clear();

        Speaker otherSpeaker = repository.getOne(s.getSpeakerId());
        assertEquals("Dan", otherSpeaker.getFirstName());

        repository.deleteById(otherSpeaker.getSpeakerId());
    }
}
