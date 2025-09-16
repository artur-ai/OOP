package maiboroda.com.labs_course_2.lab2;

class MyString implements Comparable<MyString> {
    private String value;

    public MyString(String value) {
        this.value = value;
    }

    public int length() {
        return value.length();
    }

    public boolean containsChar(char c) {
        return value.indexOf(c) != -1;
    }

    public void reverse() {
        value = new StringBuilder(value).reverse().toString();
    }

    public void append(String s) {
        value += s;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int compareTo(MyString other) {
        return Integer.compare(this.length(), other.length());
    }

    @Override
    public String toString() {
        return value + " (len=" + length() + ")";
    }
}