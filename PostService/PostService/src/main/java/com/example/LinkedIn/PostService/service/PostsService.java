package com.example.LinkedIn.PostService.service;

import com.example.LinkedIn.PostService.dto.PostCreateRequestDTO;
import com.example.LinkedIn.PostService.dto.PostDTO;
import com.example.LinkedIn.PostService.entity.Post;
import com.example.LinkedIn.PostService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostsService {
    //basically we are not passing explicit userId as userId will be extracted from Jwt
    //Token
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostDTO createPost(PostCreateRequestDTO postCreateRequestDTO, Long userid) {
        //only populating the content part , userId is explicitly coming here
        Post post = modelMapper.map(postCreateRequestDTO, Post.class);
        post.setUserId(userid);
        post = postRepository.save(post);
        return modelMapper.map(post, PostDTO.class);
    }

    public PostDTO getPost(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        return modelMapper.map(post, PostDTO.class);

    }
}
