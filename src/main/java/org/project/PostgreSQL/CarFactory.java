package org.project.PostgreSQL;

import java.util.LinkedList;
import java.util.Random;

public class CarFactory {
    public Random random = new Random();

    private int getRandomIntInRange(int min, int max){
        return random.nextInt((max - min) + 1) + min;
    }

    public Car createCar(int index){
        Car car = new Car();
        car.setModel("model "+index);
        car.setEngineCapacity(getRandomIntInRange(1, 4));
        car.setKilometersTraversed(getRandomIntInRange(10000, 40000));
        car.setPrice(getRandomIntInRange(20000, 80000));
        car.setCarBrandId(getRandomIntInRange(1, 3));
        car.setFuelTypeId(getRandomIntInRange(1, 3));
        car.setGearBoxTypeId(getRandomIntInRange(1, 3));
        car.setOfficeId(getRandomIntInRange(1, 2));
        car.setPersonId(getRandomIntInRange(1, 2));
        return car;
    }
    public LinkedList<Car> createCarsInBulk(int n){
        LinkedList<Car> cars = new LinkedList<Car>();
        for(int i = 0; i<n;i++){
            Car car = new Car();
            car.setModel("model "+i);
            car.setEngineCapacity(getRandomIntInRange(1, 4));
            car.setKilometersTraversed(getRandomIntInRange(10000, 40000));
            car.setPrice(getRandomIntInRange(20000, 80000));
            car.setCarBrandId(getRandomIntInRange(1, 3));
            car.setFuelTypeId(getRandomIntInRange(1, 3));
            car.setGearBoxTypeId(getRandomIntInRange(1, 3));
            car.setOfficeId(getRandomIntInRange(1, 2));
            car.setPersonId(getRandomIntInRange(1, 2));
            cars.add(car);
        }
        return cars;
    }
}
