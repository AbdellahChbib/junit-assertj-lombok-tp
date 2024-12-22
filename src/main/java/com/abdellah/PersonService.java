package com.abdellah;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PersonService {

    public static List<Person> filterByLocation(String location) {
        List<Person> personDatabase = Arrays.asList(
                Person.builder().firstName("Adam").lastName("White").birthDate(LocalDate.of(1990, 1, 15))
                        .city("123 Maple St").build(),
                Person.builder().firstName("Eve").lastName("Black").birthDate(LocalDate.of(2000, 8, 10))
                        .city("456 Oak St").build(),
                Person.builder().firstName("Noah").lastName("Green").birthDate(LocalDate.of(1985, 3, 25))
                        .city("123 Maple St").build()
        );

        Predicate<Person> livesInLocation = person -> person.getCity().equals(location);

        return personDatabase.stream()
                .filter(livesInLocation)
                .collect(Collectors.toList());
    }

    public static List<Person> retrieveAdults() {
        List<Person> personDatabase = Arrays.asList(
                Person.builder().firstName("Adam").lastName("White").birthDate(LocalDate.of(1990, 1, 15))
                        .city("123 Maple St").build(),
                Person.builder().firstName("Eve").lastName("Black").birthDate(LocalDate.of(2000, 8, 10))
                        .city("456 Oak St").build(),
                Person.builder().firstName("Noah").lastName("Green").birthDate(LocalDate.of(1985, 3, 25))
                        .city("123 Maple St").build()
        );

        Predicate<Person> isAdult = person -> person.calculateAge() >= 18;

        return personDatabase.stream()
                .filter(isAdult)
                .collect(Collectors.toList());
    }

    public static Set<Person> excludeEveWithoutIterator() {
        Set<Person> individuals = new HashSet<>();
        individuals.add(Person.builder().firstName("Liam").lastName("Brown").build());
        individuals.add(Person.builder().firstName("Emma").lastName("Eve").build());
        individuals.add(Person.builder().firstName("Olivia").lastName("Smith").build());

        // This will throw ConcurrentModificationException
        for (Person person : individuals) {
            if (person.getLastName().equals("Eve")) {
                individuals.remove(person);
            }
        }
        return individuals;
    }

    public static Set<Person> excludeEveUsingIterator() {
        Set<Person> individuals = new HashSet<>();
        individuals.add(Person.builder().firstName("Liam").lastName("Brown").build());
        individuals.add(Person.builder().firstName("Emma").lastName("Eve").build());
        individuals.add(Person.builder().firstName("Olivia").lastName("Smith").build());

        // Secure removal using iterator
        Iterator<Person> iterator = individuals.iterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            if (person.getLastName().equals("Eve")) {
                iterator.remove();
            }
        }
        return individuals;
    }
}
