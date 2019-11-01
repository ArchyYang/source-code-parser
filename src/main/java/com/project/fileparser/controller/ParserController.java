package com.project.fileparser.controller;

import com.project.fileparser.serv.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api")
public class ParserController {

    @Autowired
    ParserService parserService;

    @GetMapping("/test")
    public String getQuestion() {
        return "hi";
    }

    @CrossOrigin
    @PostMapping("/upload")
    void getFile(@RequestParam("file") MultipartFile file) {
        try {
            parserService.getFileInput(file);
            System.out.println("hi");
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }


}
