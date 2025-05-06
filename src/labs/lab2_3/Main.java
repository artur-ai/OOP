package labs.lab2_3;

public class Main {
    public static void main(String[] args) {
        TextContainer text = new TextContainer();

        text.addLine(new MyString("hello world"));
        text.addLine(new MyString("java programming"));
        text.addLine(new MyString("object oriented design"));
        text.addLine(new MyString("remove me please"));
        String str = "hello";

        System.out.println("To UpperCase");
        String upperCase = str.toUpperCase();
        System.out.println(upperCase);

        System.out.println("\nInitial text:");
        text.printText();

        System.out.println("\nCapitalizing all words:");
        text.capitalizeAllWords();
        text.printText();

        System.out.println("\nRemoving lines with 'remove':");
        text.removeLinesContaining("remove");
        text.printText();

        System.out.println("\nMax line length: " + text.getMaxLineLength());

        System.out.println("\nClearing text:");
        text.clearText();
        text.printText();
    }
}

