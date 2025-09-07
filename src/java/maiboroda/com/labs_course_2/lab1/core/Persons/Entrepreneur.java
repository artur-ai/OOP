package maiboroda.com.labs_course_2.lab1.core.Persons;

public class Entrepreneur extends Person {
    private String companyName;

    public Entrepreneur(String firstName, String lastName, String companyName) {
        super(firstName, lastName);
        this.companyName = companyName;
    }

    public void work() {
        System.out.println("Entrepreneur: " + getFirstName() + " " + getLastName() + " is working!");
    }



    @Override
    public String toFileFormat() {
        return "Entrepreneur " + firstName + lastName + "\n" +
                "{ \"firstname\": \"" + firstName + "\",\n" +
                "  \"lastname\": \"" + lastName + "\",\n" +
                "  \"companyName\": \"" + companyName + "\"};\n";
    }
}
