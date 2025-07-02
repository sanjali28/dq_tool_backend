package com.app.comparetool.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class OracleDatabaseRepositoryImpl implements DatabaseRepository{

    @Autowired
    @Qualifier("oracleJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<String> getAllDatabaseNames() {

        List<String> listOfDatabases = new ArrayList<>();
        String query = "SELECT name FROM v$database";
        listOfDatabases = jdbcTemplate.queryForList(query,String.class).stream().toList();
        return listOfDatabases;

    }

    @Override
    public List<String> getAllTableNames() {

        List<String> listOfTables = new ArrayList<>();
        String query = "SELECT TABLE_NAME FROM USER_TABLES";
        listOfTables = jdbcTemplate.queryForList(query,String.class).stream().toList();
        return listOfTables;

    }
}
