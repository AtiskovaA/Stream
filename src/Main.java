import java.util.*;
import java.util.stream.Collectors;


public class Main {


    public static void main(String[] args) {


        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (
                int i = 0;
                i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

//1.Для поиска несовершеннолетних

        long agePerson = persons.stream()
                .filter(p -> p.getAge() >= 18)
                .count();
        System.out.println(agePerson);


//2.Для получения списка призывников

        List<String> cons = persons.stream()
                .filter(p -> p.getAge() >= 18)
                .filter(p -> p.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(cons);


//3.Для получения списка потенциально работоспособных людей

        List<Person> workers;
        workers = persons.stream()
                .filter(w -> w.getAge() >= 18)
                .filter(w -> w.getAge() <= 60)
                .filter(m -> m.getAge() <= 65)
                .filter(w -> w.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(workers);

    }
}
