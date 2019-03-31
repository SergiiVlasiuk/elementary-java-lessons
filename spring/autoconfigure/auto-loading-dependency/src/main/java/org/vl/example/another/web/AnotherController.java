package org.vl.example.another.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("another")
@Slf4j
public class AnotherController {

    @GetMapping("{name}")
    String getAnother(@PathVariable String name) {
        return name;
    }
}
