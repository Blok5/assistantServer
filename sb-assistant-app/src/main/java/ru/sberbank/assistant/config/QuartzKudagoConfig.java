package ru.sberbank.assistant.config;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import ru.sberbank.assistant.job.KudagoJob;
import ru.sberbank.assistant.scheduler.AutoWiringSpringBeanJobFactory;

@Configuration
@EnableAutoConfiguration
@ConditionalOnExpression("'${using.spring.schedulerFactory}'=='true'")

public class QuartzKudagoConfig {

    Logger logger = LoggerFactory.getLogger(getClass());

    private final ApplicationContext applicationContext;

    public QuartzKudagoConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        logger.debug("Configuring Job factory");

        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean scheduler(Trigger trigger, JobDetail job) {

        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();

        logger.debug("Setting the Scheduler up");
        schedulerFactory.setJobFactory(springBeanJobFactory());
        schedulerFactory.setJobDetails(job);
        schedulerFactory.setTriggers(trigger);


        return schedulerFactory;
    }

    @Bean
    public JobDetailFactoryBean jobDetail() {

        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(KudagoJob.class);
        jobDetailFactory.setName("Qrtz_Job_Detail");
        jobDetailFactory.setDescription("Invoke Sample Job service...");
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    @Bean
    public SimpleTriggerFactoryBean trigger(JobDetail job) {

        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(job);

        long frequencyInSec = 24 * 60 * 60;
        logger.info("Configuring trigger to fire every {} seconds", frequencyInSec);

        trigger.setRepeatInterval(frequencyInSec * 1000L);
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        trigger.setName("Qrtz_Trigger");
        return trigger;
    }


}
