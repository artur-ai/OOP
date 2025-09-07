package maiboroda.com.labs_course_2.lab1.core.Actions;

import maiboroda.com.labs_course_2.lab1.core.Persons.Person;

import java.util.List;

public interface IRepository<T extends Person> {
    void saveAll(List<T> persons);
    List<T> loadAll();
}
