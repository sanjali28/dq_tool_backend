package com.app.comparetool.repository;

import com.app.comparetool.dto.DatabaseDetails;
import com.app.comparetool.dto.EnvironmentDetails;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface DatabaseRepository {

    public List<String> getAllDatabaseNames();
    public List<String> getAllTableNames(String databaseName);
    public List<String> getAllColumnNames(String databaseName, String tableName);
    public List<String> fetchPrimaryKeysFromTable(String databaseName, String tableName);
    public List<Map<String, Object>> fetchDataForUserInput(String[] columnNames, String databaseName, String tableName);
}
