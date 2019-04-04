package org.vl.example.stopwatch.util;

import static com.google.common.base.Stopwatch.createStarted;

import com.google.common.base.Stopwatch;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.vl.example.stopwatch.dto.RequestData;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Component
@Slf4j
public class GoogleStopWatchHttpRequestService {

  private Map<RequestData, Stopwatch> stopWatchMap = new ConcurrentHashMap();

  public void start(HttpRequest request, Class<?> aClass) {
    startWatch(getRequestData(request, aClass));
  }

  public void start(HttpServletRequest request, Class<?> aClass) {
    startWatch(getRequestData(request, aClass));
  }

  private void startWatch(RequestData requestData) {
    stopWatchMap.put(requestData, createStarted());
  }

  public void stop(HttpRequest request, Class<?> aClass) {
    stopWatch(getRequestData(request, aClass));
  }

  public void stop(HttpServletRequest request, Class<?> aClass) {
    stopWatch(getRequestData(request, aClass));
  }

  private void stopWatch(RequestData requestData) {
    Stopwatch stopwatch = stopWatchMap.remove(requestData);
    stopwatch.stop();
    log.info("{} spend time {} for request {}", requestData.getClazz().getSimpleName(), stopwatch, requestData);
  }

  private RequestData getRequestData(HttpRequest request, Class<?> aClass) {
    return RequestData.builder()
        .url(request.getURI().toString())
//        .httpHeaders(request.getHeaders())
        .method(request.getMethod().toString())
        .threadName(Thread.currentThread().getName())
        .clazz(aClass)
        .build();
  }

  private RequestData getRequestData(HttpServletRequest request, Class<?> aClass) {
    return RequestData.builder()
        .url(request.getRequestURL().toString())
//        .httpHeaders(request.getHeaders())
        .method(request.getMethod())
        .threadName(Thread.currentThread().getName())
        .clazz(aClass)
        .build();
  }

}
