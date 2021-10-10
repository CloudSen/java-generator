package com.yangyunsen.generator.java;

import com.yangyunsen.generator.java.common.model.statics.CommonStatic;
import org.apache.commons.lang3.SystemUtils;

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
                URI uri = SystemUtils.IS_OS_WINDOWS ?
                    new URI("file:/" + CommonStatic.JAVA_PATH.replaceAll("\\\\", CommonStatic.SLASH) + pathStr)
                    : new URI("file:" + CommonStatic.JAVA_PATH + pathStr);
                Path path = Paths.get(uri);
                if (Files.isDirectory(path)) {
                    Files.walk(path)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
                }
            }
        });
    }
}
