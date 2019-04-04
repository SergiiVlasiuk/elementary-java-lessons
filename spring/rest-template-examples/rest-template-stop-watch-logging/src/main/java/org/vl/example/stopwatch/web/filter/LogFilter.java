package org.vl.example.stopwatch.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.vl.example.stopwatch.util.GoogleStopWatchHttpRequestService;

@WebFilter("/*")
@Slf4j
public class LogFilter implements Filter {

  @Autowired
  private GoogleStopWatchHttpRequestService stopWatchService;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    log.info("init");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    log.info("doFilter");
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
    log.info("destroy");
  }
}
