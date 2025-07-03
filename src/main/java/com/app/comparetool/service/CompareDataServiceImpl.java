package com.app.comparetool.service;

import com.app.comparetool.dto.DatabaseDetails;
import com.app.comparetool.dto.EnvironmentDetails;
import com.app.comparetool.repository.DatabaseRepository;
import com.app.comparetool.helper.DataValidationHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CompareDataServiceImpl implements CompareDataService{

    private static final Logger log = LogManager.getLogger(CompareDataServiceImpl.class);

    @Autowired
    private DatabaseRepository databaseRepository;

    @Autowired
    private DataValidationHelper dataValidationHelper;

    @Override
    public void fetchDataAndCompareForUserInput(DatabaseDetails databaseDetails) {

        EnvironmentDetails environmentDetailsForFirstTable = databaseDetails.getEnvironmentDetails()[0];
        EnvironmentDetails environmentDetailsForSecondTable = databaseDetails.getEnvironmentDetails()[1];

        log.info(environmentDetailsForFirstTable);
        log.info(environmentDetailsForSecondTable);

        String envForFirstTable = environmentDetailsForFirstTable.getEnv();
        String envForSecondTable = environmentDetailsForSecondTable.getEnv();

        String[] listOfAllColumns = databaseDetails.getColumnNames();

        String databaseNameForFirstTable = environmentDetailsForFirstTable.getDatabaseName();
        String databaseNameForSecondTable = environmentDetailsForSecondTable.getDatabaseName();

        String tableNameForFirstTable = environmentDetailsForFirstTable.getTableName();
        String tableNameForSecondTable = environmentDetailsForSecondTable.getTableName();

        List<Map<String, Object>> dataForFirstTable = databaseRepository.fetchDataForUserInput(listOfAllColumns, databaseNameForFirstTable, tableNameForFirstTable);
        List<Map<String, Object>> dataForSecondTable = databaseRepository.fetchDataForUserInput(listOfAllColumns, databaseNameForSecondTable, tableNameForSecondTable);

        log.info("Data received from repository layer for {} - {}", envForFirstTable, dataForFirstTable);
        log.info("Data received from repository layer for {} - {}", envForSecondTable, dataForSecondTable);

        List<String> primaryKeyForFirstColumn = databaseRepository.fetchPrimaryKeysFromTable(databaseNameForFirstTable, tableNameForFirstTable);
        List<String> primaryKeyForSecondColumn = databaseRepository.fetchPrimaryKeysFromTable(databaseNameForSecondTable, tableNameForSecondTable);

        log.info("Primary key for {}.{} - {}" , databaseNameForFirstTable, tableNameForFirstTable, primaryKeyForFirstColumn);
        log.info("Primary key for {}.{} - {}" , databaseNameForSecondTable, tableNameForSecondTable, primaryKeyForSecondColumn);

        if(!dataValidationHelper.checkIfPrimaryKeysMatch(primaryKeyForFirstColumn, primaryKeyForSecondColumn)){
            log.info("Primary key mismatch found in the tables {}.{} and {}.{}",
                    databaseNameForFirstTable, tableNameForFirstTable,
                    databaseNameForSecondTable, tableNameForSecondTable);

            throw new RuntimeException("Primary key mismatch found in the tables");
        }

        log.info("Primary keys matched");


    }
}
