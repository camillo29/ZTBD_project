package org.project.Mongo.CRUD;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.ClientSession;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class DeleteManager {
    private MongoCollection oneCollection;
    private MongoClient client;
    public DeleteManager(MongoCollection oneCollection, MongoClient client){
        this.oneCollection = oneCollection;
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
}
