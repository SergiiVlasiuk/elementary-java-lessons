package org.vl.example.jks.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello trusted World!! " + new Date();
    }

}
