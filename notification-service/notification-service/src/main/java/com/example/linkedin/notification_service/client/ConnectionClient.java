package com.example.linkedin.notification_service.client;


import com.example.linkedin.notification_service.dto.PersonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient( name ="connections-service", path="/" )
public interface ConnectionClient {

    @GetMapping("/{userId}/first-connections")
    List<PersonDTO> getFirstConnections(@PathVariable("userId") Long userId);
}
