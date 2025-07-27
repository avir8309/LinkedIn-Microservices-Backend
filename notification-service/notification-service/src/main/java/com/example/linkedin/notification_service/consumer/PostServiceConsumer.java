package com.example.linkedin.notification_service.consumer;

import com.example.linkedin.PostService.event.PostCreatedEvent;
import com.example.linkedin.PostService.event.PostLikedEvent;
import com.example.linkedin.notification_service.client.ConnectionClient;
import com.example.linkedin.notification_service.dto.PersonDTO;
import com.example.linkedin.notification_service.entity.Notification;
import com.example.linkedin.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceConsumer {
     private final ConnectionClient connectionClient;
     private final NotificationRepository notificationRepository;
    // to send notification we need first degree connection of a user
    @KafkaListener( topics= "post-created-topic")
    public void handlePostCreatedEvent(PostCreatedEvent postCreatedEvent) {
        List<PersonDTO> connections = connectionClient.getFirstConnections(postCreatedEvent.getCreatorId());
        for(PersonDTO connection : connections) {
            sendNotification(connection.getUserId(),"Post created by your connection");
        }
    }

    @KafkaListener( topics ="post-like-event")
    public void handlePostLikeEvent(PostLikedEvent postLikedEvent) {
        //creator will get the notification
        sendNotification(postLikedEvent.getCreatorId(), " user liked your post ");

    }
    public void sendNotification(Long userId, String message){
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);
        notificationRepository.save( notification );

    }
}
