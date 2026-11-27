package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.jcf.JCFChannelService;

import javax.swing.*;
import java.util.*;
import java.util.stream.IntStream;

class TestRunner {
    TestRunner run(ChannelService channelService) {
        new ChannelTest(channelService);
        return this;
    }

    TestRunner run(MessageService messageService) {
        new MessageTest(messageService);
        return this;
    }

    TestRunner run(UserService userService) {
        new UserTest(userService);
        return this;
    }
}

abstract class TestCRUD<T, U> {
    TestCRUD(T t) {
        run(t);
    }

    protected void run(T t) {
        System.out.println("\n==== create ========================");

        List<U> entities = IntStream.range(0, 10)
                .mapToObj(i -> create(t, i))
                .peek(System.out::println)
                .toList();

        final UUID testId = getId(entities.get(3));

        System.out.println("\n==== find ==========================");
        System.out.println("find id : " + testId);
        System.out.println(findById(t, testId));

        System.out.println("\n==== update ========================");

        System.out.println("update id : " + testId);
        update(t, testId);
        findAll(t).forEach(System.out::println);

        System.out.println("\n==== delete ========================");
        System.out.println("delete id : " + testId);
        delete(t, testId);
        findAll(t).forEach(System.out::println);
    }

    abstract protected U create(T service, int index);

    abstract protected UUID getId(U entity);

    abstract protected U findById(T service, UUID id);

    abstract protected List<U> findAllById(T service, List<UUID> ids);

    abstract protected List<U> findAll(T service);

    abstract protected U update(T service, UUID id);

    abstract protected void delete(T service, UUID id);
}

class ChannelTest extends TestCRUD<ChannelService, Channel> {
    ChannelTest(ChannelService channelService) {
        super(channelService);
    }

    @Override
    protected Channel create(ChannelService service, int index) {
        return service.create("채널" + index, null);
    }

    @Override
    protected UUID getId(Channel entity) {
        return entity.getId();
    }

    @Override
    protected Channel findById(ChannelService service, UUID id) {
        return service.findById(id);
    }

    @Override
    protected List<Channel> findAllById(ChannelService service, List<UUID> ids) {
        return service.findAllById(ids);
    }

    @Override
    protected List<Channel> findAll(ChannelService service) {
        return service.findAll();
    }

    @Override
    protected Channel update(ChannelService service, UUID id) {
        return service.update(id, "수정된 채널명", null);
    }

    @Override
    protected void delete(ChannelService service, UUID id) {
        service.delete(id);
    }
}

class MessageTest extends TestCRUD<MessageService, Message> {
    MessageTest(MessageService messageService) {
        super(messageService);
    }

    @Override
    protected Message create(MessageService service, int index) {
        return service.create("메세지 테스트" + index, null, null);
    }

    @Override
    protected UUID getId(Message entity) {
        return entity.getId();
    }

    @Override
    protected Message findById(MessageService service, UUID id) {
        return service.findById(id);
    }

    @Override
    protected List<Message> findAllById(MessageService service, List<UUID> ids) {
        return service.findAllById(ids);
    }

    @Override
    protected List<Message> findAll(MessageService service) {
        return service.findAll();
    }

    @Override
    protected Message update(MessageService service, UUID id) {
        return service.update(id, "수정된 메세지 Content", null, null);
    }

    @Override
    protected void delete(MessageService service, UUID id) {
        service.delete(id);
    }
}


class UserTest extends TestCRUD<UserService, User> {
    UserTest(UserService userService) {
        super(userService);
    }

    @Override
    protected User create(UserService service, int index) {
        return service.create("유저 테스트" + index, "이미지Url", "이메일", "비번");
    }

    @Override
    protected UUID getId(User entity) {
        return entity.getId();
    }

    @Override
    protected User findById(UserService service, UUID id) {
        return service.findById(id);
    }

    @Override
    protected List<User> findAllById(UserService service, List<UUID> ids) {
        return service.findAllById(ids);
    }

    @Override
    protected List<User> findAll(UserService service) {
        return service.findAll();
    }

    @Override
    protected User update(UserService service, UUID id) {
        return service.update(id, "수정된 메세지 Content", "수정된Url", "수정된 이메일", "수정된 비번");
    }

    @Override
    protected void delete(UserService service, UUID id) {
        service.delete(id);
    }
}