package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class Message extends Entity{
    private static final long serialVersionUID = 1L;

    private final UUID userId;
    public UUID getUserId() {
        return userId;
    }

    private final UUID channelId;
    public UUID getChannelId() {
        return channelId;
    }

    private String text;
    public String getText(){
        return text;
    }


    public void updateText(String text){
        this.text = text;
        updateTimestamp();
    }

    public Message(UUID userId, UUID channelId, String text){
        this.userId = userId;
        this.channelId = channelId;
        this.text = text;
    }

}
