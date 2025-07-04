package com.app.comparetool.dto;

public enum ComparisonResult {

    MATCHED("Matched"), 
    UNMATCHED("Unmatched"), 
    NOT_FOUND("Not Found");

    private final String value;

    ComparisonResult(String value) {
        this.value = value;
    }

    public String getComparisonResultValue(){
        return value;
    }
}
