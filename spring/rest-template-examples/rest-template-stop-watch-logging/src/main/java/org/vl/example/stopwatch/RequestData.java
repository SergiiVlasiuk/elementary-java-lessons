package org.vl.example.stopwatch;

import com.google.common.base.Stopwatch;
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
  private URI uri;
  private HttpMethod method;
  private HttpHeaders httpHeaders;
  private Thread thread;
  private Stopwatch stopwatch;
}
