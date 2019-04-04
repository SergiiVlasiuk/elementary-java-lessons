package org.vl.example.stopwatch.logging;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.vl.example.stopwatch.util.HttpCommunicationUtil;
import org.vl.example.stopwatch.util.SpringStopWatchService;

@Component
@Slf4j
public class SpringStopWatchLoggingInterceptor implements ClientHttpRequestInterceptor {

  @Autowired
  private SpringStopWatchService stopWatchService;

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body,
      ClientHttpRequestExecution execution) throws IOException {

    logRequest(request, body);
    ClientHttpResponse response = execution.execute(request, body);
    logResponse(response);

    //Add optional additional headers
    response.getHeaders().add("headerName", "VALUE");

    return response;
  }

  private void logRequest(HttpRequest request, byte[] body) throws IOException {
    stopWatchService.start(request);
    HttpCommunicationUtil.logRequest(request, body);
  }

  private void logResponse(ClientHttpResponse response) throws IOException {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      log.error(e.getLocalizedMessage(), e);
    }
    stopWatchService.stop();
    log.info("stop watch id: {}", stopWatchService.getStopWatch().getId());
    log.info("stop watch details: {}", stopWatchService.getStopWatch().prettyPrint());

    HttpCommunicationUtil.logResponse(response);
  }
}
