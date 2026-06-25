public abstract class Account {
// Abstract class representing a bank account
    protected String firstName;
    protected String lastName;

    public Account(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public abstract double minimumDeposit();

}