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
            ResultSet rs = st.executeQuery("Select * from public.\"Cars\" c," +
                    " public.\"CarBrands\" cb," +
                    " public.\"GearBoxTypes\" gbt," +
                    " public.\"FuelTypes\" ft," +
                    " public.\"Offices\" o," +
                    " public.\"People\" p " +
                    " WHERE c.\"carBrandId\" = cb.\"id\"" +
                    " AND c.\"gearBoxTypeId\" = gbt.\"id\"" +
                    " AND c.\"fuelTypeId\" = ft.\"id\"" +
                    " AND c.\"officeId\" = o.\"id\"" +
                    " AND c.\"personId\" = p.\"id\"");
            //ResultSetMetaData rsmd = rs.getMetaData();
            //System.out.println(converter.getJson(rs).toString());
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void selectCarsInBulk(final int n){
        try {
            Statement st = conn.createStatement();
            st.executeQuery("Select * from public.\"Cars\" LIMIT " + n);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
