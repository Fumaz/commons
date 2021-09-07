package dev.fumaz.commons.localization;

import dev.fumaz.commons.collection.FStreams;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;

/**
 * A utility to work with jar resources
 *
 * @author Fumaz
 * @version 1.0
 * @since 1.0
 */
public final class Resources {

    private Resources() {
    }

    /**
     * Returns all names of resources in a specified path
     *
     * @param classLoader the {@link ClassLoader} to use
     * @param path        the resource path
     * @return a list of resource names
     */
    public static List<String> getResourcesInPath(ClassLoader classLoader, String path) {
        URL resourcePath = classLoader.getResource(path);

        if (resourcePath == null) {
            return Collections.emptyList(); // No resources in the path, because the path doesn't exist!
        }

        String jarPath = resourcePath.getPath().substring(5, resourcePath.getPath().indexOf("!")); // Strips out only the JAR file
        JarFile jarFile;

        try {
            jarFile = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException(e); // The jar file isn't accessible, this should *never* happen
        }

        Enumeration<JarEntry> entries = jarFile.entries();

        return FStreams.stream(entries)
                .map(ZipEntry::getName) // We only need the name of the resources
                .filter(name -> name.startsWith(path)) // We only care about resources in our path
                .filter(name -> name.charAt(name.length() - 1) != File.separatorChar) // Filter out directories
                .map(name -> name.substring(path.length() + 1)) // Map to the actual resource name without the path
                .filter(name -> name.matches(".*[a-zA-Z0-9.*]")) // Resource name *must* contain at least one character or number
                .collect(Collectors.toList());
    }

}
