package StreamAPI;

import java.util.Arrays;
import java.util.List;


class Person {
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Andrew", 20),
                new Person("Igor", 23),
                new Person("Ira", 23),
                new Person("Victor", 29)
        );
        persons.parallelStream()
                .reduce(0,
                        (sum, p) -> {
                            System.out.format("accum: sum=%s; person=%s [% s]\n",
                            sum, p, Thread.currentThread().getName());
        return sum += p.age;
                        },
                        (sum1, sum2) -> {
                            System.out.format("combiner: sum1=%s; sum2=%s [% s]\n",
                                    sum1, sum2, Thread.currentThread().getName());
                            return sum1 + sum2;
                        });
//                .parallelStream()
//                .reduce(0,
//                        (sum, p) -> {
//                            System.out.format("accum: sum=%s; person=%s [% s]\n",
//                                    sum, p, Thread.currentThread().getName());
//                            return sum += p.age;
//                        },
//                        (sum1, sum2) -> {
//                            System.out.format("combiner: sum1=%s; sum2=%s [% s]\n",
//                                    sum1, sum2, Thread.currentThread().getName());
//                            return sum1 + sum2;
//                        });
    }
}

