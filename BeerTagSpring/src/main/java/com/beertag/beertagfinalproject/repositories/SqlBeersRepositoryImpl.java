package com.beertag.beertagfinalproject.repositories;

import com.beertag.beertagfinalproject.models.Beer;
import com.beertag.beertagfinalproject.models.dto_models.BeerDTO;
import com.beertag.beertagfinalproject.repositories.base.BeersRepository;
import com.beertag.beertagfinalproject.utils.mappers.BeersMapperImpl;
import com.beertag.beertagfinalproject.utils.mappers.base.BeersMapper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SqlBeersRepositoryImpl implements BeersRepository {
    private static final String GET_BEERS_SORTED_BY_RATING_QUERY = "";
    private static final String GET_BEERS_SORTED_BY_ABV_QUERY = "FROM Beer ORDER BY beerAbv DESC";
    private static final String GET_BEERS_SORTED_BY_NAME_QUERY = "FROM Beer ORDER BY beerName";
    private static final String GET_BEERS_FILTERED_BY_TAG_QUERY = "";
    private static final String GET_BEERS_FILTERED_BY_STYLE_QUERY = "FROM Beer WHERE beerStyle = :style";
    private static final String TAG_PARAMETER ="tag" ;
    private static final String STYLE_PARAMETER = "style";
    private static final String COUNTRY_PARAMETER = "country";
    private static final String GET_BEERS_FILTERED_BY_COUNTRY_QUERY = "FROM Beer WHERE originCountry = :country";
    private final SessionFactory sessionFactory;
    private BeersMapper mapper;

    @Autowired
    public SqlBeersRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
        this.mapper=new BeersMapperImpl();
    }

    @Override
    public Beer getBeerById(int id) {
        Beer beer = null;
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            beer = session.get(Beer.class, id);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return beer;
    }

    @Override
    public List<BeerDTO> getAllBeers() {
        List<Beer> allBeers = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Beer> criteriaQuery = criteriaBuilder.createQuery(Beer.class);
            criteriaQuery.from(Beer.class);

            allBeers = session
                    .createQuery(criteriaQuery)
                    .getResultList();


            transaction.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }

        List<BeerDTO> result=allBeers.stream().map(beer ->mapper.mapBeerToDTO(beer)).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Beer> getAllBeersSortedByRating() {

        List<Beer> beersSortedByRating = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();


            beersSortedByRating = session
                    .createQuery(GET_BEERS_SORTED_BY_RATING_QUERY, Beer.class)
                    .list();


            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return beersSortedByRating;
    }

    @Override
    public List<Beer> getAllBeersSortedByAbv() {
        List<Beer> beersSortedByAbv = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();


            beersSortedByAbv = session
                    .createQuery(GET_BEERS_SORTED_BY_ABV_QUERY, Beer.class)
                    .list();


            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return beersSortedByAbv;
    }

    @Override
    public List<Beer> getAllBeersSortedByName() {
        List<Beer> beersSortedByName = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();


            beersSortedByName = session
                    .createQuery(GET_BEERS_SORTED_BY_NAME_QUERY, Beer.class)
                    .list();


            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return beersSortedByName;
    }

    @Override
    public List<Beer> getBeersByTag(String tag) {
        List<Beer> beersByTag = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();


            beersByTag = session
                    .createQuery(GET_BEERS_FILTERED_BY_TAG_QUERY, Beer.class)
                    .setParameter(TAG_PARAMETER,tag)
                    .list();


            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return beersByTag;
    }

    @Override
    public List<Beer> getBeersByStyle(String style) {
        List<Beer> beersByStyle = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();


            beersByStyle = session
                    .createQuery(GET_BEERS_FILTERED_BY_STYLE_QUERY, Beer.class)
                    .setParameter(STYLE_PARAMETER,style)
                    .list();


            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return beersByStyle;
    }

    @Override
    public List<Beer> getBeersByCountry(String country) {
        List<Beer> beersByCountry = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();


            beersByCountry = session
                    .createQuery(GET_BEERS_FILTERED_BY_COUNTRY_QUERY, Beer.class)
                    .setParameter(COUNTRY_PARAMETER,country)
                    .list();


            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return beersByCountry;
    }

    @Override
    public Beer addNewBeer(Beer newBeer) {
        Integer id = 0;
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            id = (Integer) session.save(newBeer);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getBeerById(id);
    }

    @Override
    public Beer editBeer(Beer beerToEdit, int id) {
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            Beer beer = session.get(Beer.class, id);
            beer.setBeerName(beerToEdit.getBeerName());
            beer.setBeerAbv(beerToEdit.getBeerAbv());
            beer.setBeerStyle(beerToEdit.getBeerStyle());
            beer.setBeerDescription(beerToEdit.getBeerDescription());
            beer.setBeerPicture(beerToEdit.getBeerPicture());
            beer.setBrewery(beerToEdit.getBrewery());
            beer.setOriginCountry(beerToEdit.getOriginCountry());
            beer.setDrank(beerToEdit.isDrank());

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return getBeerById(id);
    }

    @Override
    public void deleteBeer(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Beer beerToDelete = session.get(Beer.class, id);

            session.delete(beerToDelete);

            transaction.commit();

        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
}
