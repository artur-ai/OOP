package maiboroda.com.labs_course_2.lab3;

public class BankApp {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("Артур", 100.0);
        BankAccount account1 = new BankAccount("Марія", 1000.0);

        account.setOverdraftListener(event -> {
            System.out.println("УВАГА! Перевищено кредитний ліміт для рахунку "
                    + event.getSource().getOwner());
            System.out.println("Дефіцит: " + event.getDeficit() + " грн");
        });

        account.deposit(50);
        account.withdraw(120);
        account.withdraw(60);

        account1.deposit(1000);
        String owner = account1.getOwner();
        System.out.println(owner);
        account1.withdraw(500);
        account1.getBalance();

    }
}
