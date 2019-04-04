## Useful material

https://github.com/callicoder/spring-boot-quartz-scheduler-email-scheduling
https://www.callicoder.com/spring-boot-quartz-scheduler-email-scheduling-example/

## DB required

Create schema in your maria db `quartz_demo_db`.

Pay special attention on next configuration part

    spring:
      quartz:
        jdbc:
          initialize-schema: NEVER

in case your DB is not initialized, change it for first time.