package com.sprint.mission.discodeit.entity;

public class User extends Entity{
    private static final long serialVersionUID = 1L;

    private String name;
    public String getName(){
        return name;
    }
    public void updateName(String name){
        this.name = name;
        updateTimestamp();
    }

    private String email;
    public String getEmail() {
        return email;
    }

    private String nickname;
    public String getNickname() {
        return nickname;
    }

    public User(String name, String nickname, String email){
        this.name = name;
        this.nickname = nickname;
        this.email = email;
    }


}
