package com.sprint.mission.discodeit.service.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IOUtils {

    public static void init(Path directory) {
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Path getFilePath(Path directory, UUID id) {
        return directory.resolve(id + ".ser");
    }

    public static <T extends Serializable> void save(Path filePath, T data){
        try(FileOutputStream fos = new FileOutputStream(filePath.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){
            oos.writeObject(data);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> load(Path directory, Class<T> type){
        if(Files.exists(directory)){
            try(var paths = Files.list(directory)) {
                List<T> list = paths
                        .map(path -> {
                            try (
                                    FileInputStream fis = new FileInputStream(path.toFile());
                                    ObjectInputStream ois = new ObjectInputStream(fis)
                            ) {
                                Object data = ois.readObject();
                                return type.cast(data);
                            } catch (IOException | ClassNotFoundException e){
                                throw new RuntimeException(e);
                            }
                        })
                        .toList();
                return list;
            }catch(IOException e){
                throw new RuntimeException(e);
            }
        }else{
            return new ArrayList<>();
        }
    }

    public static <T> T loadOne(Path filePath, Class<T> type){
        if(!Files.exists(filePath)){
            return null;
        }
        try (FileInputStream fis = new FileInputStream(filePath.toFile());
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return type.cast(ois.readObject());

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public static void delete(Path filePath){
        try{
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
