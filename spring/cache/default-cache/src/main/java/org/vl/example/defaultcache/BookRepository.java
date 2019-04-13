package org.vl.example.defaultcache;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames = "books")
public interface BookRepository {

  @Cacheable(unless = "#result == null || #isbn == null")
  Book getByIsbn(String isbn);

  @Cacheable(key = "#isbn+'_'+#market", unless = "#result == null || #isbn == null || #market == null")
  Book getByIsbn(String isbn, String market);

}