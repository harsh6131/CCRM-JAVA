package edu.ccrm.util;

import java.nio.file.*;
import java.io.IOException;

public class BackupUtility {

    public static void backupDirectory(Path sourceDirectory, Path destinationDirectory) throws IOException {
        if (!Files.exists(sourceDirectory) || !Files.isDirectory(sourceDirectory))
            throw new IOException("Source not found or not a directory: " + sourceDirectory);

        // Ensure destination exists
        if (!Files.exists(destinationDirectory)) {
            Files.createDirectories(destinationDirectory);
        }

        // Use Files.walk for recursive traversal
        Files.walk(sourceDirectory)
                .forEach(currentSourcePath -> {
                    try {
                        Path relativePath = sourceDirectory.relativize(currentSourcePath);
                        Path targetPath = destinationDirectory.resolve(relativePath);
                        if (Files.isDirectory(currentSourcePath)) {
                            if (!Files.exists(targetPath))
                                Files.createDirectories(targetPath);
                        } else {
                            Files.copy(currentSourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                        }
                    } catch (IOException exception) {
                        System.err.println("Failed to copy: " + currentSourcePath + " - " + exception.getMessage());
                    }
                });
    }
}