package com.example.quartz.quartzmixdemo;

import com.example.quartz.quartzmixdemo.quartz.DifferentJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@SpringBootApplication
@Slf4j
public class QuartzDifferentJobDemoApplication {

	public static void main(String[] args) throws SchedulerException {
		ConfigurableApplicationContext context = SpringApplication.run(QuartzDifferentJobDemoApplication.class, args);

/*
		for (String beanName : context.getBeanDefinitionNames()) {
			log.info("bean name {}", beanName);
		}
*/

//		initFirstJob(context);
	}

	private static void initFirstJob(ConfigurableApplicationContext context) throws SchedulerException {
		Scheduler scheduler = (Scheduler) context.getBean("quartzScheduler");
		log.info("publish job");

		JobDetail jobDetail = getJobDetail();

		scheduler.scheduleJob(jobDetail, getJobTrigger(jobDetail));
	}

	private static SimpleTrigger getJobTrigger(JobDetail jobDetail) {
		return TriggerBuilder.newTrigger()
				.forJob(jobDetail)
				.withIdentity(jobDetail.getKey().getName(), "mix-triggers")
				.withDescription("mixed job Trigger")
				.startAt(Date.from(Calendar.getInstance().toInstant().plusSeconds(15)))
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
				.build();
	}

	private static JobDetail getJobDetail() {
		JobDataMap jobDataMap = new JobDataMap();

		jobDataMap.put("anyKey", "anyValue");
		jobDataMap.put("anySubjectKey", "any-subject-value");
		jobDataMap.put("more-keys", "more key values");

		return JobBuilder.newJob(DifferentJob.class)
				.withIdentity(UUID.randomUUID().toString(), "mixed-jobs")
				.withDescription("mixed Job details")
				.usingJobData(jobDataMap)
				.storeDurably()
				.build();
	}

}
