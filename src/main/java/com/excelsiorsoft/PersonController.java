/**
 * 
 */
package com.excelsiorsoft;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Simeon
 *
 */
@Controller
public class PersonController {

	@Autowired
	private  GreetingRepository greetingRepository;
	
	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}
	
	@GetMapping("/greetings")
	Iterable<Greeting> greetings(){
		return greetingRepository.findAll();
	}

}
