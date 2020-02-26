package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@RequestMapping("/products")
	public String list(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products/list";
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public String details(@PathVariable("id") long id, Model model) {
		model.addAttribute("product", productService.getById(id));
		return "products/details";
	}
	
	@RequestMapping("/products/create")
	public String showCreateForm() {
		return "products/create";
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String create(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
		productService.create(product);
		redirectAttributes.addFlashAttribute("success_message", "Product created!");
		return "redirect:/products";
	}
	
	@RequestMapping(value = "/products/{id}", method= RequestMethod.DELETE)
	public String remove(@PathVariable("id") long id) {
		productService.removeById(id);
		return "redirect:/products";
	}
	
}
