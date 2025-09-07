package maiboroda.com.labs_course_2.lab1.core.Persons;

public abstract class Person {
    protected String firstName;
    protected String lastName;

    public Person(String firstName, String lastName) {
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Wrong firstname or lastname format");
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

    public abstract String toFileFormat();

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
