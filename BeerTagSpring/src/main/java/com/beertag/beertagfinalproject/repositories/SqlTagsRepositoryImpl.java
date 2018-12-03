package com.beertag.beertagfinalproject.repositories;

import com.beertag.beertagfinalproject.models.Tag;
import com.beertag.beertagfinalproject.repositories.base.TagsRepository;
import com.beertag.beertagfinalproject.utils.mappers.BeersMapperImpl;
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

@Repository
public class SqlTagsRepositoryImpl implements TagsRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public SqlTagsRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Override
    public Tag getTagById(int id) {
        Tag tag = null;
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            tag = session.get(Tag.class, id);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tag;
    }

    @Override
    public List<Tag> getAllTags() {
        List<Tag> allTags = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tag> criteriaQuery = criteriaBuilder.createQuery(Tag.class);
            criteriaQuery.from(Tag.class);

            allTags= session
                    .createQuery(criteriaQuery)
                    .getResultList();


            transaction.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }

        return allTags;
    }

    @Override
    public Tag addNewTag(Tag newTag) {
        Integer id = 0;
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            id = (Integer) session.save(newTag);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getTagById(id);
    }

    @Override
    public void deleteTag(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Tag tagToDelete = session.get(Tag.class, id);

            session.delete(tagToDelete);

            transaction.commit();

        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
}
