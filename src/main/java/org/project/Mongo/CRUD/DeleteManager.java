package org.project.Mongo.CRUD;

import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import org.bson.BSONObject;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeleteManager {
    private MongoCollection oneCollection;
    private HashMap<String, MongoCollection> collections;
    private MongoClient client;
    public DeleteManager(MongoCollection oneCollection, MongoClient client){
        this.oneCollection = oneCollection;
        this.client = client;
    }

    public DeleteManager(HashMap<String, MongoCollection> collections, MongoClient client){
        this.collections = collections;
        this.client = client;
    }

    public void oneCollectionDeleteInBulk(int n) {
        FindIterable<Document> documents = oneCollection.find().limit(n);
        ClientSession session = client.startSession();
        try{
            session.startTransaction();
            for(Document document: documents){
                BasicDBObject query = new BasicDBObject();
                query.put("_id", document.get("_id"));
                oneCollection.deleteOne(query);
            }
            session.commitTransaction();
        } catch(MongoException e){
            e.printStackTrace();
            session.abortTransaction();
        } finally{
            session.close();
        }
    }

    public void deleteFromCollection(MongoCollection collection, FindIterable<Document> it, String field){
        for(Document document: it) {
            BasicDBObject query = new BasicDBObject();
            query.put("_id", document.get(field));
            collection.deleteOne(query);
        }
    }

    public void multipleCollectionsDeleteInBulk(int n){
        ClientSession session = client.startSession();
        FindIterable<Document> orders = collections.get("orders").find().limit(n);
        List<ObjectId> userIds = new ArrayList<>();
        for(Document doc: orders){
            userIds.add(doc.get("client_id", ObjectId.class));
        }
        FindIterable<Document> clients = collections.get("users").find(new BasicDBObject("_id", new BasicDBObject("$in", userIds)));
        try {
            session.startTransaction();
            deleteFromCollection(collections.get("people"), clients, "person_id");
            deleteFromCollection(collections.get("users"), clients, "_id");
            deleteFromCollection(collections.get("orders"), orders, "_id");
            session.commitTransaction();
        } catch(MongoException e){
            e.printStackTrace();
            session.abortTransaction();
        } finally {
            session.close();
        }
    }
}
