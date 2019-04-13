package org.vl.example.stopwatch.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.vl.example.stopwatch.util.GoogleStopWatchHttpRequestService;

@Component
@Slf4j
public class RestApiStopWatchLoggingInterceptor extends HandlerInterceptorAdapter {

  @Autowired
  private GoogleStopWatchHttpRequestService googleStopwatchService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    log.info("preHandle");
    RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
    googleStopwatchService.start(request, this.getClass());
    return super.preHandle(request, response, handler);
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    log.info("postHandle");
    super.postHandle(request, response, handler, modelAndView);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    log.info("afterCompletion");
//    googleStopwatchService.stop(httpRequest.getObject());
    googleStopwatchService.stop(request, this.getClass());
    super.afterCompletion(request, response, handler, ex);
  }

  @Override
  public void afterConcurrentHandlingStarted(HttpServletRequest request,
      HttpServletResponse response, Object handler) throws Exception {
    log.info("afterConcurrentHandlingStarted");
    super.afterConcurrentHandlingStarted(request, response, handler);
  }
}
