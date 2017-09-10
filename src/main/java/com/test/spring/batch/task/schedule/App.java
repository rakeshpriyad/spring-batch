package com.test.spring.batch.task.schedule;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		String springConfig = "spring-batch-job-task-schedule.xml";

		@SuppressWarnings("resource")
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
		// context.close();

	}
}