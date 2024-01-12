package com.fastfood.config;

import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class ScheduleConfig {
	
//	@Scheduled(fixedDelay = 1000)
//	public void scheduleFixedDelayTask() throws InterruptedException {
//	  System.out.println("Task1 - " + new Date());
//	}

}
