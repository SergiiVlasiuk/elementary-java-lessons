package org.vl.example.stopwatch.web.controller;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("home")
@Slf4j
public class HomeController {

  @Autowired
  RestTemplate restTemplate;

  @GetMapping
  String home(HttpServletRequest request) {
//    log.info("getRequestURI: {}", request.getRequestURI());
//    log.info("getRequestURL: {}", request.getRequestURL());
//    log.info("getPathInfo: {}", request.getPathInfo());
//    log.info("fromCurrentRequest: {}", ServletUriComponentsBuilder.fromCurrentRequest());
//    log.info("fromCurrentRequestUri: {}", ServletUriComponentsBuilder.fromCurrentRequestUri());
    return restTemplate
        .getForObject(request.getRequestURL().toString() + "/" + UUID.randomUUID(), String.class);
  }

  @GetMapping("{local}")
  String homeLocal(@PathVariable String local) {
    return local;
  }

}
