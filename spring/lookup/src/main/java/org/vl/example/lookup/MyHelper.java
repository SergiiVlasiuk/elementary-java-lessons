package org.vl.example.lookup;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MyHelper implements SomethingUseful {
    private String v = "hey from prototype.<br/>";

    public String doSomethingUsefully() {
        System.out.println(v);
        return v + this.toString();
    }
}
