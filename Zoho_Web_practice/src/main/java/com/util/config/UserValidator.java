package com.util.config;


import java.util.regex.Pattern;
import com.util.user.User;

import com.util.customexception.ExceptionMessages;

public class UserValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern MOBILE_PATTERN = Pattern.compile("^\\d{10}$");
    private static final Pattern AADHAR_PATTERN = Pattern.compile("^\\d{12}$");
    private static final Pattern PAN_PATTERN = Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]{1}$");
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z\\s]{2,50}$");
    private static final Pattern ADDRESS_PATTERN = Pattern.compile("^[A-Za-z0-9\\s,.-]{5,100}$");
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[A-Za-z0-9]{3,20}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,20}$");
    private static final Pattern ACCOUNT_NUMBER_PATTERN = Pattern.compile("^\\d{10,16}$");
    private static final Pattern IFSC_PATTERN = Pattern.compile("^[A-Z]{4}0[A-Za-z0-9]{6}$");

    public static String validateInput(User user, boolean isCreate) {
        if (user.getName() == null || !NAME_PATTERN.matcher(user.getName()).matches())
            return ExceptionMessages.NAME_INVALID;

        if (user.getFatherName() == null || !NAME_PATTERN.matcher(user.getFatherName()).matches())
            return ExceptionMessages.FATHER_NAME_INVALID;

        if (user.getMotherName() == null || !NAME_PATTERN.matcher(user.getMotherName()).matches())
            return ExceptionMessages.MOTHER_NAME_INVALID;

        if (user.getDob() == null || user.getDob().isEmpty())
            return ExceptionMessages.DOB_REQUIRED;

        try {
            java.time.LocalDate dob = java.time.LocalDate.parse(user.getDob());
            if (dob.plusYears(18).isAfter(java.time.LocalDate.now()))
                return ExceptionMessages.DOB_UNDERAGE;
        } catch (Exception e) {
            return ExceptionMessages.DOB_INVALID_FORMAT;
        }

        if (user.getEmail() == null || !EMAIL_PATTERN.matcher(user.getEmail()).matches())
            return ExceptionMessages.EMAIL_REQUIRED;

        if (user.getMobileNumber() == null || !MOBILE_PATTERN.matcher(user.getMobileNumber()).matches())
            return ExceptionMessages.MOBILE_INVALID;

        if (user.getAddress() == null || !ADDRESS_PATTERN.matcher(user.getAddress()).matches())
            return ExceptionMessages.ADDRESS_INVALID;

        if (user.getAadharNumber() == null || !AADHAR_PATTERN.matcher(user.getAadharNumber()).matches())
            return ExceptionMessages.AADHAR_INVALID;

        if (user.getPanNumber() == null || !PAN_PATTERN.matcher(user.getPanNumber()).matches())
            return ExceptionMessages.PAN_INVALID;

        if (user.getUsername() == null || !USERNAME_PATTERN.matcher(user.getUsername()).matches())
            return ExceptionMessages.USERNAME_INVALID;

        if (isCreate && (user.getPassword() == null || !PASSWORD_PATTERN.matcher(user.getPassword()).matches()))
            return ExceptionMessages.PASSWORD_INVALID;

        if (user.getAccountNumber() == null || !ACCOUNT_NUMBER_PATTERN.matcher(user.getAccountNumber()).matches())
            return ExceptionMessages.ACCOUNT_NUMBER_INVALID;

        if (user.getIfscCode() == null || !IFSC_PATTERN.matcher(user.getIfscCode()).matches())
            return ExceptionMessages.IFSC_INVALID;

        if (user.getBranchName() == null || !NAME_PATTERN.matcher(user.getBranchName()).matches())
            return ExceptionMessages.BRANCH_NAME_INVALID;

        if (user.getAccountType() == null || (!user.getAccountType().equals("Savings") && !user.getAccountType().equals("Current")))
            return ExceptionMessages.ACCOUNT_TYPE_INVALID;

        if (user.getBalance() < 0)
            return ExceptionMessages.BALANCE_NEGATIVE;

        return null;
    }
}