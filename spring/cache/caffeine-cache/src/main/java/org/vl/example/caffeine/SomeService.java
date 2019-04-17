package org.vl.example.caffeine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SomeService {

    void someImportantLogic() {
        log.info("you've triggered some search logic");
    }
}
