package com.sprint.mission.discodeit.entity;

import com.sprint.mission.ChannelType;

public class Channel extends Entity{
    private static final long serialVersionUID = 1L;

    private ChannelType type;
    public ChannelType getType() {
        return type;
    }

    private String name;
    public String getName(){
        return name;
    }
    public void updateName(String name){
        this.name = name;
        updateTimestamp();
    }

    private String description;
    public String getDescription() {
        return description;
    }


    public Channel(ChannelType type, String name, String description){
        this.type = type;
        this.name = name;
        this.description = description;
    }


}
