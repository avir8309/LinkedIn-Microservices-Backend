package com.example.LinkedIn.PostService.controller;

import com.example.LinkedIn.PostService.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikesController {

    private final LikeService likeService;

    @PostMapping("/{postId}")
    public ResponseEntity<Void> likePost(@PathVariable("postId") Long postId, @RequestHeader("X-User-Id") String userId) {
        likeService.likePost(postId, 1L);
        return ResponseEntity.ok().build();

    }
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> unLikePost(@PathVariable("postId") Long postId) {
        likeService.unLikePost(postId, 1L);
        return ResponseEntity.ok().build();

    }


}
