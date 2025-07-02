package com.app.comparetool.repository;

import java.util.List;

public interface DatabaseRepository {

    public List<String> getAllDatabaseNames();
    public List<String> getAllTableNames();
}
