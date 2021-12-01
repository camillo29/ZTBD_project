package org.project.Mongo.CRUD;

import com.mongodb.client.MongoCollection;
import org.project.Mongo.MongoManager;

public class FindManager {
    private MongoCollection oneCollection;

    public FindManager(MongoCollection oneCollection){
        this.oneCollection = oneCollection;
    }

    public void oneCollectionFindInBulk(final int n){
        try {
            oneCollection.find().limit(n);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
