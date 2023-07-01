package CriticalThinking.Module7;

public class ScopeMinimizationDiscussionNonCompliant {
    String accountNumber;
    double balance;

    public ScopeMinimizationDiscussionNonCompliant(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            
            balance = balance + amount;
            System.out.println("Deposit of $" + amount + " successful. New balance: $" + balance);
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            
            balance = balance - amount;
            System.out.println("Withdrawal of $" + amount + " successful. New balance: $" + balance);
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }

    public static void main(String[] args) {
        ScopeMinimizationDiscussionNonCompliant account = new ScopeMinimizationDiscussionNonCompliant("1234567890");
        System.out.println("Account Number: " + account.accountNumber);  
        System.out.println("Initial Balance: $" + account.balance); 

        account.balance = 1000.0;  
        account.balance = -500.0;  

        System.out.println("Updated Balance: $" + account.balance); 
    }
}
