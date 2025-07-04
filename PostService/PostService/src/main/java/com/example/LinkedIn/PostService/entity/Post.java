package com.example.LinkedIn.PostService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table( name = "Posts" )
public class Post {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( nullable = false )
    private String content;

    @Column( nullable = false )
    private Long userId;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
