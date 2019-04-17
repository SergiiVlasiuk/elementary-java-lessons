package org.vl.example.caffeine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book")
@Slf4j
public class BookController {

    @Autowired
    private BookRepository repository;

    @GetMapping("tocache/{isbn}")
    Book getBook(@PathVariable String isbn) {
        log.info("controller loads data by: {}", isbn);
//    reportCacheDetails.printCacheInfo();
        return repository.getByIsbn(isbn);
    }

    @GetMapping("clean")
    String getBook() {
        log.info("controller: clean caches");
//    reportCacheDetails.printCacheInfo();
        repository.cleanCaches();
        return "CLEANED";
    }
}
