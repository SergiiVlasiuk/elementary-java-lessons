package org.vl.example.stopwatch.logging;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.vl.example.stopwatch.util.GoogleStopWatchCommunicationService;
import org.vl.example.stopwatch.util.HttpCommunicationUtil;

@Component
@Slf4j
public class GoogleStopWatchLoggingInterceptor implements ClientHttpRequestInterceptor {

  @Autowired
  private GoogleStopWatchCommunicationService googleStopwatchService;

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
    googleStopwatchService.start(request);
    HttpCommunicationUtil.logRequest(request, body);
  }

  private void logResponse(ClientHttpResponse response) throws IOException {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      log.error(e.getLocalizedMessage(), e);
    }
    googleStopwatchService.stop();

    HttpCommunicationUtil.logResponse(response);
  }
}
