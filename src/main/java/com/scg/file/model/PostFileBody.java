package com.scg.file.model;

/**
 * Created by marcus on 7/12/2017 AD.
 */
public class PostFileBody {

    private String filename;

    private byte[] content;

    public PostFileBody() {
    }

    public PostFileBody(String filename, byte[] content) {
        this.filename = filename;
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
