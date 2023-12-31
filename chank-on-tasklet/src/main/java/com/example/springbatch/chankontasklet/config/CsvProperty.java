package com.example.springbatch.chankontasklet.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.ToString;

@Component
@PropertySource("classpath:property/csv.properties")
@ToString
public class CsvProperty {

	@Value("${csv.path.orders}")
	private String ordersCsvPath;

	public String getOrdersCsvPath() {
		return ordersCsvPath;
	}
}
