package com.youjia.system.youplus.global.exception;


/**
 * @author wuweifeng wrote on 2017/10/27.
 */
public class NoLoginException extends RuntimeException {

    public NoLoginException() {
        super();
    }

    public NoLoginException(String message) {
        super(message);
    }
}
