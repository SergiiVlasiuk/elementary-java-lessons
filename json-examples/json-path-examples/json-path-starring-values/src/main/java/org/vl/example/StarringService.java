package org.vl.example;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import org.apache.commons.lang3.StringUtils;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Optional.ofNullable;

import java.util.List;

public class StarringService {

    private static final Configuration CONFIGURATION = Configuration
            .builder()
            .options(Option.SUPPRESS_EXCEPTIONS)
            .jsonProvider(new JacksonJsonNodeJsonProvider())
            .mappingProvider(new JacksonMappingProvider())
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
        PATHS_TO_REPLACE.forEach(path -> parsed.map(path, (currentValue, conf) -> starringCurrentValue(currentValue)));
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
