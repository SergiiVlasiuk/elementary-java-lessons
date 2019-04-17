package org.vl.example.caffeine;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames = "books")
public interface BookRepository {

    //  @Cacheable(value = "books", key = "#isbn", unless = "#result == null || #isbn == null")
    @Cacheable(key = "#isbn", unless = "#result == null || #isbn == null")
    Book getByIsbn(String isbn);

    //  @Cacheable(value = "books", key = "#isbn+'_'+#market", unless = "#result == null || #isbn == null || #market == null")
    @Cacheable(key = "#isbn+'_'+#market", unless = "#result == null || #isbn == null || #market == null")
    Book getByIsbn(String isbn, String market);

    //  @CacheEvict(cacheNames = {"books"}, allEntries = true)
    @CacheEvict(allEntries = true)
    void cleanCaches();
}