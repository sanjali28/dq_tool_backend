package com.app.comparetool.service;

import com.app.comparetool.dto.DatabaseDetails;

import java.util.List;

public interface DatabaseCheckerService {

    public List<String> getAllDatabaseNames();
    public List<String> getAllTableNames(String databaseName);
    public List<String> getAllColumnNames(String databaseName, String tableName);
}
