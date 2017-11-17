package com.fisher.blockchain.controller;
  
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.RequestMapping;  

@Controller
public class IndexController {

	@RequestMapping(value = "/")
	public String Index(Model model) {
		model.addAttribute("message", "Hello World!!!");
		return "index";
	}

}
