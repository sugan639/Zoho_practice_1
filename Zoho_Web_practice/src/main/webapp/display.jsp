<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.lang.reflect.*" %>

<%
	Class<?> userClass = null;
	Method getName = null, getEmail = null, getUsername = null, getId = null;
	List<?> userList = (List<?>) session.getAttribute("userList");
	String message = (String) session.getAttribute("message");
    if (userList != null && !userList.isEmpty()) {
        Object sampleUser = userList.get(0);
        if (sampleUser != null) {
            userClass = sampleUser.getClass();
            getName = userClass.getMethod("getName");
            getEmail = userClass.getMethod("getEmail");
            getUsername = userClass.getMethod("getUsername");
            getId = userClass.getMethod("getId");
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Display Users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
    <style>
        body { background: radial-gradient(circle at top left, #e9f0fa, #f7f9fc, #ffffff); font-family: 'Poppins', sans-serif; margin: 0; transition: all 0.3s ease; }
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
        .container { max-width: 95%; margin: 80px auto 20px; padding: 25px; border-radius: 20px; background: #f0f2f5; box-shadow: 6px 6px 12px rgba(0, 0, 0, 0.05), -6px -6px 12px rgba(255, 255, 255, 0.8); transition: all 0.3s ease; min-height: calc(100vh - 100px); }
        .dark-mode .container { background: #2d2d2d; box-shadow: 0 4px 15px rgba(0, 0, 0, 0.4); }
        .table { width: 100%; border-radius: 12px; overflow: hidden; background-color: #ffffff; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05); }
        .dark-mode .table { background-color: #333333; }
        thead { background: linear-gradient(135deg, #48c9b0, #16a085); color: #f5f6f5; text-align: center; }
        th { padding: 14px 16px; font-weight: 600; border: none; border-right: 2px solid rgba(255, 255, 255, 0.5); text-align: center; }
        th:last-child { border-right: none; }
        td { padding: 14px 16px; border: 1px solid #e9ecef; text-align: center; }
        tbody tr:nth-child(odd) td { background-color: #fdfdfd; color: #333; }
        tbody tr:nth-child(even) td { background-color: #f7f9fc; color: #333; }
        tbody tr:hover td { background-color: #eef2f7; }
        .dark-mode tbody tr:nth-child(odd) td { background-color: #3a3a3a; color: #e0e0e0; }
        .dark-mode tbody tr:nth-child(even) td { background-color: #2d2d2d; color: #e0e0e0; }
        .dark-mode tbody tr:hover td { background-color: #444; }
        .btn-container { display: flex; justify-content: center; gap: 10px; }
        .action-btn { border: none; background: none; font-size: 20px; cursor: pointer; padding: 8px; border-radius: 50%; transition: background-color 0.2s ease; }
        .action-btn:hover { background-color: rgba(74, 144, 226, 0.1); }
        .edit-btn { color: #ff9800; }
        .edit-btn:hover { color: #e68900; }
        .delete-btn { color: #e63946; }
        .delete-btn:hover { color: #c82333; }
        .dark-mode .edit-btn { color: #ffb74d; }
        .dark-mode .edit-btn:hover { color: #ff9800; }
        .dark-mode .delete-btn { color: #ef5350; }
        .dark-mode .delete-btn:hover { color: #dc3545; }
        .table-title { text-align: center; font-size: 26px; font-weight: 600; color: #357abd; margin-bottom: 20px; padding: 10px; background: #e3f2fd; border-radius: 10px; display: flex; align-items: center; justify-content: space-between; }
        .dark-mode .table-title { color: #66b3ff; background: #1a2b3c; }
        .search-container { flex-grow: 1; margin: 0 20px; max-width: 300px; position: relative; }
        .search-input { width: 100%; padding: 10px 40px 10px 15px; border: none; border-radius: 25px; background: rgba(255, 255, 255, 0.9); box-shadow: inset 2px 2px 5px rgba(0, 0, 0, 0.05); font-size: 14px; color: #495057; }
        .search-input:focus { background: #ffffff; box-shadow: 0 0 10px rgba(74, 144, 226, 0.5); outline: none; }
        .dark-mode .search-input { background: #444; border: 1px solid #666; color: #e0e0e0; }
        .dark-mode .search-input:focus { border-color: #66b3ff; box-shadow: 0 0 8px rgba(102, 179, 255, 0.5); }
        .search-icon { position: absolute; right: 12px; top: 50%; transform: translateY(-50%); color: #4a90e2; font-size: 20px; }
        .dark-mode .search-icon { color: #b0bec5; }
        .add-btn { background: #28a745; color: white; width: 40px; height: 40px; display: flex; align-items: center; justify-content: center; border-radius: 50%; box-shadow: 0 4px 10px rgba(40, 167, 69, 0.3); transition: transform 0.2s ease, background 0.2s ease; }
        .add-btn:hover { background: #218838; transform: scale(1.1); }
        .no-data-container { display: flex; flex-direction: column; align-items: center; justify-content: center; flex-grow: 1; }
        .no-data-container .title { font-size: 26px; font-weight: 600; color: #357abd; margin-bottom: 20px; }
        .dark-mode .no-data-container .title { color: #66b3ff; }
        .no-data-container p { font-size: 18px; margin-bottom: 20px; }
        .toast-container { position: fixed; top: 20px; right: 20px; z-index: 1000; }
        .toast { background: #28a745; color: white; padding: 15px 20px; border-radius: 8px; margin-bottom: 10px; box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2); opacity: 0; transform: translateX(100%); transition: opacity 0.3s ease, transform 0.3s ease; }
        .toast.error { background: #e63946; }
        .toast.show { opacity: 1; transform: translateX(0); }
    </style>
    <script>
    <% if (userList != null && !userList.isEmpty()) { %>
    <script>
        const userData = [
            <% for (int i = 0; i < userList.size(); i++) {
                Object user = userList.get(i);
                String name = "N/A";
                String email = "N/A";
                String username = "N/A";
                String id = "0";

                if (user != null) {
                    try {
                        name = (String) getName.invoke(user);
                        email = (String) getEmail.invoke(user);
                        username = (String) getUsername.invoke(user);
                        id = String.valueOf(getId.invoke(user));
                    } catch (Exception e) {
                       
                    }
                }

                // Escape quotes for JSON
                name = name.replace("\"", "\\\"");
                email = email.replace("\"", "\\\"");
                username = username.replace("\"", "\\\"");

                out.print("[\"" + name + "\",\"" + email + "\",\"" + username + "\"]");
                if (i < userList.size() - 1) out.print(",");
            } %>
        ];
   

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
                    if (allData.includes(input)) {
                        row.style.display = '';
                        row.cells[0].textContent = serialNumber++;
                    } else {
                        row.style.display = 'none';
                    }
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
    <div class="container">
        <% if (userList != null && !userList.isEmpty()) { %>
            <div class="table-title">
                <span class="flex-grow-1 text-center">Users</span>
                <div class="search-container">
                    <input type="text" id="searchInput" class="search-input" placeholder="Search users...">
                    <span class="material-icons search-icon">search</span>
                </div>
                <button class="add-btn" onclick="location.replace('/Zoho_Web_practice');" title="Add New User">
                    <span class="material-icons">add</span>
                </button>
            </div>
            <div class="table-responsive">
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>S.No</th>
                            <th>Full Name</th>
                            <th>Email</th>
                            <th>Username</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                       <% int serialNumber = 1;
						if (userList != null && !userList.isEmpty()) {
						    for (Object user : userList) {
						        String rowId = "0";
						        String name = "N/A";
						        String email = "N/A";
						        String username = "N/A";
						
						        if (user != null) {
						            try {
						                rowId = String.valueOf(getId.invoke(user));
						                name = (String) getName.invoke(user);
						                email = (String) getEmail.invoke(user);
						                username = (String) getUsername.invoke(user);
						            } catch (Exception ignored) {}
						        }
						%>
<tr id="row-<%= rowId %>">
    <td><%= serialNumber++ %></td>
    <td><%= name != null ? name : "N/A" %></td>
    <td><%= email != null ? email : "N/A" %></td>
    <td><%= username != null ? username : "N/A" %></td>
    <td>
        <div class="btn-container">
            <form action="submitForm" method="post">
                <input type="hidden" name="id" value="<%= rowId %>">
                <input type="hidden" name="action" value="edit">
                <button type="submit" class="action-btn edit-btn" title="Edit">
                    <span class="material-icons">edit</span>
                </button>
            </form>
            <form action="submitForm" method="post" onsubmit="return confirmDelete();">
                <input type="hidden" name="id" value="<%= rowId %>">
                <input type="hidden" name="action" value="delete">
                <button type="submit" class="action-btn delete-btn" title="Delete">
                    <span class="material-icons">delete</span>
                </button>
            </form>
        </div>
    </td>
</tr>
<% } %>
                    </tbody>
                </table>
            </div>
        <% } else { %>
            <div class="no-data-container">
                <span class="title">Users</span>
                <p>No user data available.</p>
                <button class="add-btn" onclick="location.replace('/Zoho_Web_practice');" title="Add New User">
                    <span class="material-icons">add</span>
                </button>
            </div>
        <% } }}%>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
