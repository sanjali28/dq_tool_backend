package com.app.comparetool.repository;

import com.app.comparetool.dto.DatabaseDetails;
import com.app.comparetool.dto.EnvironmentDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Primary
public class MySqlDatabaseRepositoryImpl extends DatabaseRepositoryDummyImpl{

    private static final Logger log = LogManager.getLogger(MySqlDatabaseRepositoryImpl.class);
    @Autowired
    @Qualifier("mysqlJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<String> getAllDatabaseNames() {

        List<String> listOfDatabases = new ArrayList<>();
        String query = "SHOW DATABASES";
        listOfDatabases = jdbcTemplate.queryForList(query,String.class);
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
        String query = "SHOW TABLES FROM "+databaseName;
        listOfTables = jdbcTemplate.queryForList(query,String.class);
        if(!listOfTables.isEmpty()){
            log.info("List of tables fetched successfully, sending data to controller");
            return listOfTables;
        }
        log.info("Empty list received from database");
        throw new RuntimeException("Issue while fetching list of tables");

    }

    @Override
    public List<String> getAllColumnNames(String databaseName, String tableName) {
        List<String> listOfColumns = new ArrayList<>();
        String query = "SELECT COLUMN_NAME FROM information_schema.columns WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";
        log.info(query);
//        log.info(jdbcTemplate.queryForList(query,String.class));
        listOfColumns = jdbcTemplate.queryForList(query,String.class,databaseName,tableName);
        if(!listOfColumns.isEmpty()){
            log.info("List of columns fetched successfully, sending data to controller");
            return listOfColumns;
        }
        log.info("Empty list received from database for table - " +tableName);
        throw new RuntimeException("Issue while fetching list of tables");
    }

    @Override
    public List<Map<String, Object>> fetchDataForUserInput(String[] columnNames, String databaseName, String tableName) {

        String query = String.format("SELECT %s FROM %s.%s",
                String.join(",", columnNames),
                databaseName,
                tableName);

        log.info("(Repository layer) Query created {}",query);

        List<Map<String, Object>> data = jdbcTemplate.queryForList(query);
        log.info("(Repository layer) Data pulled successfully from database {}",data);

        return data;
    }

    @Override
    public List<String> fetchPrimaryKeysFromTable(String databaseName, String tableName) {

        String query = String.format("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME = '%s' AND TABLE_SCHEMA = '%s' AND CONSTRAINT_NAME = 'PRIMARY'",
                tableName,
                databaseName);

        log.info(query);

        return jdbcTemplate.queryForList(query, String.class);

    }
}
