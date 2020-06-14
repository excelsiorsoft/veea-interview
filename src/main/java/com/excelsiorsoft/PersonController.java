/**
 * 
 */
package com.excelsiorsoft;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Simeon
 *
 */
@Controller
public class PersonController {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}

}
