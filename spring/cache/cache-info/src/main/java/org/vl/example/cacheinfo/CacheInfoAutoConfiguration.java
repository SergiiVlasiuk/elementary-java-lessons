package org.vl.example.cacheinfo;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.vl.example.cacheinfo.details.ReportCacheDetails;

@Configuration
@ConditionalOnBean(CacheManager.class)
@Import(ReportCacheDetails.class)
@ComponentScan("org.vl.example.cacheinfo.details")
@Slf4j
public class CacheInfoAutoConfiguration {

  @Autowired
  private ReportCacheDetails reportCacheDetails;

  @PostConstruct
  void postConstruct() {
    log.info("Auto configuration for CacheInfoAutoConfiguration is loaded...");
    reportCacheDetails.printCacheInfo();
  }

}
