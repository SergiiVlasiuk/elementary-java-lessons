package org.vl.example.stopwatch.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.vl.example.stopwatch.dto.RequestData;

@Component
public class SpringStopWatchService {

  @Autowired
  private StopWatch stopWatch;

  public void start(HttpRequest request) {
    String requestDto =
//    RequestData requestDto =
        RequestData.builder()
            .url(request.getURI().toString())
//            .httpHeaders(request.getHeaders())
            .method(request.getMethod().toString())
            .threadName(Thread.currentThread().getName())
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
