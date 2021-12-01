package org.project.Factories;

import org.project.Models.User;

import java.util.LinkedList;
import java.util.Random;

public class UserFactory {
    Random rn = new Random();

    public LinkedList<User> createUsersInBulk(int n){
        LinkedList<User> users = new LinkedList<>();
        for(int i = 0; i<n; i++){
            User user = new User();
            user.setUserName("login "+rn.nextInt(n));
            user.setPassword("$2b$08$g9V4kCf6bL.Pg5GWlO0V3eNMipRXq4fum1Doko.V0x4fZSJJILPGK");
            user.setRoleID(1+rn.nextInt(2));
            users.add(user);
        }
        return users;
    }
}
