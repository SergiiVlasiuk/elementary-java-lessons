package org.vl.example.stopwatch.dto;

import java.net.URI;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

@Data
@Builder
@ToString
public class RequestData {

  @NotNull
  private String url;
  private String method;
//  private HttpHeaders httpHeaders;
//  private Thread thread;
  private String threadName;
  private Class clazz;
//  private String clazz;
}
