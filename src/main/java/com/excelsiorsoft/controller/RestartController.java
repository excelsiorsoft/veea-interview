/**
 * 
 */
package com.excelsiorsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excelsiorsoft.service.RestartService;

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
