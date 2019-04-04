package org.vl.example.stopwatch.util;

import static com.google.common.base.Stopwatch.createStarted;

import com.google.common.base.Stopwatch;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.vl.example.stopwatch.RequestData;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Component
@Slf4j
public class GoogleStopWatchCommunicationService {

  private Map<Thread, RequestData> stopWatchMap = new ConcurrentHashMap();

  public void start(HttpRequest request) {
    RequestData requestData =
        RequestData.builder()
            .uri(request.getURI())
            .httpHeaders(request.getHeaders())
            .method(request.getMethod())
            .thread(Thread.currentThread())
            .stopwatch(createStarted())
            .build();
    stopWatchMap.put(Thread.currentThread(), requestData);
  }

  public void stop() {
    RequestData requestData = stopWatchMap.remove(Thread.currentThread());
    Stopwatch stopwatch = requestData.getStopwatch();
    stopwatch.stop();
    log.info("spend time {} for request {}", stopwatch, requestData);
  }

}
