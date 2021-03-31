package com.example.market.order;

import javax.transaction.Transactional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderService {

	private RabbitTemplate rabbit;

	@Autowired
	public PurchaseOrderService(RabbitTemplate rabbit) {
		this.rabbit = rabbit;
	}

	public void purchase(PurchaseOrder order) {
		System.out.println("-------------Market LOG-------------");
		System.out.println(order);
		try {
			rabbit.convertAndSend("Market.purchaseOrder", order);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
