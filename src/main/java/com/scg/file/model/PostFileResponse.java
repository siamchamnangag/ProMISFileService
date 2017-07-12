package com.scg.file.model;

/**
 * Created by marcus on 7/12/2017 AD.
 */
public class PostFileResponse {

    private String link;

    private String message;

    public PostFileResponse() {
    }

    public PostFileResponse(String link, String message) {
        this.link = link;
        this.message = message;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
