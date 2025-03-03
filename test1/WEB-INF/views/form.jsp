<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Class<?> userClass = Class.forName("User");
    Object user = request.getAttribute("user");
    if (user == null) {
        user = userClass.getDeclaredConstructor().newInstance();
    }
    java.lang.reflect.Method getName = userClass.getMethod("getName");
    java.lang.reflect.Method getFatherName = userClass.getMethod("getFatherName");
    java.lang.reflect.Method getMotherName = userClass.getMethod("getMotherName");
    java.lang.reflect.Method getDob = userClass.getMethod("getDob");
    java.lang.reflect.Method getEmail = userClass.getMethod("getEmail");
    java.lang.reflect.Method getMobileNumber = userClass.getMethod("getMobileNumber");
    java.lang.reflect.Method getAddress = userClass.getMethod("getAddress");
    java.lang.reflect.Method getAadharNumber = userClass.getMethod("getAadharNumber");
    java.lang.reflect.Method getPanNumber = userClass.getMethod("getPanNumber");
    java.lang.reflect.Method getUsername = userClass.getMethod("getUsername");
    java.lang.reflect.Method getPassword = userClass.getMethod("getPassword");
    java.lang.reflect.Method getAccountNumber = userClass.getMethod("getAccountNumber");
    java.lang.reflect.Method getIfscCode = userClass.getMethod("getIfscCode");
    java.lang.reflect.Method getBranchName = userClass.getMethod("getBranchName");
    java.lang.reflect.Method getAccountType = userClass.getMethod("getAccountType");
    java.lang.reflect.Method getBalance = userClass.getMethod("getBalance");
    java.lang.reflect.Method getId = userClass.getMethod("getId");

    String action = request.getAttribute("action") != null ? (String) request.getAttribute("action") : "create";
    Integer id = (Integer) session.getAttribute("id");
%>
<div class="view-btn-container">
    <button class="view-btn" onclick="viewData('view')">View Data</button>
</div>
<div class="form-container">
    <h2><i class="fa fa-university"></i> Banking Sign-Up</h2>
    <form id="signupForm" action="submitForm" method="post" onsubmit="return validateForm()">
        <div class="form-group">
            <label for="name">Full Name</label>
            <input type="text" id="name" name="name" value="<%= getName.invoke(user) %>" required>
            <span id="nameError" class="error-message"></span>
        </div>
        <div class="form-group">
            <label for="father_name">Father's Name</label>
            <input type="text" id="father_name" name="father_name" value="<%= getFatherName.invoke(user) %>" required>
            <span id="fatherNameError" class="error-message"></span>
        </div>
        <div class="form-group">
            <label for="mother_name">Mother's Name</label>
            <input type="text" id="mother_name" name="mother_name" value="<%= getMotherName.invoke(user) %>" required>
            <span id="motherNameError" class="error-message"></span>
        </div>
        <div class="form-group">
            <label for="dob">Date of Birth</label>
            <input type="date" id="dob" name="dob" value="<%= getDob.invoke(user) %>" required>
            <span id="dobError" class="error-message"></span>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" value="<%= getEmail.invoke(user) %>" required>
            <span id="emailError" class="error-message"></span>
        </div>
        <div class="form-group">
            <label for="mobile">Mobile Number</label>
            <input type="text" id="mobile" name="mobile" value="<%= getMobileNumber.invoke(user) %>" required>
            <span id="mobileError" class="error-message"></span>
        </div>
        <div class="form-group">
            <label for="address">Address</label>
            <input type="text" id="address" name="address" value="<%= getAddress.invoke(user) %>" required>
            <span id="addressError" class="error-message"></span>
        </div>
        <div class="form-group">
            <label for="aadhar">Aadhar Number</label>
            <input type="text" id="aadhar" name="aadhar" value="<%= getAadharNumber.invoke(user) %>" required>
            <span id="aadharError" class="error-message"></span>
        </div>
        <div class="form-group">
            <label for="pan">PAN Number</label>
            <input type="text" id="pan" name="pan" value="<%= getPanNumber.invoke(user) %>" required>
            <span id="panError" class="error-message"></span>
        </div>
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" id="username" name="u_name" value="<%= getUsername.invoke(user) %>" required>
            <span id="usernameError" class="error-message"></span>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" value="<%= getPassword.invoke(user) %>" required>
            <span id="passwordError" class="error-message"></span>
        </div>
        <div class="form-group">
            <label for="confirm_password">Confirm Password</label>
            <input type="password" id="confirm_password" name="confirm_password" required>
            <span id="confirmPasswordError" class="error-message"></span>
        </div>
        <div class="form-group">
            <label for="account_number">Account Number</label>
            <input type="text" id="account_number" name="account_number" value="<%= getAccountNumber.invoke(user) %>" required>
            <span id="accountNumberError" class="error-message"></span>
        </div>
        <div class="form-group">
            <label for="ifsc_code">IFSC Code</label>
            <input type="text" id="ifsc_code" name="ifsc_code" value="<%= getIfscCode.invoke(user) %>" required>
            <span id="ifscCodeError" class="error-message"></span>
        </div>
        <div class="form-group">
            <label for="branch_name">Branch Name</label>
            <input type="text" id="branch_name" name="branch_name" value="<%= getBranchName.invoke(user) %>" required>
            <span id="branchNameError" class="error-message"></span>
        </div>
        <div class="form-group">
            <label for="account_type">Account Type</label>
            <select id="account_type" name="account_type" required>
                <option value="" <%= ((String) getAccountType.invoke(user)).isEmpty() ? "selected" : "" %>>Select</option>
                <option value="Savings" <%= "Savings".equals(getAccountType.invoke(user)) ? "selected" : "" %>>Savings</option>
                <option value="Current" <%= "Current".equals(getAccountType.invoke(user)) ? "selected" : "" %>>Current</option>
            </select>
            <span id="accountTypeError" class="error-message"></span>
        </div>
        <div class="form-group">
            <label for="balance">Initial Balance</label>
            <input type="number" id="balance" name="balance" value="<%= getBalance.invoke(user) %>" step="0.01" required>
            <span id="balanceError" class="error-message"></span>
        </div>
        <% if ((Integer) getId.invoke(user) != 0 || id != null) { %>
            <input type="hidden" name="id" value="<%= (Integer) getId.invoke(user) != 0 ? getId.invoke(user) : id %>">
        <% } %>
        <input type="hidden" name="action" id="action" value="<%= action %>">
        <div class="button-group">
            <button type="button" class="clear-btn" onclick="clearForm()">Clear</button>
            <button type="submit" class="submit-btn"><i class="fa fa-paper-plane"></i> <%= action.equals("create") ? "Submit" : "Update" %></button>
        </div>
    </form>
</div>
