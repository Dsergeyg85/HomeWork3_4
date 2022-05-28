import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
            Stream<Person> stream1 = persons.stream();
            long youngPersonCount  = stream1.filter(person -> person.getAge() < 18)
                    .count();
            Stream<Person> stream2 = persons.stream();
            List<String> сonscriptsCount = stream2.filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                    .map(Person::getFamily)
                    .collect(Collectors.toList());
            Stream<Person> stream3 = persons.stream();
            List<Person> peopleHigherEducation = stream3.filter(person -> ( person.getAge() >= 18 && person.getAge() <= 65
                                                                                                && person.getEducation() == Education.HIGHER
                                                                                                && person.getSex() == Sex.MAN) ||
                                                                            person.getAge() >= 18 && person.getAge() <= 60
                                                                                                && person.getEducation() == Education.HIGHER
                                                                                                && person.getSex() == Sex.WOMAN)
                    .sorted(Comparator.comparing(Person::getFamily))
                    .collect(Collectors.toList());
        }
    }
}
