lib consumer imcludes auto configured library.

library provides conditionally loaded beans.

To verify result:
 
 - build auto configured dependent library
 - start lib-consumer project and find in logs something like:
 
> 2019-03-31 23:41:55.545  INFO 23478 --- [           main] o.v.e.another.AnotherAutoConfiguration   : Auto configuration for AnotherAutoConfiguration is loaded...
> 2019-03-31 23:41:55.549  INFO 23478 --- [           main] o.v.e.r.RetryApiAutoConfiguration        : Auto configuration for RetryApiAutoConfiguration is loaded...
 
 - start lib-consumer with local profile, and check mappings with actuator (filtering `another`):
 
> http://localhost:9000/actuator/mappings

 - filtered endpoint was configured because of `auto-loading-dependency`

 - remove from`application.yml` file configuration:
 
        spring:
          profiles:
            include: 'al'

 - verify started application (logs, endpoints)
