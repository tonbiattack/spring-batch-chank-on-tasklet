package com.example.springbatch.chankontasklet.reader;

import java.nio.charset.StandardCharsets;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.example.springbatch.chankontasklet.config.CsvProperty;
import com.example.springbatch.chankontasklet.entity.Order;

@Component
@StepScope
public class OrderReader implements ItemStreamReader<Order> {

	private FlatFileItemReader<Order> delegate;

	@Autowired
	private CsvProperty csvProperty;

	@PostConstruct
	public void init() {
		this.delegate = new FlatFileItemReaderBuilder<Order>()
				.name("orderItemReader")
				.resource(new ClassPathResource(csvProperty.getOrdersCsvPath()))
				.linesToSkip(1)
				.encoding(StandardCharsets.UTF_8.name())
				.delimited()
				.names(new String[] { "orderId", "customerId", "productId", "quantity", "orderDate", "deliveryStatus" })
				.fieldSetMapper(new OrderFieldSetMapper())
				.build();
	}

	@Override
	public Order read() throws Exception {
		return delegate.read();
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		delegate.open(executionContext);
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		delegate.update(executionContext);
	}

	@Override
	public void close() throws ItemStreamException {
		delegate.close();
	}
}
