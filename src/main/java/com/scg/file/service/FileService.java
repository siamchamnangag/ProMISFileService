package com.scg.file.service;

import com.scg.file.common.SCGResponseBody;
import com.scg.file.model.PostFileBody;
import com.scg.file.model.PostFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by marcus on 7/12/2017 AD.
 */
@Service
public class FileService {

    @Value("${upload.postFileUrl}")
    private String postFileUrl;

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<?> downloadFile(String documentUrl){

        //supply required header for request if required
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
//
//        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<?> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(restTemplate.getForEntity(
                    documentUrl, byte[].class),HttpStatus.OK);
        }catch (Exception ex){
            responseEntity = new ResponseEntity<>(new SCGResponseBody(ex.getMessage()),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    public PostFileResponse uploadFile(MultipartFile uploadingFile) throws IOException{

        //create body
        PostFileBody postBody = new PostFileBody(uploadingFile.getOriginalFilename(),uploadingFile.getBytes());
        //upload file to external service
        HttpEntity<PostFileBody> requestEntity = new HttpEntity<>(postBody);
        System.out.println("post file url = "+postFileUrl);
        return restTemplate.postForObject(postFileUrl, requestEntity, PostFileResponse.class);
        //POST "http://document-api.cloudhub.io/mock/v1/sap/file"
    }


}
