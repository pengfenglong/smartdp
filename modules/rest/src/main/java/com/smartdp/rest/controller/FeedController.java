//FINAL 
package com.smartdp.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class FeedController {
    @RequestMapping("/jsonfeed")
    public String getJSON(Model model) {
    	
    	return "";
    	
    }
}
