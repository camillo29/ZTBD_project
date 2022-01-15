package org.project.PostgreSQL.CRUD;

import org.project.Models.Dish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            ResultSet rs = conn.createStatement().executeQuery("SELECT id from public.dishes LIMIT "+n);
            PreparedStatement ps = conn.prepareStatement("UPDATE public.dishes SET" +
                    " description = ?, name = ?, price = ?" +
                    " WHERE ctid in (SELECT ctid from public.dishes LIMIT ?)" +
                    " AND public.dishes.id = ?");
            for(int i = 0 ;i<n && rs.next(); i++){
                ps.setString(1, dishes.get(i).getDescription());
                ps.setString(2, dishes.get(i).getName());
                ps.setDouble(3, dishes.get(i).getPrice());
                ps.setInt(4, n);
                ps.setInt(5, rs.getInt(1));
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();
        } catch(Exception e){
            e.printStackTrace();
            try{
                conn.rollback();
            } catch(SQLException sqlE){
                e.printStackTrace();
            }
        }
    }
}
