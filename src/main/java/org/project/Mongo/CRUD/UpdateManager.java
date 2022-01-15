package org.project.Mongo.CRUD;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.ClientSession;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.project.Models.Dish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class UpdateManager {
    private Random rn = new Random();
    private MongoCollection oneCollection;
    private HashMap<String,MongoCollection> collections;
    private MongoClient client;

    public UpdateManager(MongoCollection oneCollection, MongoClient client){
        this.oneCollection = oneCollection;
        this.client = client;
    }
   public UpdateManager(HashMap<String, MongoCollection> collections, MongoClient client){
        this.collections = collections;
        this.client = client;
   }

    public void oneCollectionUpdateInBulk(int n, LinkedList<Dish> dishes){
        FindIterable<Document> documents = oneCollection.find().limit(n);

        ClientSession session = client.startSession();
        try{
            session.startTransaction();
            for(Document document: documents){
                BasicDBObject query = new BasicDBObject();
                query.put("_id", document.get("_id"));
                BasicDBObject newDocument = new BasicDBObject();
                newDocument.put("orders.0.dishes.0.name", dishes.get(rn.nextInt(dishes.size())).getName());
                newDocument.put("orders.0.dishes.0.description", dishes.get(rn.nextInt(dishes.size())).getDescription());
                newDocument.put("orders.0.dishes.0.price", dishes.get(rn.nextInt(dishes.size())).getPrice());
                BasicDBObject updateObject = new BasicDBObject();
                updateObject.put("$set", newDocument);
                oneCollection.updateOne(query, updateObject);
            }
            session.commitTransaction();
        } catch(MongoException e){
            session.abortTransaction();
            e.printStackTrace();
        } finally{
            session.close();
        }
    }
    public void multipleCollectionsUpdateInBulk(int n, LinkedList<Dish> dishes){
        FindIterable<Document> documents = collections.get("dishes").find().limit(n);
        ClientSession session = client.startSession();
        try {
            session.startTransaction();
            for(Document document: documents){
                BasicDBObject query = new BasicDBObject();
                query.put("_id", document.get("_id"));
                BasicDBObject newDocument = new BasicDBObject();
                newDocument.put("name", dishes.get(rn.nextInt(dishes.size())).getName());
                newDocument.put("description", dishes.get(rn.nextInt(dishes.size())).getDescription());
                newDocument.put("price", dishes.get(rn.nextInt(dishes.size())).getPrice());
                BasicDBObject updateObject = new BasicDBObject();
                updateObject.put("$set", newDocument);
                collections.get("dishes").updateOne(query, updateObject);
            }
            session.commitTransaction();
        } catch(MongoException e){
            e.printStackTrace();
            session.abortTransaction();
        } finally {
            session.close();
        }

    }
}
