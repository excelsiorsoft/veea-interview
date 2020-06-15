/**
 * 
 */
package com.excelsiorsoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Simeon
 *
 */
@RestController
public class RestartController {

	@Autowired
    private RestartService restartService;

	@GetMapping("/restart")
	public void restart() {
		restartService.restart();
	}

}
