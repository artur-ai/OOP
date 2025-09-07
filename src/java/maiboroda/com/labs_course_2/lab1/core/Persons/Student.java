package maiboroda.com.labs_course_2.lab1.core.Persons;

import maiboroda.com.labs_course_2.lab1.core.Actions.Studyable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Student extends Person implements Studyable {
    private int course;
    private String studentId;
    private LocalDate birthday;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Student(String firstName, String lastName, int course, String studentId, LocalDate birthday) {
        super(firstName, lastName);
        if (!studentId.matches("КВ\\d{6}")) {
            throw new IllegalArgumentException("Student id must be in format -> КВ******");
        }
        if (course < 1 || course > 5) {
            throw new IllegalArgumentException("Course must be 1 to 5");
        }
        this.course = course;
        this.studentId = studentId;
        this.birthday = birthday;
    }

    public Student(String line) {
        String[] parts = line.split(",");
        if (parts.length != 6 || !parts[0].equals("Student")) {
            throw new IllegalArgumentException("Invalid line for Student: " + line);
        }
        super(parts[1], parts[2]);
        this.studentId = parts[3];
        this.course = Integer.parseInt(parts[4]);
        this.birthday = LocalDate.parse(parts[5], FORMATTER);
    }

    @Override
    public void study() {
        this.course++;
        System.out.println("Student " + getFirstName() + " " + getLastName()
                + " is already on course: " + course);

    }


    public int getCourse() { return course; }
    public String getStudentId() { return studentId; }
    public LocalDate getBirthday() { return birthday; }

    @Override
    public String toFileFormat() {
        return "Student," + firstName + "," + lastName + "," + studentId + "," + course + "," +
                birthday.format(FORMATTER);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " | Course: " + course + " | ID: " + studentId +
                " | Birthday: " + birthday.format(FORMATTER);
    }
}
