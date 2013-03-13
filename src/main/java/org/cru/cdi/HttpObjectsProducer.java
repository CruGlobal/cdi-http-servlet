package org.cru.cdi;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestScoped
public class HttpObjectsProducer
{
    private HttpServletRequest httpServletRequest;

    public void requestInitialized(ServletRequestEvent sre)
    {
        ServletRequest servletRequest = sre.getServletRequest();
        if (servletRequest instanceof HttpServletRequest)
        {
            httpServletRequest = (HttpServletRequest) servletRequest;
        }
    }

    @Produces @RequestScoped
    HttpServletRequest produceHttpServletRequest()
    {
        return httpServletRequest;
    }
    
    @Produces @RequestScoped
    HttpSession produceHttpSession()
    {
        return httpServletRequest.getSession();
    }

    
}
