package maiboroda.com.labs_course_2.lab1.core;

import maiboroda.com.labs_course_2.lab1.core.Actions.IRepository;
import maiboroda.com.labs_course_2.lab1.core.Persons.Musician;
import maiboroda.com.labs_course_2.lab1.core.Persons.Person;
import maiboroda.com.labs_course_2.lab1.core.Persons.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final IRepository<Person> repository;
    private final List<Person> persons;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleMenu(IRepository<Person> repository, List<Person> persons) {
        this.repository = repository;
        this.persons = persons;
    }

    public void show() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        while (true) {
            System.out.println("===== MENU =====");
            System.out.println("1. Show all persons");
            System.out.println("2. Add student");
            System.out.println("3. Add musician");
            System.out.println("4. Save to file");
            System.out.println("5. Load from file");
            System.out.println("6. Count 4th-year students born in spring");
            System.out.println("0. Exit");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> persons.forEach(System.out::println);
                case 2 -> {
                    System.out.print("First name: ");
                    String first = scanner.nextLine();
                    System.out.print("Last name: ");
                    String last = scanner.nextLine();
                    System.out.print("Course (1-5): ");
                    int course = Integer.parseInt(scanner.nextLine());
                    System.out.print("Student ID (КВ******): ");
                    String id = scanner.nextLine();
                    System.out.print("Birthday (dd.MM.yyyy): ");
                    LocalDate birth = LocalDate.parse(scanner.nextLine(), formatter);
                    persons.add(new Student(first, last, course, id, birth));
                }
                case 3 -> {
                    System.out.print("First name: ");
                    String first = scanner.nextLine();
                    System.out.print("Last name: ");
                    String last = scanner.nextLine();
                    System.out.print("Instrument: ");
                    String instr = scanner.nextLine();
                    persons.add(new Musician(first, last, instr));
                }
                case 4 -> repository.saveAll(persons);
                case 5 -> {
                    persons.clear();
                    persons.addAll(repository.loadAll());
                    ;
                }
                case 6 -> {
                    int springStudents = StudentUtils.countSpringFourthYearStudents(persons);
                    System.out.println("Number of 4th-year students born in spring: " + springStudents);
                }

                case 0 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
