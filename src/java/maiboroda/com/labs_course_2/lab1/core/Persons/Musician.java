package maiboroda.com.labs_course_2.lab1.core.Persons;

import maiboroda.com.labs_course_2.lab1.core.Actions.Playable;

public class Musician extends Person implements Playable {
    private String instrument;

    public Musician(String firstName, String lastName, String instrument) {
        super(firstName, lastName);
        this.instrument = instrument;
    }

    @Override
    public void play() {
        System.out.println(firstName + " " + lastName + " is playing " + instrument);
    }

    @Override
    public String toFileFormat() {
        return "Musician," + firstName + "," + lastName + "," + instrument;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " | Instrument: " + instrument;
    }
}
