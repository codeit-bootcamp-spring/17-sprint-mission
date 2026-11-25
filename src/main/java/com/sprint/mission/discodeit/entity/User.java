package com.sprint.mission.discodeit.entity;

public class User extends Entity{
    private String name;
    public String getName(){
        return name;
    }
    public void updateName(String name){
        this.name = name;
        updateTimestamp();
    }

    public User(String name){
        this.name = name;
    }


}
