

public class Main {
// Main class to initialize the database and launch the GUI
    public static void main(String[] args) {
        DatabaseManager.initializeDatabase();
        System.out.println("Working directory: " + System.getProperty("user.dir"));
        System.out.println("Expected DB path: " + new java.io.File("./bankdb.accdb").getAbsolutePath());
        new FirstBankGUI();

    }
}