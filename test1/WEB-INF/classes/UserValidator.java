import java.util.regex.Pattern;

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
            return "Name must be 2-50 letters only.";
        if (user.getFatherName() == null || !NAME_PATTERN.matcher(user.getFatherName()).matches())
            return "Father's name must be 2-50 letters only.";
        if (user.getMotherName() == null || !NAME_PATTERN.matcher(user.getMotherName()).matches())
            return "Mother's name must be 2-50 letters only.";
        if (user.getDob() == null || user.getDob().isEmpty())
            return "Date of birth is required.";
        try {
            java.time.LocalDate dob = java.time.LocalDate.parse(user.getDob());
            if (dob.plusYears(18).isAfter(java.time.LocalDate.now()))
                return "User must be at least 18 years old.";
        } catch (Exception e) {
            return "Invalid date of birth format.";
        }
        if (user.getEmail() == null || !EMAIL_PATTERN.matcher(user.getEmail()).matches())
            return "Valid email is required.";
        if (user.getMobileNumber() == null || !MOBILE_PATTERN.matcher(user.getMobileNumber()).matches())
            return "Mobile number must be 10 digits.";
        if (user.getAddress() == null || !ADDRESS_PATTERN.matcher(user.getAddress()).matches())
            return "Address must be 5-100 characters.";
        if (user.getAadharNumber() == null || !AADHAR_PATTERN.matcher(user.getAadharNumber()).matches())
            return "Aadhaar must be 12 digits.";
        if (user.getPanNumber() == null || !PAN_PATTERN.matcher(user.getPanNumber()).matches())
            return "PAN must follow format (e.g., ABCDE1234F).";
        if (user.getUsername() == null || !USERNAME_PATTERN.matcher(user.getUsername()).matches())
            return "Username must be 3-20 alphanumeric characters.";
        if (isCreate && (user.getPassword() == null || !PASSWORD_PATTERN.matcher(user.getPassword()).matches()))
            return "Password must be 8-20 characters with uppercase, lowercase, digit, and special character.";
        if (user.getAccountNumber() == null || !ACCOUNT_NUMBER_PATTERN.matcher(user.getAccountNumber()).matches())
            return "Account number must be 10-16 digits.";
        if (user.getIfscCode() == null || !IFSC_PATTERN.matcher(user.getIfscCode()).matches())
            return "IFSC must follow format (e.g., ABCD0EF1234).";
        if (user.getBranchName() == null || !NAME_PATTERN.matcher(user.getBranchName()).matches())
            return "Branch name must be 2-50 letters only.";
        if (user.getAccountType() == null || (!user.getAccountType().equals("Savings") && !user.getAccountType().equals("Current")))
            return "Account type must be Savings or Current.";
        if (user.getBalance() < 0)
            return "Balance cannot be negative.";
        return null; // No validation errors
    }
}
