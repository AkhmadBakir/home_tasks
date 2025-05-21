package StreamAPI.UniversityExample;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UniversityExampleTest {

    Set<String> examPassedNames;
    List<Course> allCourses;
    List<Student> students;
    Map<Integer, String> groupNames;
    List<String> graduatesClub;

    @BeforeEach
    void init() {
        examPassedNames = new HashSet<>();
        examPassedNames.add("Иванов Иван");
        examPassedNames.add("Попова Яна");

        allCourses = new ArrayList<>();
        Course java = new Course("java");
        allCourses.add(java);
        Course python = new Course("python");
        allCourses.add(python);
        Course javascript = new Course("javascript");
        allCourses.add(javascript);

        //allCourses.add(new Course("C++"));

        //соответствие года поступления и названия группы
        groupNames = new HashMap<>();
        groupNames.put(2020, "2020-ГР1");
        groupNames.put(2021, "2021-ГР0");

        //список с адресами email выпускников
        graduatesClub = new ArrayList<>();

        //студенты, планирующие завершить обучение
        students = new ArrayList<>();

        Student yana = new Student("Попова", "Яна", "yana@yandex.ru", 2021);
        yana.courses.add(java);
        yana.courses.add(python);
        students.add(yana);
        Student ivan = new Student("Иванов", "Иван", "ivan_ivanov@mail.ru", 2020);
        ivan.courses.add(java);
        ivan.courses.add(python);
        ivan.courses.add(javascript);
        ivan.courses.add(javascript);
        students.add(ivan);
        Student dmitriy = new Student("Сергеев", "Дмитрий", "iamdmitry@gmail.com", 2021);
        dmitriy.courses.add(javascript);
        students.add(dmitriy);

    }

    @Test
    void shouldGetListStudentsOf3Students() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Попова", "Яна", "yana@yandex.ru", 2021));
        students.add(new Student("Иванов", "Иван", "ivan_ivanov@mail.ru", 2020));
        students.add(new Student("Сергеев", "Дмитрий", "iamdmitry@gmail.com", 2021));
        assertThat(students).hasSize(3);
    }

    @Test
    void shouldGetUniqueCourses() {
        List<String> courses = List.of("java", "python", "java", "javascript");
        List<String> uniqueCourses = UniversityExample.getUniqueCourses(courses);
        assertThat(uniqueCourses).hasSize(3);
        assertThat(uniqueCourses).containsExactlyInAnyOrder("java", "python", "javascript");
    }

    @Test
    void shouldGetTopStudents() {
        List<String> topStudents = UniversityExample.getTopStudents(students);
        assertThat(topStudents).hasSize(2);
        assertThat(topStudents).containsExactly("Иван", "Яна");
    }

    @Test
    void shouldGetTopCourses() {
        Optional<Course> topCourse = UniversityExample.getTopCourse(students);
        Course javascript = new Course("javascript");
        assertThat(topCourse).isNotEmpty();
        Course course = topCourse.get();
        assertThat(course).isEqualTo(javascript);
    }
    @Test
    void shouldGetHalfCourse() {
        List<String> students1 = UniversityExample.moreHalfCourses(students, allCourses);
        assertThat(students1).hasSize(2);
        assertThat(students1).containsExactlyInAnyOrder("Иван", "Яна");
    }
}