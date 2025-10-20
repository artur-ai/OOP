package com.maiboroda.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Student {
    private String firstName;
    private String lastName;
    private int course;
    private String studentId;
    private LocalDate birthday;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Student(String firstName, String lastName, int course, String studentId, String birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
        this.studentId = studentId;
        this.birthday = LocalDate.parse(birthday, DATE_TIME_FORMATTER);
    }

    public Student(String firstName, String lastName, int course, String studentId, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
        this.studentId = studentId;
        this.birthday = birthday;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        if (course < 1 || course > 5) {
            throw new IllegalArgumentException("Course must be 1 to 5");
        }
        this.course = course;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        if (!studentId.startsWith("КВ")) {
            throw new IllegalArgumentException("Student id must be in format КВxxxxxx");
        }
        this.studentId = studentId;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getBirthDateFormatted() {
        return birthday.format(DATE_TIME_FORMATTER);
    }

    public int getAge() {
        return (int) ChronoUnit.YEARS.between(birthday, LocalDate.now());
    }

    public boolean isBornInSpring() {
        int month = birthday.getMonthValue();
        return month >= 3 && month <= 5;
    }

    public void incrementAgeOnBirthday() {
        LocalDate today = LocalDate.now();
        LocalDate birthdayThisYear = birthday.withYear(today.getYear());

        if (today.equals(birthdayThisYear)) {
            System.out.println("З днем народження, " + firstName + "! Вам виповнилось " + getAge() + " років!");
        }
    }

    public boolean isBirthdayToday() {
        LocalDate today = LocalDate.now();
        return birthday.getMonthValue() == today.getMonthValue()
                && birthday.getDayOfMonth() == today.getDayOfMonth();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return course == student.course && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(studentId, student.studentId) && Objects.equals(birthday, student.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", course=" + course +
                ", studentId='" + studentId + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
