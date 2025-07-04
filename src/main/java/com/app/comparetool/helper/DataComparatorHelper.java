package com.app.comparetool.helper;

import com.app.comparetool.analytics.CreateAnalytics;
import com.app.comparetool.dto.ColumnCount;
import com.app.comparetool.dto.ComparisonResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataComparatorHelper{

    private static final Logger log = LogManager.getLogger(DataComparatorHelper.class);

    @Autowired
    private CreateAnalytics createAnalytics;

    public void compareLineByLine(String header, String[] listOfAllColumns, Map<String,String> dataFromTableOne, Map<String,String> dataFromTableTwo){

        try{
            long matchedCount = 0L;
            long unmatchedCount = 0L;
            long notFoundCount = 0L;

            Map<String, ColumnCount> columnCountMap = new HashMap<>();

            for(String column : listOfAllColumns){
                columnCountMap.put(column,new ColumnCount());
            }

            try (BufferedWriter report = new BufferedWriter(new FileWriter(new File("C:\\Users\\sanja\\report.txt")));
                 BufferedWriter notFoundReport = new BufferedWriter(new FileWriter(new File("C:\\Users\\sanja\\not_found_report.txt")))) {

                report.write(header);
                report.newLine();

                for (Map.Entry<String, String> row : dataFromTableOne.entrySet()) {
                    String key = row.getKey();
                    String valueFromTableOne = row.getValue();
                    String valueFromTableTwo = dataFromTableTwo.get(key);

                    if(valueFromTableTwo == null || valueFromTableTwo.isEmpty()){
                        log.info("Key not present in table two - {}, Added entry to Not found report file.",key);
                        notFoundReport.write(valueFromTableOne);
                        notFoundReport.newLine();
                        notFoundCount++;
                        log.info("Skipping comparison, Moving to the next entry");
                        continue;
                    }

                    String[] columnArrayTableOne = valueFromTableOne.split("\\|");
                    String[] columnArrayTableTwo = valueFromTableTwo.split("\\|");

                    StringBuilder line = new StringBuilder();

                    for (int i = 0; i < columnArrayTableOne.length; i++) {
                        String comparisonResult = compareValues(columnArrayTableOne[i],columnArrayTableTwo[i]);
                        line.append("%s|%s|%s|".formatted(columnArrayTableOne[i], columnArrayTableTwo[i], comparisonResult));

                        ColumnCount columnCount = columnCountMap.get(listOfAllColumns[i]);
                        columnCount.incrementCount(comparisonResult.equals(ComparisonResult.MATCHED.getComparisonResultValue()));
                    }

                    report.write(line.toString());
                    report.newLine();
                }
            } catch (IOException e) {
                throw new RuntimeException("Error while writing data to file", e);
            }

            log.info("Matched count - {} ", matchedCount);
            log.info("Unmatched count - {}" , unmatchedCount);
            log.info("Not Found count - {}" , notFoundCount);

            log.info("Creating summary report for analytics");
            createAnalytics.createSummaryReportWithAnalytics(listOfAllColumns,columnCountMap);

        } catch (Exception e){
            throw new RuntimeException("Error while comparing data",e);
        }
    }

    public String compareValues(String value1, String value2){
        if (isNumeric(value1) && isNumeric(value2)) {
            if (isInteger(value1) && isInteger(value2)) {
                long long1 = Long.parseLong(value1);
                long long2 = Long.parseLong(value2);
                return long1 == long2
                        ? ComparisonResult.MATCHED.getComparisonResultValue()
                        : ComparisonResult.UNMATCHED.getComparisonResultValue();
            } else {
                double double1 = Double.parseDouble(value1);
                double double2 = Double.parseDouble(value2);
                return double1 == double2
                        ? ComparisonResult.MATCHED.getComparisonResultValue()
                        : ComparisonResult.UNMATCHED.getComparisonResultValue();
            }
        } else {
            return value1.equals(value2)
                    ? ComparisonResult.MATCHED.getComparisonResultValue()
                    : ComparisonResult.UNMATCHED.getComparisonResultValue();
        }
    }

    private boolean isNumeric(String value) {
        return value.matches("-?\\d{1,}\\.?\\d*");
    }

    private boolean isInteger(String value) {
        return value.matches("-?\\d+");
    }
}
