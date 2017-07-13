package com.scg.file.service;

import com.scg.file.common.SCGResponseBody;
import com.scg.file.model.PostFileBody;
import com.scg.file.model.PostFileResponse;
import com.scg.file.model.UploadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by marcus on 7/12/2017 AD.
 */
@Service
public class FileService {

    @Value("${upload.postFileUrl}")
    private String postFileServiceURL;

    public String getPostFileServiceURL() {
        return postFileServiceURL;
    }

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

    public  ResponseEntity<PostFileResponse> uploadFile(MultipartFile uploadingFile, String description) throws IOException{

        //create file name from description
        String generatedFileName = generateFileName(uploadingFile.getOriginalFilename(), description, new Date());

        PostFileBody postBody = new PostFileBody(generatedFileName, uploadingFile.getBytes());

        //upload file to external service
        HttpEntity<PostFileBody> requestEntity = new HttpEntity<>(postBody);

        try{
            return new ResponseEntity<PostFileResponse>(restTemplate.postForObject(postFileServiceURL, requestEntity, PostFileResponse.class),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<PostFileResponse>(new PostFileResponse("","upload failed"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity uploadFile(UploadDTO uploadingBody) {
        //create file name from description
        String generatedFileName = generateFileName(uploadingBody.getFilename(), uploadingBody.getDescription(), new Date());
        PostFileBody postBody = new PostFileBody(generatedFileName, uploadingBody.getContent());

        //upload file to external service
        HttpEntity<PostFileBody> requestEntity = new HttpEntity<>(postBody);

        try{
            return new ResponseEntity<PostFileResponse>(restTemplate.postForObject(postFileServiceURL, requestEntity, PostFileResponse.class),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<PostFileResponse>(new PostFileResponse("","upload failed"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String generateFileName(String originalFilename, String description,Date date) {
        String fileExtension = getFileExtensionFromStringFileName(originalFilename);

        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStamp = df.format(date).toString();
        return description+"_"+timeStamp+"."+fileExtension;
    }


    public String getFileExtensionFromStringFileName(String originalFilename) {
        String extension = "";

        int i = originalFilename.lastIndexOf('.');
        if (i > 0) {
            extension = originalFilename.substring(i+1);
        }

        return extension;
    }


}
