package com.sprint.mission;

import com.sprint.mission.discodeit.entity.*;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.file.FileChannelRepository;
import com.sprint.mission.discodeit.repository.file.FileMessageRepository;
import com.sprint.mission.discodeit.repository.file.FileUserRepository;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.basic.BasicChannelService;
import com.sprint.mission.discodeit.service.basic.BasicMessageService;
import com.sprint.mission.discodeit.service.basic.BasicUserService;

import java.io.*;
public class JavaApplication {
    static User setupUser(UserService userService) {
        User user = userService.create("woody", "woody@codeit.com", "woody1234");
        return user;
    }

    static Channel setupChannel(ChannelService channelService) {
        Channel channel = channelService.create(ChannelType.PUBLIC, "공지", "공지 채널입니다.");
        return channel;
    }

    static void messageCreateTest(MessageService messageService, Channel channel, User author) {
        Message message = messageService.create(author.getId(), channel.getId(), "안녕하세요.");
        System.out.println("메시지 생성: " + message.getId());
    }

    public static void main(String[] args) {


        UserRepository userRepository = new FileUserRepository();
        ChannelRepository channelRepository = new FileChannelRepository();
        MessageRepository messageRepository = new FileMessageRepository();

        UserService userService = new BasicUserService(userRepository);
        ChannelService channelService = new BasicChannelService(channelRepository);
        MessageService messageService = new BasicMessageService(messageRepository, userService, channelService);

        User author = setupUser(userService);
        Channel testChannel = setupChannel(channelService);
        messageCreateTest(messageService, testChannel, author);


        System.out.println("===== 등록 =====");
        User user = userService.create("김코딩", "kimcoding@codeit.com", "김코딩1234");
        userService.create("이코딩", "leecoding@codeit.com", "이코딩1234");
        userService.create("최코딩", "choicoding@codeit.com", "최코딩1234");

        Channel channel = channelService.create(ChannelType.PUBLIC, "채널1", "공개 채널입니다");

        channelService.create(ChannelType.PRIVATE, "채널2", "비공개 채널입니다");
        channelService.create(ChannelType.PUBLIC, "채널3", "채널3입니다");

        messageService.create(user.getId(), channel.getId(),"메세지1");
        messageService.create(user.getId(), channel.getId(),"메세지2");
        messageService.create(user.getId(), channel.getId(),"메세지3");


        System.out.println("\n===== 조회(단건, 다건) =====");
        System.out.println("유저 조회:");
        userService.readAll().forEach(x -> System.out.println(String.format("%s(%s)", x.getName(), x.getId())));
        System.out.println("\n채널 조회:");
        channelService.readAll().forEach(x -> System.out.println(String.format("%s(%s)", x.getName(), x.getId())));
        System.out.println("\n메세지 조회:");
        messageService.readAll().forEach(x -> System.out.println(String.format("%s(%s)", x.getText(), x.getId())));


        System.out.println("\n1명 조회:");
        User foundUser = userService.read(user.getId());
        System.out.printf("%s(%s)%n", foundUser.getName(), foundUser.getId());


        System.out.println("\n===== 수정 =====");
        userService.update(user.getId(), "박수정");

        System.out.println("\n===== 수정된 데이터 조회 =====");
        userService.readAll().forEach(x -> System.out.println(String.format("%s(%s)", x.getName(), x.getId())));


        System.out.println("\n===== 삭제 =====");
        userService.delete(user.getId());


        System.out.println("\n===== 삭제 조회 =====");
        userService.readAll().forEach(x -> System.out.println(String.format("%s(%s)", x.getName(), x.getId())));

    }
}