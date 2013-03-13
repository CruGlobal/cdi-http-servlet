package org.cru.cdi;

import javax.inject.Inject;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class RequestWebListener
 *
 */
@WebListener
public class RequestWebListener implements ServletRequestListener {

    @Inject HttpObjectsProducer producer;

    public void requestDestroyed(ServletRequestEvent sre) {
    }

    public void requestInitialized(ServletRequestEvent sre) {
        producer.requestInitialized(sre);
    }
	
}
