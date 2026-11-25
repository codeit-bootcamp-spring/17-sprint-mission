package com.sprint.mission;

import com.sprint.mission.discodeit.entity.*;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.file.FileChannelService;
import com.sprint.mission.discodeit.service.file.FileMessageService;
import com.sprint.mission.discodeit.service.file.FileUserService;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class JavaApplication {

    public static void main(String[] args) {

        Path directory = Paths.get(System.getProperty("user.dir"),"data");

        UserService userService = new FileUserService();
        ChannelService channelService = new FileChannelService();
        MessageService messageService = new FileMessageService(userService, channelService);


        System.out.println("===== 등록 =====");
        userService.create("김코딩");
        userService.create("이코딩");
        userService.create("최코딩");
        UUID userId = userService.findByName("김코딩").get(0).getId();

        channelService.create("채널1");
        channelService.create("채널2");
        channelService.create("채널3");
        UUID channelId = channelService.findByName("채널1").get(0).getId();

        messageService.create(userId, channelId, "메세지1");
        messageService.create(userId, channelId, "메세지2");
        messageService.create(userId, channelId, "메세지3");


        System.out.println("\n===== 조회(단건, 다건) =====");
        System.out.println("유저 조회:");
        userService.readAll().forEach(x -> System.out.println(String.format("%s(%s)", x.getName(), x.getId())));
        System.out.println("\n채널 조회:");
        channelService.readAll().forEach(x -> System.out.println(String.format("%s(%s)", x.getName(), x.getId())));
        System.out.println("\n메세지 조회:");
        messageService.readAll().forEach(x -> System.out.println(String.format("%s(%s)", x.getText(), x.getId())));


        System.out.println("\n1명 조회:");
        System.out.println(String.format("김코딩 ID: %s", userId));


        System.out.println("\n===== 수정 =====");
        userService.update(userId, "박수정");

        System.out.println("\n===== 수정된 데이터 조회 =====");
        userService.readAll().forEach(x -> System.out.println(String.format("%s(%s)", x.getName(), x.getId())));


        System.out.println("\n===== 삭제 =====");
        userService.delete(userId);


        System.out.println("\n===== 삭제 조회 =====");
        userService.readAll().forEach(x -> System.out.println(String.format("%s(%s)", x.getName(), x.getId())));
    }
}