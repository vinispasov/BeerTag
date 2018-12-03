package com.beertag.beertagfinalproject.repositories;

import com.beertag.beertagfinalproject.models.Beer;
import com.beertag.beertagfinalproject.models.BeerTag;
import com.beertag.beertagfinalproject.models.dto_models.BeerTagDTO;
import com.beertag.beertagfinalproject.repositories.base.BeersTagsRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class SqlBeersTagsRepositoryImpl implements BeersTagsRepository {
    private static final String GET_BEERTAG_BY_BEER_AND_TAG_QUERY ="FROM BeerTag WHERE beerId = :beerId AND tagId = :tagId";
    private static final String BEER_PARAMETER = "beerId";
    private static final String TAG_PARAMETER = "tagId";
    private static final String GET_BEERTAG_BY_BEER_QUERY = "FROM BeerTag WHERE beerId = :beerId";
    private static final String GET_BEERTAG_BY_TAG_QUERY = "FROM BeerTag WHERE tagId = :tagId";
    private final SessionFactory sessionFactory;

    @Autowired
   public SqlBeersTagsRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }


    @Override
    public BeerTag createBeerTag(BeerTag newBeerTag) {
        Integer id = 0;
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            id = (Integer) session.save(newBeerTag);
            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return getBeerTagById(id);
    }

    @Override
    public BeerTag getBeerTagByBeerAndTag(BeerTagDTO beerTagDTO) {
        BeerTag beerTag = null;

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            beerTag = session
                    .createQuery(GET_BEERTAG_BY_BEER_AND_TAG_QUERY, BeerTag.class)
                    .setParameter(BEER_PARAMETER, beerTagDTO.getBeerId())
                    .setParameter(TAG_PARAMETER, beerTagDTO.getTagId())
                    .uniqueResult();

            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return beerTag;
    }

    @Override
    public BeerTag getBeerTagById(int beerTagId) {
        BeerTag beerTag = null;

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            beerTag = session.get(BeerTag.class,beerTagId);

            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return beerTag;
    }

    @Override
    public boolean isBeerTagCreated(BeerTagDTO beerTagDTO) {
        BeerTag beerTag = null;

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            beerTag = session
                    .createQuery(GET_BEERTAG_BY_BEER_AND_TAG_QUERY, BeerTag.class)
                    .setParameter(BEER_PARAMETER, beerTagDTO.getBeerId())
                    .setParameter(TAG_PARAMETER, beerTagDTO.getTagId())
                    .uniqueResult();

            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        if (Objects.equals(beerTag, null)) {
            return false;
        }
        return true;
    }

    @Override
    public List<BeerTag> getAllBeersTagsByBeer(int beerId) {
        List<BeerTag> beersTags = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Query<BeerTag> query = session.createQuery(GET_BEERTAG_BY_BEER_QUERY, BeerTag.class);
            query.setParameter(BEER_PARAMETER, beerId);
            beersTags = query.list();

            transaction.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return beersTags;
    }

    @Override
    public List<BeerTag> getAllBeersTagsByTag(int tagId) {
        List<BeerTag> beersTags = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Query<BeerTag> query = session.createQuery(GET_BEERTAG_BY_TAG_QUERY,BeerTag.class);
            query.setParameter(TAG_PARAMETER,tagId);
            beersTags = query.list();

            transaction.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return beersTags;
    }
}
