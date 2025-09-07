package maiboroda.com.labs_course_2.lab1.app;

import maiboroda.com.labs_course_2.lab1.core.*;
import maiboroda.com.labs_course_2.lab1.core.Persons.*;
import maiboroda.com.labs_course_2.lab1.dataaccess.UniversalFileRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UniversalFileRepository repository = new UniversalFileRepository("students.txt");
        List<Person> persons = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        persons.add(new Student("Ivan", "Petrov", 1, "КВ123456", LocalDate.parse("12.05.2003", formatter)));
        persons.add(new Student("Maksim", "Sydorez", 2,"КВ456987", LocalDate.parse("29.12.2006", formatter)));
        persons.add(new Student("Dasha", "Ivanova", 4, "КВ654321", LocalDate.parse("08.03.2004", formatter)));
        persons.add(new Student("Maria", "Lobach", 4, "КВ654331", LocalDate.parse("25.03.2004", formatter)));


        Student student = new Student("Artem", "Balak", 3, "КВ123458", LocalDate.parse("25.03.2003", formatter));
        persons.add(student);
        student.study();

        Baker baker = new Baker("Ohel", "Khmel", "TopBakery");
        persons.add(baker);
        baker.bakeBread();

        Entrepreneur entrepreneur = new Entrepreneur("Vadim", "Shevchenko", "TechnoTop");
        persons.add(entrepreneur);
        entrepreneur.work();


        Musician musician = new Musician("Vlad", "Melnik", "Violin");
        persons.add(musician);
        musician.play();



        ConsoleMenu menu = new ConsoleMenu(repository, persons);
        menu.show();
    }
}
