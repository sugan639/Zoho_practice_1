function validateForm() {
    const rules = [
        { id: "name", regex: /^[A-Za-z\s]{2,50}$/, errorMsg: "Name must be 2-50 letters only." },
        { id: "father_name", regex: /^[A-Za-z\s]{2,50}$/, errorMsg: "Father's name must be 2-50 letters only." },
        { id: "mother_name", regex: /^[A-Za-z\s]{2,50}$/, errorMsg: "Mother's name must be 2-50 letters only." },
        { id: "dob", custom: value => {
            const today = new Date();
            const birthDate = new Date(value);
            let age = today.getFullYear() - birthDate.getFullYear();
            const monthDiff = today.getMonth() - birthDate.getMonth();
            if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) age--;
            return value && birthDate < today && age >= 18;
        }, errorMsg: "Must be at least 18 years old." },
        { id: "email", regex: /^[^\s@]+@[^\s@]+\.[^\s@]+$/, errorMsg: "Valid email required." },
        { id: "mobile", regex: /^\d{10}$/, errorMsg: "Mobile number must be 10 digits." },
        { id: "address", regex: /^[A-Za-z0-9\s,.-]{5,100}$/, errorMsg: "Address must be 5-100 characters." },
        { id: "aadhar", regex: /^\d{12}$/, errorMsg: "Aadhaar must be 12 digits." },
        { id: "pan", regex: /^[A-Z]{5}[0-9]{4}[A-Z]{1}$/, errorMsg: "PAN must be 5 letters, 4 digits, 1 letter." },
        { id: "username", regex: /^[A-Za-z0-9]{3,20}$/, errorMsg: "Username must be 3-20 alphanumeric characters." },
        { id: "password", regex: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,20}$/, errorMsg: "Password must be 8-20 characters with uppercase, lowercase, digit, and special character." },
        { id: "account_number", regex: /^\d{10,16}$/, errorMsg: "Account number must be 10-16 digits." },
        { id: "ifsc_code", regex: /^[A-Z]{4}0[A-Za-z0-9]{6}$/, errorMsg: "IFSC must follow format (e.g., ABCD0EF1234)." },
        { id: "branch_name", regex: /^[A-Za-z\s]{2,50}$/, errorMsg: "Branch name must be 2-50 letters only." },
        { id: "account_type", custom: value => value === "Savings" || value === "Current", errorMsg: "Account type must be Savings or Current." },
        { id: "balance", custom: value => !isNaN(value) && value >= 0, errorMsg: "Balance must be a positive number." }
    ];

    let isValid = true;
    rules.forEach(rule => {
        const input = document.getElementById(rule.id);
        const errorElement = document.getElementById(rule.id + "Error");
        const isValidRule = rule.regex ? rule.regex.test(input.value) : rule.custom(input.value);
        if (!isValidRule) {
            errorElement.style.display = "block";
            input.classList.add("invalid");
            isValid = false;
        } else {
            errorElement.style.display = "none";
            input.classList.remove("invalid");
        }
    });

    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirm_password").value;
    const confirmError = document.getElementById("confirmPasswordError");
    if (password !== confirmPassword) {
        confirmError.style.display = "block";
        document.getElementById("confirm_password").classList.add("invalid");
        isValid = false;
    } else {
        confirmError.style.display = "none";
        document.getElementById("confirm_password").classList.remove("invalid");
    }

    return isValid;
}
