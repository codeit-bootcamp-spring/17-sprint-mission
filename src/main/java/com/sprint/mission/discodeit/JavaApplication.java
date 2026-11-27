package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.repository.file.FileChannelRepository;
import com.sprint.mission.discodeit.repository.file.FileMessageRepository;
import com.sprint.mission.discodeit.repository.file.FileUserRepository;
import com.sprint.mission.discodeit.repository.jcf.JCFChannelRepository;
import com.sprint.mission.discodeit.repository.jcf.JCFMessageRepository;
import com.sprint.mission.discodeit.repository.jcf.JCFUserRepository;
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

public class JavaApplication {

    public static void main(String[] args) {
        testJCF();
        testFile();
        testBasicJCF();
        testBasicFile();
    }

    static void testJCF() {
        System.out.println("\n\n=================================================================================");
        System.out.println("====JCF*Service Test=============================================================");
        ChannelService channelService = new JCFChannelService();
        MessageService messageService = new JCFMessageService();
        UserService userService = new JCFUserService();

        TestRunner testRunner = new TestRunner();
        testRunner.run(channelService);
        testRunner.run(messageService);
        testRunner.run(userService);
    }

    static void testFile() {
        System.out.println("\n\n=================================================================================");
        System.out.println("====File*Service Test============================================================");
        ChannelService channelService = new FileChannelService();
        MessageService messageService = new FileMessageService();
        UserService userService = new FileUserService();

        TestRunner testRunner = new TestRunner();
        testRunner.run(channelService);
        testRunner.run(messageService);
        testRunner.run(userService);
    }

    static void testBasicJCF() {
        System.out.println("\n\n=================================================================================");
        System.out.println("====Basic*Service / JCF*Repository Test==========================================");
        ChannelService channelService = new BasicChannelService(new JCFChannelRepository());
        MessageService messageService = new BasicMessageService(new JCFMessageRepository());
        UserService userService = new BasicUserService(new JCFUserRepository());

        TestRunner testRunner = new TestRunner();
        testRunner.run(channelService);
        testRunner.run(messageService);
        testRunner.run(userService);
    }

    static void testBasicFile() {
        System.out.println("\n\n=================================================================================");
        System.out.println("====Basic*Service / File*Repository Test=========================================");
        ChannelService channelService = new BasicChannelService(new FileChannelRepository());
        MessageService messageService = new BasicMessageService(new FileMessageRepository());
        UserService userService = new BasicUserService(new FileUserRepository());

        TestRunner testRunner = new TestRunner();
        testRunner.run(channelService);
        testRunner.run(messageService);
        testRunner.run(userService);
    }

}
