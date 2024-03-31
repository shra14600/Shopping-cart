package com.jsp.shoppingcart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingcart.dao.MerchantDao;
import com.jsp.shoppingcart.dao.ProductDao;
import com.jsp.shoppingcart.dto.Merchant;
import com.jsp.shoppingcart.dto.Product;

@Controller
public class ProductController {
	@Autowired
	ProductDao dao;
	@Autowired
	MerchantDao mdao;
	@RequestMapping("/addproduct")
	public ModelAndView addproduct() {
		Product p= new Product();
		ModelAndView mav = new ModelAndView();
		mav.addObject("productobj", p);
		mav.setViewName("productform");
		return mav;
		
	}
	@RequestMapping("/saveproduct")
	public ModelAndView saveProduct(@ModelAttribute("productobj") Product p,HttpSession session) {
		Merchant merchant=(Merchant)session.getAttribute("merchantinfo");
		List<Product> products=merchant.getProduct();
		if(products.size()>0) {
			products.add(p);
			merchant.setProduct(products);
		}
		else {
			List<Product> productsList=new ArrayList<Product>();
			productsList.add(p);
			merchant.setProduct(productsList);
		}
		dao.saveProduct(p);
		mdao.updateMerchant(merchant);
		ModelAndView mav= new ModelAndView();
		mav.addObject("msg","data saved successfully");
		mav.setViewName("merchantoptions");
		return mav;
		
	}
	@RequestMapping("/deleteproduct")
	public ModelAndView deleteProduct(@RequestParam("id") int id,HttpSession session) {
		Merchant merchant=(Merchant)session.getAttribute("merchantinfo");
		Merchant m=mdao.deleteProductFromMerchant(merchant.getId(),id);
		mdao.updateMerchant(m);
		dao.deleteProductById(id);
		session.setAttribute("merchantinfo", m);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("viewallproducts");
		return mav;
	}
	@RequestMapping("/displayproducts")
	public ModelAndView displayProducts() {
		List<Product> products=dao.fetchAllProducts();
		ModelAndView mav=new ModelAndView();
		mav.addObject("productlist",products);
		mav.setViewName("viewallproductstocustomer");
		return mav;
	}
	@RequestMapping("/displayproductbybrand")
	public ModelAndView displayProductByBrand(ServletRequest req) {
		String brand=req.getParameter("brand");
		List<Product> products= dao.findProductByBrand(brand);
		ModelAndView mav=new ModelAndView();
		mav.addObject("productlist",products);
		mav.setViewName("viewallproductstocustomer");
		return mav;
	}
	@RequestMapping("/displayproductbycategory")
	public ModelAndView displayProductByCategory(ServletRequest req) {
		String category=req.getParameter("category");
		List<Product> products=dao.findProductByCategory(category);
		ModelAndView mav=new ModelAndView();
		mav.addObject("productlist", products);
		mav.setViewName("viewallproductstocustomer");
		return mav;
	}
}
