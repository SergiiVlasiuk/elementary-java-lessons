package com.example.quartz.quartzmixdemo.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

import static java.util.Optional.ofNullable;

@Component
@Slf4j
public class DifferentJob extends QuartzJobBean {

    @Autowired
    Scheduler scheduler;

    @Override
    protected void executeInternal(JobExecutionContext jobContext) throws JobExecutionException {

        SimpleTriggerImpl trigger = ofNullable(jobContext)
                .map(JobExecutionContext::getTrigger)
                .filter(SimpleTriggerImpl.class::isInstance)
                .map(SimpleTriggerImpl.class::cast)
                .orElse(null);
        log.info("job execution was triggered {} time(s)", trigger.getTimesTriggered());

        trigger.setStartTime(Date.from(Calendar.getInstance().toInstant().plusSeconds(15)));
        try {
            scheduler.rescheduleJob(trigger.getKey(), trigger);
            log.info("reschedule job");
        } catch (SchedulerException e) {
            log.error("error", e);
        }
    }
}
