package com.scg.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Arrays;

/**
 * Created by marcus on 7/12/2017 AD.
 */
@Service
public class FileService {

    @Value("${download.test.fileUrl}")
    private String testFileUrl;

    @Autowired
    RestTemplate restTemplate;



    public ResponseEntity<byte[]> downloadFile(String documentUrl){

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return restTemplate.exchange(
                documentUrl,
                HttpMethod.GET,
                entity,
                byte[].class,
                "1");
    }

}
