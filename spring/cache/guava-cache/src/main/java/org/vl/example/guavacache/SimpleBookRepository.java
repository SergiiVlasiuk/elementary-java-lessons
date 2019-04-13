package org.vl.example.guavacache;

import static org.vl.example.guavacache.AppRunner.simulateSlowService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.vl.example.cacheinfo.details.ReportCacheDetails;

@Component
public class SimpleBookRepository implements BookRepository {

  private static final Logger logger = LoggerFactory.getLogger(SimpleBookRepository.class);

  @Autowired
  private ReportCacheDetails reportCacheDetails;
  private @Autowired
  CacheManager manager;

  @Override
  public Book getByIsbn(String isbn) {
    logger.info("isbn: {}   cache manager show caches {}. cache manager class: {}", isbn,
        manager.getCacheNames(), manager.getClass().getSimpleName());
    simulateSlowService();
    return new Book(isbn, "Some book");
  }

  @Override
  public Book getByIsbn(String isbn, String market) {
    logger.info("isbn: {}", isbn);
    simulateSlowService();
    return new Book(isbn, "Some book  " + market);
  }

}
