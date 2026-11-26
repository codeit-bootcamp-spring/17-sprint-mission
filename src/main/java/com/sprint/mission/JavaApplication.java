package com.sprint.mission;

import com.sprint.mission.discodeit.entity.*;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.file.FileChannelRepository;
import com.sprint.mission.discodeit.repository.file.FileMessageRepository;
import com.sprint.mission.discodeit.repository.file.FileUserRepository;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class JavaApplication {

    public static void main(String[] args) {

        Path directory = Paths.get(System.getProperty("user.dir"),"data");

        UserRepository userRepository = new FileUserRepository();
        ChannelRepository channelRepository = new FileChannelRepository();
        MessageRepository messageRepository = new FileMessageRepository();


        System.out.println("===== 등록 =====");
        User user = new User("김코딩");
        userRepository.save(user);
        userRepository.save(new User("이코딩"));
        userRepository.save(new User("최코딩"));

        Channel channel = new Channel("채널1");
        channelRepository.save(channel);
        channelRepository.save(new Channel("채널2"));
        channelRepository.save(new Channel("채널3"));

        Message message = new Message(user.getId(), channel.getId(),"메세지1");
        messageRepository.save(message);
        messageRepository.save(new Message(user.getId(), channel.getId(),"메세지2"));
        messageRepository.save(new Message(user.getId(), channel.getId(),"메세지3"));


        System.out.println("\n===== 조회(단건, 다건) =====");
        System.out.println("유저 조회:");
        userRepository.findAll().forEach(x -> System.out.println(String.format("%s(%s)", x.getName(), x.getId())));
        System.out.println("\n채널 조회:");
        channelRepository.findAll().forEach(x -> System.out.println(String.format("%s(%s)", x.getName(), x.getId())));
        System.out.println("\n메세지 조회:");
        messageRepository.findAll().forEach(x -> System.out.println(String.format("%s(%s)", x.getText(), x.getId())));


        System.out.println("\n1명 조회:");
        System.out.println(String.format("김코딩 ID: %s", user.getId()));


        System.out.println("\n===== 수정 =====");
        user.updateName("박수정");
        userRepository.save(user);

        System.out.println("\n===== 수정된 데이터 조회 =====");
        userRepository.findAll().forEach(x -> System.out.println(String.format("%s(%s)", x.getName(), x.getId())));


        System.out.println("\n===== 삭제 =====");
        userRepository.deleteById(user.getId());


        System.out.println("\n===== 삭제 조회 =====");
        userRepository.findAll().forEach(x -> System.out.println(String.format("%s(%s)", x.getName(), x.getId())));
    }
}