package com.abdellah;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Person implements Comparable<Person> {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String city;

    public int calculateAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    @Override
    public int compareTo(Person other) {
        int lastNameComparison = this.lastName.compareTo(other.lastName);
        if (lastNameComparison != 0) {
            return lastNameComparison;
        } else {
            return this.firstName.compareTo(other.firstName);
        }
    }
}