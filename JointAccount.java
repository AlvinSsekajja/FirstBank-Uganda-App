public class JointAccount extends Account {

    public JointAccount(String firstName, String lastName) {
        super(firstName, lastName);
    }
// Minimum deposit for a joint account is 1,000,000
    @Override
    public double minimumDeposit() {
        return 100000;
    }
}