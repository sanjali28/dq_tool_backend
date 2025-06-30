package com.app.comparetool.service;

import com.app.comparetool.dto.DatabaseDetails;
import com.app.comparetool.exceptionhandler.CustomException.DatabaseNotFoundException;
import com.app.comparetool.repository.DatabaseRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseCheckerServiceImpl implements DatabaseCheckerService {

    private static final Logger log = LogManager.getLogger(DatabaseCheckerServiceImpl.class);

    @Autowired
    private DatabaseRepositoryImpl postgresDatabaseRepository;

    @Override
    public boolean checkIfDatabaseExists(DatabaseDetails databaseDetails) {
        long count = postgresDatabaseRepository.checkDatabaseExists(databaseDetails.getDatabaseName());
        log.info("Count {}",count);

        if(count == 1)
            return true;

        throw new DatabaseNotFoundException("Database doesn't exists - "+databaseDetails.getDatabaseName());

    }
}
