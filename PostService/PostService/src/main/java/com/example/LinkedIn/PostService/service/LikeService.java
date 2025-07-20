package com.example.LinkedIn.PostService.service;

import com.example.LinkedIn.PostService.entity.Post;
import com.example.LinkedIn.PostService.entity.PostLike;
import com.example.LinkedIn.PostService.event.PostLikedEvent;
import com.example.LinkedIn.PostService.exception.BadRequestException;
import com.example.LinkedIn.PostService.exception.ResourceNotFoundException;
import com.example.LinkedIn.PostService.repository.LikeRepository;
import com.example.LinkedIn.PostService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final KafkaTemplate<String, PostLikedEvent> kafkaTemplate;

    public void likePost(Long postId, Long userId) {
        log.info("Attempting to like the post" + userId);
        boolean exists = postRepository.existsById(postId);
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post Not Found with Id "+ postId));

        if (!exists) {
            throw new ResourceNotFoundException(" post not found with user id "+ userId);
        }
        boolean alreadyLiked = likeRepository.existsByUserIdAndPostId(userId, postId);
        if (alreadyLiked) {
            throw new BadRequestException(" post already liked with user id "+ userId);

        }
        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);
        likeRepository.save(postLike);
        PostLikedEvent postLikedEvent = PostLikedEvent.builder().postId(postId).likedById(userId).creatorId(post.getUserId()).build();
        kafkaTemplate.send("post-liked-event", postLikedEvent);
        log.info("Successfully liked the post "+ userId);


    }

    public void unLikePost(Long postId, Long userId) {
        log.info("Attempting to unlike the post" + userId);
        boolean exists = postRepository.existsById(postId);
        if (!exists) {
            throw new ResourceNotFoundException(" post not found with user id "+ userId);
        }
        boolean alreadyLiked = likeRepository.existsByUserIdAndPostId(userId, postId);
        if (!alreadyLiked) {
            throw new BadRequestException(" post not liked with user id "+ userId);

        }
        likeRepository.deleteByUserIdAndPostId(userId,postId);
        log.info("Successfully unliked the post "+ userId);


    }

}
