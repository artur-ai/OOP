package labs.lab2_3;

public class MyString implements CaseChanger {
    private final String value;

    public MyString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toUpperCase() {
        return value.toUpperCase();
    }

    @Override
    public String toLowerCase() {
        return value.toLowerCase();
    }

    @Override
    public String capitalizeWords() {
        String[] words = value.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return result.toString().trim();
    }

    @Override
    public String toString() {
        return value;
    }
}

