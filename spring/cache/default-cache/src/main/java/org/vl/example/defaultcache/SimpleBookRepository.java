package org.vl.example.defaultcache;

import static org.vl.example.defaultcache.AppRunner.simulateSlowService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vl.example.cacheinfo.details.ReportCacheDetails;

@Component
public class SimpleBookRepository implements BookRepository {

  private static final Logger logger = LoggerFactory.getLogger(SimpleBookRepository.class);



  @Override
  public Book getByIsbn(String isbn) {
//    reportCacheDetails.printCacheInfo();

    logger.info("isbn: {}", isbn);
    simulateSlowService();
    return new Book(isbn, "Some book");
//    return null;
  }

  @Override
  public Book getByIsbn(String isbn, String market) {
    logger.info("isbn: {}", isbn);
    simulateSlowService();
    return new Book(isbn, "Some book  " + market);
  }


}
