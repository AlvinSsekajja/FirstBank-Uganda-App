import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.time.*;

public class FirstBankGUI extends JFrame {

    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtNIN;
    private JTextField txtEmail;
    private JTextField txtConfirmEmail;
    private JTextField txtPhone;
    private JTextField txtDeposit;
    private JTextField txtJointNIN;

    private JPasswordField txtPIN;
    private JPasswordField txtConfirmPIN;

    private JComboBox<String> cmbYear;
    private JComboBox<String> cmbMonth;
    private JComboBox<String> cmbDay;

    private JComboBox<String> cmbAccountType;
    private JComboBox<String> cmbBranch;

    private JTextArea txtRequirements;
    private JTextArea txtSummary;

    private JButton btnSubmit;
    private JButton btnReset;

    private JLabel lblFirstNameError;
    private JLabel lblLastNameError;
    private JLabel lblNINError;
    private JLabel lblEmailError;
    private JLabel lblPhoneError;
    private JLabel lblPinError;
    private JLabel lblDepositError;

    private Border defaultBorder;
// Constructor to set up the GUI
    public FirstBankGUI() {

        setTitle("First Bank Uganda - Account Opening Form");
        setSize(1000, 850);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0,3,5,5));

        txtFirstName = new JTextField();
        txtLastName = new JTextField();
        txtNIN = new JTextField();
        txtEmail = new JTextField();
        txtConfirmEmail = new JTextField();
        txtPhone = new JTextField();
        txtDeposit = new JTextField();
        txtJointNIN = new JTextField();

        txtPIN = new JPasswordField();
        txtConfirmPIN = new JPasswordField();

        defaultBorder = txtFirstName.getBorder();

        lblFirstNameError = createErrorLabel();
        lblLastNameError = createErrorLabel();
        lblNINError = createErrorLabel();
        lblEmailError = createErrorLabel();
        lblPhoneError = createErrorLabel();
        lblPinError = createErrorLabel();
        lblDepositError = createErrorLabel();

        cmbYear = new JComboBox<>();

        for(int y=1950; y<=2010; y++) {
            cmbYear.addItem(String.valueOf(y));
        }
// Create the month combo box with month names
        cmbMonth = new JComboBox<>(new String[]{
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"
        });

        cmbDay = new JComboBox<>();

        cmbAccountType = new JComboBox<>(new String[]{
                "Savings",
                "Current",
                "Fixed Deposit",
                "Student",
                "Joint"
        });

        cmbBranch = new JComboBox<>(new String[]{
                "Kampala",
                "Gulu",
                "Mbarara",
                "Jinja",
                "Mbale"
        });

        updateDays();

        formPanel.add(new JLabel("First Name"));
        formPanel.add(txtFirstName);
        formPanel.add(lblFirstNameError);

        formPanel.add(new JLabel("Last Name"));
        formPanel.add(txtLastName);
        formPanel.add(lblLastNameError);

        formPanel.add(new JLabel("National ID (NIN)"));
        formPanel.add(txtNIN);
        formPanel.add(lblNINError);

        formPanel.add(new JLabel("Email"));
        formPanel.add(txtEmail);
        formPanel.add(lblEmailError);

        formPanel.add(new JLabel("Confirm Email"));
        formPanel.add(txtConfirmEmail);
        formPanel.add(new JLabel(""));

        formPanel.add(new JLabel("Phone Number"));
        formPanel.add(txtPhone);
        formPanel.add(lblPhoneError);

        formPanel.add(new JLabel("PIN"));
        formPanel.add(txtPIN);
        formPanel.add(lblPinError);

        formPanel.add(new JLabel("Confirm PIN"));
        formPanel.add(txtConfirmPIN);
        formPanel.add(new JLabel(""));

        formPanel.add(new JLabel("Birth Year"));
        formPanel.add(cmbYear);
        formPanel.add(new JLabel(""));

        formPanel.add(new JLabel("Birth Month"));
        formPanel.add(cmbMonth);
        formPanel.add(new JLabel(""));

        formPanel.add(new JLabel("Birth Day"));
        formPanel.add(cmbDay);
        formPanel.add(new JLabel(""));

        formPanel.add(new JLabel("Account Type"));
        formPanel.add(cmbAccountType);
        formPanel.add(new JLabel(""));

        formPanel.add(new JLabel("Branch"));
        formPanel.add(cmbBranch);
        formPanel.add(new JLabel(""));

        formPanel.add(new JLabel("Opening Deposit"));
        formPanel.add(txtDeposit);
        formPanel.add(lblDepositError);

        formPanel.add(new JLabel("Joint Account NIN"));
        formPanel.add(txtJointNIN);
        formPanel.add(new JLabel(""));

        txtRequirements = new JTextArea(5,40);
        txtRequirements.setEditable(false);
        txtRequirements.setBackground(new Color(240,240,240));

        JPanel requirementsPanel = new JPanel(new BorderLayout());

        requirementsPanel.add(
                new JLabel("Account Requirements"),
                BorderLayout.NORTH);

        requirementsPanel.add(
                new JScrollPane(txtRequirements),
                BorderLayout.CENTER);

        txtSummary = new JTextArea(5,50);
        txtSummary.setEditable(false);
        txtSummary.setLineWrap(true);
        txtSummary.setWrapStyleWord(true);
