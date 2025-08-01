package com.example.LinkedIn.PostService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table( name = "post_likes")
public class PostLike {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false)
    private Long postId;

    @Column( nullable = false)
    private Long userId;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
