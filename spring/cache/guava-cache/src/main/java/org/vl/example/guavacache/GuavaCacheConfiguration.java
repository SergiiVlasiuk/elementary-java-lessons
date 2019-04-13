package org.vl.example.guavacache;

import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class GuavaCacheConfiguration implements CachingConfigurer {

  @Override
  public CacheManager cacheManager() {
    return new ConcurrentMapCacheManager() {
      @Override
      protected Cache createConcurrentMapCache(final String name) {
        return new ConcurrentMapCache(name,
            CacheBuilder.newBuilder().expireAfterWrite(4, TimeUnit.SECONDS)
                .maximumSize(100).build().asMap(), false);
      }
    };
  }

  @Override
  public CacheResolver cacheResolver() {
    return null;
  }

  @Override
  public KeyGenerator keyGenerator() {
    return null;
  }

  @Override
  public CacheErrorHandler errorHandler() {
    return null;
  }

  /*
    @Override
  public CacheResolver cacheResolver() {
    return new SimpleCacheResolver();
  }

  @Override
  public KeyGenerator keyGenerator() {
    return new SimpleKeyGenerator();
  }

  @Override
  public CacheErrorHandler errorHandler() {
    return new SimpleCacheErrorHandler();
  }
   */
}
