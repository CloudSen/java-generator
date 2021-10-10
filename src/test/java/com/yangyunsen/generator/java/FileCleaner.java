package com.yangyunsen.generator.java;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * 文件清理
 *
 * @author CloudS3n
 * @date 2021-10-08 11:27
 */
public interface FileCleaner {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    static void deleteFiles(Set<String> filePaths) {
        assertDoesNotThrow(() -> {
            for (String pathStr : filePaths) {
                URI uri = new URI("file:" + pathStr);
                Path path = Paths.get(uri);
                while (true) {
                    Path parentPath = path.getParent();
                    if (parentPath.endsWith("java")) {
                        break;
                    }
                    path = parentPath;
                }
                if (Files.exists(path) && Files.isDirectory(path)) {
                    Files.walk(path)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
                    System.out.println("删除目录和内容 => " + path);
                }
            }
        });
    }
}
