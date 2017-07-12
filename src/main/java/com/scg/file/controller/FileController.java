package com.scg.file.controller;

import com.scg.file.common.SCGResponseBody;
import com.scg.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by tanatloke on 7/12/2017.
 */
@RestController()
@RequestMapping("files")
public class FileController {

    @Autowired
    FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    /*
    accept file url and return downloaded content as byte[]
     */
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity downloadFile(@RequestParam(required = true,value = "url") String documentUrl){
        //return file content
        return fileService.downloadFile(documentUrl);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity uploadFile(@RequestParam("file") MultipartFile file){

        ResponseEntity response;

        try{

            response = new ResponseEntity(fileService.uploadFile(file), HttpStatus.CREATED);

        }catch (IOException ioException){

            response = new ResponseEntity(new SCGResponseBody("upload failed"),HttpStatus.INTERNAL_SERVER_ERROR);

        }catch(Exception ex){

            response = new ResponseEntity(new SCGResponseBody(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return response;
    }



}
