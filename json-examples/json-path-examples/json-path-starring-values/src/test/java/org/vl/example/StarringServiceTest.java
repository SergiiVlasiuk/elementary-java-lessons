package org.vl.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
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
        String expected = "\"user\":{\"id\":2,\"role\":\"user\"}";

        String actual = testee.flexibleMaskingSensitiveData(expected);

        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }
}