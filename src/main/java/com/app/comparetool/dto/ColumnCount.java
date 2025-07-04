package com.app.comparetool.dto;

import lombok.Getter;

@Getter
public class ColumnCount {
    private int matchedCount;
    private int unmatchedCount;

    public void incrementCount(boolean isMatched) {
        if (isMatched) {
            matchedCount++;
        } else {
            unmatchedCount++;
        }
    }
}
