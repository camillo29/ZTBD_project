package org.project.Factories;

import org.project.Models.Dish;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Random;

public class DishFactory {
    private Random rn = new Random();
    DecimalFormat df = new DecimalFormat("##,##");
    public LinkedList<Dish> createDishesInBulk(int n){
        LinkedList<Dish> dishes = new LinkedList<>();
        for(int i = 0; i<n; i++){
            Dish dish = new Dish();
            dish.setDescription("Description " + (rn.nextInt(n) + rn.nextInt(n)));
            dish.setName("Dish " + (rn.nextInt(n) + rn.nextInt(n)));
            dish.setPrice(Double.parseDouble(df.format(10.0 + (50.0 - 10.0) * rn.nextDouble())));
            dishes.add(dish);
        }
        return dishes;
    }
}
