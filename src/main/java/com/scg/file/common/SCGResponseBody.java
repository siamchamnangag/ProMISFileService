package com.scg.file.common;

import org.springframework.util.MultiValueMap;

/**
 * Created by marcus on 7/12/2017 AD.
 */
public class SCGResponseBody {

    private String message;

    public SCGResponseBody(String message) {
        this.message = message;
    }

    public SCGResponseBody() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
