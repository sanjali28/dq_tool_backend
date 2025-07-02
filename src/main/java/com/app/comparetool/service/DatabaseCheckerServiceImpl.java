package com.app.comparetool.service;

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
}
