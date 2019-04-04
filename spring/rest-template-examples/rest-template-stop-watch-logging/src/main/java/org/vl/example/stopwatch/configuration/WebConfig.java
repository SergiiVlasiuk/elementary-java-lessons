package org.vl.example.stopwatch.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.vl.example.stopwatch.web.interceptor.RestApiStopWatchLoggingInterceptor;

@Configuration
@ServletComponentScan("org.vl.example.stopwatch.web")
public class WebConfig implements WebMvcConfigurer {

  @Autowired
  private RestApiStopWatchLoggingInterceptor restApiStopWatchLoggingInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(restApiStopWatchLoggingInterceptor);
  }

}
