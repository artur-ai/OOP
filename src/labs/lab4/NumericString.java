package labs.lab4;

public class NumericString extends StringBase {

    public NumericString(String value) {
        super(value);
    }

    public String reverseString() {
        return new StringBuilder(value).reverse().toString();
    }
}
