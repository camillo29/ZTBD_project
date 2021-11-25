package org.project.PostgreSQL.CRUD;

import org.project.PostgreSQL.Car;
import org.project.PostgreSQL.CarFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

public class InsertManager {
    private Connection conn;
    public InsertManager(final Connection conn){
        this.conn = conn;
    }

    public void insertCar(){
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO public.\"Cars\" " +
                    "(\"model\", \"engineCapacity\", \"kilometersTraversed\", \"price\", \"carBrandId\", \"fuelTypeId\", \"gearBoxTypeId\", \"officeId\", \"personId\") " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            //ps.setInt(1, 2);
            ps.setString(1, "v2");
            ps.setInt(2, 3000);
            ps.setInt(3, 20000);
            ps.setInt(4, 120000);
            ps.setInt(5, 1);
            ps.setInt(6, 2);
            ps.setInt(7, 3);
            ps.setInt(8, 2);
            ps.setInt(9, 1);
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void insertInBulk(int n, LinkedList<Car> cars){
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO public.\"Cars\" " +
                    "(\"model\", \"engineCapacity\", \"kilometersTraversed\", \"price\", \"carBrandId\", \"fuelTypeId\", \"gearBoxTypeId\", \"officeId\", \"personId\") " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            for(Car car: cars){
                ps.setString(1, car.getModel());
                ps.setInt(2, car.getEngineCapacity());
                ps.setInt(3, car.getKilometersTraversed());
                ps.setInt(4, car.getPrice());
                ps.setInt(5, car.getCarBrandId());
                ps.setInt(6, car.getFuelTypeId());
                ps.setInt(7, car.getGearBoxTypeId());
                ps.setInt(8, car.getOfficeId());
                ps.setInt(9, car.getPersonId());
                ps.addBatch();
            }
            ps.executeBatch();
            conn.setAutoCommit(true);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
