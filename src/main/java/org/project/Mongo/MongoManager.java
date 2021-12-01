package org.project.Mongo;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.project.Factories.*;
import org.project.Models.*;
import org.project.Mongo.CRUD.*;

import java.util.LinkedList;


public class MongoManager {
    private MongoClient client;
    private MongoDatabase db;
    private MongoCollection oneCollection;

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

        dishFactory = new DishFactory();
        officeFactory = new OfficeFactory();
        personFactory = new PersonFactory();
        userFactory = new UserFactory();
        orderFactory = new OrderFactory();

        findManager = new FindManager(oneCollection);
        insertManager = new InsertManager(oneCollection, client);
        updateManager = new UpdateManager(oneCollection, client);
        deleteManager = new DeleteManager(oneCollection, client);
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

}
