package com.maiboroda.pl;

import com.maiboroda.bll.StudentService;
import com.maiboroda.dal.IStudentRepo;

import java.io.IOException;

import com.maiboroda.dal.RepositoryFactory;
import com.maiboroda.models.Baker;
import com.maiboroda.models.Student;
import java.util.List;
import java.util.Scanner;


public class ConsoleApp {

    private final StudentService studentService;
    private final Scanner scanner;

    public ConsoleApp() {
        IStudentRepo repository = RepositoryFactory.createFileRepository();
        this.studentService = new StudentService(repository);
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("=== Система управління студентами ===\n");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput("Виберіть опцію: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    findStudent();
                    break;
                case 4:
                    removeStudent();
                    break;
                case 5:
                    countFourthCourseSpringBorn();
                    break;
                case 6:
                    viewFourthCourseSpringBorn();
                    break;
                case 7:
                    celebrateBirthdays();
                    break;
                case 8:
                    loadFromFile();
                    break;
                case 9:
                    saveToFile();
                    break;
                case 10:
                    demoBackerFunctionality();
                    break;
                case 0:
                    running = false;
                    System.out.println("До побачення!");
                    break;
                default:
                    System.out.println("Невірна опція!");
            }

            System.out.println();
        }

        scanner.close();
    }

    private void printMenu() {
        System.out.println("--- МЕНЮ ---");
        System.out.println("1. Додати студента");
        System.out.println("2. Переглянути всіх студентів");
        System.out.println("3. Знайти студента за квитком");
        System.out.println("4. Видалити студента");
        System.out.println("5. Підрахувати студентів 4 курсу народжених навесні");
        System.out.println("6. Показати студентів 4 курсу народжених навесні");
        System.out.println("7. Відзначити дні народження");
        System.out.println("8. Завантажити з файлу");
        System.out.println("9. Зберегти у файл");
        System.out.println("10. Демонстрація Baker функціоналу");
        System.out.println("0. Вихід");
    }

    private void addStudent() {
        System.out.println("\n--- Додавання студента ---");

        try {
            String lastName = getStringInput("Прізвище: ");
            String firstName = getStringInput("Ім'я: ");
            int course = getIntInput("Курс (1-6): ");
            String studentId = getStringInput("Студентський квиток: ");
            String birthDate = getStringInput("Дата народження (ДД.ММ.РРРР): ");

            Student student = new Student(lastName, firstName, course, studentId, birthDate);
            studentService.addStudent(student);

            System.out.println("✓ Студента успішно додано!");
        } catch (Exception e) {
            System.out.println("✗ Помилка: " + e.getMessage());
        }
    }

    private void viewAllStudents() {
        System.out.println("\n--- Список всіх студентів ---");

        List<Student> students = studentService.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("Список студентів порожній.");
        } else {
            System.out.printf("%-15s %-15s %-5s %-12s %-15s %-5s%n",
                    "Прізвище", "Ім'я", "Курс", "Квиток", "Дата народж.", "Вік");
            System.out.println("─".repeat(80));

            for (Student s : students) {
                System.out.printf("%-15s %-15s %-5d %-12s %-15s %-5d%n",
                        s.getLastName(), s.getFirstName(), s.getCourse(),
                        s.getStudentId(), s.getBirthDateFormatted(), s.getAge());
            }

            System.out.println("\nВсього студентів: " + students.size());
        }
    }

    private void findStudent() {
        System.out.println("\n--- Пошук студента ---");

        String studentId = getStringInput("Студентський квиток: ");
        Student student = studentService.findStudentById(studentId);

        if (student == null) {
            System.out.println("✗ Студента не знайдено.");
        } else {
            System.out.println("\n✓ Знайдено:");
            System.out.println(student);
        }
    }

    private void removeStudent() {
        System.out.println("\n--- Видалення студента ---");

        String studentId = getStringInput("Студентський квиток: ");
        boolean removed = studentService.removeStudent(studentId);

        if (removed) {
            System.out.println("✓ Студента видалено.");
        } else {
            System.out.println("✗ Студента не знайдено.");
        }
    }

    private void countFourthCourseSpringBorn() {
        System.out.println("\n--- Підрахунок студентів 4 курсу народжених навесні ---");

        int count = studentService.countFourthCourseSpringBorn();
        System.out.println("Кількість: " + count);
    }

    private void viewFourthCourseSpringBorn() {
        System.out.println("\n--- Студенти 4 курсу народжені навесні ---");

        List<Student> students = studentService.getFourthCourseSpringBornStudents();

        if (students.isEmpty()) {
            System.out.println("Таких студентів немає.");
        } else {
            System.out.printf("%-15s %-15s %-5s %-12s %-15s%n",
                    "Прізвище", "Ім'я", "Курс", "Квиток", "Дата народж.");
            System.out.println("─".repeat(65));

            for (Student s : students) {
                System.out.printf("%-15s %-15s %-5d %-12s %-15s%n",
                        s.getLastName(), s.getFirstName(), s.getCourse(),
                        s.getStudentId(), s.getBirthDateFormatted());
            }

            System.out.println("\nВсього: " + students.size());
        }
    }

    private void celebrateBirthdays() {
        System.out.println("\n--- Відзначення днів народження ---");
        studentService.celebrateBirthdays();
        System.out.println("Перевірку завершено.");
    }

    private void loadFromFile() {
        System.out.println("\n--- Завантаження з файлу ---");

        String filePath = getStringInput("Шлях до файлу: ");

        try {
            studentService.loadStudentsFromFile(filePath);
            System.out.println("✓ Завантажено " + studentService.getStudentCount() + " студентів.");
        } catch (IOException e) {
            System.out.println("✗ Помилка читання файлу: " + e.getMessage());
        }
    }

    private void saveToFile() {
        System.out.println("\n--- Збереження у файл ---");

        String filePath = getStringInput("Шлях до файлу: ");

        try {
            studentService.saveStudentsToFile(filePath);
            System.out.println("✓ Збережено " + studentService.getStudentCount() + " студентів.");
        } catch (IOException e) {
            System.out.println("✗ Помилка запису файлу: " + e.getMessage());
        }
    }

    private void demoBackerFunctionality() {
        System.out.println("\n--- Демонстрація функціоналу Baker ---");

        try {
            Baker baker = new Baker("Коваленко", "Костянтин", 3, "BK001", "20.05.2003");
            System.out.println("Створено пекаря: " + baker.getFirstName() + " " + baker.getLastName());

            System.out.println("\n1. Готуємо тістечка:");
            String cake1 = baker.prepareCake(java.util.Arrays.asList("шоколад", "вершки"));
            System.out.println("   " + cake1);

            String cake2 = baker.prepareCake(java.util.Arrays.asList("ванілін", "полуниця", "крем"));
            System.out.println("   " + cake2);

            System.out.println("\n2. Готуємо торт:");
            String layered = baker.prepareLayeredCake(java.util.Arrays.asList("бісквіт", "крем", "фрукти"));
            System.out.println("   " + layered);

            System.out.println("\nВсього спеціальностей: " + baker.getSpecialtiesCount());

            System.out.println("\n3. Стрибки з парашутом:");
            System.out.println("   Має ліцензію: " + baker.hasParachuteLicense());

            baker.obtainParachuteLicense();
            System.out.println("   ✓ Отримано ліцензію");
            System.out.println("   Має ліцензію: " + baker.hasParachuteLicense());

            baker.jumpWithParachute(2500);
            System.out.println("   ✓ Успішний стрибок!");

        } catch (Exception e) {
            System.out.println("✗ Помилка: " + e.getMessage());
        }
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Будь ласка, введіть число.");
            }
        }
    }

    public static void main(String[] args) {
        ConsoleApp app = new ConsoleApp();
        app.run();
    }
}
