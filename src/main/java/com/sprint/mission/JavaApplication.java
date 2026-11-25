package com.sprint.mission;

import com.sprint.mission.discodeit.entity.*;

public class JavaApplication {

    public static void main(String[] args) {
        User user = new User("테스트 유저");
        Channel channel = new Channel("테스트 채널");
        Message message = new Message("테스트 메시지");

        // User 테스트
        System.out.println("===== User =====");
        printEntity(user);
        System.out.println("Username: " + user.getName());

        // Channel 테스트
        System.out.println("\n===== Channel =====");
        printEntity(channel);
        System.out.println("Name: " + channel.getName());

        // Message 테스트
        System.out.println("\n===== Message =====");
        printEntity(message);
        System.out.println("Content: " + message.getText());

        // 수정 테스트
        System.out.println("\n===== Update =====");

        user.updateName("수정된 유저");
        channel.updateName("수정된 채널");
        message.updateText("수정된 메시지");

        System.out.println("Username: " + user.getName());
        System.out.println("UpdatedAt: " + user.getUpdatedAt());

        System.out.println("Name: " + channel.getName());
        System.out.println("UpdatedAt: " + channel.getUpdatedAt());

        System.out.println("Content: " + message.getText());
        System.out.println("UpdatedAt: " + message.getUpdatedAt());
    }

    private static void printEntity(Entity entity) {
        System.out.println("ID: " + entity.getId());
        System.out.println("CreatedAt: " + entity.getCreatedAt());
        System.out.println("UpdatedAt: " + entity.getUpdatedAt());
    }
}