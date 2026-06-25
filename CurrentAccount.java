public class CurrentAccount extends Account {
// Represents a current account
    public CurrentAccount(String firstName, String lastName) {
        super(firstName, lastName);
    }
// Minimum deposit for a current account is 200,000
    @Override
    public double minimumDeposit() {
        return 200000;
    }
}