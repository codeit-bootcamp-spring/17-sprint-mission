package com.sprint.mission.discodeit.config;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SerializedFileStore {
    private static final Path DATA_HOME = Path.of(System.getProperty("user.dir"), "data");

    private SerializedFileStore() {
    }

    public static <T extends Serializable> T serializeToFile(T object, String directoryName, Object key, String extension) {
        final Path dir = getDirectoryPath(directoryName);
        final Path file = dir.resolve(normalizeFileName(key, extension));

        createPath(dir);
        return serialize(object, file.toFile());
    }

    public static <T extends Serializable> T deserializeFromFile(Class<T> objectType, String directoryName, Object key, String extension) {
        final Path dir = getDirectoryPath(directoryName);
        final Path file = dir.resolve(normalizeFileName(key, extension));

        return deserialize(objectType, file.toFile());
    }

    public static <T extends Serializable> List<T> deserializeFromFile(Class<T> objectType, String directoryName, List<?> keys, String extension) {
        return keys.stream()
                .map(key -> getDirectoryPath(directoryName).resolve(normalizeFileName(key, extension)))
                .map(path -> deserialize(objectType, path.toFile()))
                .collect(Collectors.toList());
    }

    public static <T extends Serializable> List<T> deserializeAllFromDirectory(Class<T> objectType, String directoryName, String extension) {
        final Path dir = getDirectoryPath(directoryName);
        final String normalizedExtension = normalizeExtension(extension);

        try (Stream<Path> paths = Files.list(dir)) {
            return paths
                    .filter(path -> path.getFileName().toString().endsWith(normalizedExtension))
                    .filter(Files::isRegularFile)
                    .map(path -> deserialize(objectType, path.toFile()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("파일 목록 조회 실패: " + dir, e);
        }
    }

    public static void deleteFile(String directoryName, Object key, String extension) {
        final Path dir = getDirectoryPath(directoryName);
        final Path file = dir.resolve(normalizeFileName(key, extension));

        try {
            Files.delete(file);
        } catch (IOException e) {
            throw new RuntimeException("파일 삭제 실패: " + file, e);
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

    private static <T extends Serializable> T serialize(T object, File file) {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(object);
        } catch (IOException e) {
            throw new RuntimeException("직렬화 실패: " + file, e);
        }

        return object;
    }

    /// TODO : IO Exception 처리 방식 검토 필요
    private static <T extends Serializable> T deserialize(Class<T> objectType, File file) {
        T object;

        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            object = objectType.cast(ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("역직렬화 실패: " + file, e);
        }

        return object;
    }

    private static Path getDirectoryPath(String directoryName) {
        return DATA_HOME.resolve(directoryName);
    }

    private static String normalizeExtension(String extension) {
        if (extension.isEmpty() || extension.startsWith("."))
            return extension;

        return "." + extension;
    }

    private static String normalizeFileName(Object key, String extension) {
        return key.toString() + normalizeExtension(extension);
    }
}
