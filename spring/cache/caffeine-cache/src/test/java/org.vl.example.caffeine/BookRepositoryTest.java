package org.vl.example.caffeine;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Slf4j
public class BookRepositoryTest {

    @Configuration
    @Import({CacheJavaConfig.class})
    static class TestConfig {
        // this bean will be injected into the class
        @Bean
        public BookRepository bookRepository() {
            SimpleBookRepository bookRepository = new SimpleBookRepository();
            // set properties, etc.
            log.info("bookRepository: {}", bookRepository);
            return bookRepository;
        }
    }

    @MockBean
    private SomeService someService;
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void getByIsbn_singleCall_cacheWasNotUsed() {
        String isbn = randomAlphabetic(5);

        Book actual = bookRepository.getByIsbn(isbn);

        verify(someService).someImportantLogic();
    }

    @Test
    public void getByIsbn_fewTimesCall_cacheWasUsed() {
        log.info("bookRepository: {}", bookRepository);
        String isbn = randomAlphabetic(5);

        Book actual = null;
        int randomInt = new Random().nextInt(1);
        log.info("random integer: {}", randomInt);
        for (int i = randomInt + 2; i-- > 0; ) {
            Book temp = bookRepository.getByIsbn(isbn);
            if (actual == null) {
                assertThat(temp).isNotEqualTo(actual);
                actual = temp;
            } else {
                assertThat(temp).isEqualTo(actual);
            }
        }

        verify(someService).someImportantLogic();
    }

    @Test
    public void cleanCaches() {
        String isbn = randomAlphabetic(5);

        Book actual1 = bookRepository.getByIsbn(isbn);
        assertThat(actual1).isNotNull();

        verify(someService).someImportantLogic();

        Book actual2 = bookRepository.getByIsbn(isbn);

        assertThat(actual2).isEqualTo(actual1);
        verify(someService).someImportantLogic();

        bookRepository.cleanCaches();
        verify(someService, times(2)).someImportantLogic();

        actual2 = bookRepository.getByIsbn(isbn);

        assertThat(actual2).isNotEqualTo(actual1);
        verify(someService, times(3)).someImportantLogic();
    }
}