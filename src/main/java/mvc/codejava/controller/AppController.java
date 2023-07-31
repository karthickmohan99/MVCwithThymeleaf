package mvc.codejava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mvc.codejava.model.Product;
import mvc.codejava.service.ProductService;

@Controller
public class AppController {

	@Autowired
	private ProductService service; 
	
	//@RequestMapping("/")
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String viewHomePage(Model model) {
		//Model object allows data to be passed from the controller to the view
		List<Product> listProducts = service.listAll();//get data from db
		model.addAttribute("listProducts", listProducts);//listProducts is added to model object with name of listProducts
		                                                         
		return "index";
	}
	
	@RequestMapping("/new")//Handle all types of http request methods
	public String showNewProductPage(Model model) {
//		Product product = new Product();
//		model.addAttribute("product", product);
		
		return "new_product";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		service.save(product);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_product"); //it is a class in spring framework ,it combines both the model data and the view
		Product product = service.get(id);
		mav.addObject("product", product);
		
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";		
	}
	// By using the redirect, the browser will issue a new GET request to the specified URL, 
	//effectively refreshing the page and preventing duplicate delete operations.
	
	
}
