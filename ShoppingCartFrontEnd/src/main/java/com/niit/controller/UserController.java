package com.niit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.domain.Category;
import com.niit.shoppingcart.domain.User;

@Controller
public class UserController {

	// we need to implement
	// createuser
	// uodate user
	// get alluser

	// To connect to user in backend
	@Autowired
	UserDAO userDAO;
	@Autowired
	User user;

	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	Category category;
	// Get the user id and password from login page
	// Send these values to userDao.validate
	// based on response, you can navigate to either login.jsp or home.jsp

	// Whenever we configure spring security - we can remove this method

	@RequestMapping("/validate")
	public ModelAndView login(@RequestParam("username") String id, @RequestParam("password") String password) {

		ModelAndView mv = new ModelAndView("/Home");
		if (userDAO.validate(id, password) == true) {
			user = userDAO.get(id);
			mv.addObject("message", "Welcome " + user.getName());

			
			  mv.addObject("categoryList", categoryDAO.list());
			  mv.addObject("category", categoryDAO);
			  /*
			 * mv.addObject("supplierList", supplierDAO.list());
			 * mv.addObject("supplier", supplierDAO);
			 */
			// check whether user role is admin or user

			if (user.getRole().equals("Admin")) {
				mv.addObject("isAdmin", "true");
			} else {
				mv.addObject("isAdmin", "false");
			}
		} 
		else {
			mv.addObject("message", "invalid credentials");
		}

		return mv;

	}

}
