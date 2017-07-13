package com.scg.file.controller;

import com.scg.file.Application;
import com.scg.file.model.PostFileBody;
import com.scg.file.model.PostFileResponse;
import com.scg.file.service.FileService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.AssertionErrors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by tanatloke on 7/12/2017 AD.
 *
 * using rest controller test guide by josh long from http://spring.io/guides/tutorials/bookmarks/
 * middle-ground between unit testing and integration testing
 * start up Spring mvc system and run tests against them without actually starting up a real HTTP service
 */

@RunWith(SpringRunner.class)
//Load test context from Application.class
@SpringBootTest(classes = {Application.class,FileControllerTestConfig.class})
@WebAppConfiguration
public class FileControllerTest {

    //from @WebAppConfiguration
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    FileService fileService;

    private MockMvc mockMvc;

    final String NOT_EXIST_URL = "https://github.com/siamchamnangag/ProMISFileService/raw/master/src/main/resources/NotExist.xlsx";
    final String MALFORMED_PROTOCOL_URL = "wrongProtocol:notfoundAddress:-1";


        @Before
    public void setup() throws Exception {
        //initialize mock mvc
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void downloadFileShouldReturnExcel() throws Exception {

        File expectedFile = new File("src/test/resources/PMoC_complexity_and_effort_assessment.xlsx");
        int expectedFileSize = (int) expectedFile.length();

        byte[] expectedContent = Files.readAllBytes(Paths.get(expectedFile.getAbsolutePath()));

        mockMvc.perform(get("/files")
                .param("url","https://github.com/siamchamnangag/ProMISFileService/raw/master/src/main/resources/PMoC_complexity_and_effort_assessment.xlsx")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode", is("OK")))
                .andExpect(jsonPath("$.headers.Content-Type[0]", is("application/octet-stream") ))
                .andExpect(jsonPath("$.headers.Content-Length[0]", is(String.valueOf(expectedFileSize)) ))
                .andExpect(jsonPath("$.body", is(new String(Base64.getEncoder().encode(expectedContent))) ));
    }

    @Test()
    public void downloadWithNotExistUrlShouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/files")
                .param("url",NOT_EXIST_URL))
                .andExpect(status().isBadRequest());
    }

    @Test()
    public void downloadWithMalformedUrlShouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/files")
                .param("url",MALFORMED_PROTOCOL_URL))
                .andExpect(status().isBadRequest());
    }

    @Test()
    public void downloadWithoutRequiredParameterShouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/files"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void uploadShouldSuccessAndGetCorrectFileName() throws Exception {

        File testFile = new File("src/test/resources/PMoC_complexity_and_effort_assessment.xlsx");

        FileInputStream testFileStream = new FileInputStream(testFile);

        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "PMoC_complexity_and_effort_assessment.xlsx","multipart/form-data",testFileStream);

        mockMvc.perform(fileUpload("/files").file(mockMultipartFile)
                    .param("description","Test")
        ).andExpect(status().isCreated())
                    .andExpect(jsonPath("$.message", is("successfully created") ))
                    .andExpect(jsonPath("$.link", allOf(containsString("Test"),containsString(".xlsx"))) );

    }


    @Test
    public void uploadShouldFailWhenDescriptionContainsFail() throws Exception {

        File testFile = new File("src/test/resources/PMoC_complexity_and_effort_assessment.xlsx");

        FileInputStream testFileStream = new FileInputStream(testFile);

        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "PMoC_complexity_and_effort_assessment.xlsx","multipart/form-data",testFileStream);

        mockMvc.perform(fileUpload("/files").file(mockMultipartFile)
                    .param("description","fail").contentType("application/json")
            ).andExpect(status().isInternalServerError())
                    .andExpect(jsonPath("$.message", is("upload failed") ));


    }
   /* @Test(expected = DownloadFailedException.class)
    public void downloadFileFailShouldReturnDownloadFailedException() throws Exception {

    }*/

 /*

    //example for json based controller response test
    @Test
    public void example() throws Exception {
        mockMvc.perform(post("/george/bookmarks/")
                .content(this.json(new Bookmark()))
                .contentType(contentType))
                .andExpect(status().isNotFound());

    }*/


}