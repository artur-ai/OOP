package labs.lab4;

public class Main {
    public static void main(String[] args) {
        NumericString numericString = new NumericString("123456");

        System.out.println("Рядок: " + numericString.getValue());

        System.out.println("Довжина рядка: " + numericString.getLength());

        System.out.println("Обернений рядок: " + numericString.reverseString());
    }
}