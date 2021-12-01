package org.project.PostgreSQL.CRUD;

import org.project.PostgreSQL.JSONConverter;

import java.sql.*;

public class SelectManager {
    Connection conn;
    JSONConverter converter = new JSONConverter();

    public SelectManager(final Connection conn){
        this.conn = conn;
    }

    public void selectAll(){
        try {
            Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM " +
                    "public.orders o," +
                    "public.order_dish o_d," +
                    "public.dishes d," +
                    "public.offices of," +
                    "public.users u," +
                    "public.people p," +
                    "public.roles r," +
                    "public.discount_type d_t " +
                    "WHERE " +
                    "o.discount_id = d_t.id AND " +
                    "o.client_id = users.id AND " +
                    "o.office_id = of.id AND " +
                    "o.id = o_d.order_id AND " +
                    "o_d.dishid = d.id AND " +
                    "u.person_id = p.id AND " +
                    "u.role_id = r.id");
            //ResultSetMetaData rsmd = rs.getMetaData();
            //System.out.println(converter.getJson(rs).toString());
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void selectAllWithLimit(final int n){
        try {
            Statement st = conn.createStatement();
            /*ResultSet rs = */st.executeQuery("SELECT * FROM " +
                    "public.orders o," +
                    "public.order_dish o_d," +
                    "public.dishes d," +
                    "public.offices of," +
                    "public.users u," +
                    "public.people p," +
                    "public.roles r," +
                    "public.discount_type d_t " +
                    "WHERE " +
                    "o.discount_id = d_t.id AND " +
                    "o.client_id = u.id AND " +
                    "o.office_id = of.id AND " +
                    "o.id = o_d.order_id AND " +
                    "o_d.dish_id = d.id AND " +
                    "u.person_id = p.id AND " +
                    "u.role_id = r.id LIMIT " + n);
            //System.out.println(converter.getJson(rs).toString());
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
