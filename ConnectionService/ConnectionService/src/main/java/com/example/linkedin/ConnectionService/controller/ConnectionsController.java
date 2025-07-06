package com.example.linkedin.ConnectionService.controller;

import com.example.linkedin.ConnectionService.entity.Person;
import com.example.linkedin.ConnectionService.service.ConnectionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class ConnectionsController {

    private final ConnectionsService connectionsService;

    @GetMapping("/{userId}/first-connections")
    public ResponseEntity<List<Person>> getFirstConnections(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(connectionsService.getFirstDegreeConnections(userId));
    }
}
