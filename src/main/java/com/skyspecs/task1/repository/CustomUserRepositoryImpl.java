package com.skyspecs.task1.repository;


import com.skyspecs.task1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;


@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository{

    @Autowired
    EntityManager entityManager;

    public List<User> findByFirstNameAndEmail(List<String> email, List<String> firstName) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        Predicate firstNamePredicate  = criteriaBuilder.and(root.get("firstName").in(firstName));
        Predicate emailPredicate =  criteriaBuilder.and(root.get("email").in(email));
        criteriaQuery.where(criteriaBuilder.and(firstNamePredicate, emailPredicate));
        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<User> findByFirstNameFilters(List<String> firstNamefilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.where(root.get("firstName").in(firstNamefilter));
        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<User> findByEmailFilters(List<String> emailFilters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.where(root.get("email").in(emailFilters));
        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
