package labs.lab2_3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TextContainer {
    private final List<MyString> lines = new ArrayList<>();

    public void addLine(MyString line) {
        lines.add(line);
    }

    public void removeLine(int index) {
        if (index >= 0 && index < lines.size()) {
            lines.remove(index);
        }
    }

    public void removeLinesContaining(String substring) {
        Iterator<MyString> iterator = lines.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getValue().contains(substring)) {
                iterator.remove();
            }
        }
    }

    public void clearText() {
        lines.clear();
    }

    public int getMaxLineLength() {
        int max = 0;
        for (MyString line : lines) {
            max = Math.max(max, line.getValue().length());
        }
        return max;
    }

    public void capitalizeAllWords() {
        for (int i = 0; i < lines.size(); i++) {
            MyString current = lines.get(i);
            lines.set(i, new MyString(current.capitalizeWords()));
        }
    }

    public void printText() {
        for (MyString line : lines) {
            System.out.println(line);
        }
    }
}
