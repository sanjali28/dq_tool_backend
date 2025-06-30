package com.app.comparetool.service;

import com.app.comparetool.dto.DatabaseDetails;

public interface DatabaseCheckerService {

    public boolean checkIfDatabaseExists(DatabaseDetails databaseDetails);

}
