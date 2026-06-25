First Bank Uganda Account Opening System
Overview

This project is a Java Swing desktop application developed to simulate a New Bank Account Opening Form for a fictional bank called First Bank Uganda.

The application allows customers to enter their personal details, select an account type, choose a branch, and submit an account application. The system performs validation, generates an account number, displays an account summary, and stores customer records in a database.

Features
Customer Information Capture

The application collects:

First Name
Last Name
National Identification Number (NIN)
Email Address
Confirm Email
Phone Number
PIN
Confirm PIN
Date of Birth
Account Type
Branch
Opening Deposit
Joint Account NIN (for Joint Accounts)
Account Types Supported
Savings Account
Current Account
Fixed Deposit Account
Student Account
Joint Account

Each account type has its own minimum opening deposit and requirements.

Account Type	Minimum Deposit (UGX)
Savings	50,000
Current	200,000
Fixed Deposit	1,000,000
Student	10,000
Joint	100,000
Validation Features

The system validates:

Names (letters only, 2–30 characters)
NIN (14 uppercase alphanumeric characters)
Email format
Matching Email Confirmation
Ugandan Phone Number format (+256XXXXXXXXX)
PIN format (4–6 digits)
Matching PIN Confirmation
Date of Birth
Age restrictions
Opening Deposit requirements
Joint Account requirements

Inline validation messages are displayed beside fields when invalid data is entered.

Date of Birth Handling

The application uses:

Year ComboBox
Month ComboBox
Day ComboBox

The day list automatically updates based on:

Selected month
Leap year calculations

Example:

February = 28 days
February in a leap year = 29 days
Account Requirements Panel

When an account type is selected, the application displays its requirements automatically.

Example:

Savings
Minimum Deposit: UGX 50,000
Earns Interest
No Overdraft
Student
Minimum Deposit: UGX 10,000
Age must be between 18 and 25
Joint
Minimum Deposit: UGX 100,000
Requires Second NIN
Account Summary

After successful submission, the application generates an account summary in the format:

ACC: KLA-2026-000142 | Okello Allan | Savings | Kampala | DOB 2004-02-29 | +256772123456 | Deposit 50,000 | okello.allan@firstbank.co.ug

The summary is displayed in a read-only area labeled:

ACCOUNT SUMMARY
Object-Oriented Design

The project demonstrates Object-Oriented Programming principles including:

Abstraction

An abstract class:

Account

defines common account behavior.

Inheritance

Subclasses include:

SavingsAccount
CurrentAccount
FixedDepositAccount
StudentAccount
JointAccount
Polymorphism

The system validates opening deposits based on the selected account type using overridden methods.

Example:

account.minimumDeposit();
Technologies Used
Java
Java Swing
JDBC
Microsoft Access Database
UCanAccess JDBC Driver
Visual Studio Code
Project Structure
FirstBankProject
│
├── src
│   ├── Main.java
│   ├── FirstBankGUI.java
│   ├── ValidationUtil.java
│   ├── DatabaseManager.java
│   ├── Account.java
│   ├── SavingsAccount.java
│   ├── CurrentAccount.java
│   ├── FixedDepositAccount.java
│   ├── StudentAccount.java
│   └── JointAccount.java
│
├── lib
│   ├── ucanaccess.jar
│   ├── commons-lang3.jar
│   ├── commons-logging.jar
│   ├── hsqldb.jar
│   └── jackcess.jar
│
└── FirstBank.accdb
Database

The application stores account records in a Microsoft Access database.

Database table:

tblAccounts

Suggested fields:

ID
AccountNumber
FirstName
LastName
NIN
Email
Phone
DOB
AccountType
Branch
Deposit
Installation
1. Clone Repository
git clone <repository-url>
2. Open in VS Code

Open the project folder using Visual Studio Code.

3. Add JDBC Libraries

Add all UCanAccess libraries to:

Referenced Libraries

including:

ucanaccess.jar
commons-lang3.jar
commons-logging.jar
hsqldb.jar
jackcess.jar
4. Configure Database Path

Update the database path in:

DatabaseManager.java

Example:

private static final String DB_PATH =
"jdbc:ucanaccess://C:/Users/Username/Documents/FirstBank.accdb";
5. Run Application

Run:

Main.java
Learning Objectives

This project was developed to demonstrate:

Java GUI Development
Event Handling
Form Validation
Database Connectivity
Object-Oriented Programming
Account Number Generation
Business Rule Implementation
Author

Developed as an academic Java Swing project for learning desktop application development, database connectivity, and object-oriented programming principles.
