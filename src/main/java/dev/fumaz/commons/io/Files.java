package dev.fumaz.commons.io;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Files {

    public static void copyFolder(Path source, Path destination) {
        try (Stream<Path> stream = java.nio.file.Files.walk(source)) {
            stream.forEach(file -> copyFile(file, destination.resolve(file.relativize(file))));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static void copyFile(Path source, Path destination) {
        try {
            java.nio.file.Files.copy(source, destination, REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
