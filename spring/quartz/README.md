# Quartz in different app instances


for instance that has similar job classes - no issues appears, other case jobs should be managed between ones.
the simpliest wauy to resolve the issue is to provide different 'scheduler-name' for each cluster

    spring:
      quartz:
        # this property should be different for different clusters
        scheduler-name: 'quartz-spam'

