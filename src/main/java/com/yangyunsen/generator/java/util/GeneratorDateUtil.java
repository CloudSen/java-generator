package com.yangyunsen.generator.java.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author CloudS3n
 * @date 2021-09-30 14:59
 */
public class GeneratorDateUtil {

    public static String getCommentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
