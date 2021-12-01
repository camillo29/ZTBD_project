package org.project.Factories;

import org.project.Models.Person;

import java.util.LinkedList;
import java.util.Random;

public class PersonFactory {
    private Random rn = new Random();

    public LinkedList<Person> createPeopleInBulk(int n){
        LinkedList<Person> people = new LinkedList<>();
        for(int i = 0;i<n;i++){
            Person person = new Person();
            person.setName("Name " + rn.nextInt(n));
            person.setPhone_number(rn.nextInt(999) + "" + rn.nextInt(999) + "" + rn.nextInt(999));
            person.setSurname("Surname " + rn.nextInt(n));
            people.add(person);
        }
        return people;
    }
}
