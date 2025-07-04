package com.app.comparetool.helper;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class HeaderGeneratorHelper {

    public String createHeaderInReportFile(String[] columnNames, String envForFirstTable, String envForSecondTable){

        StringBuilder header = new StringBuilder();

        for(String column: columnNames){
            String columnNameForFirstTable = column+"_"+envForFirstTable;
            String columnNameForSecondTable = column+"_"+envForSecondTable;

            header.append("%s|%s|Matched/Unmatched|".formatted(columnNameForFirstTable, columnNameForSecondTable));
        }

        return header.toString();
    }

    public String createHeaderInNotFoundFile(String[] columnNames){

        StringBuilder header = new StringBuilder();

        for(String column: columnNames){
            header.append("%s|".formatted(column));
        }

        return header.toString();
    }

    public String createHeaderInSummaryReport(){
        return "Column Name|Matched|Unmatched";
    }
}
