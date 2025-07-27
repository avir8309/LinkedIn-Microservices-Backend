package com.example.linkedin.ConnectionService.controller;

import com.example.linkedin.ConnectionService.entity.Person;
import com.example.linkedin.ConnectionService.service.ConnectionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //send ConnectionRequest
    @PostMapping("/request/{userId}")
    public ResponseEntity<Boolean>  sendConnectionRequest(@PathVariable Long userId) {
        return ResponseEntity.ok(connectionsService.sendConnectionRequest(userId));

    }

    @PostMapping("/accept/{userId}")
    public ResponseEntity<Boolean> acceptConnection(@PathVariable Long userId) {
        return ResponseEntity.ok(connectionsService.acceptConnectionRequest(userId));
    }
}