// Create the summary panel
        JPanel summaryPanel = new JPanel(
                new BorderLayout());

        summaryPanel.add(
                new JLabel("ACCOUNT SUMMARY"),
                BorderLayout.NORTH);

        summaryPanel.add(
                new JScrollPane(txtSummary),
                BorderLayout.CENTER);

        btnSubmit = new JButton("Submit");
        btnReset = new JButton("Reset");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnSubmit);
        buttonPanel.add(btnReset);

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout());

        centerPanel.add(
                requirementsPanel,
                BorderLayout.NORTH);

        centerPanel.add(
                summaryPanel,
                BorderLayout.CENTER);

        centerPanel.add(
                buttonPanel,
                BorderLayout.SOUTH);

        mainPanel.add(
                formPanel,
                BorderLayout.NORTH);

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER);

        add(mainPanel);

        cmbYear.addActionListener(
                e -> updateDays());

        cmbMonth.addActionListener(
                e -> updateDays());

        cmbAccountType.addActionListener(
                e -> showRequirements());

        showRequirements();

        attachValidation();

        btnSubmit.addActionListener(
                e -> submitForm());

        btnReset.addActionListener(
                e -> resetForm());

        setVisible(true);
    }
        private JLabel createErrorLabel() {

        JLabel lbl = new JLabel("");
        lbl.setForeground(Color.RED);

        return lbl;
    }
// Attach focus listeners to validate fields when they lose focus
    private void attachValidation() {
    txtDeposit.addFocusListener(
    new FocusAdapter() {

        @Override
        public void focusLost(FocusEvent e) {

            validateDepositField();
        }
    });

// Add focus listener for first name field
        txtFirstName.addFocusListener(
                new FocusAdapter() {

                    public void focusLost(
                            FocusEvent e) {

                        if (!ValidationUtil
                                .validName(
                                        txtFirstName
                                                .getText()
                                                .trim())) {

                            txtFirstName
                                    .setBorder(
                                            BorderFactory
                                                    .createLineBorder(
                                                            Color.RED));

                            lblFirstNameError
                                    .setText(
                                            "Invalid Name");
                        } else {

                            txtFirstName
                                    .setBorder(
                                            defaultBorder);

                            lblFirstNameError
                                    .setText("");
                        }
                    }
                });
// Add focus listener for last name field
        txtLastName.addFocusListener(
                new FocusAdapter() {

                    public void focusLost(
                            FocusEvent e) {

                        if (!ValidationUtil
                                .validName(
                                        txtLastName
                                                .getText()
                                                .trim())) {

                            txtLastName
                                    .setBorder(
                                            BorderFactory
                                                    .createLineBorder(
                                                            Color.RED));

                            lblLastNameError
                                    .setText(
                                            "Invalid Name");
                        } else {

                            txtLastName
                                    .setBorder(
                                            defaultBorder);

                            lblLastNameError
                                    .setText("");
                        }
                    }
                });
// Add focus listener for NIN field
        txtNIN.addFocusListener(
                new FocusAdapter() {

                    public void focusLost(
                            FocusEvent e) {

                        if (!ValidationUtil
                                .validNIN(
                                        txtNIN
                                                .getText()
                                                .trim())) {

                            txtNIN.setBorder(
                                    BorderFactory
                                            .createLineBorder(
                                                    Color.RED));

                            lblNINError
                                    .setText(
                                            "Invalid NIN");
                        } else {

                            txtNIN.setBorder(
                                    defaultBorder);

                            lblNINError
                                    .setText("");
                        }
                    }
                });
// Add focus listener for email field
        txtEmail.addFocusListener(
                new FocusAdapter() {

                    public void focusLost(
                            FocusEvent e) {

                        if (!ValidationUtil
                                .validEmail(
                                        txtEmail
                                                .getText()
                                                .trim())) {

                            txtEmail
                                    .setBorder(
                                            BorderFactory
                                                    .createLineBorder(
                                                            Color.RED));

                            lblEmailError
                                    .setText(
                                            "Invalid Email");
                        } else {

                            txtEmail
                                    .setBorder(
                                            defaultBorder);

                            lblEmailError
                                    .setText("");
                        }
                    }
                });

        txtPhone.addFocusListener(
                new FocusAdapter() {

                    public void focusLost(
                            FocusEvent e) {

                        if (!ValidationUtil
                                .validPhone(
                                        txtPhone
                                                .getText()
                                                .trim())) {

                            txtPhone
                                    .setBorder(
                                            BorderFactory
                                                    .createLineBorder(
                                                            Color.RED));

                            lblPhoneError
                                    .setText(
                                            "Invalid Phone");
                        } else {

                            txtPhone
                                    .setBorder(
                                            defaultBorder);

                            lblPhoneError
                                    .setText("");
                        }
                    }
                });
    }
