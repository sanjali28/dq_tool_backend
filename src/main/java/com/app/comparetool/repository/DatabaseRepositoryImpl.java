package com.app.comparetool.repository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseRepositoryImpl implements DatabaseRepository{

    @Autowired
    private EntityManager entityManager;

    public Long checkDatabaseExists(@Param("databaseName") String databaseName){
        String query = "SELECT COUNT(*) FROM pg_database WHERE datname = :databaseName";
        return (Long) entityManager.createNativeQuery(query)
                .setParameter("databaseName",databaseName)
                .getSingleResult();

    }
}
