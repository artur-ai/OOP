package com.maiboroda.dal;


import java.io.*;
import com.maiboroda.models.Student;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudentFileRepo implements IStudentRepo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public List<Student> loadStudents(String filePath) {
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(";");
                if (parts.length == 5) {
                    String lastName = parts[0].trim();
                    String firstName = parts[1].trim();
                    int course = Integer.parseInt(parts[2].trim());
                    String studentId = parts[3].trim();
                    String birthday = parts[4].trim();

                    students.add(new Student(lastName, firstName, course, studentId, birthday));
                }
            }
        } catch (FileNotFoundException exception) {
            throw new RuntimeException(exception);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return students;
    }

    @Override
    public void saveStudents(List<Student> students, String filePath) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (Student student : students) {
                String line = String.format("%s;%s;%d;%s;%s",
                        student.getLastName(),
                        student.getFirstName(),
                        student.getCourse(),
                        student.getStudentId(),
                        student.getBirthDateFormatted()
                );
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }
}
