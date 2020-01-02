package org.vl.example;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class StarringServiceTest {

    @InjectMocks
    private StarringService testee;


    @Test(expected = IllegalArgumentException.class)
    public void maskSensitiveData_emptyString() {
        testee.maskSensitiveData("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void maskSensitiveData_nullString() {
        testee.maskSensitiveData(null);
    }

    @Test
    public void maskSensitiveData_emailIsHidden() {
        String json = "{\"name\":\"anyName\",\"email\":\"any@ukr.net\"}";
        String expected = "{\"name\":\"anyName\",\"email\":\"***starred***\"}";

        String actual = testee.maskSensitiveData(json);

        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void maskSensitiveData_correctJsonButNoMatching() {
        String expected = "{\"user\":{\"id\":2,\"role\":\"user\"}}";

        String actual = testee.maskSensitiveData(expected);

        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void flexibleMaskingSensitiveData() {
        String json = "{\"name\":\"anyName\",\"email\":\"any@ukr.net\",\"colleagues\":["
                + "{\"id\":1, \"email\":\"tor@ukr.net\"}"
                + ",{\"id\":2, \"email\":\"taras@ukr.net\"}"
                + ",{\"id\":3, \"email\":\"kvitka_long_email@ukr.net\"}"
                + "]}";
        String expected = "{\"name\":\"anyName\",\"email\":\"***********\",\"colleagues\":["
                + "{\"id\":1, \"email\":\"***********\"}"
                + ",{\"id\":2, \"email\":\"*************\"}"
                + ",{\"id\":3, \"email\":\"*************************\"}"
                + "]}";

        String actual = testee.flexibleMaskingSensitiveData(json);

        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void flexibleMaskingSensitiveData_correctJsonButNoMatching() {
        String expected = "{\"user\":{\"id\":2,\"role\":\"user\"}}";

        String actual = testee.flexibleMaskingSensitiveData(expected);

        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void exampleToReplaceSingleElement() {
        String json = "{\n" +
                "  \"Added\": {\n" +
                "    \"type\": \"K\",\n" +
                "    \"newmem\": {\n" +
                "      \"IDNew\": {\n" +
                "        \"id\": \"777709\",\n" +
                "        \"type\": \"LOP\"\n" +
                "      },\n" +
                "      \"birthDate\": \"2000-12-09\"\n" +
                "    },\n" +
                "    \"code\": \"\",\n" +
                "    \"newest\": {\n" +
                "      \"curlNew\": \"\",\n" +
                "      \"addedForNew\": \"\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        String expectedId = "12345678";
        String expectedJson = "{\n" +
                "  \"Added\": {\n" +
                "    \"type\": \"K\",\n" +
                "    \"newmem\": {\n" +
                "      \"IDNew\": {\n" +
                "        \"id\": \"12345678\",\n" +
                "        \"type\": \"LOP\"\n" +
                "      },\n" +
                "      \"birthDate\": \"2000-12-09\"\n" +
                "    },\n" +
                "    \"code\": \"\",\n" +
                "    \"newest\": {\n" +
                "      \"curlNew\": \"\",\n" +
                "      \"addedForNew\": \"\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        Configuration configuration = Configuration
                .builder()
                .options(Option.SUPPRESS_EXCEPTIONS)
                .build();
        DocumentContext parsed = JsonPath.using(configuration).parse(json);

        parsed.set("$.Added.newmem.IDNew.id", expectedId);

        String actual = parsed.jsonString();
        log.info("After ID value updated: {}", actual);
        assertThat(actual).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    public void exampleToReplaceSingleElement_jsonTakenFromFile() throws IOException {
        String expectedId = "12345678";
        String expectedJson = "{\n" +
                "  \"Added\": {\n" +
                "    \"type\": \"K\",\n" +
                "    \"newmem\": {\n" +
                "      \"IDNew\": {\n" +
                "        \"id\": \"12345678\",\n" +
                "        \"type\": \"LOP\"\n" +
                "      },\n" +
                "      \"birthDate\": \"2000-12-09\"\n" +
                "    },\n" +
                "    \"code\": \"\",\n" +
                "    \"newest\": {\n" +
                "      \"curlNew\": \"\",\n" +
                "      \"addedForNew\": \"\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        Configuration configuration = Configuration
                .builder()
                .options(Option.SUPPRESS_EXCEPTIONS)
                .build();
        File json = new File("src/test/resources/test.json");
        System.out.println(json.getAbsolutePath());
        DocumentContext parsed = JsonPath.using(configuration).parse(json);

        parsed.set("$.Added.newmem.IDNew.id", expectedId);
        String actual = parsed.jsonString();

        log.info("After ID value updated: {}", actual);
        assertThat(actual).isEqualToIgnoringWhitespace(expectedJson);
    }
}