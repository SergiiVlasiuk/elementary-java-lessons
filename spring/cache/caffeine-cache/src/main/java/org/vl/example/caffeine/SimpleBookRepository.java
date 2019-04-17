package org.vl.example.caffeine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.RandomStringUtils.random;

@Component
//@CacheConfig(cacheNames = "books")
@Slf4j
public class SimpleBookRepository implements BookRepository {

    @Autowired
    private SomeService someService;

    public Book getByIsbn(String isbn) {
//    reportCacheDetails.printCacheInfo();

        log.info("isbn: {}", isbn);
        someService.someImportantLogic();
        return new Book(isbn, random(10));
//    return null;
    }

//    //  @Cacheable(cacheNames = "books", key = "#isbn+'_'+#market", unless = "#result == null || #isbn == null || #market == null")
//    @Cacheable(value = "books", key = "#isbn+'_'+#market", unless = "#result == null || #isbn == null || #market == null")
    public Book getByIsbn(String isbn, String market) {
        log.info("isbn: {}", isbn);
        someService.someImportantLogic();
        return new Book(isbn, random(10) + '_' + market);
    }

//    @CacheEvict(cacheNames = {"books"}, allEntries = true)
    public void cleanCaches() {
        log.info("clean caches");
        someService.someImportantLogic();
    }

}
