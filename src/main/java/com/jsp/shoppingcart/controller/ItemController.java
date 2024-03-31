package com.jsp.shoppingcart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingcart.dao.CartDao;
import com.jsp.shoppingcart.dao.CustomerDao;
import com.jsp.shoppingcart.dao.ItemDao;
import com.jsp.shoppingcart.dao.ProductDao;
import com.jsp.shoppingcart.dto.Cart;
import com.jsp.shoppingcart.dto.Customer;
import com.jsp.shoppingcart.dto.Item;
import com.jsp.shoppingcart.dto.Product;

@Controller
public class ItemController {
	@Autowired
	ItemDao dao;
	@Autowired
	ProductDao pdao;
	@Autowired
	CartDao cdao;
	@Autowired
	CustomerDao custdao;
	
	@RequestMapping("/additem")
	public ModelAndView addItem(@RequestParam("id") int id) {
		Product p=pdao.findProductById(id);
		ModelAndView mav=new ModelAndView();
		mav.addObject("prodobj", p);
		mav.setViewName("itemform");
		return mav;
	}
	
	@RequestMapping("/additemtocart")
	public ModelAndView addItemToCart(ServletRequest req,HttpSession session) {
		int pid=Integer.parseInt(req.getParameter("id"));      //to reduce the stock number after buying product
		String brand=req.getParameter("brand");
		double price=Double.parseDouble(req.getParameter("price"));
		String model=req.getParameter("model");
		String category=req.getParameter("category");
		int quantity=Integer.parseInt(req.getParameter("quantity"));
		Item i=new Item();
		i.setBrand(brand);
		i.setModel(model);
		i.setCategory(category);
		i.setQuantity(quantity);
		i.setProduct_id(pid);
		i.setPrice(quantity*price);
		Customer customer=(Customer)session.getAttribute("customerinfo");
		Cart c=customer.getCart();
		if(c==null) {
			Cart cart=new Cart();
			List<Item> items=new ArrayList<Item>();
			items.add(i);
			cart.setItem(items);
			cart.setName(customer.getName());
			cart.setTotalprice(i.getPrice());
			customer.setCart(cart);
			dao.saveItem(i);
			cdao.saveCart(cart);
			custdao.updateCustomer(customer);
		}
		else {
			List<Item> items=c.getItem();
			if(items.size()>0) {
				items.add(i);
				c.setItem(items);
				double totalprice=0;
				for(Item it:items) totalprice=totalprice+it.getPrice();
				c.setTotalprice(totalprice);
				customer.setCart(c);
				dao.saveItem(i);
				cdao.updateCart(c);
				custdao.updateCustomer(customer);
			}
			else {
				List<Item> itemlist=new ArrayList<Item>();
				itemlist.add(i);
				c.setItem(itemlist);
				c.setTotalprice(i.getPrice());
				dao.saveItem(i);
				cdao.updateCart(c);
				custdao.updateCustomer(customer);
			}
		}
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect://displayproducts");
		return mav;
	}
	
}
