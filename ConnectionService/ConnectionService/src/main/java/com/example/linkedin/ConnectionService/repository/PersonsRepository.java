package com.example.linkedin.ConnectionService.repository;

import com.example.linkedin.ConnectionService.entity.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;
import java.util.Optional;

public interface PersonsRepository extends Neo4jRepository<Person, Long> {
    Optional<Person> getByName(String name);

    @Query("MATCH (personA:Person)-[:CONNECTED_TO]-(personB:Person) " +
            "WHERE personA.userId = $userId " +
            "RETURN personB")
    List<Person> findAllFirstDegreeConnections( Long userId );

}
