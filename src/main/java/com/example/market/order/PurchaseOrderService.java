package com.example.market.order;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderService {

	private PurchaseOrderRepository orderRepo;

	@Autowired
	public PurchaseOrderService(PurchaseOrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}

}
