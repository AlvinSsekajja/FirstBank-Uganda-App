public class SavingsAccount extends Account {

    public SavingsAccount(String firstName, String lastName) {
        super(firstName, lastName);
    }
// Minimum deposit for a savings account is 50,000
    @Override
    public double minimumDeposit() {
        return 50000;
    }
}