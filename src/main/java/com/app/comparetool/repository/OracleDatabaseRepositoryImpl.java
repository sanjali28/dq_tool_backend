package com.app.comparetool.repository;

import com.app.comparetool.dto.DatabaseDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OracleDatabaseRepositoryImpl extends DatabaseRepositoryDummyImpl{

    private static final Logger log = LogManager.getLogger(OracleDatabaseRepositoryImpl.class);
    @Autowired
    @Qualifier("oracleJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<String> getAllDatabaseNames() {

        List<String> listOfDatabases = new ArrayList<>();
        String query = "SELECT name FROM v$database";
        listOfDatabases = jdbcTemplate.queryForList(query,String.class).stream().toList();
        if(!listOfDatabases.isEmpty()){
            log.info("List of databases fetched successfully, sending data to controller");
            return listOfDatabases;
        }
        log.info("Empty list received from database");
        throw new RuntimeException("Issue while fetching list of databases");

    }

    @Override
    public List<String> getAllTableNames(String databaseName) {

        List<String> listOfTables = new ArrayList<>();
        String query = "SELECT TABLE_NAME FROM USER_TABLES";
        listOfTables = jdbcTemplate.queryForList(query,String.class).stream().toList();
        return listOfTables;

    }
}
