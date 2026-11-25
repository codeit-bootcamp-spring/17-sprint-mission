package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestRunner {
    void runTestCRUD(ChannelService channelService) {
        new ChannelCRUDTest(channelService);
    }

    void runTestCRUD(MessageService messageService) {
        new MessageCRUDTest(messageService);
    }

    void runTestCRUD(UserService userService) {
        new UserCRUDTest(userService);
    }
}

class ChannelCRUDTest {
    ChannelCRUDTest(ChannelService channelService) {
        System.out.println("=================================================================================");
        System.out.println("== ChannelService CRUD 테스트 ===================================================");
        List<UUID> channelIds = new ArrayList<>();

        System.out.println("==== create ========================");
        {
            for (int i = 0; i < 10; ++i) {
                channelIds.add(channelService.create("채널" + i, null).getId());
            }
            System.out.println("find All");
            channelService.findAll().forEach(System.out::println);
        }

        System.out.println("==== find ==========================");
        {
            final UUID testId = channelIds.get(3);
            System.out.println("find id : " + testId);
            System.out.println(channelService.findById(testId));

        }

        System.out.println("==== update ========================");
        {
            final UUID testId = channelIds.get(3);
            System.out.println("update id : " + testId);
            channelService.update(testId, "수정된 채널", null);
            System.out.println("find All");
            channelService.findAll().forEach(System.out::println);
        }

        System.out.println("==== delete ========================");
        {
            final UUID testId = channelIds.get(3);
            System.out.println("delete id : " + testId);
            channelService.delete(testId);
            System.out.println("find All");
            channelService.findAll().forEach(System.out::println);
            // 삭제된 채널 id로 검색 : 오류
            //channelService.find(testId);
        }
    }
}

class MessageCRUDTest {
    MessageCRUDTest(MessageService messageService) {

    }
}

class UserCRUDTest {
    UserCRUDTest(UserService userService) {

    }
}