package com.scg.file.controller;

import com.scg.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity downloadFile(@RequestParam(required = true,value = "url") String documentUrl){
        //return file content
        return new ResponseEntity(fileService.downloadFile(documentUrl), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity uploadFile(){
        return new ResponseEntity("document uploaded", HttpStatus.OK);
    }



}
