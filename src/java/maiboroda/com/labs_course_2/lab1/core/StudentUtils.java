package maiboroda.com.labs_course_2.lab1.core;

import maiboroda.com.labs_course_2.lab1.core.Persons.Person;
import maiboroda.com.labs_course_2.lab1.core.Persons.Student;

import java.util.List;

public class StudentUtils {
    public static int countSpringFourthYearStudents(List<Person> persons) {
        int count = 0;
        for (Person p : persons) {
            if (p instanceof Student) {
                Student s = (Student) p;
                if (s.getCourse() == 4) {
                    int month = s.getBirthday().getMonthValue();
                    if (month >= 3 && month <= 5) count++;
                }
            }
        }
        return count;
    }
}
