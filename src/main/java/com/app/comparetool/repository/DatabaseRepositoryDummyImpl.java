package com.app.comparetool.repository;

import com.app.comparetool.dto.DatabaseDetails;
import com.app.comparetool.dto.EnvironmentDetails;

import java.util.List;
import java.util.Map;

public class DatabaseRepositoryDummyImpl implements DatabaseRepository{
    @Override
    public List<String> getAllDatabaseNames() {
        return List.of();
    }

    @Override
    public List<String> getAllTableNames(String databaseName) {
        return List.of();
    }

    @Override
    public List<String> getAllColumnNames(String databaseName, String tableName) {
        return List.of();
    }

    @Override
    public List<String> fetchPrimaryKeysFromTable(String databaseName, String tableName) {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> fetchDataForUserInput(String[] columnNames, String databaseName, String tableName) {
        return List.of();
    }
}
