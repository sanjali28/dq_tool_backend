package com.app.comparetool.service;

import com.app.comparetool.dto.DatabaseDetails;

public interface CompareDataService {
    public void fetchDataAndCompareForUserInput(DatabaseDetails databaseDetails);
}
