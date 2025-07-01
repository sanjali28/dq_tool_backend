package com.app.comparetool.service;

import com.app.comparetool.repository.DatabaseRepositoryImpl;
import jakarta.persistence.PersistenceException;
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
    private DatabaseRepositoryImpl postgresDatabaseRepository;

    @Override
    public List<String> getAllDatabaseNames() {

        try {
            return postgresDatabaseRepository.getAllDatabaseNames();
        } catch (PersistenceException e){
            return Collections.emptyList();
        } catch (Exception e){
            throw new RuntimeException("Failed to retrieve database names",e);
        }

    }
}
