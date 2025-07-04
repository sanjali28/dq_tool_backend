package com.app.comparetool.analytics;

import com.app.comparetool.dto.ColumnCount;
import com.app.comparetool.helper.HeaderGeneratorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

@Component
public class CreateAnalytics {

    @Autowired
    private HeaderGeneratorHelper headerGeneratorHelper;

    public void createSummaryReportWithAnalytics(String[] listOfAllColumns, Map<String, ColumnCount> analyticsData){

        try(BufferedWriter summaryReport = new BufferedWriter(new FileWriter(new File("C:\\Users\\sanja\\summary_report.txt")))){

            summaryReport.write(headerGeneratorHelper.createHeaderInSummaryReport());
            summaryReport.newLine();

            for(String column : listOfAllColumns){

                ColumnCount columnCount = analyticsData.get(column);

                summaryReport.write(column+"|"+columnCount.getMatchedCount()+"|"+columnCount.getUnmatchedCount());
                summaryReport.newLine();
            }
        } catch (IOException e){
            throw new RuntimeException("Issue while writing analytics data to summary report",e);
        }
    }
}
