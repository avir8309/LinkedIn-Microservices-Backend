package com.example.linkedin.ConnectionService.service;

import com.example.linkedin.ConnectionService.auth.UserContextHolder;
import com.example.linkedin.ConnectionService.entity.Person;
import com.example.linkedin.ConnectionService.repository.PersonsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ConnectionsService {

    private final PersonsRepository personsRepository;
    private final ConnectionsService connectionsService;

    public List<Person> getFirstDegreeConnections(Long userId) {
        return personsRepository.findAllFirstDegreeConnections(userId);
    }

    public Boolean sendConnectionRequest(Long userId) {
        Long senderId = UserContextHolder.getContext();
        boolean alreadyExist = personsRepository.connectionRequestExists(senderId,userId);
        if (alreadyExist) {
            throw new RuntimeException("Connection request already exists");
        }
        boolean alreadyConnected = personsRepository.alreadyConnected(senderId,userId);
        if (alreadyConnected) {
            throw new RuntimeException("Connection request already exists");
        }
        //add connection request function for senderid and reciever id in personRepo
        return true;

    }
    public Boolean acceptConnectionRequest(Long userId) {
        Long recieverId = UserContextHolder.getContext();
        boolean alreadyExist = personsRepository.connectionRequestExists(recieverId,userId);
        if (alreadyExist) {
            throw new RuntimeException("Connection request already exists");
        }
        //add accept request in repository
        return true;

    }


}
