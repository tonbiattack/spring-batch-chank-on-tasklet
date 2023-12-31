package com.example.springbatch.chankontasklet.reader;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.example.springbatch.chankontasklet.entity.Customer;
import com.example.springbatch.chankontasklet.entity.Order;
import com.example.springbatch.chankontasklet.entity.Product;

public class OrderFieldSetMapper implements FieldSetMapper<Order> {

	/**
	 * CSVファイルで読み込んだStringのデータを独自のクラスにマッピングする
	 * Date型はyyy-MM-dd HH:mm:ssではない限りマッピングしないといけない
	 * このクラスを利用する場合は全てのプロパティを明示的に変換しないといけない
	 */
	@Override
	public Order mapFieldSet(FieldSet fieldSet) throws BindException {
		Order order = new Order();
		order.setOrderId(fieldSet.readString("orderId"));
		order.setQuantity(Integer.parseInt(fieldSet.readString("quantity")));
		order.setDeliveryStatus(fieldSet.readString("deliveryStatus"));

		Product product = new Product();
		product.setProductId(fieldSet.readString("productId"));
		order.setProduct(product);

		try {
			order.setOrderDate(new SimpleDateFormat("yyyy-MM-dd").parse(fieldSet.readString("orderDate")));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Customer customer = new Customer();
		customer.setCustomerId(fieldSet.readString("customerId"));
		order.setCustomer(customer);
		return order;
	}
}
