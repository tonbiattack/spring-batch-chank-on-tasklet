package com.example.springbatch.chankontasklet.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springbatch.chankontasklet.entity.Customer;
import com.example.springbatch.chankontasklet.entity.Order;
import com.example.springbatch.chankontasklet.entity.Product;
import com.example.springbatch.chankontasklet.repository.CustomerRepository;
import com.example.springbatch.chankontasklet.repository.OrderRepository;
import com.example.springbatch.chankontasklet.repository.ProductRepository;

/**
 * ChankコンポーネントをTaskletの中で利用することによって、エラー(RunTime Exception)があったときにトランザクションをロールバックできるようにする。
 * ワントランザクションでできるようにする。
 * Chankコンポーネント化をしながらクラス分割をしながらSpring Batchの便利な機能を利用することができる。
 */
@Component
@StepScope
public class OrderTasklet implements Tasklet {

	@Autowired
	private ItemStreamReader<Order> itemReader;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
			throws Exception {
		try {
			openReader(chunkContext);
			processItems();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			closeReader();
		}
		return RepeatStatus.FINISHED;
	}

	private void openReader(ChunkContext context) throws Exception {
		itemReader.open(context.getStepContext().getStepExecution().getExecutionContext());
	}

	private void processItems() throws Exception {
		Order order;
		//ロールバックの確認のために必要
		int count = 0;
		while ((order = itemReader.read()) != null) {
			Customer customer = new Customer();
			customer.setCustomerId(order.getCustomer().getCustomerId());
			customerRepository.save(customer);
			Product product = new Product();
			product.setProductId(order.getProduct().getProductId());
			productRepository.save(product);
			orderRepository.save(order);
			//	特定の条件で例外を発生させてロールバックをテスト
			// 5件登録した後に例外を発生させる
			if (++count == 5) {
				throw new RuntimeException("Test Rollback");
			}
		}
	}

	private void closeReader() {
		if (itemReader != null) {
			itemReader.close();
		}
	}
}
