package com.example.LinkedIn.PostService.repository;

import com.example.LinkedIn.PostService.entity.Post;
import com.example.LinkedIn.PostService.entity.PostLike;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

public interface LikeRepository extends CrudRepository<PostLike, Long> {

    boolean existsByUserIdAndPostId(Long userId,Long postId);

    //All
    @Transactional
    void deleteByUserIdAndPostId(Long userId,Long postId);

}
