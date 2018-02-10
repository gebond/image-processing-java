package com.gebond.ip.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Gleb on 16.01.2018.
 */
@RestController()
@RequestMapping("/api")
public class ImageProcessingController {

    private static final Logger LOG = LoggerFactory.getLogger(ImageProcessingController.class);

    public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";

    @RequestMapping(path = "/hello")
    public @ResponseBody
    String sayHello() {
        LOG.info("GET called on /hello resource");
        return HELLO_TEXT;
    }
}
