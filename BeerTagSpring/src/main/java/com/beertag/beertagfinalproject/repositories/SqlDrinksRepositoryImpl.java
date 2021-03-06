package com.beertag.beertagfinalproject.repositories;

import com.beertag.beertagfinalproject.models.Beer;
import com.beertag.beertagfinalproject.models.Drink;
import com.beertag.beertagfinalproject.repositories.base.DrinksRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SqlDrinksRepositoryImpl implements DrinksRepository {

    private static final String GET_TOP_THREE_DRINKS_BY_USER_QUERY = "FROM Drink WHERE userId=:userId ORDER BY rating DESC";
    private static final String USER_ID_PARAMETER = "userId";
    private static final String BEER_ID_PARAMETER = "beerId";
    private static final String IS_DRANK_PARAMETER = "isDrank";
    private static final String GET_DRINKS_BY_BEER_QUERY = "FROM Drink WHERE beerId=:beerId";
    private static final String GET_DRINK_BY_BEER_AND_USER_QUERY = "FROM Drink WHERE beerId=:beerId AND userId=:userId";
    private static final String GET_ALL_BEER_IDS_QUERY = "SELECT DISTINCT beerId FROM Drink";
    private static final String CHECK_IF_BEER_IS_RATED_QUERY = "FROM Drink WHERE rating IS NOT NULL AND beerId=:beerId AND userId=:userId";
    //private static final String SET_DRANK_BY_BEER_AND_USER_QUERY = "UPDATE Drink set isDrank=:isDrank WHERE beerId=:beerId AND userId=:userId";
    private final SessionFactory sessionFactory;

    @Autowired
    public SqlDrinksRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }


    @Override
    public Drink addDrink(Drink newDrink) {
        Integer id = 0;
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            id = (Integer) session.save(newDrink);
            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return getDrinkById(id);
    }

    @Override
    public List<Drink> getTopThreeRatedDrinksByUser(int userId) {
        List<Drink> drinks = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Query<Drink> query = session.createQuery(GET_TOP_THREE_DRINKS_BY_USER_QUERY, Drink.class);
            query.setParameter(USER_ID_PARAMETER, userId);
            query.setMaxResults(3);
            drinks = query.list();

            transaction.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return drinks;
    }

    @Override
    public List<Drink> getAllDrinksByBeerId(int beerId) {
        List<Drink> drinks = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Query<Drink> query = session.createQuery(GET_DRINKS_BY_BEER_QUERY, Drink.class);
            query.setParameter(BEER_ID_PARAMETER, beerId);
            drinks = query.list();

            transaction.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return drinks;
    }

    @Override
    public void deleteDrinkById(int drinkId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Drink drinkToDelete = session.get(Drink.class, drinkId);

            session.delete(drinkToDelete);

            transaction.commit();

        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    @Override
    public Drink setDrankBeer(int drinkId,Drink updatedDrink) {


        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

        Drink drinkToUpdate = session
                 .get(Drink.class,drinkId);
        drinkToUpdate.setDrank(updatedDrink.isDrank());

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return getDrinkById(drinkId);
    }

    @Override
    public Drink rateBeer(int beerId,int userId,Drink updatedDrink) {
        Drink drinkToUpdate=null;

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            drinkToUpdate = session
                    .createQuery(GET_DRINK_BY_BEER_AND_USER_QUERY, Drink.class)
                    .setParameter(BEER_ID_PARAMETER, beerId)
                    .setParameter(USER_ID_PARAMETER, userId)
                    .setMaxResults(1)
                    .uniqueResult();

            drinkToUpdate.setRating(updatedDrink.getRating());

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return drinkToUpdate;
    }

    @Override
    public Drink getDrinkById(int drinkId) {
       Drink drink = null;

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            drink = session.get(Drink.class,drinkId);

            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return drink;
    }

    @Override
    public List<Integer> getAllBeerIds() {
        List<Integer> beerIds = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();


            beerIds = session
                    .createQuery(GET_ALL_BEER_IDS_QUERY, Integer.class)
                    .list();


            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return beerIds;
    }

    @Override
    public Drink checkIfBeerIsRated(int beerId, int userId) {
        Drink drink = null;

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Query<Drink> query = session.createQuery(CHECK_IF_BEER_IS_RATED_QUERY, Drink.class);
            query.setParameter(BEER_ID_PARAMETER, beerId);
            query.setParameter(USER_ID_PARAMETER,userId);
            drink=query.setMaxResults(1).uniqueResult();

            transaction.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return drink;
    }
}
