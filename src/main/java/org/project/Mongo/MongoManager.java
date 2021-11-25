package org.project.Mongo;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoManager {
    private MongoClient client;
    private MongoDatabase db;

    public MongoManager(final String dataBaseName, final String port){
        client = MongoClients.create("mongodb://localhost:" + port);
        db = client.getDatabase(dataBaseName);
        System.out.println("Mongo successfully connected to " + db.getName());
        //MongoCollection collection = db.getCollection("Komis");
    }
}
