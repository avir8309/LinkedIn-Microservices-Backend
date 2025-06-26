package com.example.LinkedIn.PostService.controller;

import com.example.LinkedIn.PostService.dto.PostCreateRequestDTO;
import com.example.LinkedIn.PostService.dto.PostDTO;
import com.example.LinkedIn.PostService.entity.Post;
import com.example.LinkedIn.PostService.service.PostsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts") //all the api will go via posts
@RequiredArgsConstructor
public class PostsController {
    //always send the data as json in postman
    private final PostsService postsService;

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostCreateRequestDTO postCreateRequestDTO, HttpServletRequest request) {
        //from HttpServletRequest we will get userId via authentication token
        PostDTO createdPost = postsService.createPost( postCreateRequestDTO, 1L ); //here 1l is a dummy userId
        return new ResponseEntity<>( createdPost, HttpStatus.CREATED );
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Long postId) {
        PostDTO postDTO = postsService.getPost( postId );
        return new ResponseEntity<>( postDTO, HttpStatus.OK );
    }
}
