package com.scg.file.controller;

import com.scg.file.exception.DownloadFailedException;
import org.junit.Test;

import java.nio.file.FileSystemNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by marcus on 7/12/2017 AD.
 */
public class FileControllerTest {


    @Test
    public void downloadFileShouldReturnExcel() throws Exception {
    }

    @Test(expected = DownloadFailedException.class)
    public void downloadFileFailShouldReturnDownloadFailedException() throws Exception {

    }

}