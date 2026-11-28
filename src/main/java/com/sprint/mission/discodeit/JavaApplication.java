package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.basic.BasicChannelService;
import com.sprint.mission.discodeit.service.basic.BasicMessageService;
import com.sprint.mission.discodeit.service.basic.BasicUserService;
import com.sprint.mission.discodeit.service.file.FileChannelService;
import com.sprint.mission.discodeit.service.file.FileMessageService;
import com.sprint.mission.discodeit.service.file.FileUserService;
import com.sprint.mission.discodeit.service.jcf.JCFChannelService;
import com.sprint.mission.discodeit.service.jcf.JCFMessageService;
import com.sprint.mission.discodeit.service.jcf.JCFUserService;

import java.util.ArrayList;
import java.util.List;

public class JavaApplication {

    static User setupUser(UserService userService) {
        User user = userService.create("woody", "Url", "woody@codeit.com", "woody1234");
        return user;
    }

    static Channel setupChannel(ChannelService channelService) {
        Channel channel = channelService.create("공지", null);
        return channel;
    }

    static void messageCreateTest(MessageService messageService, User author, Channel channel) {
        Message message = messageService.create("안녕하세요.", author.getId(), channel.getId());
        System.out.println("메시지 생성: " + message.getId());
    }

    public static void main(String[] args) {
        UserService userService =       FileUserService.getInstance();
        ChannelService channelService = FileChannelService.getInstance();
        MessageService messageService = FileMessageService.getInstance();

        // 셋업
        User user = setupUser(userService);
        Channel channel = setupChannel(channelService);
        // 테스트
        messageCreateTest(messageService, user, channel);

//        new TestRunner(TestServiceType.JCF);
//        new TestRunner(TestServiceType.FILE);
//        new TestRunner(TestServiceType.BASIC);
    }
}
