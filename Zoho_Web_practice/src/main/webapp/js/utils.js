function clearForm() {
    location.replace("/test1");
}

function viewData(action) {
    document.getElementById("action").value = action;
    document.getElementById("signupForm").submit();
}

function toggleMode() {
    document.body.classList.toggle('dark-mode');
    const icon = document.getElementById('modeToggleIcon');
    icon.textContent = document.body.classList.contains('dark-mode') ? 'light_mode' : 'dark_mode';
    localStorage.setItem('darkMode', document.body.classList.contains('dark-mode'));
}
