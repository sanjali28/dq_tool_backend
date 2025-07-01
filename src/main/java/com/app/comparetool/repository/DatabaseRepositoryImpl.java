package com.app.comparetool.repository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DatabaseRepositoryImpl implements DatabaseRepository{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<String> getAllDatabaseNames() {

        List<String> listOfDatabases = new ArrayList<>();
        String query = "SELECT datname FROM pg_database WHERE datname NOT IN ('postgres', 'template0', 'template1')";
        listOfDatabases = entityManager.createNativeQuery(query, String.class).getResultList();

        return listOfDatabases;

    }
}
