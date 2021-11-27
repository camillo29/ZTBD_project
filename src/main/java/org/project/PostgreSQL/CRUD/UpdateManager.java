package org.project.PostgreSQL.CRUD;


import org.project.Models.Dish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

public class UpdateManager {
    private Connection conn;

    public UpdateManager(final Connection conn){
        this.conn = conn;
    }

    public void updateInBulk(final int n, final LinkedList<Dish> dishes){
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("UPDATE public.dishes SET" +
                    " description = ?, name = ?, price = ?" +
                    " WHERE ctid in (SELECT ctid from public.dishes LIMIT ?)");
            for(Dish dish: dishes){
                ps.setString(1, dish.getDescription());
                ps.setString(2, dish.getName());
                ps.setDouble(3, dish.getPrice());
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
