package com.util.controller;

import javax.servlet.http.HttpServletRequest;

import com.util.user.User;


public class UserRequestParser {

    public User parseUserFromRequest(HttpServletRequest request) {
        User user = new User();

        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            user.setId(Integer.parseInt(idStr));
        }

        user.setName(request.getParameter("name"));
        user.setFatherName(request.getParameter("father_name"));
        user.setMotherName(request.getParameter("mother_name"));
        user.setDob(request.getParameter("dob"));
        user.setEmail(request.getParameter("email"));
        user.setMobileNumber(request.getParameter("mobile"));
        user.setAddress(request.getParameter("address"));
        user.setAadharNumber(request.getParameter("aadhar"));
        user.setPanNumber(request.getParameter("pan"));
        user.setUsername(request.getParameter("u_name"));
        user.setPassword(request.getParameter("password"));
        user.setAccountNumber(request.getParameter("account_number"));
        user.setIfscCode(request.getParameter("ifsc_code"));
        user.setBranchName(request.getParameter("branch_name"));
        user.setAccountType(request.getParameter("account_type"));

        String balanceStr = request.getParameter("balance");
        if (balanceStr != null && !balanceStr.isEmpty()) {
            user.setBalance(Double.parseDouble(balanceStr));
        }

        return user;
    }
}