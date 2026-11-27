package com.sprint.mission.discodeit;

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
//        testJCF();
//        testFile();
//        testBasic();

        messageServiceTest();
    }

    static void messageServiceTest() {
        ChannelService channelService = BasicChannelService.getInstance();
        UserService UserService = BasicUserService.getInstance();
        MessageService messageService = BasicMessageService.getInstance();


    }

    static void testJCF() {
        System.out.println("\n\n=================================================================================");
        System.out.println("====JCF*Service Test=============================================================");

        new TestRunner()
                .run(JCFChannelService.getInstance())
                .run(JCFMessageService.getInstance())
                .run(JCFUserService.getInstance());
    }

    static void testFile() {
        System.out.println("\n\n=================================================================================");
        System.out.println("====File*Service Test============================================================");

        new TestRunner()
                .run(FileChannelService.getInstance())
                .run(FileMessageService.getInstance())
                .run(FileUserService.getInstance());
    }

    static void testBasic() {
        System.out.println("\n\n=================================================================================");
        System.out.println("====Basic*Service / File*Repository Test=========================================");

        new TestRunner()
                .run(BasicChannelService.getInstance())
                .run(BasicMessageService.getInstance())
                .run(BasicUserService.getInstance());
    }

}
