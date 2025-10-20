package com.maiboroda.bll;

import com.maiboroda.dal.IStudentRepo;
import java.io.IOException;
import com.maiboroda.models.Student;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class StudentService {
    private final IStudentRepo repository;
    private List<Student> students;

    public StudentService(IStudentRepo repository) {
        if (repository == null) {
            throw new IllegalArgumentException("Repository не може бути null");
        }
        this.repository = repository;
        this.students = new ArrayList<>();
    }

    public void loadStudentsFromFile(String filePath) throws IOException {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Шлях до файлу не може бути порожнім");
        }
        students = repository.loadStudents(filePath);
    }

    public void saveStudentsToFile(String filePath) throws IOException {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Шлях до файлу не може бути порожнім");
        }
        repository.saveStudents(students, filePath);
    }

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Студент не може бути null");
        }

        validateStudent(student);

        if (students.stream().anyMatch(s -> s.getStudentId().equals(student.getStudentId()))) {
            throw new IllegalArgumentException("Студент з таким квитком вже існує");
        }

        students.add(student);
    }

    public boolean removeStudent(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Студентський квиток не може бути порожнім");
        }
        return students.removeIf(s -> s.getStudentId().equals(studentId));
    }

    public Student findStudentById(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Студентський квиток не може бути порожнім");
        }
        return students.stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .findFirst()
                .orElse(null);
    }

    public int countFourthCourseSpringBorn() {
        return (int) students.stream()
                .filter(s -> s.getCourse() == 4)
                .filter(Student::isBornInSpring)
                .count();
    }

    public List<Student> getFourthCourseSpringBornStudents() {
        return students.stream()
                .filter(s -> s.getCourse() == 4)
                .filter(Student::isBornInSpring)
                .collect(Collectors.toList());
    }

    public void celebrateBirthdays() {
        students.stream()
                .filter(Student::isBirthdayToday)
                .forEach(Student::incrementAgeOnBirthday);
    }

    public List<Student> getStudentsByCourse(int course) {
        if (course < 1 || course > 6) {
            throw new IllegalArgumentException("Курс повинен бути від 1 до 6");
        }
        return students.stream()
                .filter(s -> s.getCourse() == course)
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsByBirthYear(int year) {
        if (year < 1900 || year > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Некоректний рік народження");
        }
        return students.stream()
                .filter(s -> s.getBirthday().getYear() == year)
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsByAge(int age) {
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("Некоректний вік");
        }
        return students.stream()
                .filter(s -> s.getAge() == age)
                .collect(Collectors.toList());
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public int getStudentCount() {
        return students.size();
    }

    public void clearStudents() {
        students.clear();
    }

    private void validateStudent(Student student) {
        if (student.getLastName() == null || student.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Прізвище не може бути порожнім");
        }
        if (student.getFirstName() == null || student.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("Ім'я не може бути порожнім");
        }
        if (student.getCourse() < 1 || student.getCourse() > 6) {
            throw new IllegalArgumentException("Курс повинен бути від 1 до 6");
        }
        if (student.getStudentId() == null || student.getStudentId().trim().isEmpty()) {
            throw new IllegalArgumentException("Студентський квиток не може бути порожнім");
        }
        if (student.getBirthday() == null) {
            throw new IllegalArgumentException("Дата народження не може бути null");
        }
        if (student.getBirthday().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Дата народження не може бути в майбутньому");
        }
        if (student.getAge() < 15 || student.getAge() > 100) {
            throw new IllegalArgumentException("Вік студента повинен бути від 15 до 100 років");
        }
    }
}