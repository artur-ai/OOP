package labs.Homework;

public class CharArray {
    private char[] array;
    private int vowelCount;

    public CharArray(int size) {
        array = new char[size];
        vowelCount = 0;
    }

    public void set(int index, char value) {
        if (index >= 0 && index < array.length) {
            if (isVowel(array[index]) && !isVowel(value)) {
                vowelCount--;
            }
            else if (!isVowel(array[index]) && isVowel(value)) {
                vowelCount++;
            }
            array[index] = value;
        }
    }

    public char get(int index) {
        if (index >= 0 && index < array.length) {
            return Character.toUpperCase(array[index]);
        } else {
            return '\0';
        }
    }

    public int getVowelCount() {
        return vowelCount;
    }

    private boolean isVowel(char ch) {
        ch = Character.toUpperCase(ch);
        return "AEIOUY".indexOf(ch) >= 0;
    }
}
