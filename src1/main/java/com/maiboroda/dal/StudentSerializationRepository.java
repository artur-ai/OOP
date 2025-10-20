package com.maiboroda.dal;

import java.io.*;
import com.maiboroda.models.Student;
import java.util.List;

class StudentSerializationRepository implements IStudentRepo {

    @Override
    public List<Student> loadStudents(String filePath) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Student>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Помилка десеріалізації", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveStudents(List<Student> students, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(students);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}