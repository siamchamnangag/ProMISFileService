package com.scg.file.service;

import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by marcus on 7/13/2017 AD.
 */
public class FileServiceTest {

    private FileService fileService;

    @Before
    public void setUp() throws Exception {
        fileService = new FileService();
    }

    @Test
    public void generateFileNameFromDescription_should_return_xlsx_when_excel_file() {
        assertEquals(fileService.getFileExtensionFromStringFileName("PMoC_complexity_and_effort_assessment.xlsx"),
                "xlsx");
    }

    @Test
    public void generateFileNameFromDescription_should_return_txt_when_text_file() {
        assertEquals(fileService.getFileExtensionFromStringFileName("PMoC_complexity_and_effort_assessment.txt"),
                "txt");
    }

    @Test
    public void generateFileNameFromDescription_should_return_last_ext_when_multiple_dots() {
        assertEquals(fileService.getFileExtensionFromStringFileName("dummy.xxx.ext"),
                "ext");
    }

    @Test
    public void generateFileNameWithXlsxShouldReturnCorrectFormat() throws ParseException {

        String inputString = "20170713102000";
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date inputDate = dateFormat.parse(inputString);

        assertEquals(fileService.generateFileName("PMoC_complexity_and_effort_assessment.xlsx", "PMoC", inputDate),
                "PMoC_"+inputString+".xlsx");
    }

    @Test
    public void generateFileNameWithTextShouldReturnCorrectFormat() throws ParseException {

        String inputString = "20170713102359";
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date inputDate = dateFormat.parse(inputString);

        assertEquals(fileService.generateFileName("dummy_file.txt", "Test", inputDate),
                "Test_"+inputString+".txt");
    }
}