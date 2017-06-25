package accounting;

/*
 * Created by kloomis on 3/14/17.
 * Tracks the individual/total account value(s) in Algora Finance Fund
 */

public class Accounts {
    // global static variables altered by static methods
    private static int universalAccountNumber = 1000000;
    // totalAccountBalance is linked to the value of
    // individual accounts. It is updated when
    // individual accounts increase/decrease in value
    private static double totalAccountBalance;
    // updatedFundBalance is updated manually by
    // account manager to reflect balance in IB
    private static double updatedFundBalance;
    private static double retainedEarnings;
    // holds the minimum retained earnings
    private static double minRetainedEarnings;

    // variables specific for individual accounts
    private String name;
    private String password;
    private String dateOfEntrance;
    private int accountNumber;
    private double fundBalanceUponEntrance;
    private double initInvestment;
    private double balance;
    private double percentUponEntrance;
    // tracks if individual is account manager
    // to waive the 2% management/20% upside fee
    private boolean isAccountManager;


    public Accounts(String name, String password, String dateOfEntrance, double initInvestment, boolean isAccountManager) {
        this.name = name;
        this.password = password;
        this.dateOfEntrance = dateOfEntrance;
        this.initInvestment = initInvestment;
        this.fundBalanceUponEntrance = totalAccountBalance;
        this.accountNumber = getUniversalAccountNumber();
        this.isAccountManager = isAccountManager;
        updateTotalAccountBalance(initInvestment);

        if (isAccountManager == true) {
            this.balance += initInvestment;
            this.percentUponEntrance = initInvestment / totalAccountBalance;
        }
        else {
            this.balance += (initInvestment)*0.98;
            this.retainedEarnings += (initInvestment)*0.02;
            this.minRetainedEarnings += retainedEarnings;
            this.percentUponEntrance = (initInvestment*0.98) / totalAccountBalance;
        }
    }

    // static methods - not associated with any accounts
    public static void updateTotalAccountBalance(double initInvestment) { totalAccountBalance += initInvestment; }
    public static double getTotalAccountBalance() { return totalAccountBalance; }

    public static int getUniversalAccountNumber() { return universalAccountNumber++; }

    public static double getRetainedEarnings() { return retainedEarnings; }
    public static double getMinRetainedEarnings() { return minRetainedEarnings; }
    public static void updateRetainedEarnings(double PL) { retainedEarnings += PL; }
    public static void updateFundAndIndBalance(double currentVal, Accounts[] accountsArray) {
        double pctPL = currentVal / getTotalAccountBalance();
        updatedFundBalance = currentVal;
        // iterates through accounts in accountsArray and
        // updates individual account values
        for (int i = 0; i < accountsArray.length; i++) {
            accountsArray[i].updatedIndAccountBalance(pctPL);
        }
        totalAccountBalance = currentVal;
    }

    // iterates through array of accounts
    public static boolean balanceCheck(Accounts[] accounts) {
        System.out.println("Checking and comparing account balance...");

        double balanceCheck = getRetainedEarnings();
        for (int i = 0; i < accounts.length; i++) {
            balanceCheck += accounts[i].getPersonalBalance();
        }
        System.out.println("Combined account value: " + balanceCheck);
        System.out.println("Total balance:          " + getTotalAccountBalance());
        return balanceCheck == (getTotalAccountBalance());
    }

    // setter/getter methods for Accounts data object
    public double getPersonalBalance() { return balance; }

    public boolean getIsAccountManager() { return isAccountManager; }
    public int getAccountNumber() { return accountNumber; }

    public void setName(String newName) { this.name = newName; }
    public String getName() { return name; }

    public void setDateOfEntrance(String dateOfEntrance) { this.dateOfEntrance = dateOfEntrance; }
    public String getDateOfEntrance() { return dateOfEntrance; }

    public void setFundBalanceUponEntrance(double fundBalanceUponEntrance) { this.fundBalanceUponEntrance = fundBalanceUponEntrance; }
    public double getFundBalanceUponEntrance() { return fundBalanceUponEntrance; }

    public void setInitInvestment(double initInvestment) { this.initInvestment = initInvestment; }
    public double getInitInvestment() { return initInvestment; }

    public double getPercentUponEntrance() { return percentUponEntrance; }

    public boolean setPassword(String oldPassword, String newPassword) {
        if (password.equals(oldPassword)) {
            password = newPassword;
            return true;
        } else { return false; }
    }

    public void updatedIndAccountBalance(double pctPL) {
        double netBalance = (balance * (pctPL - 1));
        if (getIsAccountManager()) {
            this.balance = balance + netBalance;
        }
        else if (pctPL > 1) {
            this.balance = balance + (netBalance * 0.8);
            updateRetainedEarnings((netBalance * 0.2) + (getRetainedEarnings() * (pctPL -1)));
        }
        else {
            if (balance + netBalance < (getInitInvestment() * 0.98)) {
                this.balance = balance + netBalance ;
                updateRetainedEarnings(getRetainedEarnings() * (pctPL -1));
            }
            else {
                this.balance = balance + netBalance;
                updateRetainedEarnings(getRetainedEarnings() * (pctPL - 1));
            }
        }
    }

    public void deposit(String enterPassword, String date, double amount) {
        if (password.equals(enterPassword)) {
            if (getIsAccountManager()) {
                this.balance = balance + amount;
            }
            else {
                this.balance = balance + (amount * 0.98);
            }
        }
    } // depoit

    public void withdraw(String enterPassword, String date, double amount) {
        // only people with the correct password and sufficient funds can withdraw
        if (password.equals(enterPassword) && balance >= amount) { this.balance = balance - amount; }
        else { System.out.println("Insufficient funds for withdrawal."); }
    } // withdraw


    // toString methods for printing data
    public void indAccountToString(String password) {
        System.out.println("Name:                           " + getName());
        System.out.println("Account number:                 " + getAccountNumber());
        System.out.println("Date of entrance:               " + getDateOfEntrance());
        System.out.println("Fund balance upon entrance:     " + getFundBalanceUponEntrance());
        System.out.println("Initial investment:             " + getInitInvestment());
        System.out.println("Personal balance:               " + getPersonalBalance());
        //System.out.println("Percent of fund upon entrance:  " + getPercentUponEntrance());
    }

    public static void accountValToString() {
        System.out.println("Total account balance:          " +  getTotalAccountBalance());
        System.out.println("Total retained earnings:        " +  getRetainedEarnings());
    }

    public static void main(String[] args) {

        Accounts kyleWLoomis = new Accounts("Kyle Loomis", "password", "2016-09-02", 1080.72, true);


        // all print statements
        System.out.println("Main method playground for accounting system database");
        System.out.println();
        kyleWLoomis.indAccountToString("password");
    } // main
} // Accounts class
