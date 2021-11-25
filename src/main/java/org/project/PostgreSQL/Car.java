package org.project.PostgreSQL;

public class Car {
    private int id;
    private String model;
    private int engineCapacity;
    private int kilometersTraversed;
    private int price;
    private int carBrandId;
    private int fuelTypeId;
    private int gearBoxTypeId;
    private int officeId;
    private int personId;

    public Car(final int id, final String model, final int engineCapacity, final int kilometersTraversed,
               final int price, final int carBrandId, final int fuelTypeId, final int gearBoxTypeId, final int officeId,
               final int personId){
        this.id = id;
        this.model = model;
        this.engineCapacity = engineCapacity;
        this.kilometersTraversed = kilometersTraversed;
        this.price = price;
        this.carBrandId = carBrandId;
        this.fuelTypeId = fuelTypeId;
        this.gearBoxTypeId = gearBoxTypeId;
        this.officeId = officeId;
        this.personId = personId;
    }
    public Car(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public int getKilometersTraversed() {
        return kilometersTraversed;
    }

    public void setKilometersTraversed(int kilometersTraversed) {
        this.kilometersTraversed = kilometersTraversed;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCarBrandId() {
        return carBrandId;
    }

    public void setCarBrandId(int carBrandId) {
        this.carBrandId = carBrandId;
    }

    public int getFuelTypeId() {
        return fuelTypeId;
    }

    public void setFuelTypeId(int fuelTypeId) {
        this.fuelTypeId = fuelTypeId;
    }

    public int getGearBoxTypeId() {
        return gearBoxTypeId;
    }

    public void setGearBoxTypeId(int gearBoxTypeId) {
        this.gearBoxTypeId = gearBoxTypeId;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
