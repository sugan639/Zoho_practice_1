package com.util.controller;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseHelper {

    public void forwardToErrorPage(HttpServletRequest request,
                                         HttpServletResponse response,
                                         String errorMessage)
            throws IOException, ServletException {
    	
        request.setAttribute("message", errorMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    public void redirectWithMessage(HttpServletResponse response,
                                           String location,
                                           String messageKey,
                                           String messageValue) 
                                        		   throws IOException {
    	
        response.sendRedirect(location + "?" + messageKey + "=" + messageValue);
    }
}