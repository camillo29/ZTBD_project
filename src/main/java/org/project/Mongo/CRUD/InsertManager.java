package org.project.Mongo.CRUD;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.project.Models.*;

import java.util.LinkedList;
import java.util.Random;

public class InsertManager {
    private MongoCollection oneCollection;
    private MongoClient client;
    private Random rn = new Random();

    public InsertManager(MongoCollection oneCollection, MongoClient client){
        this.oneCollection = oneCollection;
        this.client = client;
    }

    public void oneCollectionInsertInBulk(int n,
                                          LinkedList<Dish> dishes,
                                          LinkedList<Office> offices,
                                          LinkedList<Person> people,
                                          LinkedList<User> users,
                                          LinkedList<Order> orders){
        LinkedList<Document> documents = new LinkedList<>();
        for(int i = 0; i<orders.size(); i++){
         BasicDBList dishList = new BasicDBList();
         DBObject obj = new BasicDBObject();
         obj.put("name", dishes.get(i).getName());
         obj.put("description", dishes.get(i).getDescription());
         obj.put("price", dishes.get(i).getPrice());
         dishList.add(obj);
         Document document = new Document()
                 .append("address", orders.get(i).getAddress())
                 .append("delivered", orders.get(i).isDelivered())
                 .append("discount_type", orders.get(i).getMongoDiscountType())
                 .append("office", new Document()
                         .append("city", offices.get(i).getCity())
                         .append("phone_number", offices.get(i).getPhone_number())
                         .append("post_code", offices.get(i).getPost_code())
                         .append("street", offices.get(i).getStreet()))
                 .append("client", new Document()
                         .append("username", users.get(i).getUserName())
                         .append("password", users.get(i).getPassword())
                         .append("role", rn.nextBoolean() ? "admin":"user")
                         .append("person", new Document()
                                 .append("name", people.get(i).getName())
                                 .append("phone_number", people.get(i).getPhone_number())
                                 .append("surname", people.get(i).getSurname())))
                 .append("dishes", dishList);
         documents.add(document);
        }
        ClientSession session = client.startSession();
        try{
            session.startTransaction();
            oneCollection.insertMany(documents);
            session.commitTransaction();
        } catch (MongoException e){
            e.printStackTrace();
            session.abortTransaction();
        } finally{
            session.close();
        }
    }
}
