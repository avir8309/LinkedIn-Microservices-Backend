package com.example.LinkedIn.PostService.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreatedEvent {
    Long creatorId;
    String content;
    Long PostId;
}
