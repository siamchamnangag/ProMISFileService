package com.scg.file.model;

/**
 * Created by tanatloke on 7/13/2017 AD.
 */
public class UploadDTO {

    private String description;
    private String filename;
    private byte[] content;

    public UploadDTO() {
    }

    public UploadDTO(String description, byte[] content, String filename) {
        this.description = description;
        this.content = content;
        this.filename = filename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
