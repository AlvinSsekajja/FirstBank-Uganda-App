public class StudentsAccount extends Account {

    public StudentsAccount(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public double minimumDeposit() {
        return 10000;
    }
}