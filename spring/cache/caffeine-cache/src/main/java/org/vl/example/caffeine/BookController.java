package org.vl.example.caffeine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vl.example.cacheinfo.details.ReportCacheDetails;

@RestController
@RequestMapping("book")
@Slf4j
public class BookController {

  @Autowired
  private SimpleBookRepository repository;
  @Autowired
  private ReportCacheDetails reportCacheDetails;

  @GetMapping("{isbn}")
  Book getBook(@PathVariable String isbn) {
    log.info("controller: {}", isbn);
    reportCacheDetails.printCacheInfo();
    return repository.getByIsbn(isbn);
  }
}
