package org.project.Mongo;
import com.mongodb.MongoCommandException;
import com.mongodb.MongoSecurityException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.project.Exceptions.ErrorException;
import org.project.Factories.*;
import org.project.Models.*;
import org.project.Mongo.CRUD.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    public MongoManager(final String dataBaseName, final String port, final String collectionName, final String user, final String password)
            throws ErrorException{
        try {
            client = MongoClients.create("mongodb://"+user+":" + URLEncoder.encode(password, "UTF-8") + "@localhost:" + port);
            db = client.getDatabase(dataBaseName);
            oneCollection = db.getCollection(collectionName);
            initFactories();
            initManagers(oneCollection);
        } catch(UnsupportedEncodingException| MongoCommandException | MongoSecurityException e) {
            throw new ErrorException("One collection connection error!");
        }

        System.out.println("Mongo successfully connected to " + db.getName());
    }

    public MongoManager(final String dataBaseName, final String port, final String user, final String password) throws ErrorException {
        try {
            client = MongoClients.create("mongodb://"+user+":" + URLEncoder.encode(password, "UTF-8") + "@localhost:" + port);
            db = client.getDatabase(dataBaseName);
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
        } catch(UnsupportedEncodingException | MongoCommandException | MongoSecurityException e) {
            throw new ErrorException("One collection connection error!");
        }
        System.out.println("Mongo successfully connected to " + db.getName());
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

    public void initManagers(HashMap<String, MongoCollection> collections){
        findManager = new FindManager(collections);
        insertManager = new InsertManager(collections, client);
        updateManager = new UpdateManager(collections, client);
        deleteManager = new DeleteManager(collections, client);
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

    public double oneCollectionUpdateInBulk(int n, LinkedList<Dish> dishes){
        double start = System.currentTimeMillis();
        updateManager.oneCollectionUpdateInBulk(n, dishes);
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

    public double multipleCollectionsFindInBulk(int n){
        double start = System.currentTimeMillis();
        findManager.multipleCollectionsFindInBulk(n);
        double finish = System.currentTimeMillis();
        return (finish-start)/1000;
    }

    public double multipleCollectionsUpdateInBulk(int n, LinkedList<Dish> dishes){
        double start = System.currentTimeMillis();
        updateManager.multipleCollectionsUpdateInBulk(n, dishes);
        double finish = System.currentTimeMillis();
        return (finish-start)/1000;
    }

    public double multipleCollectionsDeleteInBulk(int n){
        double start = System.currentTimeMillis();
        deleteManager.multipleCollectionsDeleteInBulk(n);
        double finish = System.currentTimeMillis();
        return (finish-start)/1000;
    }

    public DishFactory getDishFactory() {
        return dishFactory;
    }

    public MongoCollection getOneCollection() {
        return oneCollection;
    }

    public HashMap<String, MongoCollection> getCollections() {
        return collections;
    }
}
