package org.project.PostgreSQL.CRUD;

import org.project.Models.*;

import java.sql.*;
import java.util.LinkedList;
import java.util.Random;

public class InsertManager {
    private Connection conn;
    private Random rn = new Random();
    public InsertManager(final Connection conn){
        this.conn = conn;
    }

    public void insertInBulk(int n,
                             LinkedList<Dish> dishes,
                             LinkedList<Office> offices,
                             LinkedList<Person> people,
                             LinkedList<User> users,
                             LinkedList<Order> orders){
        try {
            conn.setAutoCommit(false);
            PreparedStatement psDishes = conn.prepareStatement("INSERT INTO public.dishes " +
                    "(description, name, price) " +
                    "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            for(Dish dish: dishes){
                psDishes.setString(1, dish.getDescription());
                psDishes.setString(2, dish.getName());
                psDishes.setDouble(3, dish.getPrice());
                psDishes.addBatch();
            }
            PreparedStatement psOffices = conn.prepareStatement("INSERT INTO public.offices " +
                    "(city, phone_number, post_code, street) " +
                    "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            for(Office office: offices){
                psOffices.setString(1, office.getCity());
                psOffices.setString(2, office.getPhone_number());
                psOffices.setString(3, office.getPost_code());
                psOffices.setString(4, office.getStreet());
                psOffices.addBatch();
            }
            PreparedStatement psPeople = conn.prepareStatement("INSERT INTO public.people " +
                    "(name, phone_number, surname) " +
                    "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            for (Person person: people){
                psPeople.setString(1, person.getName());
                psPeople.setString(2, person.getPhone_number());
                psPeople.setString(3, person.getSurname());
                psPeople.addBatch();
            }
            psDishes.executeBatch();
            psOffices.executeBatch();
            psPeople.executeBatch();
            ResultSet peopleKeys = psPeople.getGeneratedKeys();
            ResultSet officesKeys = psOffices.getGeneratedKeys();
            ResultSet dishesKeys = psDishes.getGeneratedKeys();
            PreparedStatement psUsers = conn.prepareStatement("INSERT INTO public.users " +
                    "(password, username, person_id, role_id) " +
                    "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            int i = 0;
            while(peopleKeys.next() && i<users.size()){
                psUsers.setString(1, users.get(i).getPassword());
                psUsers.setString(2, users.get(i).getUserName());
                psUsers.setInt(3, peopleKeys.getInt(1));
                psUsers.setInt(4, users.get(i).getRoleID());
                psUsers.addBatch();
                i++;
            }
            psUsers.executeBatch();
            ResultSet usersKeys = psUsers.getGeneratedKeys();
            PreparedStatement psOrders = conn.prepareStatement("Insert INTO public.orders " +
                    "(address, delivered, discount_id, client_id, office_id) " +
                    "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            i = 0;
            while(i<orders.size() && usersKeys.next() && officesKeys.next()) {
                psOrders.setString(1, orders.get(i).getAddress());
                psOrders.setBoolean(2, orders.get(i).isDelivered());
                psOrders.setInt(3, orders.get(i).getDiscountType());
                psOrders.setInt(4, usersKeys.getInt(1));
                psOrders.setInt(5, officesKeys.getInt(1));
                psOrders.addBatch();
                i++;
            }
            psOrders.executeBatch();
            ResultSet ordersKeys = psOrders.getGeneratedKeys();
            PreparedStatement psOrders_Dishes = conn.prepareStatement("Insert INTO public.order_dish " +
                    "(amount, dish_id, order_id) " +
                    "VALUES (?, ?, ?)");
            while(ordersKeys.next() && dishesKeys.next()){
                psOrders_Dishes.setInt(1,1+rn.nextInt(3));
                psOrders_Dishes.setInt(2, dishesKeys.getInt(1));
                psOrders_Dishes.setInt(3, ordersKeys.getInt(1));
                psOrders_Dishes.addBatch();
            }
            psOrders_Dishes.executeBatch();
            conn.commit();
        } catch(Exception e){
            e.printStackTrace();
            try{
                conn.rollback();
            } catch(SQLException sqlE){
                sqlE.printStackTrace();
            }
        }
    }
}
