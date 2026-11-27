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
        //testJCF();
        //testFile();
        //testBasicJCF();
        testBasicFile();
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
                .run(new FileChannelService())
                .run(new FileMessageService())
                .run(new FileUserService());
    }

    static void testBasicJCF() {
        System.out.println("\n\n=================================================================================");
        System.out.println("====Basic*Service / JCF*Repository Test==========================================");

        new TestRunner()
                .run(new BasicChannelService(JCFChannelRepository.getInstance()))
                .run(new BasicMessageService(JCFMessageRepository.getInstance()))
                .run(new BasicUserService(JCFUserRepository.getInstance()));
    }

    static void testBasicFile() {
        System.out.println("\n\n=================================================================================");
        System.out.println("====Basic*Service / File*Repository Test=========================================");

        new TestRunner()
                .run(new BasicChannelService(new FileChannelRepository()))
                .run(new BasicMessageService(new FileMessageRepository()))
                .run(new BasicUserService(new FileUserRepository()));
    }

}
