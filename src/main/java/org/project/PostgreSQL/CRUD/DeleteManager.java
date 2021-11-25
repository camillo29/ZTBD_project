package org.project.PostgreSQL.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteManager {
    private Connection conn;

    public DeleteManager(final Connection conn){
        this.conn = conn;
    }

    public void deleteOneCar(final int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM public.\"Cars\"" +
                    " WHERE \"id\" = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteInBulk(final int n){
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM public.\"Cars\" " +
                    "WHERE ctid IN " +
                    "(SELECT ctid FROM public.\"Cars\" LIMIT ?)");
            ps.setInt(1, n);
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
