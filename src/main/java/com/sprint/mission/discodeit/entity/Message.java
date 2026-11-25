package com.sprint.mission.discodeit.entity;

public class Message extends Entity{
    private String text;
    public String getText(){
        return text;
    }
    public void updateText(String text){
        this.text = text;
        updateTimestamp();
    }

    public Message(String text){
        this.text = text;
    }
}
