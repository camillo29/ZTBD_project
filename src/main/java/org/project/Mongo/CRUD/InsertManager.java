package org.project.Mongo.CRUD;

import com.mongodb.*;
import com.mongodb.client.ClientSession;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.project.Models.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class InsertManager {
    private MongoCollection oneCollection;
    private HashMap<String, MongoCollection> collections;
    private MongoClient client;
    private Random rn = new Random();

    public InsertManager(MongoCollection oneCollection, MongoClient client){
        this.oneCollection = oneCollection;
        this.client = client;
    }

    public InsertManager(HashMap collections, MongoClient client){
        this.collections = collections;
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

    public void multipleCollectionsInsertBasicData(){
        ClientSession session = client.startSession();
        try{
            session.startTransaction();
            BasicDBObject query = new BasicDBObject();
            query.put("_id", 1);
            if(collections.get("roles").find().first() == null) {
                collections.get("roles").insertOne(new Document()
                        .append("_id", 1)
                        .append("name", "admin"));
                collections.get("roles").insertOne(new Document()
                        .append("_id", 2)
                        .append("name", "user"));
            }
            if(collections.get("discount_type").find().first() == null){
                collections.get("discount_type").insertOne(new Document()
                        .append("_id", 1)
                        .append("name", "None"));
                collections.get("discount_type").insertOne(new Document()
                        .append("_id", 2)
                        .append("name", "Student"));
                collections.get("discount_type").insertOne(new Document()
                        .append("_id", 3)
                        .append("name", "Veteran"));
                collections.get("discount_type").insertOne(new Document()
                        .append("_id", 4)
                        .append("name", "Big family"));
                collections.get("discount_type").insertOne(new Document()
                        .append("_id", 5)
                        .append("name", "Disabled"));
            }
            session.commitTransaction();
        } catch(MongoException e){
            e.printStackTrace();
            session.abortTransaction();
        } finally {
            session.close();
        }
    }
    public void multipleCollectionsInsertInBulk(int n,
                             LinkedList<Dish> dishes,
                             LinkedList<Office> offices,
                             LinkedList<Person> people,
                             LinkedList<User> users,
                             LinkedList<Order> orders){
        ClientSession session = client.startSession();
        try{
            LinkedList<Document> roles = new LinkedList<>();
            LinkedList<Document> discountTypes = new LinkedList<>();
            LinkedList<Document> dishDocuments = new LinkedList<>();
            LinkedList<Document> officeDocuments = new LinkedList<>();
            LinkedList<Document> peopleDocuments = new LinkedList<>();
            LinkedList<Document> userDocuments = new LinkedList<>();
            LinkedList<Document> orderDocuments = new LinkedList<>();

            collections.get("roles").find().into(roles);
            collections.get("discount_type").find().into(discountTypes);

            for(Dish d: dishes){
                dishDocuments.add(new Document()
                        .append("name", d.getName())
                        .append("description", d.getDescription())
                        .append("price", d.getPrice()));
            }
            for(Office o: offices){
                officeDocuments.add(new Document()
                        .append("city", o.getCity())
                        .append("phone_number", o.getPhone_number())
                        .append("post_code", o.getPost_code())
                        .append("street", o.getStreet()));
            }
            for(Person p: people){
                peopleDocuments.add(new Document()
                        .append("name", p.getName())
                        .append("surname", p.getSurname())
                        .append("phone_number", p.getPhone_number()));
            }
            session.startTransaction();
            collections.get("dishes").insertMany(dishDocuments);
            collections.get("offices").insertMany(officeDocuments);
            collections.get("people").insertMany(peopleDocuments);
            for(int i = 0; i<users.size(); i++){
                userDocuments.add(new Document()
                        .append("username", users.get(i).getUserName())
                        .append("password", users.get(i).getPassword())
                        .append("person_id", peopleDocuments.get(i).get("_id"))
                        .append("role_id", roles.get(rn.nextInt(1)+1).get("_id")));
            }
            collections.get("users").insertMany(userDocuments);
            for(int i = 0; i<orders.size(); i++){
                BasicDBList dishList = new BasicDBList();
                dishList.add(new BasicDBObject("dish_id", dishDocuments.get(i).get("_id")));
                dishList.add(new BasicDBObject("dish_id", dishDocuments.get(rn.nextInt(dishDocuments.size())).get("_id")));
                orderDocuments.add(new Document()
                        .append("address", orders.get(i).getAddress())
                        .append("delivered", orders.get(i).isDelivered())
                        .append("discount_type", discountTypes.get(rn.nextInt(4)+1).get("_id"))
                        .append("client_id", userDocuments.get(i).get("_id"))
                        .append("office_id", officeDocuments.get(i).get("_id"))
                        .append("dishes", dishList));
            }
            collections.get("orders").insertMany(orderDocuments);
            session.commitTransaction();
        } catch(MongoException e){
            e.printStackTrace();
            session.abortTransaction();
        } finally {
            session.close();
        }
    }
}
