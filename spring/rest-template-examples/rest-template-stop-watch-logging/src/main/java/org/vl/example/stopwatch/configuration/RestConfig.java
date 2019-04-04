package org.vl.example.stopwatch.configuration;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;
import org.vl.example.stopwatch.logging.RestTemplateGoogleStopWatchLoggingInterceptor;
import org.vl.example.stopwatch.logging.RestTemplateSpringStopWatchLoggingInterceptor;

@Configuration
@ComponentScan({
    "org.vl.example.stopwatch.logging"
})
public class RestConfig {


  @Bean
  RestTemplate restTemplate(
      RestTemplateSpringStopWatchLoggingInterceptor restTemplateSpringStopWatchLoggingInterceptor,
      RestTemplateGoogleStopWatchLoggingInterceptor restTemplateGoogleStopWatchLoggingInterceptor) {
    /*
     to cache response
     check next article for more details
     https://objectpartners.com/2018/03/01/log-your-resttemplate-request-and-response-without-destroying-the-body/
      */
    ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(
        new SimpleClientHttpRequestFactory());
    RestTemplate restTemplate = new RestTemplate(factory);
    List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
//    interceptors.add(new RestTemplateRequestResponseLoggingInterceptor());
//    interceptors.add(restTemplateSpringStopWatchLoggingInterceptor);
    interceptors.add(restTemplateGoogleStopWatchLoggingInterceptor);
    restTemplate.setInterceptors(interceptors);
    return restTemplate;
  }

  @Bean
  StopWatch stopWatch() {
    return new StopWatch();
  }
}
