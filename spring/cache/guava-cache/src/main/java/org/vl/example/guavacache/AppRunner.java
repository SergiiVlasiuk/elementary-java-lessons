package org.vl.example.guavacache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

  private final BookRepository bookRepository;

  public AppRunner(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    logger.info(".... Fetching books");
//    getFromRepositoryByIsbn(null);
//    getFromRepositoryByIsbn(null);
//    getFromRepositoryByIsbn("isbn-1234");
//    getFromRepositoryByIsbn("isbn-4567");
//    getFromRepositoryByIsbn("isbn-1234");
//    getFromRepositoryByIsbn("isbn-4567");
//    getFromRepositoryByIsbn("isbn-1234");
//    getFromRepositoryByIsbn("isbn-1234");
    logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234", "market"));
    logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234", "market"));
    simulateSlowService();
    simulateSlowService();
    logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234", "market"));
  }

  private void getFromRepositoryByIsbn(String s) {
//    simulateSlowService();
    logger.info("{} -->{}", s, bookRepository.getByIsbn(s));
  }

  // Don't do this at home
  public static void simulateSlowService() {
    try {
      long time = 3000L;
      Thread.sleep(time);
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }

}