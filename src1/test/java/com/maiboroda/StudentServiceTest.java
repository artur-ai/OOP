package com.maiboroda;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import com.maiboroda.bll.StudentService;
import com.maiboroda.dal.IStudentRepo;
import java.io.IOException;
import com.maiboroda.models.Student;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    private IStudentRepo mockRepository;
    private StudentService service;

    @BeforeEach
    void setUp() {
        mockRepository = Mockito.mock(IStudentRepo.class);
        service = new StudentService(mockRepository);
    }

    @Test
    @DisplayName("Конструктор: створення сервісу з валідним репозиторієм")
    void testConstructor_WithValidRepository_Success() {
        StudentService newService = new StudentService(mockRepository);

        assertNotNull(newService);
        assertEquals(0, newService.getStudentCount());
    }

    @Test
    @DisplayName("Конструктор: виняток при null репозиторії")
    void testConstructor_WithNullRepository_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new StudentService(null);
        });
        assertEquals("Repository не може бути null", exception.getMessage());
    }

    @Test
    @DisplayName("Додавання: валідний студент успішно додається")
    void testAddStudent_ValidStudent_Success() {
        Student student = new Student("Іваненко", "Іван", 4, "ST123456", "15.04.2002");

        service.addStudent(student);

        assertEquals(1, service.getStudentCount());
        assertNotNull(service.findStudentById("ST123456"));
    }

    @Test
    @DisplayName("Додавання: null студент викликає виняток")
    void testAddStudent_NullStudent_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.addStudent(null);
        });
        assertEquals("Студент не може бути null", exception.getMessage());
    }

    @Test
    @DisplayName("Додавання: дублікат студентського квитка викликає виняток")
    void testAddStudent_DuplicateStudentId_ThrowsException() {
        Student student1 = new Student("Іваненко", "Іван", 4, "ST123456", "15.04.2002");
        Student student2 = new Student("Петренко", "Петро", 3, "ST123456", "20.03.2003");
        service.addStudent(student1);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.addStudent(student2);
        });
        assertEquals("Студент з таким квитком вже існує", exception.getMessage());
    }

    @Test
    @DisplayName("Додавання: студент з порожнім прізвищем викликає виняток")
    void testAddStudent_EmptyLastName_ThrowsException() {
        Student student = new Student("", "Іван", 4, "ST123456", "15.04.2002");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.addStudent(student);
        });
        assertEquals("Прізвище не може бути порожнім", exception.getMessage());
    }

    @Test
    @DisplayName("Додавання: студент з некоректним курсом викликає виняток")
    void testAddStudent_InvalidCourse_ThrowsException() {
        Student student = new Student("Іваненко", "Іван", 7, "ST123456", "15.04.2002");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.addStudent(student);
        });
        assertTrue(exception.getMessage().contains("курс"));
    }

    @Test
    @DisplayName("Видалення: успішне видалення існуючого студента")
    void testRemoveStudent_ExistingStudent_Success() {
        Student student = new Student("Іваненко", "Іван", 4, "ST123456", "15.04.2002");
        service.addStudent(student);

        boolean result = service.removeStudent("ST123456");

        assertTrue(result);
        assertEquals(0, service.getStudentCount());
        assertNull(service.findStudentById("ST123456"));
    }

    @Test
    @DisplayName("Видалення: неіснуючий студент повертає false")
    void testRemoveStudent_NonExistingStudent_ReturnsFalse() {
        boolean result = service.removeStudent("ST999999");

        assertFalse(result);
    }

    @Test
    @DisplayName("Видалення: null квиток викликає виняток")
    void testRemoveStudent_NullStudentId_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.removeStudent(null);
        });
        assertEquals("Студентський квиток не може бути порожнім", exception.getMessage());
    }

    @Test
    @DisplayName("Пошук: знаходить існуючого студента")
    void testFindStudentById_ExistingStudent_ReturnsStudent() {
        Student student = new Student("Іваненко", "Іван", 4, "ST123456", "15.04.2002");
        service.addStudent(student);

        Student found = service.findStudentById("ST123456");

        assertNotNull(found);
        assertEquals("Іваненко", found.getLastName());
        assertEquals("Іван", found.getFirstName());
    }

    @Test
    @DisplayName("Пошук: неіснуючий студент повертає null")
    void testFindStudentById_NonExistingStudent_ReturnsNull() {
        Student found = service.findStudentById("ST999999");

        assertNull(found);
    }


    @Test
    @DisplayName("Підрахунок: студенти 4 курсу народжені навесні")
    void testCountFourthCourseSpringBorn_WithMatchingStudents_ReturnsCorrectCount() {
        service.addStudent(new Student("Іваненко", "Іван", 4, "ST001", "15.04.2002"));
        service.addStudent(new Student("Петренко", "Петро", 4, "ST002", "20.03.2002"));
        service.addStudent(new Student("Сидоренко", "Сидір", 4, "ST003", "10.05.2002"));
        service.addStudent(new Student("Коваленко", "Костя", 3, "ST004", "15.04.2003"));
        service.addStudent(new Student("Мельник", "Марія", 4, "ST005", "20.06.2002"));

        int count = service.countFourthCourseSpringBorn();

        assertEquals(3, count);
    }

    @Test
    @DisplayName("Підрахунок: немає студентів 4 курсу народжених навесні")
    void testCountFourthCourseSpringBorn_NoMatchingStudents_ReturnsZero() {
        service.addStudent(new Student("Іваненко", "Іван", 3, "ST001", "15.04.2002"));
        service.addStudent(new Student("Петренко", "Петро", 4, "ST002", "20.06.2002"));

        int count = service.countFourthCourseSpringBorn();

        assertEquals(0, count);
    }

    @Test
    @DisplayName("Отримання: список студентів 4 курсу народжених навесні")
    void testGetFourthCourseSpringBornStudents_ReturnsCorrectList() {
        Student s1 = new Student("Іваненко", "Іван", 4, "ST001", "15.04.2002");
        Student s2 = new Student("Петренко", "Петро", 4, "ST002", "20.03.2002");
        Student s3 = new Student("Коваленко", "Костя", 3, "ST003", "15.04.2003");

        service.addStudent(s1);
        service.addStudent(s2);
        service.addStudent(s3);

        List<Student> result = service.getFourthCourseSpringBornStudents();

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(s -> s.getCourse() == 4));
        assertTrue(result.stream().allMatch(Student::isBornInSpring));
    }

    @Test
    @DisplayName("Перевірка: березень це весна")
    void testSpringBorn_March_IsSpring() {
        Student student = new Student("Іваненко", "Іван", 4, "ST001", "15.03.2002");

        boolean isSpring = student.isBornInSpring();

        assertTrue(isSpring);
    }

    @Test
    @DisplayName("Перевірка: квітень це весна")
    void testSpringBorn_April_IsSpring() {
        Student student = new Student("Іваненко", "Іван", 4, "ST001", "15.04.2002");

        boolean isSpring = student.isBornInSpring();

        assertTrue(isSpring);
    }

    @Test
    @DisplayName("Перевірка: травень це весна")
    void testSpringBorn_May_IsSpring() {
        Student student = new Student("Іваненко", "Іван", 4, "ST001", "15.05.2002");

        boolean isSpring = student.isBornInSpring();

        assertTrue(isSpring);
    }

    @Test
    @DisplayName("Перевірка: лютий не весна")
    void testSpringBorn_February_NotSpring() {
        Student student = new Student("Іваненко", "Іван", 4, "ST001", "15.02.2002");

        boolean isSpring = student.isBornInSpring();

        assertFalse(isSpring);
    }

    @Test
    @DisplayName("Перевірка: червень не весна")
    void testSpringBorn_June_NotSpring() {
        Student student = new Student("Іваненко", "Іван", 4, "ST001", "15.06.2002");

        boolean isSpring = student.isBornInSpring();

        assertFalse(isSpring);
    }


    @Test
    @DisplayName("Завантаження: успішне завантаження з файлу")
    void testLoadStudentsFromFile_Success() throws IOException {
        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student("Іваненко", "Іван", 4, "ST001", "15.04.2002"));
        mockStudents.add(new Student("Петренко", "Петро", 3, "ST002", "20.03.2003"));

        when(mockRepository.loadStudents(anyString())).thenReturn(mockStudents);

        service.loadStudentsFromFile("test.txt");

        assertEquals(2, service.getStudentCount());
        verify(mockRepository, times(1)).loadStudents("test.txt");
    }

    @Test
    @DisplayName("Завантаження: порожній шлях викликає виняток")
    void testLoadStudentsFromFile_EmptyPath_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.loadStudentsFromFile("");
        });
        assertEquals("Шлях до файлу не може бути порожнім", exception.getMessage());
    }

    @Test
    @DisplayName("Завантаження: IOException передається далі")
    void testLoadStudentsFromFile_IOException_ThrowsException() throws IOException {
        when(mockRepository.loadStudents(anyString())).thenThrow(new IOException("File not found"));

        assertThrows(IOException.class, () -> {
            service.loadStudentsFromFile("nonexistent.txt");
        });
    }

    @Test
    @DisplayName("Збереження: успішне збереження у файл")
    void testSaveStudentsToFile_Success() throws IOException {
        service.addStudent(new Student("Іваненко", "Іван", 4, "ST001", "15.04.2002"));
        doNothing().when(mockRepository).saveStudents(any(), anyString());
        service.saveStudentsToFile("output.txt");

        verify(mockRepository, times(1)).saveStudents(any(), eq("output.txt"));
    }

    @Test
    @DisplayName("Збереження: порожній шлях викликає виняток")
    void testSaveStudentsToFile_EmptyPath_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.saveStudentsToFile("  ");
        });
        assertEquals("Шлях до файлу не може бути порожнім", exception.getMessage());
    }


    @Test
    @DisplayName("Фільтр: отримання студентів певного курсу")
    void testGetStudentsByCourse_ReturnsCorrectStudents() {
        service.addStudent(new Student("Іваненко", "Іван", 4, "ST001", "15.04.2002"));
        service.addStudent(new Student("Петренко", "Петро", 3, "ST002", "20.03.2003"));
        service.addStudent(new Student("Сидоренко", "Сидір", 4, "ST003", "10.05.2002"));

        List<Student> fourthCourse = service.getStudentsByCourse(4);

        assertEquals(2, fourthCourse.size());
        assertTrue(fourthCourse.stream().allMatch(s -> s.getCourse() == 4));
    }

    @Test
    @DisplayName("Фільтр: некоректний курс викликає виняток")
    void testGetStudentsByCourse_InvalidCourse_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.getStudentsByCourse(7);
        });
        assertTrue(exception.getMessage().contains("Курс повинен бути"));
    }


    @Test
    @DisplayName("Фільтр: отримання студентів певного віку")
    void testGetStudentsByAge_ReturnsCorrectStudents() {
        int currentYear = LocalDate.now().getYear();
        service.addStudent(new Student("Іваненко", "Іван", 4, "ST001",
                "15.04." + (currentYear - 20)));
        service.addStudent(new Student("Петренко", "Петро", 3, "ST002",
                "20.03." + (currentYear - 21)));

        List<Student> age20 = service.getStudentsByAge(20);

        assertEquals(1, age20.size());
        assertEquals(20, age20.get(0).getAge());
    }

    @Test
    @DisplayName("Фільтр: некоректний вік викликає виняток")
    void testGetStudentsByAge_InvalidAge_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.getStudentsByAge(150);
        });
        assertEquals("Некоректний вік", exception.getMessage());
    }


    @Test
    @DisplayName("Операції: отримання всіх студентів")
    void testGetAllStudents_ReturnsAllStudents() {
        service.addStudent(new Student("Іваненко", "Іван", 4, "ST001", "15.04.2002"));
        service.addStudent(new Student("Петренко", "Петро", 3, "ST002", "20.03.2003"));

        List<Student> all = service.getAllStudents();

        assertEquals(2, all.size());
    }

    @Test
    @DisplayName("Операції: очищення списку студентів")
    void testClearStudents_RemovesAllStudents() {
        service.addStudent(new Student("Іваненко", "Іван", 4, "ST001", "15.04.2002"));
        service.addStudent(new Student("Петренко", "Петро", 3, "ST002", "20.03.2003"));

        service.clearStudents();

        assertEquals(0, service.getStudentCount());
    }

    @Test
    @DisplayName("Операції: підрахунок кількості студентів")
    void testGetStudentCount_ReturnsCorrectCount() {
        service.addStudent(new Student("Іваненко", "Іван", 4, "ST001", "15.04.2002"));
        service.addStudent(new Student("Петренко", "Петро", 3, "ST002", "20.03.2003"));
        service.addStudent(new Student("Сидоренко", "Сидір", 2, "ST003", "10.05.2004"));

        int count = service.getStudentCount();

        assertEquals(3, count);
    }
}
