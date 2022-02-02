package org.project.Mongo;

import com.mongodb.client.MongoCollection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class RowCountFetcher {
    private MongoCollection oneCollection;
    private HashMap<String, MongoCollection> collections;
    private Connection conn;

    public RowCountFetcher(MongoManager oneCollectionManager, MongoManager multipleCollectionsManager, Connection conn){
        oneCollection = oneCollectionManager.getOneCollection();
        collections = multipleCollectionsManager.getCollections();
        this.conn = conn;
    }

    public HashMap<String, Long> getCounts(){
        HashMap<String, Long> counts = new HashMap<>();
        counts.put("oneCollection", oneCollection.countDocuments());
        counts.put("discount_type", collections.get("discount_type").countDocuments());
        counts.put("dishes", collections.get("dishes").countDocuments());
        counts.put("offices", collections.get("offices").countDocuments());
        counts.put("orders", collections.get("orders").countDocuments());
        counts.put("people", collections.get("people").countDocuments());
        counts.put("roles", collections.get("roles").countDocuments());
        counts.put("users", collections.get("users").countDocuments());
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) as count FROM public.order_dish");
            rs.next();
            counts.put("order_dish", rs.getLong("count"));
        } catch(SQLException e){
            e.printStackTrace();
        }
        return counts;
    }
}