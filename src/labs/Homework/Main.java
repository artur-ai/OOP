package labs.Homework;

public class Main {
    public static void main(String[] args) {
        CharArray myArray = new CharArray(5);

        myArray.set(0, 'a');
        myArray.set(1, 'b');
        myArray.set(2, 'e');
        myArray.set(3, 'k');
        myArray.set(4, 'i');

        for (int i = 0; i < 5; i++) {
            System.out.println("Елемент " + i + ": " + myArray.get(i));
        }

        System.out.println("Кількість голосних літер: " + myArray.getVowelCount());
    }
}

