package com.example.LinkedIn.PostService.repository;

import com.example.LinkedIn.PostService.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
