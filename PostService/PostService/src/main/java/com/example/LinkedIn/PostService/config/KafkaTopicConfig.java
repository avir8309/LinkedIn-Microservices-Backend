package com.example.LinkedIn.PostService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.kafka.clients.admin.NewTopic;

@Configuration
public class KafkaTopicConfig {

    //creating new Topic
    @Bean
    public NewTopic postCreatedTopic(){
        return new NewTopic("post-created-topic", 3, (short) 1);
    }

    //post-like-topic
    @Bean
    public NewTopic postLikeTopic(){
        return new NewTopic("post-like-topic", 3, (short) 1);
    }
}
