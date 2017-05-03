package com.niit.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.domain.Category;

import ch.qos.logback.classic.Logger;

@Controller
public class CategoryController {
	

	//create category
	//fetch all categories
	//delete category
	//update category
	@Autowired HttpSession session;
	
	@Autowired  CategoryDAO categoryDao;
	
	@Autowired  Category category;
	
	private static  Logger log = (Logger) LoggerFactory.getLogger(CategoryController.class);

	
	@RequestMapping("/manage_category_add")
	public ModelAndView  createCategory(@RequestParam("id") String id, 
			@RequestParam("name") String name,  @RequestParam("description") String desc)
	{
		category.setId(id);
		category.setName(name);
		category.setDescription(desc);
		
		ModelAndView mv = new ModelAndView("Home");
		
		mv.addObject("isAdminClickedCategories", "true");
		mv.addObject("isAdmin", "true");
		
		//Before calling save method, check whether the category id already exist in db or not
		
		//if it is does not exist, then only call save method
		
		if (categoryDao.getCategoryByID(id) !=null)
		{
			//category already exist
			mv.addObject("message", "Category already exist with teh id " +id);
			return mv;
			
		}
		else  // actualy else is not required if return statement is there in if condition
		{
			categoryDao.save(category);
			mv.addObject("message", "Category created successfuly ");
		}
		
		return mv;
		
		
		
	}
	
	//@RequestMapping("/manage_category_delete/{id}")
	
	@RequestMapping("/manage_category_delete/{id}")
	public ModelAndView deleteCategory(@PathVariable("id") String id)
	{
		
		log.debug("Starting of the method deleteCategory");
		log.debug("You are going to delete " + id);
		ModelAndView mv = new ModelAndView("Home");
		 if( categoryDao.delete(id))
		 {
			 mv.addObject("message", "successfully deleted the category");
		 }
		 else
		 {
			 mv.addObject("message", "Not able to delte the category");
		 }
		 
			session.setAttribute("categoryList", categoryDao.list());
			session.setAttribute("category", category);
			
		
			log.debug("Ending of the method deleteCategory");
		 
		 return mv;
		
	}

	
}
