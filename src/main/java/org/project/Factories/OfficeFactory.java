package org.project.Factories;

import org.project.Models.Office;

import java.util.LinkedList;
import java.util.Random;

public class OfficeFactory {
    private Random rn = new Random();

    public LinkedList<Office> createOfficesInBulk(int n){
        LinkedList<Office> offices = new LinkedList<>();
        for(int i = 0; i<n; i++){
            Office office = new Office();
            office.setCity("City " + rn.nextInt(n));
            office.setPhone_number(rn.nextInt(999) + "" + rn.nextInt(999) + "" + rn.nextInt(999));
            office.setStreet("Street" + rn.nextInt(n));
            office.setPost_code(rn.nextInt(99) + "-" + rn.nextInt(999));
            office.setStreet("Street " + rn.nextInt(n));
            offices.add(office);
        }
        return offices;
    }

}
