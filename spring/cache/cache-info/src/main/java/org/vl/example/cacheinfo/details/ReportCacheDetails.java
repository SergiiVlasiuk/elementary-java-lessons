package org.vl.example.cacheinfo.details;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReportCacheDetails {

  private @Autowired
  CacheManager manager;

  public void printCacheInfo() {
    log.info("============ cache manager show caches {}. cache manager class: {}", manager.getCacheNames(),
        manager.getClass().getSimpleName());
    manager.getCacheNames().forEach(name -> {
      Cache cache = manager.getCache(name);
      log.info("cache <{}> info  class: {}", name, cache.getClass().getSimpleName());
      log.info("cache: {}", new Gson().toJson(cache));
    });
  }
}
