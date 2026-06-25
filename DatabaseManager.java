import java.sql.*;
public class DatabaseManager {
    // The path to our Access file.
     private static final String DB_URL = "jdbc:ucanaccess://./bankdb.accdb";

    // method help us in getting a connection to the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // method auto creates the Customers table if it doesn't exist
    public static void initializeDatabase() {
        String createTableSQL = 
            "CREATE TABLE Customers (" +
            "  ID AUTOINCREMENT PRIMARY KEY," +
            "  FirstName TEXT(50)," +
            "  LastName TEXT(50)," +
            "  NIN TEXT(20)," +
            "  Email TEXT(100)," +
            "  Phone TEXT(20)," +
            "  PIN TEXT(10)," +
            "  BirthYear INTEGER," +
            "  BirthMonth INTEGER," +
            "  BirthDay INTEGER," +
            "  AccountType TEXT(30)," +
            "  Branch TEXT(50)," +
            "  OpeningDeposit DOUBLE" +
            ")";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            // Check if table already exists
            if (!tableExists(conn, "Customers")) {
                stmt.execute(createTableSQL);
                System.out.println("Customers table created.");
            } else {
                System.out.println("Customers table already exists.");
            }
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //  checks if a table exists in the database
    private static boolean tableExists(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        try (ResultSet rs = meta.getTables(null, null, tableName, null)) {
            return rs.next(); // returns true if there's at least one matching table
        }
    }

    // Inserting a new customer using all the fields from your form
    public static boolean insertCustomer(String firstName, String lastName, String nin,
                                         String email, String phone, String pin,
                                         int birthYear, int birthMonth, int birthDay,
                                         String accountType, String branch, double openingDeposit) {
        String sql = "INSERT INTO Customers (FirstName, LastName, NIN, Email, Phone, PIN, " +
                     "BirthYear, BirthMonth, BirthDay, AccountType, Branch, OpeningDeposit) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, nin);
            pstmt.setString(4, email);
            pstmt.setString(5, phone);
            pstmt.setString(6, pin);
            pstmt.setInt(7, birthYear);
            pstmt.setInt(8, birthMonth);
            pstmt.setInt(9, birthDay);
            pstmt.setString(10, accountType);
            pstmt.setString(11, branch);
            pstmt.setDouble(12, openingDeposit);

            int rows = pstmt.executeUpdate();
            return rows > 0; // true if one row was inserted

        } catch (SQLException e) {
            System.err.println("Insert failed: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}