# Quartz in different app instances


For `quartz` instances that has similar job classes - no issues appears, other case qurtz engine would have class cast exception because of job deserialization.
The simpliest way to resolve the issue is to provide different 'scheduler-name' for each cluster

    spring:
      quartz:
        # this property should be different for different clusters
        scheduler-name: 'quartz-spam'

