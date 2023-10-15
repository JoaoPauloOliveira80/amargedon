package com.vigjoaopaulo.armagedon.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class HomeController {
	@RequestMapping ("/index")
	public ModelAndView index () {
	  return new ModelAndView ("index");
	}
}
