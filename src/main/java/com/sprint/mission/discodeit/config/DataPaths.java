package com.sprint.mission.discodeit.config;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import java.util.zip.CheckedInputStream;

public final class DataPaths {
    private static final Path DATA_HOME = Path.of(System.getProperty("user.dir"), "data");

    private static final String CHANNEL_FOLDER = "channels";
    private static final String MESSAGE_FOLDER = "messages";
    private static final String USER_FOLDER = "users";

    private DataPaths() {
    }

    public static File serializedFileName(Channel entity) throws IOException {
        return getFileName(entity, entity.getId());
    }

    public static File serializedFileName(Message entity) throws IOException {
        return getFileName(entity, entity.getId());
    }

    public static File serializedFileName(User entity) throws IOException {
        return getFileName(entity, entity.getId());
    }

    private static File getFileName(Object entity, UUID id) throws IOException {
        return createPath(DATA_HOME.resolve(entity.getClass().getSimpleName())).resolve(id + ".ser").toFile();
    }

    private static Path createPath(Path path) throws IOException {
        try {
            Files.createDirectories(path);
            System.out.println("폴더 생성");
        } catch (IOException e) {
            throw new IOException("폴더 생성 실패. path : " + path, e);
        }
        return path;
    }
}
