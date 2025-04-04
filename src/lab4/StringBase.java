package lab4;

public class StringBase {
        protected String value;

        public StringBase(String value) {
            this.value = value;
        }

        public int getLength() {
            return value.length();
        }

        public String getValue() {
            return value;
        }
    }


