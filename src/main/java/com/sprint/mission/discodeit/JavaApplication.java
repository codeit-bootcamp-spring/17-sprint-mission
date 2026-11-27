package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.file.FileChannelService;
import com.sprint.mission.discodeit.service.jcf.JCFChannelService;
import com.sprint.mission.discodeit.service.jcf.JCFMessageService;
import com.sprint.mission.discodeit.service.jcf.JCFUserService;

public class JavaApplication {
    public static void main(String[] args) {
//        ChannelService channelService = new JCFChannelService();
        ChannelService channelService = new FileChannelService();
        MessageService messageService = new JCFMessageService();
        UserService userService = new JCFUserService();

        TestRunner testRunner = new TestRunner();
        testRunner.run(channelService);
//        testRunner.run(messageService);
//        testRunner.run(userService);
    }
}
