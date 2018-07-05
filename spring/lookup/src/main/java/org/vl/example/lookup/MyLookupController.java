package org.vl.example.lookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vl.example.dto.LookupDto;

@RestController
public class MyLookupController {

    @Autowired
    private MyHelper myHelper;
    @Autowired
    private AbstractLookupDemoBean abstractLookupDemoBean;

    // returns singleton - no prototype result here
    @GetMapping("/lookup/bean")
    public String handleBean() {
        return myHelper.doSomethingUsefully();
    }

    // returns each time different object (prototype behavior)
    @GetMapping("/lookup/lookup")
    public String handleLookup() {
        return abstractLookupDemoBean.someOperation();
    }

    // returns custom dto implementation (because DTO is non spring controlled object)
    @GetMapping("/lookup/viaDto")
    public String handleDto() {
        return new LookupDto().getMyHelper().doSomethingUsefully();
    }

}
