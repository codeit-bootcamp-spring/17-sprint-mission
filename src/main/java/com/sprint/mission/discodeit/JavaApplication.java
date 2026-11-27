package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.file.FileChannelService;
import com.sprint.mission.discodeit.service.file.FileMessageService;
import com.sprint.mission.discodeit.service.file.FileUserService;
import com.sprint.mission.discodeit.service.jcf.JCFChannelService;
import com.sprint.mission.discodeit.service.jcf.JCFMessageService;
import com.sprint.mission.discodeit.service.jcf.JCFUserService;

public class JavaApplication {
    static void testJCF() {
        ChannelService channelService = new JCFChannelService();
        MessageService messageService = new JCFMessageService();
        UserService userService = new JCFUserService();

        TestRunner testRunner = new TestRunner();
        testRunner.run(channelService);
        testRunner.run(messageService);
        testRunner.run(userService);
    }

    static void testFile() {
        ChannelService channelService = new FileChannelService();
        MessageService messageService = new FileMessageService();
        UserService userService = new FileUserService();

        TestRunner testRunner = new TestRunner();
        testRunner.run(channelService);
        testRunner.run(messageService);
        testRunner.run(userService);
    }

    public static void main(String[] args) {

        testJCF();
        testFile();
    }
}
