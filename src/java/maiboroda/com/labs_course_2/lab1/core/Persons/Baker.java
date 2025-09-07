package maiboroda.com.labs_course_2.lab1.core.Persons;

public class Baker extends Person {
    private String bakeryName;

    public Baker(String firstName, String lastName, String bakeryName) {
        super(firstName, lastName);
        this.bakeryName = bakeryName;
    }

    public void bakeBread() {
        System.out.println("Baker: " + getFirstName() + " " + getLastName() + " is bake whiteBread");

    }

    @Override
    public String toFileFormat() {
        return "Baker " + firstName + lastName + "\n" +
                "{ \"firstname\": \"" + firstName + "\",\n" +
                "  \"lastname\": \"" + lastName + "\",\n" +
                "  \"bakeryName\": \"" + bakeryName + "\"};\n";
    }
}
