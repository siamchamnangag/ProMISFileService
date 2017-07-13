package com.scg.file.controller;

import com.scg.file.model.PostFileBody;
import com.scg.file.model.PostFileResponse;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tanatloke on 7/13/2017 AD.
 */
@Configuration
public class FileControllerTestConfig {

    @RestController
    class MockExternalFileService {

        @RequestMapping(value = "/mock/upload", method = RequestMethod.POST)
        ResponseEntity mockExternalUploadFile() {
            {
                return new ResponseEntity(new PostFileResponse("Test.xlsx","successfully created"), HttpStatus.OK);
            }
        }
    }
}
