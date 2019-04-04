
## terminal instruction

    curl -GET "http://localhost:8080/home"

## Web interceptor to manage controller actions example

    extends HandlerInterceptorAdapter

## Rest template interceptor to manage all calls from restTemplate example

    implements ClientHttpRequestInterceptor 

## Web Filter example  (useless for logging)

To register this filter should be used 

    @ServletComponentScan("org.vl.example.stopwatch.web")

### some logs for example

    2019-04-04 20:22:56.686  INFO 14744 --- [nio-8080-exec-3] o.v.e.stopwatch.web.filter.LogFilter     : doFilter
    2019-04-04 20:22:56.687  INFO 14744 --- [nio-8080-exec-3] s.w.i.RestApiStopWatchLoggingInterceptor : preHandle
    2019-04-04 20:22:56.687  INFO 14744 --- [nio-8080-exec-3] emplateGoogleStopWatchLoggingInterceptor : start observing
    2019-04-04 20:22:56.692  INFO 14744 --- [nio-8080-exec-4] o.v.e.stopwatch.web.filter.LogFilter     : doFilter
    2019-04-04 20:22:56.693  INFO 14744 --- [nio-8080-exec-4] s.w.i.RestApiStopWatchLoggingInterceptor : preHandle
    2019-04-04 20:22:56.694  INFO 14744 --- [nio-8080-exec-4] s.w.i.RestApiStopWatchLoggingInterceptor : postHandle
    2019-04-04 20:22:56.694  INFO 14744 --- [nio-8080-exec-4] s.w.i.RestApiStopWatchLoggingInterceptor : afterCompletion
    2019-04-04 20:22:56.694  INFO 14744 --- [nio-8080-exec-4] .e.s.u.GoogleStopWatchHttpRequestService : RestApiStopWatchLoggingInterceptor spend time 1.615 ms for request RequestData(url=http://localhost:8080/home/fe818017-f444-4549-81dd-52358c2ba3e8, method=GET, threadName=http-nio-8080-exec-4, clazz=class org.vl.example.stopwatch.web.interceptor.RestApiStopWatchLoggingInterceptor)
    2019-04-04 20:22:56.795  INFO 14744 --- [nio-8080-exec-3] .e.s.u.GoogleStopWatchHttpRequestService : RestTemplateGoogleStopWatchLoggingInterceptor spend time 106.9 ms for request RequestData(url=http://localhost:8080/home/fe818017-f444-4549-81dd-52358c2ba3e8, method=GET, threadName=http-nio-8080-exec-3, clazz=class org.vl.example.stopwatch.logging.RestTemplateGoogleStopWatchLoggingInterceptor)
    2019-04-04 20:22:56.798  INFO 14744 --- [nio-8080-exec-3] emplateGoogleStopWatchLoggingInterceptor : stop observing
    2019-04-04 20:22:56.799  INFO 14744 --- [nio-8080-exec-3] s.w.i.RestApiStopWatchLoggingInterceptor : postHandle
    2019-04-04 20:22:56.800  INFO 14744 --- [nio-8080-exec-3] s.w.i.RestApiStopWatchLoggingInterceptor : afterCompletion
    2019-04-04 20:22:56.800  INFO 14744 --- [nio-8080-exec-3] .e.s.u.GoogleStopWatchHttpRequestService : RestApiStopWatchLoggingInterceptor spend time 112.4 ms for request RequestData(url=http://localhost:8080/home, method=GET, threadName=http-nio-8080-exec-3, clazz=class org.vl.example.stopwatch.web.interceptor.RestApiStopWatchLoggingInterceptor)
