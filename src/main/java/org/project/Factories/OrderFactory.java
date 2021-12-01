package org.project.Factories;

import org.project.Models.Order;

import java.util.LinkedList;
import java.util.Random;

public class OrderFactory {
    private Random rn = new Random();
    String[] discountTypes = {"None", "Student", "Veteran", "Big family", "Disabled"};
    public LinkedList<Order> createOrdersInBulk(int n){
        LinkedList<Order> orders = new LinkedList<>();
        for(int i = 0; i<n; i++){
            Order order = new Order();
            order.setAddress("Address " + rn.nextInt(n));
            order.setDelivered(rn.nextBoolean());
            order.setDiscountType(1+rn.nextInt(4));
            order.setMongoDiscountType(discountTypes[rn.nextInt(4)]);
            orders.add(order);
        }
        return orders;
    }
}
