package org.vl.example.caffeine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
//@CacheConfig(cacheNames = "books")
public class SimpleBookRepository {

  private static final Logger logger = LoggerFactory.getLogger(SimpleBookRepository.class);


//  @Cacheable(unless = "#result == null || #isbn == null")
  @CachePut(cacheNames = "books", key = "#isbn")
  public Book getByIsbn(String isbn) {
//    reportCacheDetails.printCacheInfo();

    logger.info("isbn: {}", isbn);
    return new Book(isbn, "Some book");
//    return null;
  }

  @Cacheable(cacheNames = "books", key = "#isbn+'_'+#market", unless = "#result == null || #isbn == null || #market == null")
  public Book getByIsbn(String isbn, String market) {
    logger.info("isbn: {}", isbn);
    return new Book(isbn, "Some book  " + market);
  }

}
