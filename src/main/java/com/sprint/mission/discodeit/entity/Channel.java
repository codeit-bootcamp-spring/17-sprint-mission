package com.sprint.mission.discodeit.entity;

public class Channel extends Entity{
    private String name;
    public String getName(){
        return name;
    }
    public void updateName(String name){
        this.name = name;
        updateTimestamp();
    }

    public Channel(String name){
        this.name = name;
    }
}
