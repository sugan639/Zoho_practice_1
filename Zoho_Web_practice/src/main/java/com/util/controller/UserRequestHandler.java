package com.util.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.config.UserValidator;
import com.util.customexception.ExceptionMessages;
import com.util.customexception.TaskException;
import com.util.dao.UserDAO;
import com.util.user.User;

public class UserRequestHandler {

    private final UserDAO userService = new UserDAO();
    UserRequestParser userRequestParser = new UserRequestParser();
    
    public void handleViewUsers(HttpServletRequest request, HttpServletResponse response)
            throws TaskException, ServletException, IOException {
    	
        List<User> userList = userService.getAllUsers();
        
        HttpSession session = request.getSession();
        session.setAttribute("userList", userList);
        response.sendRedirect("display.jsp");
        
    
    }

    public void handleEditUser(HttpServletRequest request, HttpServletResponse response)
            throws TaskException, ServletException, IOException {
    	
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.getUserById(id);

        if (user != null) {
        	
            request.setAttribute("user", user);
            request.setAttribute("action", "update");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }
        else {
            throw new TaskException(ExceptionMessages.USER_NOTFOUND + id);
        }
    }

    public void handleCreateUser(HttpServletRequest request, HttpServletResponse response)
            throws TaskException, ServletException, IOException {
        
    	User user = userRequestParser.parseUserFromRequest(request);
        String error = UserValidator.validateInput(user, true);

        if (error != null) {
            request.setAttribute("message", error);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        User createdUser = userService.createUser(user);
        request.setAttribute("message", "User created successfully!");
        request.setAttribute("user", createdUser);
        request.setAttribute("action", "update");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    public void handleUpdateUser(HttpServletRequest request, HttpServletResponse response)
            throws TaskException, ServletException, IOException {
    	
        User user = userRequestParser.parseUserFromRequest(request);
        String error = UserValidator.validateInput(user, false);
       
        if (error != null) {
        	
            request.setAttribute("message", error);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        System.out.println(error);
        

        boolean success = userService.updateUser(user);
        request.setAttribute("message", success ? "User updated" : "User not found");
        request.setAttribute("user", user);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    public void handleDeleteUser(HttpServletRequest request, HttpServletResponse response)
            throws TaskException, IOException {
    	
        int id = Integer.parseInt(request.getParameter("id"));
        boolean success = userService.deleteUser(id);

        HttpSession session = request.getSession();
        session.setAttribute("message", success ? "User deleted successfully!" : "User not found.");
        response.sendRedirect("submitForm?action=view");
    }
}