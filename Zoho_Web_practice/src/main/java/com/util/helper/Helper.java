package com.util.helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.user.User;

public class Helper {


    // == HELPER METHODS ==

    private void setCommonParameters(PreparedStatement ps, User user) throws SQLException {
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
    }

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
