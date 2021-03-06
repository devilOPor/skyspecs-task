package com.skyspecs.task1.repository;


import com.skyspecs.task1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;


@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository{

    @Autowired
    EntityManager entityManager;

    public Page<User> findByFirstNameAndEmail(List<String> firstName, List<String> email, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        Predicate firstNamePredicate  = criteriaBuilder.and(root.get("firstName").in(firstName));
        Predicate emailPredicate =  criteriaBuilder.and(root.get("email").in(email));

        criteriaQuery.where(criteriaBuilder.and(firstNamePredicate, emailPredicate));
        String sort = pageable.getSort().toString();
        int colonIndex = sort.indexOf(':');
        String sortBy = sort.substring(0,colonIndex);
        System.out.println(sortBy);
        TypedQuery<User> query = entityManager.createQuery(criteriaQuery.orderBy(criteriaBuilder.desc(root.get(sortBy))));
        if(sort.contains("ASC")) {
            query = entityManager.createQuery(criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sortBy))));
        }
        List<User> users =  query.getResultList();
        if(users!=null ) {
            final Page<User> usersOnPage = new PageImpl<>(users, pageable, (long)users.size());
            return usersOnPage;
        }
        else
        {
            System.out.println("users is null");
        }
        return null;
    }

    public Page<User> findByFirstNameFilters(List<String> firstNamefilter, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.where(root.get("firstName").in(firstNamefilter));

        String sort = pageable.getSort().toString();
        int colonIndex = sort.indexOf(':');
        String sortBy = sort.substring(0,colonIndex);
        System.out.println(sortBy);
        TypedQuery<User> query = entityManager.createQuery(criteriaQuery.orderBy(criteriaBuilder.desc(root.get(sortBy))));
        if(sort.contains("ASC")) {
            query = entityManager.createQuery(criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sortBy))));
        }
        List<User> users =  query.getResultList();
        if(users!=null ) {
            final Page<User> usersOnPage = new PageImpl<>(users, pageable, (long)users.size());
            return usersOnPage;
        }
        else
        {
            System.out.println("users is null");
        }
        return null;
    }

    public Page<User> findByEmailFilters(List<String> emailFilters, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.where(root.get("email").in(emailFilters));
        String sort = pageable.getSort().toString();
        int colonIndex = sort.indexOf(':');
        String sortBy = sort.substring(0,colonIndex);
        System.out.println(sortBy);
        TypedQuery<User> query = entityManager.createQuery(criteriaQuery.orderBy(criteriaBuilder.desc(root.get(sortBy))));
        if(sort.contains("ASC")) {
            query = entityManager.createQuery(criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sortBy))));
        }
        List<User> users =  query.getResultList();
        if(users!=null ) {
            final Page<User> usersOnPage = new PageImpl<>(users, pageable, (long)users.size());
            return usersOnPage;
        }
        else
        {
            System.out.println("users is null");
        }
        return null;
    }
}
