package com.yangyunsen.generator.java.common;

import lombok.NoArgsConstructor;

/**
 * @author clouds3n
 * @date 2021-09-24
 */
@NoArgsConstructor
public class GeneratorException extends RuntimeException{

    public GeneratorException(String message) {
        super(message);
    }

    public GeneratorException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneratorException(Throwable cause) {
        super(cause);
    }
}
