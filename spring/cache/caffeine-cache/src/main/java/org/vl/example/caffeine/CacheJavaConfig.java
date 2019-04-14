package org.vl.example.caffeine;

import static java.lang.System.out;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.CaffeineSpec;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheJavaConfig {

  /**
   * initialCapacity maximumSize maximumWeight expireAfterAccess expireAfterWrite refreshAfterWrite
   * weakKeys weakValues softValues recordStats
   */
  @Bean
  public CacheManager cacheManager() {
    String specAsString = "initialCapacity=100,maximumSize=500,expireAfterAccess=5m,recordStats";
//    CaffeineCacheManager cacheManager = new CaffeineCacheManager("AIRCRAFTS", "SECOND_CACHE");
    CaffeineCacheManager cacheManager = new CaffeineCacheManager("books");
    cacheManager.setAllowNullValues(
        false); //can happen if you get a value from a @Cachable that returns null
    //cacheManager.setCacheSpecification(specAsString);
    //cacheManager.setCaffeineSpec(caffeineSpec());
    cacheManager.setCaffeine(caffeineCacheBuilder());
    return cacheManager;
  }

  CaffeineSpec caffeineSpec() {
    return CaffeineSpec.parse
        ("initialCapacity=100,maximumSize=500,expireAfterAccess=5m,recordStats");
  }

  Caffeine<Object, Object> caffeineCacheBuilder() {
    return Caffeine.newBuilder()
        .initialCapacity(100)
        .maximumSize(150)
        .expireAfterAccess(5, TimeUnit.MINUTES)
        .weakKeys()
        .removalListener(new CustomRemovalListener())
        .recordStats();
  }

  class CustomRemovalListener implements RemovalListener<Object, Object> {

    @Override
    public void onRemoval(Object key, Object value, RemovalCause cause) {
      out.format("removal listener called with key [%s], cause [%s], evicted [%S], value [%s]\n",
          key, cause.toString(), cause.wasEvicted(), value);
    }
  }

}
