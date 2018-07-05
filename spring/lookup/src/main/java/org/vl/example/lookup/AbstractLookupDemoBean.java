package org.vl.example.lookup;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class AbstractLookupDemoBean implements DemoBean {

    @Lookup
    public SomethingUseful getMyHelper() {
//        return new SomethingUseful() {
//            @Override
//            public String doSomethingUsefully() {
//                return "lookup";
//            }
//        };
        return () -> "lookup";
    }

    public String someOperation() {
        return getMyHelper().doSomethingUsefully();
    }
}

