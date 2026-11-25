package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class User {

    UUID id;
    Long createdAt;
    Long updatedAt;

    // 로그인 // id만 접근 가능
    String userId;
    String userPassword;

    // 프로필
    String profileName;
    String profileStatusMsg;

    User(){
        id = UUID.randomUUID();
        createdAt = System.currentTimeMillis();
    }

    public UUID getId() {
        return id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return profileName;
    }

    public void setUserName(String userName) {
        this.profileName = userName;
    }

    public String getUserStatus() {
        return profileStatusMsg;
    }

    public void setUserStatus(String userStatus) {
        this.profileStatusMsg = userStatus;
    }

    // 업데이트
    public void updateProfileName(String name){
        this.profileName = name;
        updateCreatedAt();
    }
    void updateCreatedAt(){
        this.createdAt = System.currentTimeMillis();
    }
}
