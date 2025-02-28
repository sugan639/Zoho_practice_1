import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection con;

    public UserDAO(Connection con) {
        this.con = con;
    }

    public User createUser(User user) throws SQLException {
        String query = "INSERT INTO users (name, father_name, mother_name, dob, email, mobile_number, address, aadhar_number, pan_number, username, password, account_number, ifsc_code, branch_name, account_type, balance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getFatherName());
            ps.setString(3, user.getMotherName());
            ps.setString(4, user.getDob());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getMobileNumber());
            ps.setString(7, user.getAddress());
            ps.setString(8, user.getAadharNumber());
            ps.setString(9, user.getPanNumber());
            ps.setString(10, user.getUsername());
            ps.setString(11, user.getPassword());
            ps.setString(12, user.getAccountNumber());
            ps.setString(13, user.getIfscCode());
            ps.setString(14, user.getBranchName());
            ps.setString(15, user.getAccountType());
            ps.setDouble(16, user.getBalance());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        user.setId(rs.getInt(1));
                    }
                }
            }
            return user;
        }
    }

    public boolean updateUser(User user) throws SQLException {
        String query = "UPDATE users SET name=?, father_name=?, mother_name=?, dob=?, email=?, mobile_number=?, address=?, aadhar_number=?, pan_number=?, username=?, account_number=?, ifsc_code=?, branch_name=?, account_type=?, balance=? WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getFatherName());
            ps.setString(3, user.getMotherName());
            ps.setString(4, user.getDob());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getMobileNumber());
            ps.setString(7, user.getAddress());
            ps.setString(8, user.getAadharNumber());
            ps.setString(9, user.getPanNumber());
            ps.setString(10, user.getUsername());
            ps.setString(11, user.getAccountNumber());
            ps.setString(12, user.getIfscCode());
            ps.setString(13, user.getBranchName());
            ps.setString(14, user.getAccountType());
            ps.setDouble(15, user.getBalance());
            ps.setInt(16, user.getId());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteUser(int id) throws SQLException {
        String query = "DELETE FROM users WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User user = new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("father_name"),
                    rs.getString("mother_name"),
                    rs.getString("dob"),
                    rs.getString("email"),
                    rs.getString("mobile_number"),
                    rs.getString("address"),
                    rs.getString("aadhar_number"),
                    rs.getString("pan_number"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("account_number"),
                    rs.getString("ifsc_code"),
                    rs.getString("branch_name"),
                    rs.getString("account_type"),
                    rs.getDouble("balance")
                );
                userList.add(user);
            }
        }
        return userList;
    }

    public User getUserById(int id) throws SQLException {
        String query = "SELECT * FROM users WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("father_name"),
                        rs.getString("mother_name"),
                        rs.getString("dob"),
                        rs.getString("email"),
                        rs.getString("mobile_number"),
                        rs.getString("address"),
                        rs.getString("aadhar_number"),
                        rs.getString("pan_number"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("account_number"),
                        rs.getString("ifsc_code"),
                        rs.getString("branch_name"),
                        rs.getString("account_type"),
                        rs.getDouble("balance")
                    );
                }
            }
        }
        return null;
    }
}
