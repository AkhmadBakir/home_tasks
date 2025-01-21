package StreamAPI.UniversityExample;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UniversityExample {

    public static void main(String[] args) {
        //множество студентов, успешно сдавших экзамен
        Set<String> examPassedNames = new HashSet<>();
        examPassedNames.add("Иванов Иван");
        examPassedNames.add("Попова Яна");

        List<Course> allCourses = new ArrayList<>();
        Course java = new Course("java");
        allCourses.add(java);
        Course python = new Course("python");
        allCourses.add(python);
        Course javascript = new Course("javascript");
        allCourses.add(javascript);

        //соответствие года поступления и названия группы
        Map<Integer, String> groupNames = new HashMap<>();
        groupNames.put(2020, "2020-ГР1");
        groupNames.put(2021, "2021-ГР0");

        //список с адресами email выпускников
        List<String> graduatesClub = new ArrayList<>();

        //студенты, планирующие завершить обучение
        List<Student> students = new ArrayList<>();
        students.add(new Student("Попова", "Яна", "yana@yandex.ru", 2021));
        students.add(new Student("Иванов", "Иван", "ivan_ivanov@mail.ru", 2020));
        students.add(new Student("Сергеев", "Дмитрий", "iamdmitry@gmail.com", 2021));

        List<Student> graduatedStudents = students.stream()
                .filter(student -> examPassedNames.contains(student.surname + " " + student.name))
                .peek(student -> student.setGroupName(groupNames.get(student.entranceYear)))
                .peek(student -> graduatesClub.add(student.email))
                .collect(Collectors.toList());

        students.get(0).courses.add(java);
        students.get(0).courses.add(javascript);
        students.get(1).courses.add(java);
        students.get(1).courses.add(python);
        students.get(2).courses.add(java);

        List<Student> studentsCourses = students.stream()
                .filter(student -> student.courses.contains(javascript))
                .collect(Collectors.toList());

        for (Student student : studentsCourses) {
            System.out.println(student);
        }

        for (Student student : graduatedStudents) {
            System.out.println(student);
        }

        for (String email: graduatesClub) {
            System.out.println(email);
        }

        List<Course> allStudentsInCourses = students.stream()
                .map(student -> new HashSet<>(student.courses))
                .reduce((set1, set2) -> {
                    set1.retainAll(set2);
                    return set1;
                })
                .stream()
                //.flatMap(set -> set.stream())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        System.out.println(allStudentsInCourses);

        List<Student> studentsInHalfCourses = students.stream()
                .filter(student -> student.courses.size() > allCourses.size() / 2)
                .collect(Collectors.toList());

        System.out.println(studentsInHalfCourses);
    }
}

class Course {
    String name;

    public Course(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name=" + name +
                "}";
    }

}

class Student {
    String surname;
    String name;
    String email;
    int entranceYear;
    String groupName;
    List<Course> courses;

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Student(String surname, String name, String email, int entranceYear) {
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.entranceYear = entranceYear;
        this.courses = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Student{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", entranceYear=" + entranceYear +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
