package com.example.market.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.market.product.Product;

@RestController
public class MarketController {

	private PurchaseOrderService service;

	private CartRepository cartRepo;
	private PurchaseOrderRepository orderRepo;
	private OrderProductRepository orderProductRepo;

	@Autowired
	public MarketController(CartRepository cartRepo, PurchaseOrderRepository orderRepo,
			OrderProductRepository orderProductRepo, PurchaseOrderService service) {
		this.cartRepo = cartRepo;
		this.orderRepo = orderRepo;
		this.orderProductRepo = orderProductRepo;
		this.service = service;
	}

	// 장바구니 갖고오기
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public List<Cart> getCart() {
		return cartRepo.findAll();
	}

	// 장바구니 추가하기
	@RequestMapping(value = "/cart", method = RequestMethod.POST)
	public Cart addCart(@RequestBody Cart cart) {
		return cartRepo.save(cart);
	}

	// 장바구니 id로 삭제
	@RequestMapping(value = "/cart/{id}", method = RequestMethod.DELETE)
	public boolean removeCart(@PathVariable("id") long id, HttpServletResponse res) {
		System.out.println(id);

		Cart cart = cartRepo.findById(id).orElse(null);
		if (cart == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}
		cartRepo.deleteById(id);
		return true;
	}

	// 구매 -> 구매 제품 추가 -> 장바구니 전체 비우기
	@RequestMapping(value = "/purchase", method = RequestMethod.POST)
	public boolean purchase() throws Exception {
		List<Cart> carts = cartRepo.findAll();
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setOrderDate(new Date());
		purchaseOrder.setOrderProduct(new ArrayList<OrderProduct>());

		for (Cart cart : carts) {
			OrderProduct orderProduct = OrderProduct.builder().productName(cart.getProduct().getName())
					.productPrice(cart.getProduct().getPrice()).productId(cart.getProduct().getId())
					.productTitleImage(cart.getProduct().getProductTitleImage()).build();

			purchaseOrder.getOrderProduct().add(orderProduct);
		}

		System.out.println("purchaseOrder = " + carts);

		orderRepo.save(purchaseOrder);
		System.out.println("확인-------------------" + purchaseOrder);
		service.purchase(purchaseOrder);
		cartRepo.deleteAll();

		return true;
	}

	// 구매 제품 갖고오기
	@RequestMapping(value = "/purchase", method = RequestMethod.GET)
	public List<OrderProduct> getOrderProduct() {
		return orderProductRepo.findAll();
	}

}
