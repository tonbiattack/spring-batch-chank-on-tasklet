package com.example.springbatch.chankontasklet.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private Tasklet orderTasklet;

	@Bean
	public Step orderTaskletStep(PlatformTransactionManager transactionManager) {
		return stepBuilderFactory.get("orderTaskletStep")
				.tasklet(orderTasklet)
				.transactionManager(transactionManager)
				.build();
	}

	@Bean
	public Job orderTaskletJob(Step orderTaskletStep) {
		return jobBuilderFactory.get("orderTaskletJob")
				.start(orderTaskletStep)
				.build();
	}
}
