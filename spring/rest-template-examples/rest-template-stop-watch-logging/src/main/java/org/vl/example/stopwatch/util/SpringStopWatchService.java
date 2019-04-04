package org.vl.example.stopwatch.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.vl.example.stopwatch.RequestData;

@Component
public class SpringStopWatchService {

  @Autowired
  private StopWatch stopWatch;

  public void start(HttpRequest request) {
    String requestDto =
//    RequestData requestDto =
        RequestData.builder()
            .uri(request.getURI())
            .httpHeaders(request.getHeaders())
            .method(request.getMethod())
            .thread(Thread.currentThread())
            .build()
            .toString();
    stopWatch.start(requestDto);
  }

  public void stop() {
    stopWatch.stop();
  }

  public StopWatch getStopWatch() {
    return stopWatch;
  }
}
