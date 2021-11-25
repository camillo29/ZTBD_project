package org.project.PostgreSQL.CRUD;


import org.project.PostgreSQL.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

public class UpdateManager {
    private Connection conn;

    public UpdateManager(final Connection conn){
        this.conn = conn;
    }

    public void updateOneCar(){
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE public.\"Cars\" SET \"model\" = ? WHERE \"id\" = ?");
            ps.setString(1, "v6");
            ps.setInt(2, 1);
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void updateInBulk(final int n, final LinkedList<Car> cars){
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("UPDATE public.\"Cars\" SET" +
                    " \"model\" = ?, \"engineCapacity\" = ?, \"kilometersTraversed\" = ?, \"price\" = ?, \"carBrandId\" = ?, \"fuelTypeId\" = ?, \"gearBoxTypeId\" = ?, \"officeId\" = ?, \"personId\" = ?" +
                    " WHERE ctid in (SELECT ctid from public.\"Cars\" LIMIT ?)");
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
                ps.setInt(10, n);
                ps.addBatch();
            }
            ps.executeBatch();
            //conn.commit();
            conn.setAutoCommit(true);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
