function toggleMode() {
    document.body.classList.toggle('dark-mode');
    const icon = document.getElementById('modeToggleIcon');
    icon.textContent = document.body.classList.contains('dark-mode') ? 'light_mode' : 'dark_mode';
    localStorage.setItem('darkMode', document.body.classList.contains('dark-mode'));
}

function confirmDelete() {
    return confirm("Are you sure you want to delete this user?");
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

function filterTable() {
    const input = document.getElementById('searchInput').value.toLowerCase();
    const rows = document.querySelectorAll('tbody tr');
    let serialNumber = 1;
    rows.forEach((row, index) => {
        const rowData = userData[index];
        if (rowData) {
            const allData = rowData.join(' ').toLowerCase();
            row.style.display = allData.includes(input) ? '' : 'none';
            if (allData.includes(input)) row.cells[0].textContent = serialNumber++;
        }
    });
}

document.addEventListener('DOMContentLoaded', () => {
    if (localStorage.getItem('darkMode') === 'true') {
        document.body.classList.add('dark-mode');
        document.getElementById('modeToggleIcon').textContent = 'light_mode';
    }
    <% if (message != null && !message.isEmpty()) { %>
        showToast("<%= message %>", <%= !message.contains("successfully") %>);
        <% session.removeAttribute("message"); %>
    <% } %>
    const searchInput = document.getElementById('searchInput');
    if (searchInput) searchInput.addEventListener('input', filterTable);
});
