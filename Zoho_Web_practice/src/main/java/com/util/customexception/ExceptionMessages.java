package com.util.customexception;




public class ExceptionMessages{


    public static final String NAME_INVALID = "Name must be 2-50 letters only.";
    public static final String FATHER_NAME_INVALID = "Father's name must be 2-50 letters only.";
    public static final String MOTHER_NAME_INVALID = "Mother's name must be 2-50 letters only.";
    public static final String DOB_REQUIRED = "Date of birth is required.";
    public static final String DOB_INVALID_FORMAT = "Invalid date of birth format.";
    public static final String DOB_UNDERAGE = "User must be at least 18 years old.";
    public static final String EMAIL_REQUIRED = "Valid email is required.";
    public static final String MOBILE_INVALID = "Mobile number must be 10 digits.";
    public static final String ADDRESS_INVALID = "Address must be 5-100 characters.";
    public static final String AADHAR_INVALID = "Aadhaar must be 12 digits.";
    public static final String PAN_INVALID = "PAN must follow format (e.g., ABCDE1234F).";
    public static final String USERNAME_INVALID = "Username must be 3-20 alphanumeric characters.";
    public static final String PASSWORD_INVALID = "Password must be 8-20 characters with uppercase, lowercase, digit, and special character.";
    public static final String ACCOUNT_NUMBER_INVALID = "Account number must be 10-16 digits.";
    public static final String IFSC_INVALID = "IFSC must follow format (e.g., ABCD0EF1234).";
    public static final String BRANCH_NAME_INVALID = "Branch name must be 2-50 letters only.";
    public static final String ACCOUNT_TYPE_INVALID = "Account type must be Savings or Current.";
    public static final String BALANCE_NEGATIVE = "Balance cannot be negative.";
   
    
    
 // Error messages
    public static String NULL_INPUT_ERROR = "Input strings are null";
    public static String LENGTH_ERROR = "String length is very less than required length!";
    public static String NEGATIVE_VALUE_ERROR = "Index or length cannot be negative";
    public static String EMPTY_STRING_ERROR = "Input string cannot be empty";
    public static String EMPTY_SEPARATOR_ERROR = "Separator cannot be empty";
    public static String EMPTY_TARGET_ERROR = "Target string cannot be empty";
    public static String EMPTY_CHAR_ERROR = "Character cannot be empty";
    public static String LENGTH_INDEX_CONFLICT = "Start index must be less than end index";
    
    public static String INDEX_OUT_OF_BOUND = "Index out of bound";
    public static String INVALID_POSITION = "Invalid position";
    public static String ELEMENT_NOT_FOUND = "Element not found";
    
    public static String KEY_NOT_EXISTS = "Key not exists! ";
    public static String EMPTY_MAP_ERROR = "Map should not be empty! ";
    public static String HTML_EMPTY_ERROR= "No HTML tags found in the input";
    public static String REGEX_ERROR="Unable to compile regex";
    
    // JDBC Exceptions
    public static final String CONNECTION_FAILED = "Failed to connect to the database";
    public static final String TABLE_CREATION_FAILED = "Failed to create table";
    public static final String EMPLOYEE_ADD_FAILED = "Failed to add employee";
    public static final String EMPLOYEE_RETRIEVAL_FAILED = "Failed to retrieve employee details";
    public static final String EMPLOYEE_UPDATE_FAILED = "Failed to update employee details";
    public static final String EMPLOYEE_DELETE_FAILED = "Failed to delete employee";
    public static final String DEPENDENT_ADD_FAILED = "Failed to add dependent";
    public static final String DEPENDENT_RETRIEVAL_FAILED = "Failed to retrieve dependents";
    public static final String DB_CREDENTIALS_NOT_FOUND = "Database Credentials not found at: ";
    public static final String DB_KEY_MISSING = "Missing required DB config key in properties";
    public static final String DRIVER_NOTFOUND ="MySQL JDBC Driver not found";
    public static final String CONNECTION_NOT_INITIALIZED = "Connection not initialized.";
    public static final String USER_NOTFOUND = "User not found with ID: ";

    public static final String DB_ERROR = "Database error during user creation";


}
