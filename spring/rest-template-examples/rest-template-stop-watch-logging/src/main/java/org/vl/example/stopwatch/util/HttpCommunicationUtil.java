package org.vl.example.stopwatch.util;

import java.io.IOException;
import java.nio.charset.Charset;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

@Slf4j
public class HttpCommunicationUtil {

  public static void logRequest(HttpRequest request, byte[] body) throws IOException {
    if (log.isDebugEnabled()) {
      log.debug(
          "===========================request begin================================================");
      log.debug("URI         : {}", request.getURI());
      log.debug("Method      : {}", request.getMethod());
      log.debug("Headers     : {}", request.getHeaders());
      log.debug("Request body: {}", new String(body, "UTF-8"));
      log.debug(
          "==========================request end================================================");
    }
  }

  public static void logResponse(ClientHttpResponse response) throws IOException {
    if (log.isDebugEnabled()) {
      log.debug(
          "============================response begin==========================================");
      log.debug("Status code  : {}", response.getStatusCode());
      log.debug("Status text  : {}", response.getStatusText());
      log.debug("Headers      : {}", response.getHeaders());
      log.debug("Response body: {}",
          StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
      log.debug(
          "=======================response end=================================================");
    }
  }
}
