package com.example.LinkedIn.PostService.service;

import com.example.LinkedIn.PostService.auth.UserContextHolder;
import com.example.LinkedIn.PostService.client.ConnectionClient;
import com.example.LinkedIn.PostService.dto.PersonDTO;
import com.example.LinkedIn.PostService.dto.PostCreateRequestDTO;
import com.example.LinkedIn.PostService.dto.PostDTO;
import com.example.LinkedIn.PostService.entity.Post;
import com.example.LinkedIn.PostService.event.PostCreatedEvent;
import com.example.LinkedIn.PostService.exception.ResourceNotFoundException;
import com.example.LinkedIn.PostService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostsService {
    //basically we are not passing explicit userId as userId will be extracted from Jwt
    //Token
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final ConnectionClient connectionClient;
    private final KafkaTemplate<String, PostCreatedEvent> kafkaTemplate;



    public PostDTO createPost(PostCreateRequestDTO postCreateRequestDTO, Long userid) {
        //only populating the content part , userId is explicitly coming here
        Post post = modelMapper.map(postCreateRequestDTO, Post.class);
        post.setUserId(userid);
        post = postRepository.save(post);
        PostCreatedEvent postCreatedEvent = PostCreatedEvent.builder().
                PostId(post.getId()).content(post.getContent()).
                creatorId(userid).build();
        kafkaTemplate.send("post-created-topic", postCreatedEvent);

        return modelMapper.map(post, PostDTO.class);
    }

    public PostDTO getPost(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        return modelMapper.map(post, PostDTO.class);

    }

    public PostDTO getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post not found"));
        //we are getting this from the contexct that we created above
        Long userId = UserContextHolder.getContext();
        //through this we are calling the other microservice api via openFieg

        //ToDo send notifiaction to all connections via kafka
        List<PersonDTO> firstConnections = connectionClient.getFirstConnections(userId);
    }
}
