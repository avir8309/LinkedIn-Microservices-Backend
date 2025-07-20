package com.example.LinkedIn.PostService.client;

import com.example.LinkedIn.PostService.dto.PersonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient( name ="connections-service", path="/" )
public interface ConnectionClient {

    @GetMapping("/{userId}/first-connections")
    List<PersonDTO> getFirstConnections(@PathVariable("userId") Long userId);
}
