package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.domain.Category;

@Controller
public class HomeController{

	//whenever the user open our website it should navigate to home page
	//http://localhost:8080/ShoppingCart/

	@Autowired HttpSession session;
	
	@Autowired Category category;
	
	@Autowired CategoryDAO categoryDAO;
	
	
	@RequestMapping("/")
	public ModelAndView goToHome()
	{
		//model.addAttribute("message", "This is my shopping cart website");
		ModelAndView mv = new ModelAndView("Home");
		mv.addObject("message", "This is my shopping cart website");
		
		
		// get all categories
				List<Category> categoryList = categoryDAO.list();

				// attach to session
				session.setAttribute("categoryList", categoryList);
				session.setAttribute("category", category);
	return  mv;
	}
	@RequestMapping("/home")
	public String home()
	{
		return "Home";
	}
	@RequestMapping("/LoginPage")
	public String loginPage(Model model)
	{
		model.addAttribute("isUserClickedLogin", "true");
		return "Home";
	}
	
	@RequestMapping("/RegistrationPage")
	public String registrationPage(Model model)
	{
		model.addAttribute("isUserClickedRegister", "true");
		return "Home";
	}
	
	
}
