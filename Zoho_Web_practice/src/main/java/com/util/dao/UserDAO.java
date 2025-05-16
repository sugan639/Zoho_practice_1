package com.util.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import com.util.config.ConnectionPool;
import com.util.customexception.ExceptionMessages;
import com.util.customexception.TaskException;
import com.util.user.User;

public class UserDAO {

    // == CREATE ==
	public User createUser(User user) throws TaskException {
	    String sql = "INSERT INTO users (name, father_name, mother_name, dob, email, mobile_number, address, aadhar_number, pan_number, username, password, account_number, ifsc_code, branch_name, account_type, balance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement ps = ConnectionPool
	    							.INSTANCE
	    							.getConnection()
	    							.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
	        int index = 1;
	        ps.setString(index++, user.getName());
	        ps.setString(index++, user.getFatherName());
	        ps.setString(index++, user.getMotherName());
	        ps.setString(index++, user.getDob());
	        ps.setString(index++, user.getEmail());
	        ps.setString(index++, user.getMobileNumber());
	        ps.setString(index++, user.getAddress());
	        ps.setString(index++, user.getAadharNumber());
	        ps.setString(index++, user.getPanNumber());
	        ps.setString(index++, user.getUsername());
	        ps.setString(index++, user.getPassword());
	        ps.setString(index++, user.getAccountNumber());
	        ps.setString(index++, user.getIfscCode());
	        ps.setString(index++, user.getBranchName());
	        ps.setString(index++, user.getAccountType());
	        ps.setDouble(index++, user.getBalance());

	        int rowsAffected = ps.executeUpdate();

	        if (rowsAffected > 0) {
	            try (ResultSet rs = ps.getGeneratedKeys()) {
	                if (rs.next()) {
	                    user.setId(rs.getInt(1));
	                }
	            }
	        }

	        return user;

	    } catch (SQLException e) {
	        throw new TaskException(ExceptionMessages.DB_ERROR + e.getMessage(), e);
	    }
	}

    // == UPDATE ==
    public boolean updateUser(User user) throws TaskException {
        String sql = "UPDATE users SET name=?, father_name=?, mother_name=?, dob=?, email=?, mobile_number=?, address=?, aadhar_number=?, pan_number=?, username=?, password=?, account_number=?, ifsc_code=?, branch_name=?, account_type=?, balance=? WHERE id=?";
        
        try (PreparedStatement ps = ConnectionPool.INSTANCE.getConnection().prepareStatement(sql)) {
            int index = 1;
            ps.setString(index++, user.getName());
            ps.setString(index++, user.getFatherName());
            ps.setString(index++, user.getMotherName());
            ps.setString(index++, user.getDob());
            ps.setString(index++, user.getEmail());
            ps.setString(index++, user.getMobileNumber());
            ps.setString(index++, user.getAddress());
            ps.setString(index++, user.getAadharNumber());
            ps.setString(index++, user.getPanNumber());
            ps.setString(index++, user.getUsername());
            ps.setString(index++, user.getPassword());
            ps.setString(index++, user.getAccountNumber());
            ps.setString(index++, user.getIfscCode());
            ps.setString(index++, user.getBranchName());
            ps.setString(index++, user.getAccountType());
            ps.setDouble(index++, user.getBalance());
            ps.setInt(index++, user.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new TaskException(ExceptionMessages.DB_ERROR + e.getMessage(), e);
        }
    }

    // == DELETE ==
    public boolean deleteUser(int id) throws TaskException {
        String sql = "DELETE FROM users WHERE id=?";
        try (PreparedStatement ps = ConnectionPool.INSTANCE.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new TaskException(ExceptionMessages.CONNECTION_FAILED + e.getMessage(), e);
        }
    }

    // == GET ALL ==
    public List<User> getAllUsers() throws TaskException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (PreparedStatement ps = ConnectionPool.INSTANCE.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                userList.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            throw new TaskException(ExceptionMessages.EMPLOYEE_RETRIEVAL_FAILED + e.getMessage(), e);
        }

        return userList;
    }

    // == GET BY ID ==
    public User getUserById(int id) throws TaskException {
        String sql = "SELECT * FROM users WHERE id=?";
        try (PreparedStatement ps = ConnectionPool.INSTANCE.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        } catch (SQLException e) {
            throw new TaskException(ExceptionMessages.EMPLOYEE_RETRIEVAL_FAILED + e.getMessage(), e);
        }
        return null;
    }

    // == HELPER METHODS ==

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
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