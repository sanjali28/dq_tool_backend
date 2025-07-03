package com.app.comparetool.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataValidationHelper {
    private static final Logger log = LogManager.getLogger(DataValidationHelper.class);

    public boolean checkIfPrimaryKeysMatch(List<String> primaryKeyForFirstTable, List<String> primaryKeyForSecondTable) {

        log.info("Size for first table {}",primaryKeyForFirstTable.size());
        log.info("Size for first table {}",primaryKeyForSecondTable.size());

        if(primaryKeyForFirstTable.size() != primaryKeyForSecondTable.size()){
            return false;
        }

        return primaryKeyForFirstTable.stream().sorted().toList()
                .equals(primaryKeyForSecondTable.stream().sorted().toList());

    }
}
