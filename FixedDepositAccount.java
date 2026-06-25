public class FixedDepositAccount extends Account {

    public FixedDepositAccount(String firstName, String lastName) {
        super(firstName, lastName);
    }
// Minimum deposit for a fixed deposit account is 1,000,000
    @Override
    public double minimumDeposit() {
        return 1000000;
    }
}