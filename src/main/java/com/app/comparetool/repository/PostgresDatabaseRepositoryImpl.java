package com.app.comparetool.repository;

import com.app.comparetool.dto.DatabaseDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostgresDatabaseRepositoryImpl extends DatabaseRepositoryDummyImpl{

    @Autowired
    @Qualifier("postgresJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<String> getAllDatabaseNames() {

        List<String> listOfDatabases = new ArrayList<>();
        String query = "SELECT datname FROM pg_database WHERE datname NOT IN ('postgres', 'template0', 'template1')";
        listOfDatabases = jdbcTemplate.queryForList(query,String.class).stream().toList();

        return listOfDatabases;

    }

    @Override
    public List<String> getAllTableNames(String databaseName) {
        List<String> listOfTables = new ArrayList<>();
        String query = "SELECT datname FROM pg_database WHERE datname NOT IN ('postgres', 'template0', 'template1')";
        listOfTables = jdbcTemplate.queryForList(query,String.class).stream().toList();

        return listOfTables;
    }
}
