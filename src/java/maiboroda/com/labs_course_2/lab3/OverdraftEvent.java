package maiboroda.com.labs_course_2.lab3;

public class OverdraftEvent {
    private final BankAccount source;
    private final double deficit;

    public OverdraftEvent(BankAccount source, double deficit) {
        this.source = source;
        this.deficit = deficit;
    }

    public BankAccount getSource() {
        return source;
    }

    public double getDeficit() {
        return deficit;
    }
}
