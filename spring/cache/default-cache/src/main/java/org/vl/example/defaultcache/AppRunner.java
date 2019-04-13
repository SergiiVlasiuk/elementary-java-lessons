package org.vl.example.defaultcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.vl.example.cacheinfo.details.ReportCacheDetails;

@Component
public class AppRunner implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

  private final BookRepository bookRepository;
  private final ReportCacheDetails reportCacheDetails;

  public AppRunner(BookRepository bookRepository,
      ReportCacheDetails reportCacheDetails) {
    this.bookRepository = bookRepository;
    this.reportCacheDetails = reportCacheDetails;
  }

  @Override
  public void run(String... args) throws Exception {
    logger.info(".... Fetching books");
    getFromRepositoryAndLog("null -->", null);
    getFromRepositoryAndLog("null -->", null);
    getFromRepositoryAndLog("isbn-1234 -->", "isbn-1234");
    getFromRepositoryAndLog("isbn-4567 -->", "isbn-4567");
    getFromRepositoryAndLog("isbn-1234 -->", "isbn-1234");
    getFromRepositoryAndLog("isbn-4567 -->", "isbn-4567");
    getFromRepositoryAndLog("isbn-1234 -->", "isbn-1234");
    getFromRepositoryAndLog("isbn-1234 -->", "isbn-1234");
    reportCacheDetails.printCacheInfo();
    logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234", "market"));
    reportCacheDetails.printCacheInfo();
    logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234", "market"));
    reportCacheDetails.printCacheInfo();
    logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234", null));
    reportCacheDetails.printCacheInfo();
    logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234", null));
    reportCacheDetails.printCacheInfo();
  }

  private void getFromRepositoryAndLog(String s, String o) {
    reportCacheDetails.printCacheInfo();
    logger.info(s + bookRepository.getByIsbn(o));
  }

  // Don't do this at home
  public static void simulateSlowService() {
    try {
      long time = 1000L;
      Thread.sleep(time);
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }

}