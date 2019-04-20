package org.vl.example.guavacache;

import static org.vl.example.guavacache.AppRunner.simulateSlowService;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.vl.example.cacheinfo.details.ReportCacheDetails;

import java.util.UUID;

@Component
@Slf4j
public class SimpleBookRepository implements BookRepository {

  @Autowired
  private ReportCacheDetails reportCacheDetails;
  private @Autowired
  CacheManager manager;

  @Override
  public Book getByIsbn(String isbn) {
    log.info("isbn: {}   cache manager show caches {}. cache manager class: {}", isbn,
        manager.getCacheNames(), manager.getClass().getSimpleName());
    simulateSlowService();
    return new Book(isbn, UUID.randomUUID().toString());
  }

  @Override
  public Book getByIsbn(String isbn, String market) {
    log.info("isbn: {}", isbn);
    simulateSlowService();
    return new Book(isbn, UUID.randomUUID() + "  " + market);
  }

  @Override
  public void cleanCaches() {
    log.info("clean caches...");
  }

}
