package com.example.linkedin.ConnectionService.service;

import com.example.linkedin.ConnectionService.entity.Person;
import com.example.linkedin.ConnectionService.repository.PersonsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@XSlf4j
public class ConnectionsService {

    private final PersonsRepository personsRepository;

    public List<Person> getFirstDegreeConnections(Long userId) {
        return personsRepository.findAllFirstDegreeConnections(userId);
    }
}
