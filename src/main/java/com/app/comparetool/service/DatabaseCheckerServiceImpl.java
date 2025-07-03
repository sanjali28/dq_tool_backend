package com.app.comparetool.service;

import com.app.comparetool.dto.DatabaseDetails;
import com.app.comparetool.repository.DatabaseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DatabaseCheckerServiceImpl implements DatabaseCheckerService {

    private static final Logger log = LogManager.getLogger(DatabaseCheckerServiceImpl.class);

    @Autowired
    private DatabaseRepository databaseRepository;

    @Override
    public List<String> getAllDatabaseNames() {

        try {
            return databaseRepository.getAllDatabaseNames();
        } catch (Exception e){
            throw new RuntimeException("Failed to retrieve database names",e);
        }

    }

    @Override
    public List<String> getAllTableNames(String databaseName) {
        try {
            return databaseRepository.getAllTableNames(databaseName);
        } catch (Exception e){
            throw new RuntimeException("Failed to retrieve tables names from - "+databaseName,e);
        }
    }

    @Override
    public List<String> getAllColumnNames(String databaseName, String tableName) {
        try {
            return databaseRepository.getAllColumnNames(databaseName,tableName);
        } catch (Exception e){
            throw new RuntimeException("Failed to retrieve column names from - "+databaseName+"."+tableName,e);
        }
    }
}
