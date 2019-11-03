package com.project.fileparser.controller;

import com.project.fileparser.modal.ParsedComment;
import com.project.fileparser.serv.ParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api")
public class ParserController {

    private static final Logger logger = LoggerFactory.getLogger(ParserController.class);

    @Autowired
    ParserService parserService;

    @CrossOrigin
    @PostMapping("/upload")
    public  @ResponseBody ResponseEntity getFile(@RequestParam("file") MultipartFile file) {
        try {
            ParsedComment result = parserService.getFileInput(file);
            return ResponseEntity.ok().body(result);
        }
        catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }


}
