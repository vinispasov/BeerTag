package com.beertag.beertagfinalproject.repositories;

import com.beertag.beertagfinalproject.models.User;
import com.beertag.beertagfinalproject.repositories.base.UsersRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SqlUsersRepositoryImpl implements UsersRepository {

    private static final String GET_USERS_ID_QUERY = "FROM User";
    private final SessionFactory sessionFactory;

    @Autowired
    public SqlUsersRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User createUser(User userToCreate) {
        Integer id = 0;
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            id = (Integer) session.save(userToCreate);
            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return getUserById(id);
    }

    @Override
    public User getUserById(int userId) {
        User user = null;

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            user = session.get(User.class, userId);

            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public User updateUser(User userToUpdate, int userId) {
        User user = null;

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            user = session.get(User.class, userId);

            user.setUserPicture(userToUpdate.getUserPicture());

            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            users = session
                    .createQuery(GET_USERS_ID_QUERY, User.class)
                    .list();

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}
