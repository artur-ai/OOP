package maiboroda.com.labs_course_2.lab3;

public class BankAccount {
    private String owner;
    private double balance;
    private OverdraftListener overdraftListener;

    public BankAccount(String owner, double initialBalance) {
        this.owner = owner;
        this.balance = initialBalance;
    }

    public void setOverdraftListener(OverdraftListener listener) {
        this.overdraftListener = listener;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println(owner + " поповнив/ла рахунок на " + amount + ". Баланс: " + balance);
    }

    public void withdraw(double amount) {
        balance -= amount;
        System.out.println(owner + " зняв/ла " + amount + ". Баланс: " + balance);

        if (balance < 0 && overdraftListener != null) {
            double deficit = Math.abs(balance);
            overdraftListener.onOverdraft(new OverdraftEvent(this, deficit));
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getOwner() {
        return owner;
    }
}
