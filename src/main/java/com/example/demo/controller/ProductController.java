package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/products")
	public String list(Model model, @RequestParam(value = "price_from", required = false) BigDecimal price_from,
			@RequestParam(value = "price_to", required = false) BigDecimal price_to,
			@PageableDefault(page=0 ,value = 5) Pageable pageRequest) {
		Page<Product> products = null;
		if(price_from != null && price_to!=null) {
			products = productService.getInPriceRange(price_from, price_to, pageRequest);
		} else {
			products = productService.findAllWithPagination(pageRequest);
		}
		model.addAttribute("products", products);
		
		int totalPages = products.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
		return "products/list";
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public String details(@PathVariable("id") long id, Model model) {
		model.addAttribute("product", productService.getById(id));
		return "products/details";
	}
	
	@RequestMapping("/products/create")
	public String showCreateForm(Model model) {
		model.addAttribute("categories", categoryService.findAll());
		return "products/create";
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String create(@ModelAttribute Product product,
			RedirectAttributes redirectAttributes) {
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
