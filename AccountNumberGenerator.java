import java.time.LocalDate;

public class AccountNumberGenerator {
// Generates account numbers based on branch and sequence number
    public static String generate(
            String branch,
            int sequence) {

        String code = "";

        switch (branch) {

            case "Kampala":
                code = "KLA";
                break;

            case "Gulu":
                code = "GUL";
                break;

            case "Mbarara":
                code = "MBA";
                break;

            case "Jinja":
                code = "JIN";
                break;

            case "Mbale":
                code = "MBL";
                break;
        }

        int year = LocalDate.now().getYear();

        return String.format(
                "%s-%d-%06d",
                code,
                year,
                sequence);
    }
}