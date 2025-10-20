package com.maiboroda.dal;

import java.io.IOException;
import com.maiboroda.models.Student;
import java.util.List;

public interface IStudentRepo {
    List<Student> loadStudents(String filePath) throws IOException;
    void saveStudents(List<Student> students, String filePath);
}
