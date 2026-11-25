package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class User {
    /// 도메인 공통 필드
    private final UUID id = UUID.randomUUID();
    private final Long createdAt = System.currentTimeMillis();
    private Long updatedAt;

    /// 유저 프로필
    private String name;    //유저이름
    private String imageUrl;

    /// 계정 로그인 정보. 임시배치
    private String email;
    private String password;

    public User(String name, String imageUrl, String email, String password) {
        update(name, imageUrl, email, password);
    }

    ///// Setter

    public User update(String name, String imageUrl, String email, String password) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.email = email;
        this.password = password;

        this.updatedAt = System.currentTimeMillis();
        return this;
    }

    //// Getter


    public UUID getId() {
        return id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append('{')
                .append(name)
                .append('(').append(id).append(')').append(',')
                .append(" createdAt=").append(createdAt).append(',')
                .append(" updatedAt=").append(updatedAt).append(',')
                .append(" imageUrl=").append(imageUrl).append(',')
                .append(" email=").append(email).append(',')
                .append(" password=").append(password)
                .append("}");
        return sb.toString();
    }
}
