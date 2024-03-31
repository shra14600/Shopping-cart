package com.jsp.shoppingcart.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingcart.dao.CustomerDao;
import com.jsp.shoppingcart.dto.Customer;

@Controller
public class CustomerController {
	@Autowired
	CustomerDao dao ;
	@RequestMapping("/addcustomer")
	public ModelAndView addCustomer() {
		Customer customer = new Customer();
		ModelAndView mav = new ModelAndView();
		mav.addObject("customerobj",customer);
		mav.setViewName("customerform");
		return mav;	
	}
	@RequestMapping("/savecustomer")
    public ModelAndView saveCustomer(@ModelAttribute("customerobj")Customer customer) {
		dao.saveCustomer(customer);
		ModelAndView mav=new ModelAndView();
		mav.addObject("msg", "Customer Profile is Created");
		mav.setViewName("customerlogin");
		return mav;	
	}
	@RequestMapping("/loginvalidations")
	public ModelAndView login(ServletRequest req, HttpSession session) {
		String email=req.getParameter("email");
		String pass=req.getParameter("password");
		Customer customer = dao.login(email, pass);
		ModelAndView mav=new ModelAndView();
		if(customer!=null) {
			mav.addObject("msg", "Loggedin Successfully");
			mav.setViewName("customeroptions");
			session.setAttribute("customerinfo", customer);
		}
		else{
			mav.addObject("msg", "Invalid Credentials");
			mav.setViewName("customerlogin");
		}
		return mav;
	}
}
