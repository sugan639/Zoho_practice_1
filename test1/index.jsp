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
    String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Banking Sign-Up</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; font-family: 'Poppins', sans-serif; }
        body { background: linear-gradient(to right, #f8f9fa, #e9ecef); transition: all 0.3s ease; margin: 0; }
        body.dark-mode { background: linear-gradient(to right, #1a1a1a, #2c3e50); color: #e0e0e0; }
        .header { background: #357abd; color: white; padding: 15px 20px; display: flex; justify-content: space-between; align-items: center; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); position: fixed; top: 0; left: 0; width: 100%; z-index: 1000; }
        .dark-mode .header { background: #1a2b3c; }
        .header .logo-container { display: flex; align-items: center; gap: 10px; }
        .header .logo { width: 40px; height: 40px; background: linear-gradient(135deg, #ffffff, #ffd700); border-radius: 50%; display: flex; align-items: center; justify-content: center; box-shadow: 0 0 10px rgba(255, 215, 0, 0.5); transition: transform 0.3s ease; }
        .header .logo:hover { transform: rotate(360deg); }
        .header .logo::before { content: 'S'; font-size: 24px; font-weight: 600; color: #357abd; }
        .dark-mode .header .logo { background: linear-gradient(135deg, #ffd700, #ffffff); }
        .dark-mode .header .logo::before { color: #1a2b3c; }
        .header .product-name { font-size: 24px; font-weight: 600; }
        .header .mode-btn { border: none; background: none; color: white; font-size: 20px; cursor: pointer; padding: 5px; transition: transform 0.2s ease; }
        .header .mode-btn:hover { transform: scale(1.1); }
        .form-container { background: white; padding: 30px; border-radius: 12px; width: 500px; text-align: center; box-shadow: 0 0 15px rgba(0, 0, 0, 0.1); margin: 80px auto; transition: all 0.3s ease; }
        .dark-mode .form-container { background: #2d2d2d; box-shadow: 0 0 15px rgba(0, 0, 0, 0.4); }
        .dark-mode .form-container h2 { color: #66b3ff; }
        .dark-mode .form-group label { color: #e0e0e0; }
        .dark-mode .form-group input, .dark-mode .form-group select { background: #444; border: 1px solid #666; color: #e0e0e0; }
        .dark-mode .button-group button { border: 1px solid rgba(255, 255, 255, 0.1); }
        .form-container h2 { font-weight: 600; margin-bottom: 20px; color: #007bff; }
        .form-group { margin-bottom: 15px; width: 100%; }
        .form-group label { font-size: 14px; color: #495057; margin-bottom: 5px; font-weight: 500; display: block; text-align: left; }
        .form-group input, .form-group select { width: 100%; border: 2px solid #ced4da; border-radius: 8px; outline: none; font-size: 16px; padding: 10px 15px; background: #f8f9fa; color: #495057; transition: all 0.3s ease; }
        .form-group input.invalid, .form-group select.invalid { border-color: #dc3545; }
        .error-message { color: red; font-size: 12px; display: none; text-align: left; margin-top: 5px; }
        .dark-mode .error-message { color: #ff6666; }
        .button-group { display: flex; justify-content: space-between; gap: 10px; margin-top: 20px; }
        .button-group button { flex: 1; padding: 12px; border: none; border-radius: 25px; font-size: 16px; font-weight: 500; color: white; cursor: pointer; transition: all 0.3s ease; box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2); }
        .button-group button:hover { transform: translateY(-2px); box-shadow: 0 6px 20px rgba(0, 0, 0, 0.3); }
        .submit-btn { background: linear-gradient(135deg, #007bff, #0056b3); }
        .submit-btn:hover { background: linear-gradient(135deg, #0056b3, #003d82); }
        .clear-btn { background: linear-gradient(135deg, #6c757d, #5a6268); }
        .clear-btn:hover { background: linear-gradient(135deg, #5a6268, #4b5259); }
        .view-btn { background: linear-gradient(135deg, #28a745, #218838); }
        .view-btn:hover { background: linear-gradient(135deg, #218838, #1c6d31); }
        .dark-mode .submit-btn { box-shadow: 0 4px 15px rgba(0, 123, 255, 0.4); }
        .dark-mode .clear-btn { box-shadow: 0 4px 15px rgba(108, 117, 125, 0.4); }
        .dark-mode .view-btn { box-shadow: 0 4px 15px rgba(40, 167, 69, 0.4); }
        .toast-container { position: fixed; top: 20px; right: 20px; z-index: 1000; }
        .toast { background: #28a745; color: white; padding: 15px 20px; border-radius: 8px; margin-bottom: 10px; box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2); opacity: 0; transform: translateX(100%); transition: opacity 0.3s ease, transform 0.3s ease; }
        .toast.error { background: #dc3545; }
        .toast.show { opacity: 1; transform: translateX(0); }
    </style>
    <script>
        function clearForm() {
            location.replace("/test1");
        }
        function viewData(action) {
            document.getElementById("action").value = action;
            document.getElementById("signupForm").submit();
        }
        function validateForm() {
            let isValid = true;
            const name = document.getElementById("name");
            const nameRegex = /^[A-Za-z\s]{2,50}$/;
            if (!nameRegex.test(name.value)) {
                document.getElementById("nameError").style.display = "block";
                name.classList.add("invalid");
                isValid = false;
            } else {
                document.getElementById("nameError").style.display = "none";
                name.classList.remove("invalid");
            }
            const fatherName = document.getElementById("father_name");
            if (!nameRegex.test(fatherName.value)) {
                document.getElementById("fatherNameError").style.display = "block";
                fatherName.classList.add("invalid");
                isValid = false;
            } else {
                document.getElementById("fatherNameError").style.display = "none";
                fatherName.classList.remove("invalid");
            }
            const motherName = document.getElementById("mother_name");
            if (!nameRegex.test(motherName.value)) {
                document.getElementById("motherNameError").style.display = "block";
                motherName.classList.add("invalid");
                isValid = false;
            } else {
                document.getElementById("motherNameError").style.display = "none";
                motherName.classList.remove("invalid");
            }
            const dob = document.getElementById("dob");
            const today = new Date();
            const birthDate = new Date(dob.value);
            let age = today.getFullYear() - birthDate.getFullYear();
            const monthDiff = today.getMonth() - birthDate.getMonth();
            if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) age--;
            if (!dob.value || birthDate >= today || age < 18) {
                document.getElementById("dobError").style.display = "block";
                dob.classList.add("invalid");
                isValid = false;
            } else {
                document.getElementById("dobError").style.display = "none";
                dob.classList.remove("invalid");
            }
            const email = document.getElementById("email");
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(email.value)) {
                document.getElementById("emailError").style.display = "block";
                email.classList.add("invalid");
                isValid = false;
            } else {
                document.getElementById("emailError").style.display = "none";
                email.classList.remove("invalid");
            }
            const mobile = document.getElementById("mobile");
            const mobileRegex = /^\d{10}$/;
            if (!mobileRegex.test(mobile.value)) {
                document.getElementById("mobileError").style.display = "block";
                mobile.classList.add("invalid");
                isValid = false;
            } else {
                document.getElementById("mobileError").style.display = "none";
                mobile.classList.remove("invalid");
            }
            const address = document.getElementById("address");
            const addressRegex = /^[A-Za-z0-9\s,.-]{5,100}$/;
            if (!addressRegex.test(address.value)) {
                document.getElementById("addressError").style.display = "block";
                address.classList.add("invalid");
                isValid = false;
            } else {
                document.getElementById("addressError").style.display = "none";
                address.classList.remove("invalid");
            }
            const aadhar = document.getElementById("aadhar");
            const aadharRegex = /^\d{12}$/;
            if (!aadharRegex.test(aadhar.value)) {
                document.getElementById("aadharError").style.display = "block";
                aadhar.classList.add("invalid");
                isValid = false;
            } else {
                document.getElementById("aadharError").style.display = "none";
                aadhar.classList.remove("invalid");
            }
            const pan = document.getElementById("pan");
            const panRegex = /^[A-Z]{5}[0-9]{4}[A-Z]{1}$/;
            if (!panRegex.test(pan.value)) {
                document.getElementById("panError").style.display = "block";
                pan.classList.add("invalid");
                isValid = false;
            } else {
                document.getElementById("panError").style.display = "none";
                pan.classList.remove("invalid");
            }
            const username = document.getElementById("username");
            const usernameRegex = /^[A-Za-z0-9]{3,20}$/;
            if (!usernameRegex.test(username.value)) {
                document.getElementById("usernameError").style.display = "block";
                username.classList.add("invalid");
                isValid = false;
            } else {
                document.getElementById("usernameError").style.display = "none";
                username.classList.remove("invalid");
            }
            const password = document.getElementById("password");
            const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,20}$/;
            if (!passwordRegex.test(password.value)) {
                document.getElementById("passwordError").style.display = "block";
                password.classList.add("invalid");
                isValid = false;
            } else {
                document.getElementById("passwordError").style.display = "none";
                password.classList.remove("invalid");
            }
            const confirmPassword = document.getElementById("confirm_password");
            if (password.value !== confirmPassword.value) {
                document.getElementById("confirmPasswordError").style.display = "block";
                confirmPassword.classList.add("invalid");
                isValid = false;
            } else {
                document.getElementById("confirmPasswordError").style.display = "none";
                confirmPassword.classList.remove("invalid");
            }
            const accountNumber = document.getElementById("account_number");
            const accountNumberRegex = /^\d{10,16}$/;
            if (!accountNumberRegex.test(accountNumber.value)) {
                document.getElementById("accountNumberError").style.display = "block";
                accountNumber.classList.add("invalid");
                isValid = false;
            } else {
                document.getElementById("accountNumberError").style.display = "none";
                accountNumber.classList.remove("invalid");
            }
            const ifscCode = document.getElementById("ifsc_code");
            const ifscRegex = /^[A-Z]{4}0[A-Za-z0-9]{6}$/;
            if (!ifscRegex.test(ifscCode.value)) {
                document.getElementById("ifscCodeError").style.display = "block";
                ifscCode.classList.add("invalid");
                isValid = false;
            } else {
                document.getElementById("ifscCodeError").style.display = "none";
                ifscCode.classList.remove("invalid");
            }
            const branchName = document.getElementById("branch_name");
            if (!nameRegex.test(branchName.value)) {
                document.getElementById("branchNameError").style.display = "block";
                branchName.classList.add("invalid");
                isValid = false;
            } else {
                document.getElementById("branchNameError").style.display = "none";
                branchName.classList.remove("invalid");
            }
            const accountType = document.getElementById("account_type");
            if (accountType.value !== "Savings" && accountType.value !== "Current") {
                document.getElementById("accountTypeError").style.display = "block";
                accountType.classList.add("invalid");
                isValid = false;
            } else {
                document.getElementById("accountTypeError").style.display = "none";
                accountType.classList.remove("invalid");
            }
            const balance = document.getElementById("balance");
            if (isNaN(balance.value) || balance.value < 0) {
                document.getElementById("balanceError").style.display = "block";
                balance.classList.add("invalid");
                isValid = false;
            } else {
                document.getElementById("balanceError").style.display = "none";
                balance.classList.remove("invalid");
            }
            return isValid;
        }
        function toggleMode() {
            document.body.classList.toggle('dark-mode');
            const icon = document.getElementById('modeToggleIcon');
            icon.textContent = document.body.classList.contains('dark-mode') ? 'light_mode' : 'dark_mode';
            localStorage.setItem('darkMode', document.body.classList.contains('dark-mode'));
        }
        function showToast(message, isError) {
            const toastContainer = document.querySelector('.toast-container');
            const toast = document.createElement('div');
            toast.classList.add('toast');
            if (isError) toast.classList.add('error');
            toast.textContent = message;
            toastContainer.appendChild(toast);
            setTimeout(() => toast.classList.add('show'), 100);
            setTimeout(() => {
                toast.classList.remove('show');
                setTimeout(() => toast.remove(), 300);
            }, 3000);
        }
        window.onload = function() {
            if (localStorage.getItem('darkMode') === 'true') {
                document.body.classList.add('dark-mode');
                document.getElementById('modeToggleIcon').textContent = 'light_mode';
            }
            <% if (message != null && !message.isEmpty()) { %>
                showToast("<%= message %>", <%= !message.contains("successfully") %>);
            <% } %>
        }
    </script>
</head>
<body>
    <div class="header">
        <div class="logo-container">
            <div class="logo"></div>
            <span class="product-name">Sugan Banking</span>
        </div>
        <button class="mode-btn" onclick="toggleMode()">
            <span class="material-icons" id="modeToggleIcon">dark_mode</span>
        </button>
    </div>
    <div class="toast-container"></div>
    <div class="form-container">
        <h2><i class="fa fa-university"></i> Banking Sign-Up</h2>
        <form id="signupForm" action="submitForm" method="post" onsubmit="return validateForm()">
            <div class="form-group">
                <label for="name">Full Name</label>
                <input type="text" id="name" name="name" value="<%= getName.invoke(user) %>" required>
                <span id="nameError" class="error-message">Name must be 2-50 letters only.</span>
            </div>
            <div class="form-group">
                <label for="father_name">Father's Name</label>
                <input type="text" id="father_name" name="father_name" value="<%= getFatherName.invoke(user) %>" required>
                <span id="fatherNameError" class="error-message">Father's name must be 2-50 letters only.</span>
            </div>
            <div class="form-group">
                <label for="mother_name">Mother's Name</label>
                <input type="text" id="mother_name" name="mother_name" value="<%= getMotherName.invoke(user) %>" required>
                <span id="motherNameError" class="error-message">Mother's name must be 2-50 letters only.</span>
            </div>
            <div class="form-group">
                <label for="dob">Date of Birth</label>
                <input type="date" id="dob" name="dob" value="<%= getDob.invoke(user) %>" required>
                <span id="dobError" class="error-message">Must be at least 18 years old.</span>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" value="<%= getEmail.invoke(user) %>" required>
                <span id="emailError" class="error-message">Valid email required.</span>
            </div>
            <div class="form-group">
                <label for="mobile">Mobile Number</label>
                <input type="text" id="mobile" name="mobile" value="<%= getMobileNumber.invoke(user) %>" required>
                <span id="mobileError" class="error-message">Mobile number must be 10 digits.</span>
            </div>
            <div class="form-group">
                <label for="address">Address</label>
                <input type="text" id="address" name="address" value="<%= getAddress.invoke(user) %>" required>
                <span id="addressError" class="error-message">Address must be 5-100 characters.</span>
            </div>
            <div class="form-group">
                <label for="aadhar">Aadhar Number</label>
                <input type="text" id="aadhar" name="aadhar" value="<%= getAadharNumber.invoke(user) %>" required>
                <span id="aadharError" class="error-message">Aadhaar must be 12 digits.</span>
            </div>
            <div class="form-group">
                <label for="pan">PAN Number</label>
                <input type="text" id="pan" name="pan" value="<%= getPanNumber.invoke(user) %>" required>
                <span id="panError" class="error-message">PAN must be 5 letters, 4 digits, 1 letter.</span>
            </div>
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="u_name" value="<%= getUsername.invoke(user) %>" required>
                <span id="usernameError" class="error-message">Username must be 3-20 alphanumeric characters.</span>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" value="<%= getPassword.invoke(user) %>" required>
                <span id="passwordError" class="error-message">Password must be 8-20 characters with uppercase, lowercase, digit, and special character.</span>
            </div>
            <div class="form-group">
                <label for="confirm_password">Confirm Password</label>
                <input type="password" id="confirm_password" name="confirm_password" required>
                <span id="confirmPasswordError" class="error-message">Passwords do not match.</span>
            </div>
            <div class="form-group">
                <label for="account_number">Account Number</label>
                <input type="text" id="account_number" name="account_number" value="<%= getAccountNumber.invoke(user) %>" required>
                <span id="accountNumberError" class="error-message">Account number must be 10-16 digits.</span>
            </div>
            <div class="form-group">
                <label for="ifsc_code">IFSC Code</label>
                <input type="text" id="ifsc_code" name="ifsc_code" value="<%= getIfscCode.invoke(user) %>" required>
                <span id="ifscCodeError" class="error-message">IFSC must follow format (e.g., ABCD0EF1234).</span>
            </div>
            <div class="form-group">
                <label for="branch_name">Branch Name</label>
                <input type="text" id="branch_name" name="branch_name" value="<%= getBranchName.invoke(user) %>" required>
                <span id="branchNameError" class="error-message">Branch name must be 2-50 letters only.</span>
            </div>
            <div class="form-group">
                <label for="account_type">Account Type</label>
                <select id="account_type" name="account_type" required>
                    <option value="" <%= ((String) getAccountType.invoke(user)).isEmpty() ? "selected" : "" %>>Select</option>
                    <option value="Savings" <%= "Savings".equals(getAccountType.invoke(user)) ? "selected" : "" %>>Savings</option>
                    <option value="Current" <%= "Current".equals(getAccountType.invoke(user)) ? "selected" : "" %>>Current</option>
                </select>
                <span id="accountTypeError" class="error-message">Account type must be Savings or Current.</span>
            </div>
            <div class="form-group">
                <label for="balance">Initial Balance</label>
                <input type="number" id="balance" name="balance" value="<%= getBalance.invoke(user) %>" step="0.01" required>
                <span id="balanceError" class="error-message">Balance must be a positive number.</span>
            </div>
            <% if ((Integer) getId.invoke(user) != 0 || id != null) { %>
                <input type="hidden" name="id" value="<%= (Integer) getId.invoke(user) != 0 ? getId.invoke(user) : id %>">
            <% } %>
            <input type="hidden" name="action" id="action" value="<%= action %>">
            <div class="button-group">
                <button type="button" class="view-btn" onclick="viewData('view')">View Data</button>
                <button type="button" class="clear-btn" onclick="clearForm()">Clear</button>
                <button type="submit" class="submit-btn"><i class="fa fa-paper-plane"></i> <%= action.equals("create") ? "Submit" : "Update" %></button>
            </div>
        </form>
    </div>
</body>
</html>
