package org.vl.example;

import com.jayway.jsonpath.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.Optional.ofNullable;

public class StarringService {

    private static final Configuration CONFIGURATION = Configuration
            .builder()
//            .jsonProvider(new JacksonJsonNodeJsonProvider())
//            .mappingProvider(new JacksonMappingProvider())
            .options(
                    newHashSet(
//                            Option.DEFAULT_PATH_LEAF_TO_NULL,
                            Option.SUPPRESS_EXCEPTIONS)
            )
            .build();

    private static final List<String> PATHS_TO_REPLACE = newArrayList(
            "$.email",
            "$.colleagues.[*].email"
    );

    public String maskSensitiveData(String asJson) {
        DocumentContext parsed = JsonPath.using(CONFIGURATION).parse(asJson);
        PATHS_TO_REPLACE.forEach(path -> parsed.set(path, "***starred***"));
        return parsed.jsonString();
    }

    public String flexibleMaskingSensitiveData(String asJson) {
        DocumentContext parsed = JsonPath.using(CONFIGURATION).parse(asJson);
        PATHS_TO_REPLACE.forEach(path -> parsed.map(path,
                (currentValue, conf) -> starringCurrentValue(currentValue)));
        return parsed.jsonString();
    }

    private Object starringCurrentValue(Object currentValue) {
        return ofNullable(currentValue)
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .map(String::length)
                .map(times -> StringUtils.repeat('*', times))
                .orElse("");
    }
}
