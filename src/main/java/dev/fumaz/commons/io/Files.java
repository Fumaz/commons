package dev.fumaz.commons.io;

import dev.fumaz.commons.exception.Exceptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;

public class Files {

    public static void copyFileOrFolder(File source, File destination, CopyOption... options) {
        Exceptions.wrap(() -> {
            if (source.isDirectory()) {
                copyFolder(source, destination, options);
                return;
            }

            ensureParentFolder(destination);
            copyFileOrFolder(source, destination, options);
        });
    }

    public static void deleteDirectory(File directory) {
        File[] allContents = directory.listFiles();

        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }

        directory.delete();
    }

    private static void copyFolder(File source, File destination, CopyOption... options) throws IOException {
        destination.mkdirs();

        File[] contents = source.listFiles();

        if (contents == null) {
            return;
        }

        for (File f : contents) {
            File newFile = new File(destination.getAbsolutePath() + File.separator + f.getName());

            if (f.isDirectory()) {
                copyFolder(f, newFile, options);
                continue;
            }

            copyFile(f, newFile, options);
        }
    }

    private static void copyFile(File source, File destination, CopyOption... options) throws IOException {
        java.nio.file.Files.copy(source.toPath(), destination.toPath(), options);
    }

    private static void ensureParentFolder(File file) {
        File parent = file.getParentFile();

        if (parent == null) {
            return;
        }

        parent.mkdirs();
    }

}
