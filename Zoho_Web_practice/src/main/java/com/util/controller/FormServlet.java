package com.util.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.customexception.TaskException;
import com.util.dao.UserDAO;

public class FormServlet extends HttpServlet {

    private static final long serialVersionUID = 2L;
	private final UserRequestHandler userRequestHandler = new UserRequestHandler();
	ResponseHelper responseHelper = new ResponseHelper();
	UserDAO userGot = new UserDAO();
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        try {
            String action = request.getParameter("action");
            switch (action) {
                
                case "edit":
                    userRequestHandler.handleEditUser(request, response);
                    break;
                default:
                	responseHelper.forwardToErrorPage(request, response, "Unsupported GET action: " + action);
            }
        } 
        catch (TaskException e) {
        	responseHelper.forwardToErrorPage(request, response, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            switch (action) {
	            case "view":
	            	
	                userRequestHandler.handleViewUsers(request, response);
	                
	                break;
	            case "edit":
                    userRequestHandler.handleEditUser(request, response);
                    break;
                case "create":
                    userRequestHandler.handleCreateUser(request, response);
                    break;
                case "update":
                    userRequestHandler.handleUpdateUser(request, response);
                    break;
                case "delete":
                    userRequestHandler.handleDeleteUser(request, response);
                    break;
                default:
                	responseHelper.forwardToErrorPage(request, response, "Unknown POST action: " + action);
            }
        }
        catch (TaskException e) {
        	responseHelper.forwardToErrorPage(request, response, e.getMessage());
        }
    }
}