package com.viking.vikingtask01.Scheduler;

import com.viking.vikingtask01.ConfCamel.TaskCamelRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CustomTaskScheduler {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    TaskCamelRoute camelRoute;

    @Scheduled(cron = "${scheduler.cronjob}")
    //@Scheduled(fixedRate = 100)
    //@Scheduled(initialDelay=0, fixedRate=4*60*60*1000)
    public void taskScheduler() throws Exception {
        logger.info("Start TaskScheduler");
        CamelContext _ctx = new DefaultCamelContext();
        _ctx.addRoutes(camelRoute.createRouteBuilder());
        _ctx.setTracing(true);
        _ctx.start();
        Thread.sleep(2000);
        _ctx.stop();
        logger.info("End TaskScheduler");
    }

}
