/**
 * 
 */
package com.excelsiorsoft.service;

/**
 * To be able to remotely restart the service once it's shut down by Heroku for inactivity
 * 
 * @author Simeon
 *
 */
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.restart.RestartEndpoint;

@Service
public class RestartService {
    
    @Autowired
    private RestartEndpoint restartEndpoint;
    
    public void restart() {
        restartEndpoint.restart();
    }

}
