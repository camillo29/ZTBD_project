package org.project.PostgreSQL.CRUD;

import org.project.Models.Dish;
import org.project.Models.Office;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

public class InsertManager {
    private Connection conn;
    public InsertManager(final Connection conn){
        this.conn = conn;
    }

    public void insertInBulk(int n,
                             LinkedList<Dish> dishes,
                             LinkedList<Office> offices){
        try {
            conn.setAutoCommit(false);
            PreparedStatement psDishes = conn.prepareStatement("INSERT INTO public.dishes " +
                    "(description, name, price) " +
                    "VALUES (?, ?, ?)");
            for(Dish dish: dishes){
                psDishes.setString(1, dish.getDescription());
                psDishes.setString(2, dish.getName());
                psDishes.setDouble(3, dish.getPrice());
                psDishes.addBatch();
            }
            PreparedStatement psOffices = conn.prepareStatement("INSERT INTO public.offices " +
                    "(city, phone_number, post_code, street) " +
                    "VALUES (?, ?, ?, ?)");
            for(Office office: offices){
                psOffices.setString(1, office.getCity());
                psOffices.setString(2, office.getPhone_number());
                psOffices.setString(3, office.getPost_code());
                psOffices.setString(4, office.getStreet());
                psOffices.addBatch();
            }

            psDishes.executeBatch();
            psOffices.executeBatch();

            conn.commit();
            //conn.setAutoCommit(true);
        } catch(Exception e){
            e.printStackTrace();
            try{
                conn.rollback();
            } catch(SQLException sqlE){
                sqlE.printStackTrace();
            }
        }
    }
}
