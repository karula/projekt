package ee.projekt.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ee.projekt.web.Guard;
import ee.projekt.web.GuardController;

@Controller
public class GuardController {
	
	private Logger log = LoggerFactory.getLogger(GuardController.class);
	
	@RequestMapping(value="/guardForm", method=RequestMethod.GET)
	public String guardForm(Model model) {
		log.debug("Successful guardform request");
		
		model.addAttribute("guard", new Guard());
		
		return "guard";
	}
	
	@RequestMapping(value="/guardForm", method=RequestMethod.POST)
	public String guardForm(@ModelAttribute Guard guard, Model model) {
		log.debug("Adding a new guard. Name: " + guard.getName() + " Age: " + guard.getAge());
		return "guard";
	}



}
