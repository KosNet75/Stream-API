import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

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
    }

    long countPerson = persons.stream()
        .filter(age -> age.getAge() > 18)
        .count();
       System.out.println("\nИз первоначального списка удалено [" + countPerson + "] лиц до 18 лет\n");


    System.out.println("[первые 10 фамилий] Cписок призывников (т.е. мужчин от 18 и до 27 лет).");
        List<String> selectionAge = persons.stream()
        .filter(age -> (18 < age.getAge()) && (age.getAge() < 27) && age.getSex().equals(Sex.MAN))
        .map(Person::getFamily)
        .limit(10).toList();
         selectionAge.forEach(System.out::println);

    System.out.println("\n[первые 10 фамилий] Список отсортированный по фамилии список потенциально работоспособных людей с высшим образованием."
        + "\n (от 18 до 60 лет для женщин и до 65 лет для мужчин). ");
    List<Person> workablePeopleAllGender = persons.stream()
        .filter(x -> x.getEducation().equals(Education.HIGHER))
        .filter(age -> ((18 < age.getAge()) && age.getAge() < 60) && age.getSex().equals(Sex.WOMAN) ||
         (18 < age.getAge()) && (age.getAge() < 65) )
        .sorted(Comparator.comparing(Person::getFamily)).limit(10).toList();
          selectionAge.forEach(System.out::println);

  }

}
