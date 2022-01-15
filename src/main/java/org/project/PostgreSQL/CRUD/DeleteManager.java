package org.project.PostgreSQL.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteManager {
    private Connection conn;

    public DeleteManager(final Connection conn){
        this.conn = conn;
    }

    public void deleteInBulk(final int n){
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("DELETE from public.people " +
                    "WHERE ctid IN (SELECT ctid FROM public.people LIMIT ?)");
            //PreparedStatement deleteOffices = conn.prepareStatement("Delete from public.offices " +
            //        "WHERE ctid IN (Select ctid FROM public.offices LIMIT ?)");
            ps.setInt(1, n);
            //deleteOffices.setInt(1, n);
            ps.executeUpdate();
            //deleteOffices.executeUpdate();
            conn.commit();
        } catch(Exception e){
            e.printStackTrace();
            try{
                conn.rollback();
            }catch(SQLException sqlE){
                sqlE.printStackTrace();
            }
        }
    }
}
