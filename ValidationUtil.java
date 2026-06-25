public class ValidationUtil {
// Utility class for validating user input
    public static boolean validName(String name) {

        return name.matches("[A-Za-z ]{2,30}");
    }

    public static boolean validNIN(String nin) {

        return nin.matches("^[A-Z0-9]{14}$");
    }

    public static boolean validEmail(String email) {

        return email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public static boolean validPhone(String phone) {

        return phone.matches("^\\+256\\d{9}$");
    }

    public static boolean validPin(String pin) {

        if (!pin.matches("\\d{4,6}")) {
            return false;
        }

        return !pin.matches("(\\d)\\1+");
    }
}