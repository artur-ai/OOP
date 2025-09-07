package maiboroda.com.labs_course_2.lab1.dataaccess;

import maiboroda.com.labs_course_2.lab1.core.Persons.Person;
import maiboroda.com.labs_course_2.lab1.core.Persons.Student;
import maiboroda.com.labs_course_2.lab1.core.Persons.Musician;
import maiboroda.com.labs_course_2.lab1.core.Actions.IRepository;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UniversalFileRepository implements IRepository<Person> {
    private final String fileName;
    private List<Person> allPersons = new ArrayList<>();

    public UniversalFileRepository(String fileName) {
        this.fileName = fileName;
        this.allPersons = loadAll();
    }

    @Override
    public void saveAll(List<Person> persons) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Person p : persons) {
                writer.write(p.toFileFormat());
                writer.newLine();
            }
            this.allPersons = new ArrayList<>(persons);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + fileName, e);
        }
    }

    @Override
    public List<Person> loadAll() {
        List<Person> persons = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Student,")) {
                    persons.add(new Student(line));
                } else if (line.contains("Course:") && line.contains("ID:") && line.contains("Birthday:")) {
                    String[] parts = line.split("\\|");
                    if (parts.length == 4) {
                        String[] nameParts = parts[0].trim().split(" ");
                        String firstName = nameParts[0];
                        String lastName = nameParts[1];
                        int course = Integer.parseInt(parts[1].trim().replace("Course:", "").trim());
                        String studentId = parts[2].trim().replace("ID:", "").trim();
                        String birthdayStr = parts[3].trim().replace("Birthday:", "").trim();

                        LocalDate birthday = LocalDate.parse(birthdayStr,
                                DateTimeFormatter.ofPattern("dd.MM.yyyy"));

                        persons.add(new Student(firstName, lastName, course, studentId, birthday));
                    }
                } else if (line.startsWith("Musician,")) {
                    String[] parts = line.split(",");
                    persons.add(new Musician(parts[1], parts[2], parts[3]));
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file: " + fileName, e);
        }
        return persons;
    }
}

