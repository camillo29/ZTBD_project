package org.project.Mongo.CRUD;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindManager {
    private MongoCollection oneCollection;
    private HashMap<String, MongoCollection> collections;

    public FindManager(MongoCollection oneCollection){
        this.oneCollection = oneCollection;
    }
    public FindManager(HashMap<String,MongoCollection> collections){
        this.collections = collections;
    }

    public void oneCollectionFindInBulk(final int n){
        try {
            FindIterable<Document> it = oneCollection.find().limit(n);
            System.out.println(it.first().toJson());
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public Bson createLookUpFilter(String from, String append){
        return new Document("$lookup", new Document("from", from)
                .append("localField", append)
                .append("foreignField", "_id")
                .append("as", append));
    }

    public Bson createLookUpFilter(String from, String local, String append){
        return new Document("$lookup", new Document("from", from)
                .append("localField", local)
                .append("foreignField", "_id")
                .append("as", append));
    }

    public void multipleCollectionsFindInBulk(final int n){
        List<Bson> filters = new ArrayList<>();
        filters.add(new Document("$limit", n));
        filters.add(createLookUpFilter("offices", "office_id"));
        filters.add(createLookUpFilter("discount_type", "discount_type"));
        filters.add(createLookUpFilter("users", "client_id"));
        filters.add(createLookUpFilter("people", "client_id.person_id", "person_data"));
        filters.add(createLookUpFilter("roles", "client_id.role_id", "user_role"));
        filters.add(createLookUpFilter("dishes", "dishes.dish_id"));
        AggregateIterable<Document> it = collections.get("orders").aggregate(filters);
        System.out.println(it.first().toJson());
    }
}