// Update the days in the day combo box based on the selected year and month
    private void updateDays() {

        int year =
                Integer.parseInt(
                        cmbYear
                                .getSelectedItem()
                                .toString());

        int month =
                cmbMonth
                        .getSelectedIndex()
                        + 1;

        YearMonth ym =
                YearMonth.of(
                        year,
                        month);

        int days =
                ym.lengthOfMonth();

        cmbDay.removeAllItems();

        for (int i = 1;
             i <= days;
             i++) {

            cmbDay.addItem(
                    String.valueOf(i));
        }
    }
// Calculate the age based on the selected date of birth
    private int calculateAge() {

        int year =
                Integer.parseInt(
                        cmbYear
                                .getSelectedItem()
                                .toString());

        int month =
                cmbMonth
                        .getSelectedIndex()
                        + 1;

        int day =
                Integer.parseInt(
                        cmbDay
                                .getSelectedItem()
                                .toString());

        LocalDate dob =
                LocalDate.of(
                        year,
                        month,
                        day);

        return Period.between(
                dob,
                LocalDate.now())
                .getYears();
    }

    private void showRequirements() {

        String type =
                cmbAccountType
                        .getSelectedItem()
                        .toString();

        switch (type) {

            case "Savings":

                txtRequirements.setText(
                        "Minimum Deposit: 50,000 UGX\n"
                                + "Earns Interest\n"
                                + "No Overdraft");
                break;

            case "Current":

                txtRequirements.setText(
                        "Minimum Deposit: 200,000 UGX\n"
                                + "Overdraft Allowed\n"
                                + "No Interest");
                break;

            case "Fixed Deposit":

                txtRequirements.setText(
                        "Minimum Deposit: 1,000,000 UGX\n"
                                + "Highest Interest\n"
                                + "Locked Term");
                break;

            case "Student":

                txtRequirements.setText(
                        "Minimum Deposit: 10,000 UGX\n"
                                + "Age 18-25 Only");
                break;

            case "Joint":

                txtRequirements.setText(
                        "Minimum Deposit: 100,000 UGX\n"
                                + "Second NIN Required");
                break;
        }
    }

    private void submitForm() {

    // Get all values from the form
     
    String firstName = txtFirstName.getText().trim();
    String lastName = txtLastName.getText().trim();
    String nin = txtNIN.getText().trim();
    String email = txtEmail.getText().trim();
    String confirmEmail = txtConfirmEmail.getText().trim();
    String phone = txtPhone.getText().trim();
    String pin = new String(txtPIN.getPassword()).trim();
    String confirmPin = new String(txtConfirmPIN.getPassword()).trim();
    String accountType = cmbAccountType.getSelectedItem().toString();
    String branch = cmbBranch.getSelectedItem().toString();
 
    // Empty fields
     
    if(firstName.isEmpty() ||
            lastName.isEmpty() ||
            nin.isEmpty() ||
            email.isEmpty() ||
            confirmEmail.isEmpty() ||
            phone.isEmpty() ||
            pin.isEmpty() ||
            confirmPin.isEmpty() ||
            txtDeposit.getText().trim().isEmpty()){

        JOptionPane.showMessageDialog(
                this,
                "All fields are required.");

        return;
    }

    // Name Validation
     
    if(!ValidationUtil.validName(firstName)){
        JOptionPane.showMessageDialog(this,
                "Invalid First Name");
        return;
    }

    if(!ValidationUtil.validName(lastName)){
        JOptionPane.showMessageDialog(this,
                "Invalid Last Name");
        return;
    }

     
    // NIN Validation
   
    if(!ValidationUtil.validNIN(nin)){
        JOptionPane.showMessageDialog(this,
                "Invalid National ID Number");
        return;
    }

    
    // Email Validation
     
    if(!ValidationUtil.validEmail(email)){
        JOptionPane.showMessageDialog(this,
                "Invalid Email Address");
        return;
    }

    if(!email.equals(confirmEmail)){
        JOptionPane.showMessageDialog(this,
                "Emails do not match");
        return;
    }
 
    // Phone Validation
     
    if(!ValidationUtil.validPhone(phone)){
        JOptionPane.showMessageDialog(this,
                "Invalid Phone Number");
        return;
    }
 
    // PIN Validation
    
    if(!ValidationUtil.validPin(pin)){
        JOptionPane.showMessageDialog(this,
                "PIN must contain 4-6 digits and cannot be all the same number.");
        return;
    }

    if(!pin.equals(confirmPin)){
        JOptionPane.showMessageDialog(this,
                "PINs do not match");
        return;
    }
 
    // Deposit Validation
     

    double deposit;

    try{

        deposit = Double.parseDouble(
                txtDeposit.getText().trim());

    }catch(NumberFormatException ex){

        JOptionPane.showMessageDialog(
                this,
                "Deposit must be a valid number.");

        return;
    }

    double minimumDeposit = 0;

    switch(accountType){

        case "Savings":
            minimumDeposit = 50000;
            break;

        case "Current":
            minimumDeposit = 200000;
            break;

        case "Fixed Deposit":
            minimumDeposit = 1000000;
            break;

        case "Student":
            minimumDeposit = 10000;
            break;

        case "Joint":
            minimumDeposit = 100000;
            break;
    }

    if(deposit < minimumDeposit){

        JOptionPane.showMessageDialog(
                this,
                "Minimum Deposit is UGX "
                        + String.format("%,.0f",
                        minimumDeposit));

        return;
    }

  
    // Age Validation
     
    int age = calculateAge();

    if(age < 18 || age > 75){

        JOptionPane.showMessageDialog(
                this,
                "Customer age must be between 18 and 75.");

        return;
    }

    if(accountType.equals("Student")){

        if(age < 18 || age > 75){

            JOptionPane.showMessageDialog(
                    this,
                    "Student Account is only for ages 18-75.");

            return;
        }
    }
    // Joint Account Validation

    if(accountType.equals("Joint")){

        if(txtJointNIN.getText().trim().isEmpty()){

            JOptionPane.showMessageDialog(
                    this,
                    "Joint Account requires the second account holder's NIN.");

            return;
        }
    }


    // Date of Birth
 
    int birthYear = Integer.parseInt(cmbYear.getSelectedItem().toString());
    int birthMonth = cmbMonth.getSelectedIndex() + 1;
    int birthDay = Integer.parseInt(cmbDay.getSelectedItem().toString());

    // Generate Account Number
     
    String accountNumber =
            AccountNumberGenerator.generate(
                    branch,
                    1);

    // Display Summary
     
    String summary =
            "ACC: " + accountNumber +
                    " | " + lastName + " " + firstName +
                    " | " + accountType +
                    " | " + branch +
                    " | DOB " +
                    birthYear + "-" +
                    birthMonth + "-" +
                    birthDay +
                    " | " + phone +
                    " | Deposit " +
                    String.format("%,.0f", deposit) +
                    " | " + email;

    txtSummary.setText(summary);

    // Save To Database
     
    boolean success =
            DatabaseManager.insertCustomer(
                    firstName,
                    lastName,
                    nin,
                    email,
                    phone,
                    pin,
                    birthYear,
                    birthMonth,
                    birthDay,
                    accountType,
                    branch,
                    deposit);

    if(success){

        JOptionPane.showMessageDialog(
                this,
                "Account Created Successfully!");

    }else{

        JOptionPane.showMessageDialog(
                this,
                "Database Error. Account not created.");
    }
}

    private void validateDepositField() {

    lblDepositError.setText("");

    try {

        double deposit =
                Double.parseDouble(
                        txtDeposit.getText());

        String type =
                cmbAccountType
                        .getSelectedItem()
                        .toString();

        double minimum = 0;

        switch(type){

            case "Savings":
                minimum = 50000;
                break;

            case "Current":
                minimum = 200000;
                break;

            case "Fixed Deposit":
                minimum = 1000000;
                break;

            case "Student":
                minimum = 10000;
                break;

            case "Joint":
                minimum = 100000;
                break;
        }

        if(deposit < minimum){

            txtDeposit.setBorder(
                    BorderFactory
                            .createLineBorder(
                                    Color.RED));

            lblDepositError.setText(
                    "Minimum UGX "
                    + String.format("%,.0f",
                    minimum));
        }
        else{

            txtDeposit.setBorder(
                    defaultBorder);

            lblDepositError.setText("");
        }

    }
    catch(Exception ex){

        txtDeposit.setBorder(
                BorderFactory
                        .createLineBorder(
                                Color.RED));

        lblDepositError.setText(
                "Numbers only");
    }
} 
   

// Reset the form fields to their default state
   private void resetForm() {

        txtFirstName.setText("");
        txtLastName.setText("");
        txtNIN.setText("");
        txtEmail.setText("");
        txtConfirmEmail.setText("");
        txtPhone.setText("");
        txtDeposit.setText("");
        txtJointNIN.setText("");
        txtPIN.setText("");
        txtConfirmPIN.setText("");
        txtSummary.setText("");
    }
} 
    
 
