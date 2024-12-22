package com.abdellah;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PersonServiceTest {

        @Test
        public void testFilterByLocation() {
                List<Person> peopleLivingIn123MapleSt = PersonService.filterByLocation("123 Maple St");
                // Liste attendue des personnes vivant à "123 Maple St"
                List<Person> expectedPeopleLivingIn123MapleSt = Arrays.asList(
                                Person.builder().firstName("Adam").lastName("White")
                                                .birthDate(LocalDate.of(1990, 1, 15))
                                                .city("123 Maple St").build(),
                                Person.builder().firstName("Noah").lastName("Green")
                                                .birthDate(LocalDate.of(1985, 3, 25))
                                                .city("123 Maple St").build());

                // Vérification avec AssertJ
                assertThat(peopleLivingIn123MapleSt).containsExactlyInAnyOrderElementsOf(expectedPeopleLivingIn123MapleSt);
        }

        @Test
        public void testRetrieveAdults() {
                List<Person> adults = PersonService.retrieveAdults();

                // Liste attendue des adultes (18 ans ou plus)
                List<Person> expectedAdults = Arrays.asList(
                                Person.builder().firstName("Adam").lastName("White")
                                                .birthDate(LocalDate.of(1990, 1, 15))
                                                .city("123 Maple St").build(),
                                Person.builder().firstName("Noah").lastName("Green")
                                                .birthDate(LocalDate.of(1985, 3, 25))
                                                .city("123 Maple St").build());

                // Vérification avec AssertJ
                assertThat(adults).containsExactlyInAnyOrderElementsOf(expectedAdults);
        }

        @Test
        public void testSortPerson() {
                List<Person> people = new ArrayList<>();
                people.add(Person.builder().firstName("John").lastName("Doe").build());
                people.add(Person.builder().firstName("Zara").lastName("Ali").build());
                people.add(Person.builder().firstName("Emma").lastName("Green").build());
                people.add(Person.builder().firstName("Liam").lastName("Green").build());

                // Tri de la liste de personnes
                Collections.sort(people);

                assertThat(people.get(0))
                                .isEqualTo(Person.builder().firstName("Zara").lastName("Ali").build());
                assertThat(people.get(1))
                                .isEqualTo(Person.builder().firstName("John").lastName("Doe").build());
                assertThat(people.get(2))
                                .isEqualTo(Person.builder().firstName("Emma").lastName("Green").build());
                assertThat(people.get(3))
                                .isEqualTo(Person.builder().firstName("Liam").lastName("Green").build());

        }

        @Test
        public void testExcludeEveWithoutIterator() {
                assertThatThrownBy(() -> PersonService.excludeEveWithoutIterator())
                                .isInstanceOf(ConcurrentModificationException.class);
        }

        @Test
        public void testExcludeEveUsingIterator() {
                Set<Person> individuals = new HashSet<>();
                individuals.add(Person.builder().firstName("Liam").lastName("Brown").build());
                individuals.add(Person.builder().firstName("Olivia").lastName("Smith").build());

                Set<Person> peopleWithoutEve = PersonService.excludeEveUsingIterator();

                assertThat(peopleWithoutEve).containsExactlyInAnyOrderElementsOf(individuals);
        }

}
