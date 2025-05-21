package StreamAPI.Student;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        List<String> courses = new ArrayList<>();

//        getUniqCourses(courses);
//
//        getTopStudents(students);
//
//        getTopCourse(students);
    }

    private static void getTopCourse(List<Student> students) {
        students.stream()
                .flatMap(student -> student.getAllCourses().stream())
                .collect(Collectors.groupingBy(course -> course, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> entry.getKey())
                .ifPresentOrElse(course -> System.out.println(course),
                        () -> System.out.println("Такого курса нет"));
    }

    private static void getTopStudents(List<Student> students) {
        List<String> topStudents = students.stream()
                .sorted((o1, o2) -> o2.getAllCourses().size() - o1.getAllCourses().size())
                .limit(3)
                //.map((Function<Student, String>) Student::getName)
                .map(student -> student.getName())
                .collect(Collectors.toList());
        System.out.println(topStudents);
    }

    private static void getUniqCourses(List<String> courses) {
        List<String> uniqueCourse = courses.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueCourse);
    }

    public List<Course> courses(List<Student> students) {
        return students.stream()
                .flatMap(student -> student.getAllCourses().stream())
                .distinct()
                .collect(Collectors.toList());
    }

}

