package org.project.Mongo;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.project.Factories.*;
import org.project.Models.*;
import org.project.Mongo.CRUD.*;

import java.util.HashMap;
import java.util.LinkedList;


public class MongoManager {
    private MongoClient client;
    private MongoDatabase db;
    private MongoCollection oneCollection;
    private HashMap<String, MongoCollection> collections;

    //Factories
    private DishFactory dishFactory;
    private OfficeFactory officeFactory;
    private PersonFactory personFactory;
    private UserFactory userFactory;
    private OrderFactory orderFactory;

    //CRUD
    private FindManager findManager;
    private InsertManager insertManager;
    private UpdateManager updateManager;
    private DeleteManager deleteManager;

    public MongoManager(final String dataBaseName, final String port, final String collectionName){
        client = MongoClients.create("mongodb://localhost:" + port);
        db = client.getDatabase(dataBaseName);
        System.out.println("Mongo successfully connected to " + db.getName());
        oneCollection = db.getCollection(collectionName);

        initFactories();
        initManagers(oneCollection);
    }

    public MongoManager(final String dataBaseName, final String port){
        client = MongoClients.create("mongodb://localhost:"+port);
        db = client.getDatabase(dataBaseName);
        System.out.println("Mongo successfully connected to " + db.getName());
        collections = new HashMap<>();
        collections.put("dishes", db.getCollection("dishes"));
        collections.put("offices", db.getCollection("offices"));
        collections.put("orders", db.getCollection("orders"));
        collections.put("users", db.getCollection("users"));
        collections.put("people", db.getCollection("people"));
        collections.put("discount_type", db.getCollection("discount_type"));
        collections.put("roles", db.getCollection("roles"));

        initFactories();
        initManagers(collections);
        insertManager.multipleCollectionsInsertBasicData();
    }

    public void initFactories(){
        dishFactory = new DishFactory();
        officeFactory = new OfficeFactory();
        personFactory = new PersonFactory();
        userFactory = new UserFactory();
        orderFactory = new OrderFactory();
    }

    public void initManagers(MongoCollection collection){
        findManager = new FindManager(collection);
        insertManager = new InsertManager(collection, client);
        updateManager = new UpdateManager(collection, client);
        deleteManager = new DeleteManager(collection, client);
    }

    public void initManagers(HashMap collections){
        //findManager = new FindManager(collection);
        insertManager = new InsertManager(collections, client);
        //updateManager = new UpdateManager(collection, client);
        //deleteManager = new DeleteManager(collection, client);
    }

    public double oneCollectionInsertInBulk(int n,
                                                LinkedList<Dish> dishes,
                                                LinkedList<Office> offices,
                                                LinkedList<Person> people,
                                                LinkedList<User> users,
                                                LinkedList<Order> orders){
        double start = System.currentTimeMillis();
        insertManager.oneCollectionInsertInBulk(n, dishes, offices, people, users, orders);
        double finish = System.currentTimeMillis();
        return (finish-start)/1000;
    }

    public double oneCollectionFindInBulk(int n){
        double start = System.currentTimeMillis();
        findManager.oneCollectionFindInBulk(n);
        double finish = System.currentTimeMillis();
        return (finish-start)/1000;
    }

    public double oneCollectionUpdateInBulk(int n){
        double start = System.currentTimeMillis();
        updateManager.oneCollectionUpdateInBulk(n);
        double finish = System.currentTimeMillis();
        return (finish-start)/1000;
    }

    public double oneCollectionDeleteInBulk(int n){
        double start = System.currentTimeMillis();
        deleteManager.oneCollectionDeleteInBulk(n);
        double finish = System.currentTimeMillis();
        return (finish-start)/1000;
    }

    public double multipleCollectionsInsertInBulk(int n,
                                                  LinkedList<Dish> dishes,
                                                  LinkedList<Office> offices,
                                                  LinkedList<Person> people,
                                                  LinkedList<User> users,
                                                  LinkedList<Order> orders){
        double start = System.currentTimeMillis();
        insertManager.multipleCollectionsInsertInBulk(n, dishes, offices, people, users, orders);
        double finish = System.currentTimeMillis();
        return (finish-start)/1000;
    }

}
