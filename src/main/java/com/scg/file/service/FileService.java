package com.scg.file.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by marcus on 7/12/2017 AD.
 */
@Service
public class FileService {

    @Value("${download.test.fileUrl}")
    private String testFileUrl;


    public byte[] downloadFile(String documentUrl){
        return documentUrl.getBytes();
    }

}
