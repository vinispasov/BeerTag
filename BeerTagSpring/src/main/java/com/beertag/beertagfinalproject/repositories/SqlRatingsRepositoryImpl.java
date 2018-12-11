package com.beertag.beertagfinalproject.repositories;

import com.beertag.beertagfinalproject.models.Rating;
import com.beertag.beertagfinalproject.models.dto_models.RatingDTO;
import com.beertag.beertagfinalproject.repositories.base.RatingsRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SqlRatingsRepositoryImpl implements RatingsRepository {
    private static final String GET_RATINGS_BY_BEER_ID_QUERY = "FROM Rating WHERE votedForId = :votedForId";
    private static final String USER_ID_VOTER_PARAMETER = "voterId";
    private static final String BEER_ID_VOTED_FOR_PARAMETER = "votedForId";
    private static final String IS_BEER_ALREADY_RATED_BY_USER_ID_QUERY = "FROM Rating WHERE voterId = :voterId AND votedForId = :votedForId";
    private static final String GET_RATINGS_BY_USER_ID_QUERY = "FROM Rating WHERE voterId = :voterId";


    private final SessionFactory sessionFactory;

    @Autowired
    public SqlRatingsRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Rating addRating(Rating newRating) {

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            session.save(newRating);
            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return newRating;
    }

    @Override
    public List<Rating> getRatingsByBeerId(int id) {
        List<Rating> ratings = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            ratings = session
                    .createQuery(GET_RATINGS_BY_BEER_ID_QUERY, Rating.class)
                    .setParameter(BEER_ID_VOTED_FOR_PARAMETER, id)
                    .list();

            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ratings;
    }
    @Override
    public List<Rating> getRatingsByUserId(int id) {
        List<Rating> ratings = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            ratings = session
                    .createQuery(GET_RATINGS_BY_USER_ID_QUERY, Rating.class)
                    .setParameter(USER_ID_VOTER_PARAMETER, id)
                    .list();

            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ratings;
    }

    @Override
    public Rating isAlreadyRated(RatingDTO ratingDTO) {
        Rating result= null;


        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            result = session
                    .createQuery(IS_BEER_ALREADY_RATED_BY_USER_ID_QUERY, Rating.class)
                    .setParameter(USER_ID_VOTER_PARAMETER, ratingDTO.getVoterId())
                    .setParameter(BEER_ID_VOTED_FOR_PARAMETER, ratingDTO.getVotedForId())
                    .uniqueResult();

            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return result;
    }
}
