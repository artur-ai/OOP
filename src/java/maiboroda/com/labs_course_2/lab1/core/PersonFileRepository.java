package maiboroda.com.labs_course_2.lab1.core;

import maiboroda.com.labs_course_2.lab1.core.Actions.IRepository;
import maiboroda.com.labs_course_2.lab1.core.Persons.Person;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonFileRepository implements IRepository<Person> {
    private final String fileName;

    public PersonFileRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void saveAll(List<Person> persons) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Person p : persons) {
                writer.write(p.toFileFormat());
                writer.newLine();
            }
        } catch (IOException exception) {
            throw new RuntimeException("Error writing to file: " + fileName, exception);
        }
    }

    @Override
    public List<Person> loadAll() {
        List<Person> persons = new ArrayList<>();
        // TODO: тут розпарсити Student/Musician в залежності від типу
        return persons;
    }
}
