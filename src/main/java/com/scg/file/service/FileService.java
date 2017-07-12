package com.scg.file.service;

import org.springframework.stereotype.Service;

/**
 * Created by marcus on 7/12/2017 AD.
 */
@Service
public class FileService {

    public byte[] downloadFile(String documentUrl){
        return documentUrl.getBytes();
    }

}
