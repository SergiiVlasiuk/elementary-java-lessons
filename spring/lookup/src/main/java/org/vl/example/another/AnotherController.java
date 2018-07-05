package org.vl.example.another;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnotherController {

    @GetMapping("/another/stub")
    public String handleBean() {
        return "stub";
    }
}
