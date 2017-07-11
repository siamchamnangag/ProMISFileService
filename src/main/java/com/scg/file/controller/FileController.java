package com.scg.file.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tanatloke on 7/12/2017.
 */
@RestController()
@RequestMapping("file")
public class FileController {

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    ResponseEntity test(){
        return new ResponseEntity("File Service", HttpStatus.OK);
    }

}
