package com.sprint.mission.discodeit.config;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SerializedFileStore {
    public static final Path DATA_HOME = Path.of(System.getProperty("user.dir"), "data");

    private SerializedFileStore() {
    }

    public static <T extends Serializable> T serialize(T object, Path path) {
        Path dir = createPath(path.getParent());
        Path temp = dir.resolve("temp_" + System.currentTimeMillis());

        try {
            try (FileOutputStream fos = new FileOutputStream(temp.toFile());
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(object);
            }
            Files.move(temp, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("직렬화 실패: " + path, e);
        }

        return object;
    }

    public static <T extends Serializable> T deserialize(Class<T> objectType, Path path) {
        try (FileInputStream fis = new FileInputStream(path.toFile());
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return objectType.cast(ois.readObject());
        } catch (NoSuchFileException | FileNotFoundException e) {
            return null;
        }
        catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("역직렬화 실패: " + path, e);
        }
    }

    public static List<Path> findAllByDirectory(Path dir) {
        try (Stream<Path> paths = Files.list(dir)) {
            return paths
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (NoSuchFileException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException("파일 목록 조회 실패: " + dir, e);
        }
    }

    public static void deleteFile(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException("파일 삭제 실패: " + path, e);
        }
    }

    private static Path createPath(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException("폴더 생성 실패: " + path, e);
        }
        return path;
    }
}
