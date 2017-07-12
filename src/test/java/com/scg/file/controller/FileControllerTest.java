package com.scg.file.controller;

import ch.qos.logback.core.util.FileUtil;
import com.scg.file.exception.DownloadFailedException;
import com.scg.file.service.FileService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.nio.file.FileSystemNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by marcus on 7/12/2017 AD.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class FileControllerTest {

//    @Value("${download.test.fileUrl}")
//    private String testFileUrl;

    FileController fileController;

    @Before
    public void setUp() throws Exception {
        fileController = new FileController(new FileService());
    }

    @Test
    public void downloadFileShouldReturnExcel() throws Exception {

        File expectedFile = new File("PMoC_complexity_and_effort_assessment.xlsx");
        assertNotNull(expectedFile);

        //expectedFileResponseEntity response = fileController.downloadFile("PMoC_complexity_and_effort_assessment.xlsx");


    }

    @Test(expected = DownloadFailedException.class)
    public void downloadFileFailShouldReturnDownloadFailedException() throws Exception {

    }

}