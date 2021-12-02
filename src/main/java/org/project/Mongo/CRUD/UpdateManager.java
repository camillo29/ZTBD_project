package org.project.Mongo.CRUD;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.client.ClientSession;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Projections;
import org.bson.BSON;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.LinkedList;

public class UpdateManager {
    private MongoCollection oneCollection;
    private MongoClient client;

    public UpdateManager(MongoCollection oneCollection, MongoClient client){
        this.oneCollection = oneCollection;
        this.client = client;
    }

    public void oneCollectionUpdateInBulk(int n){
        FindIterable<Document> documents = oneCollection.find().limit(n);

        ClientSession session = client.startSession();
        try{
            session.startTransaction();
            for(Document document: documents){
                BasicDBObject query = new BasicDBObject();
                query.put("_id", document.get("_id"));
                BasicDBObject newDocument = new BasicDBObject();
                newDocument.put("delivered", true);
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
}
