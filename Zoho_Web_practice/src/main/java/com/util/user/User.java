package com.util.user;


import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
	private int id;
    private String name;
    private String fatherName;
    private String motherName;
    private String dob;
    private String email;
    private String mobileNumber;
    private String address;
    private String aadharNumber;
    private String panNumber;
    private String username;
    private String password;
    private String accountNumber;
    private String ifscCode;
    private String branchName;
    private String accountType;
    private double balance;

    // Default constructor
    public User() {
        this.id = 0;
        this.name = "";
        this.fatherName = "";
        this.motherName = "";
        this.dob = "";
        this.email = "";
        this.mobileNumber = "";
        this.address = "";
        this.aadharNumber = "";
        this.panNumber = "";
        this.username = "";
        this.password = "";
        this.accountNumber = "";
        this.ifscCode = "";
        this.branchName = "";
        this.accountType = "";
        this.balance = 0.0;
    }

    // Parameterized constructor
    public User(int id, String name, String fatherName, String motherName, String dob,
                String email, String mobileNumber, String address, String aadharNumber,
                String panNumber, String username, String password, String accountNumber,
                String ifscCode, String branchName, String accountType, double balance) {
        this.id = id;
        this.name = name;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.dob = dob;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.aadharNumber = aadharNumber;
        this.panNumber = panNumber;
        this.username = username;
        this.password = password;
        this.accountNumber = accountNumber;
        this.ifscCode = ifscCode;
        this.branchName = branchName;
        this.accountType = accountType;
        this.balance = balance;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}