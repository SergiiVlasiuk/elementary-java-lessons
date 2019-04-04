package org.vl.example.stopwatch.logging;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.vl.example.stopwatch.util.GoogleStopWatchHttpRequestService;
import org.vl.example.stopwatch.util.HttpCommunicationUtil;

@Component
@Slf4j
public class RestTemplateGoogleStopWatchLoggingInterceptor implements ClientHttpRequestInterceptor {

  @Autowired
  private GoogleStopWatchHttpRequestService googleStopwatchService;

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body,
      ClientHttpRequestExecution execution) throws IOException {

//    logRequest(request, body);
    log.info("start observing");
    googleStopwatchService.start(request, this.getClass());
    ClientHttpResponse response = execution.execute(request, body);
//    googleStopwatchService.stop(request);
    logResponse(response);
    googleStopwatchService.stop(request, this.getClass());
    log.info("stop observing");

    //Add optional additional headers
    response.getHeaders().add("headerName", "VALUE");

    return response;
  }

  private void logRequest(HttpRequest request, byte[] body) throws IOException {
    HttpCommunicationUtil.logRequest(request, body);
  }

  private void logResponse(ClientHttpResponse response) throws IOException {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      log.error(e.getLocalizedMessage(), e);
    }
//    HttpCommunicationUtil.logResponse(response);
  }
}
