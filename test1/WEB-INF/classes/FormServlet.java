import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FormServlet extends HttpServlet {
    private DatabaseUtil dbUtil;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        try {
            dbUtil = new DatabaseUtil();
            userDAO = new UserDAO(dbUtil.getConnection());
        } catch (SQLException e) {
            throw new ServletException("Database initialization failed: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("view".equals(action)) {
            doPost(request, response);
        } else {
            request.setAttribute("message", "GET method not supported.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

	//create
        if ("create".equals(action)) {
            try {
                User user = new User();
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
                user.setBalance(Double.parseDouble(request.getParameter("balance") != null ? request.getParameter("balance") : "0.0"));

                String error = UserValidator.validateInput(user, true);
                if (error != null) {
                    request.setAttribute("message", error);
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    return;
                }

                user = userDAO.createUser(user);
                session.setAttribute("id", user.getId());
                request.setAttribute("message", "Data inserted successfully!");
                request.setAttribute("action", "update");
                request.setAttribute("user", user);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (SQLException e) {
                request.setAttribute("message", "Database error: " + e.getMessage());
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } 
        
        
        //update
        else if ("update".equals(action)) {
            try {
                User user = new User();
                user.setId(Integer.parseInt(request.getParameter("id")));
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
                user.setAccountNumber(request.getParameter("account_number"));
                user.setIfscCode(request.getParameter("ifsc_code"));
                user.setBranchName(request.getParameter("branch_name"));
                user.setAccountType(request.getParameter("account_type"));
                user.setBalance(Double.parseDouble(request.getParameter("balance") != null ? request.getParameter("balance") : "0.0"));

                String error = UserValidator.validateInput(user, false);
                if (error != null) {
                    request.setAttribute("message", error);
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    return;
                }

                boolean updated = userDAO.updateUser(user);
                request.setAttribute("message", updated ? "Record updated successfully!" : "No record found with ID: " + user.getId());
                request.setAttribute("user", user);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (SQLException e) {
                request.setAttribute("message", "Database error: " + e.getMessage());
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } 
        
        //delete
        else if ("delete".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                boolean deleted = userDAO.deleteUser(id);
                session.setAttribute("message", deleted ? "Record deleted successfully!" : "No record found with ID: " + id);
                response.sendRedirect("submitForm?action=view");
            } catch (SQLException e) {
                request.setAttribute("message", "Database error: " + e.getMessage());
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else if ("view".equals(action)) {
            try {
                List<User> userList = userDAO.getAllUsers();
                session.setAttribute("userList", userList);
                request.getRequestDispatcher("display.jsp").forward(request, response);
            } catch (SQLException e) {
                request.setAttribute("message", "Database error: " + e.getMessage());
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } 
        
        
        //edit
        else if ("edit".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                User user = userDAO.getUserById(id);
                if (user != null) {
                    request.setAttribute("user", user);
                    request.setAttribute("action", "update");
                } else {
                    request.setAttribute("message", "No record found with ID: " + id);
                }
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (SQLException e) {
                request.setAttribute("message", "Database error: " + e.getMessage());
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "Unknown action: " + action);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        if (dbUtil != null) {
            dbUtil.closeConnection();
        }
    }
}
