package com.directcore.NexcomAcquisitionPortal.validation;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(urlPatterns = "/admin/*")
public class AuthenticationFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

      //  Integer user_admin = (Integer) request.getAttribute("user_admin");
      Integer user_admin = (Integer)  req.getSession().getAttribute("user_admin");
        if (user_admin == null) {   //checking whether the session exists
            this.context.log("Unauthorized access request");
            res.sendRedirect(req.getContextPath() + "/login");
        } else {
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }
    }
    public void destroy() {
        //close any resources here
    }

}
