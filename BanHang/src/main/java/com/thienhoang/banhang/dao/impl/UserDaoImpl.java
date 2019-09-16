package com.thienhoang.banhang.dao.impl;

import com.thienhoang.banhang.dao.UserDao;
import com.thienhoang.banhang.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User add(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }

    @Override
    public User get(int id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public List<User> search(String fullName, int page, int max) {
        //		String jpql = "SELECT add FROM Address add "
//				+ "WHERE add.name LIKE :param";
//
//		TypedQuery<Address> query =
//				entityManager.createQuery(jpql, Address.class);
//
//		query.setParameter("param", "%" + name + "%");


        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> add = query.from(User.class);

        if (fullName != null) {
            Predicate predicate =
                    builder.like(add.get("full name"), "%" + fullName + "%");

            query.where(predicate);
        }
        query.select(add);

        TypedQuery<User> typedQuery = entityManager.createQuery(query);
//
        typedQuery.setFirstResult(page * max);//
        typedQuery.setMaxResults(max);///so ban ghi tren 1 trang
        return typedQuery.getResultList();
    }

    @Override
    public long count(String fullname) {
        //		String jpql = "SELECT count(add) FROM Address add "
//				+ "WHERE add.name LIKE :param";
//
//		TypedQuery<Long> query =
//				entityManager.createQuery(jpql, Long.class);
//		query.setParameter("param", "%" + name + "%");

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<User> add = criteriaQuery.from(User.class);

        if (fullname != null) {
            Predicate predicate =
                    builder.like(add.get("Full Name"), "%" + fullname + "%");

            criteriaQuery.where(predicate);
        }
        criteriaQuery.select(builder.count(add));

        TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public Collection<User> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM User e");
        return (Collection<User>) query.getResultList();
    }
}
